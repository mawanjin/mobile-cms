package com.joinsoft.mobile.cms.web.admin.poll;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.framework.web.json.JsonObject;
import com.joinsoft.framework.web.message.WebMessageLevel;
import com.joinsoft.mobile.cms.entity.poll.TbPoll;
import com.joinsoft.mobile.cms.service.poll.PollRewardService;
import com.joinsoft.mobile.cms.service.poll.PollService;
import com.joinsoft.mobile.cms.web.admin.AdminController;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * User: wujun
 * Date: 2014/9/12
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/poll/reward/*")
public class PollRewardController extends AdminController {
    @Resource
    private PollRewardService pollRewardService;
    @Resource
    private PollService pollService;

    @RequestMapping("index")
    public void index(@RequestParam("Q_EQ_poll.node.id") Long pollNodeId, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        TbPoll poll = pollService.getPollByMeta(pollNodeId);
        model.addAttribute("page", pollRewardService.searchUserRewardPage(searchParams, buildPageRequest(request)));
        model.addAttribute("poll", poll);
    }

    @RequestMapping("reward")
    @ResponseBody
    public JsonObject reward(@RequestParam("poll_id") Long pollId, Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return JsonObject.alert("请先勾选用户", WebMessageLevel.ERROR);
        }
        pollRewardService.reward(pollId, ids);
        return JsonObject.refresh("兑奖成功");
    }

    @RequestMapping("all_reward")
    @ResponseBody
    public JsonObject reward(@RequestParam("poll_id") Long pollId) {
        pollRewardService.reward(pollId);
        return JsonObject.refresh("全部兑奖成功");
    }
}
