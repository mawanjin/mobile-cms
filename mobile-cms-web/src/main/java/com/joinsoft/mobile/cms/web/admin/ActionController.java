package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.mobile.cms.component.eventbus.EventBusService;
import com.joinsoft.mobile.cms.entity.TbAction;
import com.joinsoft.mobile.cms.entity.TbConfig;
import com.joinsoft.mobile.cms.entity.enumerate.ActionCycle;
import com.joinsoft.mobile.cms.entity.enumerate.ValueType;
import com.joinsoft.mobile.cms.form.ActionEditForm;
import com.joinsoft.mobile.cms.form.Option;
import com.joinsoft.mobile.cms.service.ActionService;
import com.joinsoft.mobile.cms.service.ConfigService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxulong on 14-8-18.
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/action/*")
public class ActionController extends AdminController {
    @Resource
    private ActionService actionService;
    @Resource
    private EventBusService eventBusService;
    @Resource
    private ConfigService configService;

    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        Page<TbAction> page = actionService.searchAction(searchParams, buildPageRequest(request));
        model.addAttribute("page", page);
    }

    @RequestMapping("edit")
    public void edit(Long id, Model model) {
        ActionEditForm command = new ActionEditForm();
        if (null != id) {
            command.formEntity(actionService.getById(id));
        }
        model.addAttribute("hooks", eventBusService.getRewardEventHooks());
        model.addAttribute("valueTypes", ValueType.values());
        model.addAttribute("cycles", ActionCycle.values());
        List<Option> options = new ArrayList<Option>();
        options.add(new Option("有效", true));
        options.add(new Option("无效", false));
        model.addAttribute("options", options);
        model.addAttribute("command", command);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(ActionEditForm form, RedirectAttributes model) {
        actionService.saveAction(form);
        saveSuccess(model, "保存成功");
        return redirect("/action/index.do");
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(Long id, RedirectAttributes model) {
        if (null == id) {
            saveError(model, "规则不存在");
            return redirect("/action/index.do");
        }
        actionService.deleteAction(id);
        return redirect("/action/index.do");
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(Long[] ids, RedirectAttributes model) {
        if (null == ids) {
            saveError(model, "请选择删除的规则");
            return redirect("/action/index.do");
        }
        actionService.deleteAction(ids);
        return redirect("/action/index.do");
    }

    @RequestMapping(value = "config", method = RequestMethod.GET)
    public void config(@ModelAttribute("id") Long id, Model model) {
        TbAction action = actionService.getById(id);
        model.addAttribute("command", action);
        List<TbConfig> configs = configService.getConfigByAction(id);
        if (configs.isEmpty()) {
            configs = configService.getGlobalConfig();
        }
        model.addAttribute("configs", configs);
    }

    @RequestMapping("bind")
    public String saveConfig(Long id, Long[] ids, String[] val, RedirectAttributes model) {
        actionService.bindConfig(id, ids, val);
        saveSuccess(model, "绑定成功");
        return redirect("/action/index.do");
    }

    @RequestMapping("unbind")
    public String deleteConfig(Long id, RedirectAttributes model) {
        actionService.unbindConfig(id);
        saveSuccess(model, "解绑成功");
        return redirect("/action/index.do");
    }
}
