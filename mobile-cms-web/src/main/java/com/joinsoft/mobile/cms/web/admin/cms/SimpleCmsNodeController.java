package com.joinsoft.mobile.cms.web.admin.cms;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.service.content.CmsContentType;
import com.joinsoft.mobile.cms.service.content.NodeService;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
public abstract class SimpleCmsNodeController extends CmsController {
    @Resource
    private NodeService nodeService;

    @RequestMapping("index")
    public String index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        searchParams.put("EQ_type", getType().toString());
        Page<TbCmsNode> page = nodeService.searchContent(searchParams, buildPageRequest(request));

        model.addAttribute("page", page);
        model.addAttribute("type", getType().toString().toLowerCase());
        return CmsController.PORTAL_PREFIX + "/index";
    }

    protected abstract CmsContentType getType();
}
