package com.joinsoft.mobile.cms.service;

import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.mobile.cms.component.VfsComponent;
import com.joinsoft.mobile.cms.entity.enumerate.ProductStatus;
import com.joinsoft.mobile.cms.entity.mall.TbProduct;
import com.joinsoft.mobile.cms.form.ProductEditForm;
import com.joinsoft.mobile.cms.repository.mall.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by wangxulong on 14-8-19.
 */
@Service
@Transactional
public class ProductService {
    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Resource
    private ProductRepository productRepository;
    @Resource
    private VfsComponent vfsComponent;


    public Page<TbProduct> getOnlineProduct(Map<String, Object> searchParams, Pageable pageable) {
        searchParams.put("EQ_status", ProductStatus.Valid);
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbProduct> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbProduct> page = productRepository.findAll(spec, pageable);
        return page;
    }

    public Page<TbProduct> searchProduct(Map<String, Object> searchParams, PageRequest pageRequest) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbProduct> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbProduct> page = productRepository.findAll(spec, pageRequest);
        return page;
    }

    public TbProduct getProductById(Long id) {
        return productRepository.findOne(id);
    }

    public void saveOrUpdateProduct(ProductEditForm form) {
        TbProduct product = form.toEntity();
        if (null != form.getId()) {
            product = productRepository.findOne(form.getId());
        }
        product.setTitle(form.getTitle());
        product.setPrice(form.getPrice());
        product.setStatus(form.getStatus());
        product.setDescription(form.getDescription());
        productRepository.save(product);
        if (!form.getPicFile().isEmpty()) {
            String productImg = vfsComponent.saveProductImage(product, form.getPicFile());
            product.setPic(productImg);
            productRepository.save(product);
        }
    }

    public void deleteProduct(Long... ids) {
        for (int i = 0; i < ids.length; i++) {
            TbProduct product = productRepository.findOne(ids[i]);
            vfsComponent.deleteProductImage(product);
            productRepository.delete(product.getId());
        }
    }
}
