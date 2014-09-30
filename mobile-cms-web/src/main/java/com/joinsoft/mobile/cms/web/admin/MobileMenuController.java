package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.web.json.JsonObject;
import com.joinsoft.framework.web.message.WebMessageLevel;
import com.joinsoft.mobile.cms.entity.TbMobileMenu;
import com.joinsoft.mobile.cms.entity.enumerate.MobileMenuType;
import com.joinsoft.mobile.cms.form.MobileMenuEditForm;
import com.joinsoft.mobile.cms.service.mobile.MobileMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;


/**
 * User: wujun
 * Date: 2014/8/8
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/menu/*")
public class MobileMenuController extends AdminController {
    @Resource
    private MobileMenuService mobileMenuService;

    @RequestMapping("index")
    public void index(Model model) {
        model.addAttribute("menuItems", mobileMenuService.getMenu());
    }

    @RequestMapping("edit")
    public void edit(Long id, Long parentId, Model model) {
        MobileMenuEditForm command = new MobileMenuEditForm();
        command.setType(MobileMenuType.Top);
        if (id != null) {
            command.formEntity(mobileMenuService.getMenu(id));
        }
        if (parentId != null) {
            TbMobileMenu parentMenu = mobileMenuService.getMenu(parentId);
            if (command.getId() == null) {
                command.setType(MobileMenuType.CMS);
            }
            command.setParentId(parentMenu.getId());
            model.addAttribute("parentMenu", parentMenu);
        }
        model.addAttribute("command", command);
        model.addAttribute("menuItems", mobileMenuService.getMenu());
        model.addAttribute("menuTypes", MobileMenuType.values());
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(MobileMenuEditForm form,
                       RedirectAttributes model) {
        TbMobileMenu menu = mobileMenuService.saveMenu(form);
        saveSuccess(model, "保存菜单成功");
        return redirect(String.format("/menu/edit.do?id=%s&parentId=%s", menu.getId(),
                form.getParentId() == null ? "" : form.getParentId()));
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonObject delete(Long id) {
        mobileMenuService.deleteMenuButton(id);
        return JsonObject.refresh();
    }

    @RequestMapping("sync")
    @ResponseBody
    public JsonObject sync() {
        try {
            mobileMenuService.syncMobileMenu();
        } catch (RuntimeException e) {
            return JsonObject.alert("同步错误" + e.getMessage(), WebMessageLevel.ERROR);
        }
        return JsonObject.alert("同步成功", WebMessageLevel.SUCCESS);
    }
}
