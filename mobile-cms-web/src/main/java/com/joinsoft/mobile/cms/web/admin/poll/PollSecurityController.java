package com.joinsoft.mobile.cms.web.admin.poll;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.framework.web.json.JsonObject;
import com.joinsoft.mobile.cms.service.poll.PollSecurityService;
import com.joinsoft.mobile.cms.web.admin.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * User: wujun
 * Date: 2014/9/7
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/poll/security/*")
public class PollSecurityController extends AdminController {
    @Resource
    private PollSecurityService pollSecurityService;

    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        model.addAttribute("page", pollSecurityService.search(searchParams, buildPageRequest(request)));
    }

    @RequestMapping("save")
    @ResponseBody
    public JsonObject save(Long[] ids, @RequestParam(value = "poll_node_id") Long pollNodeId) {
        pollSecurityService.savePollVisibility(ids, pollNodeId);
        return JsonObject.refresh();
    }

    @RequestMapping("edit")
    public void edit(@RequestParam(value = "poll_node_id") Long pollNodeId, HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        model.addAttribute("page", pollSecurityService.searchUser(pollNodeId, searchParams, buildPageRequest(request)));
        model.addAttribute("isajax", "ajax");
    }

    @RequestMapping("delete")
    public String delete(@RequestParam(value = "poll_node_id") Long pollNodeId, Long[] ids, RedirectAttributesModelMap model) {
        if (ids == null || ids.length == 0) {
            saveError(model, "请选择要关闭权限的用户");
            return redirect("/poll/security/index.do?Q_EQ_poll.node.id=" + pollNodeId);
        }
        pollSecurityService.deletePollVisibility(ids);
        saveSuccess(model, "操作成功");
        return redirect("/poll/security/index.do?Q_EQ_poll.node.id=" + pollNodeId);
    }
}
