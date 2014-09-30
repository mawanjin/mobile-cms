package com.joinsoft.mobile.cms.form;

import java.util.List;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
public class ArticleGroupEditForm extends NodeEditForm {
    private List<Long> nodeIds;
    private Map<Long, Integer> orderMap;

    public ArticleGroupEditForm(Long id, List<Long> nodeIds, Map<Long, Integer> orderMap) {
        super(id);
        this.nodeIds = nodeIds;
        this.orderMap = orderMap;
    }

    public List<Long> getNodeIds() {
        return nodeIds;
    }

    public void setNodeIds(List<Long> nodeIds) {
        this.nodeIds = nodeIds;
    }

    public Map<Long, Integer> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<Long, Integer> orderMap) {
        this.orderMap = orderMap;
    }
}
