package com.joinsoft.mobile.cms.service.poll;

import com.google.common.collect.Lists;
import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.framework.security.SecurityService;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.mobile.cms.entity.poll.TbPoll;
import com.joinsoft.mobile.cms.entity.poll.TbPollVisibility;
import com.joinsoft.mobile.cms.repository.poll.PollRepository;
import com.joinsoft.mobile.cms.repository.poll.PollVisibilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * User: wujun
 * Date: 2014/9/7
 */
@Service
@Transactional
public class PollSecurityService {
    private Logger logger = LoggerFactory.getLogger(PollSecurityService.class);

    @Resource
    private PollRepository pollRepository;
    @Resource
    private PollVisibilityRepository pollVisibilityRepository;
    @Resource
    private SecurityService securityService;

    public Page<TbPollVisibility> search(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbPollVisibility> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbPollVisibility> page = pollVisibilityRepository.findAll(spec, pageable);
        return page;
    }

    public void deletePollVisibility(Long[] ids) {
        pollVisibilityRepository.deleteInId(Lists.newArrayList(ids));
        logger.info("delete PollVisibility ids: {}", Arrays.toString(ids));
    }

    public void savePollVisibility(Long[] userIds, Long pollNodeId) {
        List<TbPollVisibility> pollVisibilityList = Lists.newArrayList();
        TbPoll poll = pollRepository.findByMeta(pollNodeId);
        Assert.notNull(poll, "pollNodeId does not exist");
        for (Long userId : userIds) {
            TbPollVisibility pollVisibility = new TbPollVisibility();
            pollVisibility.setUser(new User(userId));
            pollVisibility.setPoll(poll);
            pollVisibilityList.add(pollVisibility);
        }
        pollVisibilityRepository.save(pollVisibilityList);
        logger.info("save PollVisibilities for users: {}", Arrays.toString(userIds));
    }

    public Page<User> searchUser(Long pollNodeId, Map<String, Object> searchParams, Pageable pageable) {
        List<TbPollVisibility> pollVisibilityList = pollVisibilityRepository.findByPoll_Node_Id(pollNodeId);
        List<Long> userIds = Lists.newArrayList();
        for (TbPollVisibility pollVisibility : pollVisibilityList) {
            userIds.add(pollVisibility.getUser().getId());
        }
        if (!userIds.isEmpty()) {
            searchParams.put("NOTIN_id", userIds);
        }
        return securityService.searchUser(searchParams, pageable);
    }
}
