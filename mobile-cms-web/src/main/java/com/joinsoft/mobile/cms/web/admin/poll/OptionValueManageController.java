package com.joinsoft.mobile.cms.web.admin.poll;

import com.joinsoft.framework.web.json.JsonObject;
import com.joinsoft.mobile.cms.entity.enumerate.PollOptionValueType;
import com.joinsoft.mobile.cms.entity.poll.TbPollOption;
import com.joinsoft.mobile.cms.entity.poll.TbPollOptionValue;
import com.joinsoft.mobile.cms.service.poll.PollService;
import com.joinsoft.mobile.cms.web.admin.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * User: wujun
 * Date: 2014/8/15
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/poll/option/value/*")
public class OptionValueManageController extends AdminController {
    @Resource
    private PollService pollService;

    @RequestMapping("index")
    public void index(@RequestParam(value = "option_id") Long optionId, Model model) {
        model.addAttribute("valueItems", pollService.getAllPollOptionValues(optionId));
        model.addAttribute("pollOption", pollService.getPollOption(optionId));
    }

    @RequestMapping("edit")
    public void edit(Long id,
                     @RequestParam(value = "poll_option_id", required = false) Long pollOptionId,
                     Model model) {
        TbPollOptionValue pollOptionValue;
        if (id != null) {
            pollOptionValue = pollService.getPollOptionValue(id);
        } else {
            Assert.notNull(pollOptionId, "poll_id must not be null");
            pollOptionValue = new TbPollOptionValue();
            TbPollOption pollOption = pollService.getPollOption(pollOptionId);
            pollOptionValue.setPollOption(pollOption);
            pollOptionValue.setType(PollOptionValueType.String);
        }
        model.addAttribute("command", pollOptionValue);
        model.addAttribute("typeItems", PollOptionValueType.values());
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Long id, PollOptionValueType type, String value, MultipartFile pic, String video, Boolean answer,
                       @RequestParam("pollOption.id") Long pollOptionId, RedirectAttributes model) {
        pollService.saveValue2PollOption(id, type, value, pic, video, answer, pollOptionId);
        saveSuccess(model, "保存投票选项内容成功");
        return redirect("/poll/option/value/index.do?option_id=" + pollOptionId);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject delete(Long[] ids) {
        pollService.deletePollOptionValue(ids);
        return JsonObject.refresh("删除投票选项内容成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonObject delete(Long id) {
        pollService.deletePollOptionValue(new Long[]{id});
        return JsonObject.refresh();
    }

    @RequestMapping("chart")
    @ResponseBody
    public JsonObject chart(@RequestParam("option_id") Long optionId) {
        return JsonObject.success(pollService.getOptionEChartData(optionId));
    }
}
