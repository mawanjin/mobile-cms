package com.joinsoft.mobile.cms.service;

import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.mobile.cms.component.VirtualFile;
import com.joinsoft.mobile.cms.entity.ad.TbAd;
import com.joinsoft.mobile.cms.entity.ad.TbAdStats;
import com.joinsoft.mobile.cms.repository.ad.AdRepository;
import com.joinsoft.mobile.cms.repository.ad.AdStatsRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wangxulong on 14-8-17.
 */
@Service
@Transactional
public class AdService {
    @Resource
    private AdRepository adRepository;
    @Value("${config.vfs.root}")
    private String filePath;
    @Resource
    private AdStatsRepository adStatsRepository;

    public void increaseRefCount(Long id) {
        TbAdStats adStats = adStatsRepository.findByAd(id);
        if (null == adStats) {
            adStats = new TbAdStats();
            adStats.setRefCount(0L);
            adStats.setVisitCount(0L);
            TbAd ad = new TbAd();
            ad.setId(id);
            adStats.setAd(ad);
        }
        adStats.setRefCount(adStats.getRefCount() + 1);
        adStatsRepository.save(adStats);
    }

    public TbAdStats increaseVisitCount(Long id) {
        TbAdStats adStats = adStatsRepository.findByAd(id);
        Assert.notNull(adStats, "访问广告统计存在");
        adStats.setVisitCount(adStats.getVisitCount() + 1);
        return adStatsRepository.save(adStats);
    }

    public Page<TbAd> search(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbAd> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbAd> page = adRepository.findAll(spec, pageable);
        for (TbAd ad : page.getContent()) {
            ad.setAdStats(adStatsRepository.findByAd(ad.getId()));
        }
        return page;
    }

    public TbAd getById(Long id) {
        return adRepository.findOne(id);
    }

    public void save(Long id, String title, String description, String picUrl, MultipartFile file) {
        TbAd ad = new TbAd();
        ad.setCreateTime(new Date());
        if (null != id) {
            ad = adRepository.findOne(id);
        }
        ad.setTitle(title);
        ad.setDescription(description);
        ad.setPic(saveFile(file, ad, filePath));
        ad.setPicUrl(picUrl);
        adRepository.save(ad);
    }

    public void delete(Long[] ids) {
        String[] pics = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            TbAd ad = adRepository.findOne(ids[i]);
            Assert.notNull(ad, "广告不存在");
            pics[i] = ad.getPic();
        }
        adRepository.deleteInId(Arrays.asList(ids));
        deleteFile(filePath, pics);
    }

    protected String saveFile(MultipartFile file, TbAd ad, String picPath) {
        if (ad.getId() != null && !file.getContentType().endsWith("jpeg")) {
            return ad.getPic();
        }
        String fileName = UUID.randomUUID().toString().replace("-", "") + "." +
                FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        String path = "/ad/" + fileName;

        VirtualFile virtualFile = new VirtualFile(new File(picPath + path), picPath);
        virtualFile.getParent().mkdirs();
        try {
            file.transferTo(virtualFile.getRealFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (ad.getId() != null) {
            deleteFile(filePath, ad.getPic());
        }
        return path;
    }

    protected void deleteFile(String filePath, String... pics) {
        for (String pic : pics) {
            if (null != pic) {
                new File(filePath + pic).delete();
            }
        }
    }


}
