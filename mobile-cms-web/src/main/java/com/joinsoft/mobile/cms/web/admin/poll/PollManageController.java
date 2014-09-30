package com.joinsoft.mobile.cms.web.admin.poll;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.framework.web.binder.EnumeratePropertyEditor;
import com.joinsoft.framework.web.json.JsonObject;
import com.joinsoft.mobile.cms.entity.enumerate.PollStatus;
import com.joinsoft.mobile.cms.entity.enumerate.PollType;
import com.joinsoft.mobile.cms.entity.poll.TbPollOption;
import com.joinsoft.mobile.cms.form.PollEditForm;
import com.joinsoft.mobile.cms.service.poll.PollService;
import com.joinsoft.mobile.cms.web.admin.AdminController;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * User: wujun
 * Date: 2014/8/15
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/poll/*")
public class PollManageController extends AdminController {
    @Resource
    private PollService pollService;

    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        Page<PollEditForm> page = pollService.search(searchParams, buildPageRequest(request));
        model.addAttribute("page", page);
    }

    @RequestMapping("edit")
    public void edit(Long id, Model model) {
        PollStatus[] statusItems;
        if (id != null) {
            statusItems = PollStatus.values();
        } else {
            statusItems = new PollStatus[]{PollStatus.New, PollStatus.Running};
        }
        model.addAttribute("command", pollService.getPollForm(id));
        model.addAttribute("statusItems", statusItems);
        model.addAttribute("typeItems", PollType.values());
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(PollEditForm form, RedirectAttributes model) {
        pollService.publishOrEditPoll(form);
        saveSuccess(model, "保存投票成功");
        return redirect("/poll/index.do");
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(Long[] ids, RedirectAttributes model) {
        pollService.delete(ids);
        saveSuccess(model, "删除投票选项成功");
        return redirect("/poll/index.do");
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonObject delete(Long id) {
        pollService.delete(new Long[]{id});
        return JsonObject.refresh();
    }

    @RequestMapping("result")
    public void result(@RequestParam("Q_EQ_poll.node.id") Long id, HttpServletRequest request, Model model) {
        Page<TbPollOption> pollOptions = pollService.getPollOptions(id, buildPageRequest(request, 30));
        model.addAttribute("page", pollOptions);
    }

    @Override
    protected void doInitBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        super.doInitBinder(request, binder);
        binder.registerCustomEditor(PollStatus.class, new EnumeratePropertyEditor(PollStatus.class));
        binder.registerCustomEditor(PollType.class, new EnumeratePropertyEditor(PollType.class));
    }
}
