package com.joinsoft.mobile.cms.service;

import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.mobile.cms.entity.TbConfig;
import com.joinsoft.mobile.cms.repository.ConfigRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
@Service
public class ConfigService {
    @Resource
    private ConfigRepository configRepository;

    public Page<TbConfig> searchConfig(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbConfig> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbConfig> page = configRepository.findAll(spec, pageable);
        return page;
    }

    public TbConfig getConfig(Long id) {
        return configRepository.findOne(id);
    }


    public void saveConfig(Long id, String name, String cnName, String val) {
        TbConfig config = new TbConfig();
        if (id != null) {
            config = configRepository.findOne(id);
        }
        config.setAction(null);
        config.setName(name);
        config.setVal(val);
        config.setCnName(cnName);
        configRepository.save(config);
    }

    public void deleteConfig(Long... ids) {
        configRepository.deleteInId(Arrays.asList(ids));
    }

    public TbConfig getConfigByName(String name) {
        return configRepository.findByName(name);
    }

    public TbConfig getBindConfig(String name, Long actionId) {
        return configRepository.findByNameAndAction(name, actionId);
    }

    public TbConfig getConfigPointByUser(String keyword, Long actionId) {
        String key = String.format("system.config.%s.point", keyword);
        TbConfig config = getBindConfig(key, actionId);
        if (config == null) {
            config = getConfigByName(key);
        }
        return config;
    }

    public TbConfig getConfigTrafficByUser(String keyword, Long actionId) {
        String key = String.format("system.config.%s.traffic", keyword);
        TbConfig config = getBindConfig(key, actionId);
        if (config == null) {
            config = getConfigByName(key);
        }
        return config;
    }


    public List<TbConfig> getConfigByAction(Long actionId) {
        return configRepository.findByAction(actionId);
    }

    public List<TbConfig> getGlobalConfig() {
        return configRepository.findGlobalConfig();
    }
}