package com.joinsoft.mobile.cms.web.admin.cms;

import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.form.CmsDialogSearchForm;
import com.joinsoft.mobile.cms.service.content.CmsContentType;
import com.joinsoft.mobile.cms.service.content.NodeService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangxulong on 14-9-4.
 */
@Controller
@RequestMapping(CmsController.PORTAL_PREFIX)
public class CmsDialogController extends CmsController {
    @Resource
    private NodeService nodeService;

    @RequestMapping(value = "dialog")
    public void show(CmsDialogSearchForm form, Boolean childAjax, HttpServletRequest request, Model model) {
        //查询数据列表
        Page<TbCmsNode> page = nodeService.searchContentForDialog(form, buildPageRequest(request));
        model.addAttribute("command", form);
        model.addAttribute("pageurl", request.getContextPath() + CmsController.PORTAL_PREFIX + "/dialog.do");
        model.addAttribute("isajax", "ajax");
        model.addAttribute("page", page);
        model.addAttribute("searchParams", addSearchParam(form));
        model.addAttribute("childAjax", childAjax);
        model.addAttribute("contentType", CmsContentType.values());
        if (form.getExistIds() != null && form.getExistIds().length > 0) {
            model.addAttribute("existIds", StringUtils.join(form.getExistIds(), ","));
        }
    }

    protected String addSearchParam(CmsDialogSearchForm form) {
        StringBuilder params = new StringBuilder("childAjax=true");
        if (ArrayUtils.isNotEmpty(form.getType())) {
            String type = StringUtils.join(form.getType(), ",");
            params.append("&type=" + type);
        }
        if (null != form.getMulti()) {
            params.append("&multi=" + form.getMulti());
        }
        return params.toString();
    }
}
