package com.joinsoft.mobile.cms.web.admin.poll;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.framework.web.binder.EnumeratePropertyEditor;
import com.joinsoft.framework.web.json.JsonObject;
import com.joinsoft.mobile.cms.entity.enumerate.PollOptionType;
import com.joinsoft.mobile.cms.entity.poll.TbPoll;
import com.joinsoft.mobile.cms.entity.poll.TbPollOption;
import com.joinsoft.mobile.cms.service.poll.PollService;
import com.joinsoft.mobile.cms.web.admin.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
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
@RequestMapping(AdminController.PORTAL_PREFIX + "/poll/option/*")
public class PollOptionManageController extends AdminController {
    @Resource
    private PollService pollService;

    @RequestMapping("index")
    public void index(@RequestParam(value = "Q_EQ_poll.node.id", required = false) Long pollNodeId,
                      @RequestParam(value = "Q_EQ_poll.id", required = false) Long pollId,
                      HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        model.addAttribute("page", pollService.searchPollOptions(searchParams, buildPageRequest(request)));
        if (pollNodeId != null) {
            model.addAttribute("poll", pollService.getPollByMeta(pollNodeId));
        } else {
            model.addAttribute("poll", pollService.getPollById(pollId));
        }
    }

    @RequestMapping("edit")
    public void edit(Long id,
                     @RequestParam(value = "poll_id", required = false) Long pollId,
                     Model model) {
        TbPollOption pollOption;
        if (id != null) {
            pollOption = pollService.getPollOption(id);
        } else {
            Assert.notNull(pollId, "poll_id must not be null");
            pollOption = new TbPollOption();
            TbPoll poll = pollService.getPollById(pollId);
            pollOption.setPoll(poll);
        }
        model.addAttribute("command", pollOption);
        model.addAttribute("typeItems", PollOptionType.values());
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Long id, String title, PollOptionType type, Float score,
                       @RequestParam("poll.id") Long pollId, RedirectAttributes model) {
        pollService.savePollOption(id, title, type, score, pollId);
        saveSuccess(model, "保存投票选项成功");
        return redirect("/poll/option/index.do?Q_EQ_poll.id=" + pollId);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(Long[] ids, HttpServletRequest request, RedirectAttributes model) {
        pollService.deletePollOption(ids);
        saveSuccess(model, "删除投票选项成功");
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonObject delete(Long id) {
        pollService.deletePollOption(new Long[]{id});
        return JsonObject.refresh();
    }

    @Override
    protected void doInitBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        super.doInitBinder(request, binder);
        binder.registerCustomEditor(PollOptionType.class, new EnumeratePropertyEditor(PollOptionType.class));
    }
}
