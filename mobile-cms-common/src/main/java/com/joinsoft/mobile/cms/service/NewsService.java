package com.joinsoft.mobile.cms.service;

import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;

import com.joinsoft.mobile.cms.entity.news.TbNews;

import com.joinsoft.mobile.cms.repository.news.newsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
@Service
@Transactional
public class NewsService {
    private Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Resource
    private newsRepository NewsRepository;



    public Page<TbNews> search(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbNews> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbNews> page = NewsRepository.findAll(spec, pageable);
        return page;
    }

    public TbNews getById(Long id) {
        return NewsRepository.findOne(id);
    }

    public void save(TbNews form) {
        /*
        TbNews action = new TbNews();
        if (form.getId() != null) {
            action = NewsRepository.findOne(form.getId());
        }

        form.toEntity(action);
        */

        NewsRepository.save(form);
    }

    public void deleteNews(Long... ids) {
        NewsRepository.deleteInId(Arrays.asList(ids));
    }

    public List<TbNews> getAllNews() {
        return (List<TbNews>) NewsRepository.findAll();
    }



}
