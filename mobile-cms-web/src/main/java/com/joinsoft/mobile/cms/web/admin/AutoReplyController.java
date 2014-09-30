package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.web.json.JsonObject;
import com.joinsoft.mobile.cms.component.eventbus.EventBusService;
import com.joinsoft.mobile.cms.entity.TbAutoReplyConfig;
import com.joinsoft.mobile.cms.entity.enumerate.AutoReplyConfigType;
import com.joinsoft.mobile.cms.service.AutoReplyService;
import com.joinsoft.mobile.cms.service.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangxulong on 14-9-7.
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/auto_reply/*")
public class AutoReplyController extends AdminController {
    public static final String PORTAL_PREFIX = AdminController.PORTAL_PREFIX + "/auto_reply";
    @Resource
    private ConfigService configService;
    @Resource
    private AutoReplyService autoReplyService;
    @Resource
    private EventBusService eventBusService;

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }

    @RequestMapping("index")
    public void index(Model model) {
        List<TbAutoReplyConfig> autoReplyList = autoReplyService.getAutoReplyConfig();
        model.addAttribute("autoReplyList", autoReplyList);
    }

    @RequestMapping(value = "reply_type", method = RequestMethod.GET)
    public void replyType(Model model) {
        model.addAttribute("replyTypes", AutoReplyConfigType.values());
    }

    @RequestMapping("edit")
    public String edit(Long id, AutoReplyConfigType type, Model model) {
        TbAutoReplyConfig command = new TbAutoReplyConfig();
        command.setType(type);
        if (null != id) {
            command = autoReplyService.getById(id);
            type = command.getType();
        }
        if (command.getNode() != null) {
            model.addAttribute("dlgResult", command.getNode().getId());
        }
        if (type.equals(AutoReplyConfigType.Event))
            model.addAttribute("hooks", eventBusService.getReplyMessageEventHooks());
        model.addAttribute("command", command);

        return PORTAL_PREFIX + "/" + type.name().toLowerCase() + "/edit";
    }

    @RequestMapping("delete")
    public
    @ResponseBody
    JsonObject delete(Long id, RedirectAttributes model) {
        autoReplyService.delete(new Long[]{id});
        return JsonObject.refresh();
    }

    @RequestMapping("save")
    public String save(Long id, String keyWord, String eventKey, Long dlgResult, AutoReplyConfigType type, RedirectAttributes model) {
        autoReplyService.save(id, keyWord, eventKey, type, dlgResult);
        saveSuccess(model, "保存成功");
        return redirect("/index.do");
    }
}
