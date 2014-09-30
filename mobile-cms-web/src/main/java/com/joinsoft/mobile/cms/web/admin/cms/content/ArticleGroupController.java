package com.joinsoft.mobile.cms.web.admin.cms.content;

import com.joinsoft.framework.web.json.JsonObject;
import com.joinsoft.framework.web.message.WebMessageLevel;
import com.joinsoft.mobile.cms.component.ResponseMessageBuilder;
import com.joinsoft.mobile.cms.dto.ArticleGroupDto;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.form.ArticleGroupEditForm;
import com.joinsoft.mobile.cms.service.content.ArticleGroupManager;
import com.joinsoft.mobile.cms.service.content.CmsContentType;
import com.joinsoft.mobile.cms.service.mobile.MobileService;
import com.joinsoft.mobile.cms.web.admin.cms.CmsController;
import com.joinsoft.mobile.cms.web.admin.cms.SimpleCmsNodeController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * xingsen@join-cn.com
 */
@Controller
@RequestMapping(CmsController.PORTAL_PREFIX + "/articlegroup/*")
public class ArticleGroupController extends SimpleCmsNodeController {
    @Resource
    private ArticleGroupManager articleGroupManager;
    @Resource
    private MobileService mobileService;
    @Resource
    private ResponseMessageBuilder responseMessageBuilder;

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public void edit(Long id, Model model) {
        List<ArticleGroupDto> articleGroupDtoList = articleGroupManager.getArticleGroup(id);
        StringBuilder existIds = new StringBuilder();
        for (ArticleGroupDto dto : articleGroupDtoList) {
            existIds.append(dto.getContentNode().getId()).append(",");
        }
        model.addAttribute("id", id);
        model.addAttribute("articleGroupDtoList", articleGroupDtoList);
        if (existIds.length() > 0) {
            model.addAttribute("existIds", existIds.substring(0, existIds.length() - 1));
        }
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Long id, Long dlgResult[], HttpServletRequest request) {
        Map<Long, Integer> orderMap = new HashMap<Long, Integer>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            if (name.startsWith("orderIndex_")) {
                Long updateId = Long.parseLong(name.split("orderIndex_")[1]);
                Integer orderIndex = Integer.parseInt(request.getParameter(name));
                orderMap.put(updateId, orderIndex);
            }
        }
        List<Long> ids = new ArrayList<Long>();
        for (Long dlgResultItem : dlgResult) {
            if (dlgResultItem != null) {
                ids.add(dlgResultItem);
            }
        }
        TbCmsNode node = articleGroupManager.saveOrUpdateArticleGroup
                (new ArticleGroupEditForm(id, ids, orderMap));
        return redirect("/articlegroup/edit.do?id=" + node.getId());
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonObject removeArticle(Long id, Long article) {
        articleGroupManager.removeArticle(id, article);
        return JsonObject.refresh();
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonObject delete(Long id, RedirectAttributes model) {
        articleGroupManager.deleteContent(new Long[]{id});
        return JsonObject.refresh();
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonObject delete(Long[] ids, RedirectAttributes model) {
        if (null != ids) {
            articleGroupManager.deleteContent(ids);
            return JsonObject.refresh();
        }
        return JsonObject.alert("请选择要删除的内容", WebMessageLevel.ERROR);
    }

    @RequestMapping(value = "mass", method = RequestMethod.GET)
    public void mass(@ModelAttribute("id") Long id, Model model) {
        model.addAttribute("groups", mobileService.getAllGroup());
    }

    @RequestMapping(value = "mass", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonObject mass(Long id, Long groupId) {
        try {
            responseMessageBuilder.sendArticleGroupForMass(groupId, id);
        } catch (RuntimeException e) {
            return JsonObject.alert("群发错误" + e.getMessage(), WebMessageLevel.ERROR);
        }
        return JsonObject.alert("群发成功", WebMessageLevel.SUCCESS);
    }

    @Override
    protected CmsContentType getType() {
        return CmsContentType.ArticleGroup;
    }

}
