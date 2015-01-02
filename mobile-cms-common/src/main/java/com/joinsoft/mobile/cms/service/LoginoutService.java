package com.joinsoft.mobile.cms.service;

import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.mobile.cms.entity.TbLoginout;
import com.joinsoft.mobile.cms.entity.news.TbNews;
import com.joinsoft.mobile.cms.repository.TbLoginoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 登录、退出日志
 */
@Service
@Transactional
public class LoginoutService {
    private Logger logger = LoggerFactory.getLogger(LoginoutService.class);

    @Resource
    private TbLoginoutRepository tbLoginoutRepository;

    public Page<TbLoginout> search(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbLoginout> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbLoginout> page = tbLoginoutRepository.findAll(spec, pageable);
        return page;
    }



    public TbLoginout getById(Long id) {
        return tbLoginoutRepository.findOne(id);
    }

    public void save(TbLoginout form) {
        /*
        TbAgent action = new TbAgent();
        if (form.getId() != null) {
            action = tbLoginoutRepository.findOne(form.getId());
        }

        form.toEntity(action);
        */

        tbLoginoutRepository.save(form);
    }

    public void delete(Long... ids) {
        tbLoginoutRepository.deleteInId(Arrays.asList(ids));
    }

    public List<TbLoginout> getAllAgents() {
        return (List<TbLoginout>) tbLoginoutRepository.findAll();
    }


    public Page<TbLoginout> findLastRecord(String loginName, Pageable pageable) {
        return tbLoginoutRepository.findByLoginName(loginName,pageable);
    }
}
