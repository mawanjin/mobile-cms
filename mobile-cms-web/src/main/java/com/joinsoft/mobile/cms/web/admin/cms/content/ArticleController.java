package com.joinsoft.mobile.cms.web.admin.cms.content;

import com.joinsoft.framework.web.json.JsonObject;
import com.joinsoft.framework.web.message.WebMessageLevel;
import com.joinsoft.mobile.cms.form.ArticleEditForm;
import com.joinsoft.mobile.cms.service.content.ArticleManager;
import com.joinsoft.mobile.cms.service.content.CmsContentType;
import com.joinsoft.mobile.cms.web.admin.cms.CmsController;
import com.joinsoft.mobile.cms.web.admin.cms.SimpleCmsNodeController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * xingsen@join-cn.com
 */
@Controller
@RequestMapping(CmsController.PORTAL_PREFIX + "/article/*")
public class ArticleController extends SimpleCmsNodeController {
    @Resource
    private ArticleManager articleManager;

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public void edit(Long id, Model model) {
        ArticleEditForm command = new ArticleEditForm();
        if (null != id) {
            command.fromEntity(articleManager.getContent(id));
        }
        model.addAttribute("command", command);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(ArticleEditForm form, RedirectAttributes model) {
        articleManager.saveContent(form);
        saveSuccess(model, "操作成功");
        return redirect("/article/index.do");
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonObject delete(Long id, RedirectAttributes model) {
        articleManager.deleteContent(new Long[]{id});
        return JsonObject.refresh();
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonObject delete(Long[] ids, RedirectAttributes model) {
        if (null != ids) {
            articleManager.deleteContent(ids);
            return JsonObject.refresh();
        }
        return JsonObject.alert("请选择要删除的内容", WebMessageLevel.ERROR);
    }

    @Override
    protected CmsContentType getType() {
        return CmsContentType.Article;
    }

}
