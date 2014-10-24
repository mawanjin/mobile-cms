package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.mobile.cms.component.eventbus.EventBusService;
import com.joinsoft.mobile.cms.entity.TbAction;
import com.joinsoft.mobile.cms.entity.TbConfig;
import com.joinsoft.mobile.cms.entity.agent.TbAgent;
import com.joinsoft.mobile.cms.entity.enumerate.ActionCycle;
import com.joinsoft.mobile.cms.entity.enumerate.ValueType;
import com.joinsoft.mobile.cms.form.ActionEditForm;
import com.joinsoft.mobile.cms.form.Option;
import com.joinsoft.mobile.cms.service.ActionService;
import com.joinsoft.mobile.cms.service.AgentService;
import com.joinsoft.mobile.cms.service.ConfigService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping(AdminController.PORTAL_PREFIX + "/agent/*")
public class AgentController extends AdminController {
    @Resource
    private AgentService agentService;


    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        Page<TbAgent> page = agentService.searchAgent(searchParams, buildPageRequest(request));
        model.addAttribute("page", page);
    }

    @RequestMapping("edit")
    public void edit(Long id, Model model) {
        /*
        ActionEditForm command = new ActionEditForm();
        if (null != id) {
            command.formEntity(actionService.getById(id));
        }
        */
        TbAgent command = new TbAgent();
        if (null != id) {
                command= agentService.getById(id);
        }
        /*
        model.addAttribute("valueTypes", ValueType.values());
        model.addAttribute("cycles", ActionCycle.values());

        */
        List<Option> options = new ArrayList<Option>();
        options.add(new Option("浙江省", "浙江省"));
        options.add(new Option("上海", "上海"));
        options.add(new Option("北京", "北京"));
        model.addAttribute("options", options);

        model.addAttribute("command", command);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbAgent form, RedirectAttributes model) {
        agentService.saveAgent(form);
        saveSuccess(model, "保存成功");
        return redirect("/agent/index.do");
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(Long id, RedirectAttributes model) {
        if (null == id) {
            saveError(model, "网点不存在");
            return redirect("/agent/index.do");
        }
        agentService.deleteAgent(id);
        return redirect("/agent/index.do");
    }



    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(Long[] ids, RedirectAttributes model) {
        if (null == ids) {
            saveError(model, "请选择删除的规则");
            return redirect("/agent/index.do");
        }
        agentService.deleteAgent(ids);
        return redirect("/agent/index.do");
    }


    @RequestMapping("/getCityByP")
    @ResponseBody
    public ArrayList<String> showAjaxTable(HttpServletRequest request) {

        ArrayList l=new ArrayList();
        l.add("莫干山第三方松岛枫松岛枫松岛枫第三方第三方");
        l.add("杭州");
        return l;
    }


}
