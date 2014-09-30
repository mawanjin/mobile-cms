package com.joinsoft.mobile.cms.entity.mall;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.mobile.cms.entity.enumerate.ProductStatus;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangxulong on 14-8-19.
 */
@Entity
@Table(name = "tb_product")
public class TbProduct extends AutoModel {
    private String title;
    private String pic;
    private String description;
    @Enumerated
    private ProductStatus status;
    private BigDecimal price;
    private Date lastModified;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
