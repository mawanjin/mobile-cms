package com.joinsoft.mobile.cms.service;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.mobile.cms.component.VfsComponent;
import com.joinsoft.mobile.cms.component.VirtualFile;
import com.joinsoft.mobile.cms.entity.enumerate.WifiCodeStatus;
import com.joinsoft.mobile.cms.entity.mall.TbWifiCode;
import com.joinsoft.mobile.cms.repository.mall.WifiCodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxulong on 14-9-17.
 */
@Service
@Transactional
public class WifiCodeService {
    @Resource
    private WifiCodeRepository wifiCodeRepository;
    @Resource
    private VfsComponent vfsComponent;

    public Page<TbWifiCode> search(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbWifiCode> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbWifiCode> page = wifiCodeRepository.findAll(spec, pageable);
        return page;
    }

    public TbWifiCode getById(Long id) {
        return wifiCodeRepository.findOne(id);
    }

    public void save(Long id, WifiCodeStatus status) {
        TbWifiCode wifiCode = wifiCodeRepository.findOne(id);
        Assert.notNull(wifiCode, "WIFI串码不存在");
        wifiCode.setStatus(status);
        wifiCodeRepository.save(wifiCode);
    }

    public void importWifiCode(MultipartFile wifiCodeFile) {
        SimpleDateFormat slf = new SimpleDateFormat("yyyyMMddHHmmss");
        VirtualFile codeFile = vfsComponent.saveWifiCodeFile(slf.format(new Date()), wifiCodeFile);
        File realFile = codeFile.getRealFile();
        try {
            Reader reader = new FileReader(realFile);
            CSVReader<String[]> csvParser = CSVReaderBuilder.newDefaultReader(reader);
            List<String[]> codes = csvParser.readAll();
            List<TbWifiCode> wifiCodes = new ArrayList<TbWifiCode>();
            for (String[] code : codes) {
                TbWifiCode wifiCode = new TbWifiCode();
                wifiCode.setCreateTime(new Date());
                wifiCode.setStatus(WifiCodeStatus.Valid);
                wifiCode.setCode(code[0]);
                wifiCodes.add(wifiCode);
            }
            wifiCodeRepository.save(wifiCodes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
