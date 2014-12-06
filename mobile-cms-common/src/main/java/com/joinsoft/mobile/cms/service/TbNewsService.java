package com.joinsoft.mobile.cms.service;

import com.joinsoft.mobile.cms.entity.news.TbNews;
import com.joinsoft.mobile.cms.repository.news.TbNewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        TbNews news = tbNewsRepository.findByTbNewsId(id);
        return new TbNews(news.getTitle(), news.getContent(), news.getOperTime());
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<TbNews> findByTbNews(int page) {
        return tbNewsRepository.findAllOnline(new PageRequest(page, 7, Sort.Direction.DESC,"operTime"));
    }

}

