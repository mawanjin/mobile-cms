package com.joinsoft.mobile.cms.service;

import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.framework.security.SecurityService;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.mobile.cms.component.eventbus.EventBusService;
import com.joinsoft.mobile.cms.dto.SystemEventDto;
import com.joinsoft.mobile.cms.entity.TbAction;
import com.joinsoft.mobile.cms.entity.TbActionLog;
import com.joinsoft.mobile.cms.entity.TbConfig;
import com.joinsoft.mobile.cms.entity.enumerate.ActionCycle;
import com.joinsoft.mobile.cms.entity.enumerate.ValueType;
import com.joinsoft.mobile.cms.form.ActionEditForm;
import com.joinsoft.mobile.cms.repository.ActionLogRepository;
import com.joinsoft.mobile.cms.repository.ActionRepository;
import com.joinsoft.mobile.cms.repository.ConfigRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * xingsen@join-cn.com
 */
@Service
@Transactional
public class ActionService {
    private Logger logger = LoggerFactory.getLogger(ActionService.class);

    @Resource
    private SecurityService securityService;
    @Resource
    private ActionRepository actionRepository;
    @Resource
    private ActionLogRepository actionLogRepository;
    @Resource
    private EventBusService eventBusService;
    @Resource
    private ConfigRepository configRepository;

    public int calcAction(String actionName) {
        return this.calcAction(actionName, null);
    }

    public int calcAction(String actionName, String toResource) {
        TbAction action = actionRepository.findByActionName(actionName);
        Assert.notNull(action, String.format("动作%s未找到", actionName));
        User user = securityService.getLoginUser();
        Assert.notNull(user, "用户尚未登录");

        int count = getCount(user.getId(), action.getCycle(), actionName, toResource);
        logger.debug(String.format("用户:%s 操作:%s 被操作对象:%s 已操作次数:%s",
                user.getId(), action, toResource, count));
        if (null != action.getCount() && count >= action.getCount()) {
            return 0;
        }
        int value = getValue(action);
        //保存日志
        TbActionLog log = new TbActionLog();
        log.setAction(action.getActionName());
        log.setCnName(action.getCnName());
        log.setLogTime(new Date());
        log.setUser(user);
        log.setValue(value);
        if (StringUtils.isNotEmpty(toResource)) {
            log.setResource(toResource);
        }
        actionLogRepository.save(log);

        return value;
    }

    public Page<TbAction> searchAction(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbAction> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbAction> page = actionRepository.findAll(spec, pageable);
        return page;
    }

    public TbAction getById(Long id) {
        return actionRepository.findOne(id);
    }

    public void saveAction(ActionEditForm form) {
        TbAction action = new TbAction();
        if (form.getId() != null) {
            action = actionRepository.findOne(form.getId());
        }
        form.toEntity(action);
        SystemEventDto eventDto = eventBusService.getEvent(form.getKey());
        action.setActionName(eventDto.getKey());
        action.setCnName(eventDto.getDescription());

        if (action.getValueType().equals(ValueType.Fixed)) {
            action.setMaxValue(action.getMinValue());
        }
        actionRepository.save(action);
    }

    public void deleteAction(Long... ids) {
        actionRepository.deleteInId(Arrays.asList(ids));
    }

    public List<TbAction> getAllActions() {
        return (List<TbAction>) actionRepository.findAll();
    }

    public void bindConfig(Long actionId, Long[] configIds, String[] val) {
        TbAction action = actionRepository.findOne(actionId);
        Assert.notNull(action, "动作不存在");
        List<TbConfig> configs = new ArrayList<TbConfig>();
        for (int i = 0; i < configIds.length; i++) {
            TbConfig config = configRepository.findOne(configIds[i]);
            TbConfig newConfig = new TbConfig();
            if (null != config.getAction()) {
                newConfig.setId(config.getId());
            }
            newConfig.setName(config.getName());
            newConfig.setCnName(config.getCnName());

            newConfig.setAction(action);
            newConfig.setVal(val[i]);

            configs.add(newConfig);
        }
        configRepository.save(configs);
    }

    public void unbindConfig(Long id) {
        configRepository.deleteByAction(id);
    }

    protected int getValue(TbAction action) {
        if (action.getValueType() == ValueType.Rand) {
            Random rand = new Random();
            return rand.nextInt(action.getMaxValue() - action.getMinValue() + 1) + action.getMinValue();
        }
        return action.getMinValue();
    }

    protected int getCount(Long userId, ActionCycle cycle, String actionName, String resource) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date begin = null;
        Date end = null;
        switch (cycle) {
            case Day:
                begin = calendar.getTime();
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                end = calendar.getTime();
                break;
            case Week:
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                begin = calendar.getTime();
                calendar.add(Calendar.WEEK_OF_MONTH, 1);
                end = calendar.getTime();
                break;
            case Month:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                begin = calendar.getTime();
                calendar.add(Calendar.MONTH, 1);
                end = calendar.getTime();
                break;
            case Empty:
                return actionLogRepository.countByUserAndNameWithoutCycleLimit(userId, actionName).intValue();
        }
        if (null != resource) {
            return actionLogRepository.countByUserAndActionNameAndBetweenTimeAndResource(userId,
                    actionName, resource, begin, end).intValue();
        }
        return actionLogRepository.countByUserAndActionNameAndBetweenTime(userId, actionName, begin, end).intValue();
    }
}
