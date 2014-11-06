package com.joinsoft.mobile.cms.service;

import com.joinsoft.mobile.cms.entity.news.TbNews;
import com.joinsoft.mobile.cms.repository.news.TbNewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 14-11-5.
 */
@Service
@Transactional
public class TbNewsService {
    @Resource
    private TbNewsRepository tbNewsRepository;

    public TbNews findByTbNewsId(Long id) {
        return tbNewsRepository.findByTbNewsId(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<TbNews> findByTbNews(int page) {
        return tbNewsRepository.findAll(new PageRequest(page, 10));
    }

}

