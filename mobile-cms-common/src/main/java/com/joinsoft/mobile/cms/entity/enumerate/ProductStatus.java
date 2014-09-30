package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * xingsen@join-cn.com
 */
public enum ProductStatus {
    Valid("有效"), SoldOut("下架"), SellOut("售罄");
    private final String title;

    ProductStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
