package com.joinsoft.mobile.cms.form;

import com.joinsoft.mobile.cms.entity.enumerate.ProductStatus;
import com.joinsoft.mobile.cms.entity.mall.TbProduct;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class ProductEditForm {
    private Long id;
    private String title;
    private BigDecimal price;
    private ProductStatus status;
    private String description;
    private MultipartFile picFile;
    private String picFilePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getPicFile() {
        return picFile;
    }

    public void setPicFile(MultipartFile picFile) {
        this.picFile = picFile;
    }

    public String getPicFilePath() {
        return picFilePath;
    }

    public void setPicFilePath(String picFilePath) {
        this.picFilePath = picFilePath;
    }

    public TbProduct toEntity() {
        TbProduct product = new TbProduct();
        product.setLastModified(new Date());
        product.setStatus(status);
        product.setDescription(description);
        product.setPrice(price);
        product.setTitle(title);
        return product;
    }

    public static ProductEditForm formEntity(TbProduct product) {
        ProductEditForm form = new ProductEditForm();
        form.setDescription(product.getDescription());
        form.setStatus(product.getStatus());
        form.setId(product.getId());
        form.setPrice(product.getPrice());
        form.setTitle(product.getTitle());
        form.setPicFilePath(product.getPic());
        return form;
    }
}
