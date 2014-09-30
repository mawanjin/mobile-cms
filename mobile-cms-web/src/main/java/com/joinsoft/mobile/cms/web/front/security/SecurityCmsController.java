package com.joinsoft.mobile.cms.web.front.security;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.mobile.cms.PollException;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.service.content.CmsContentType;
import com.joinsoft.mobile.cms.service.content.NodeService;
import com.joinsoft.mobile.cms.service.poll.PollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
@Controller
@RequestMapping(AccessTokenController.PORTAL_PREFIX + "/cms/*")
public class SecurityCmsController extends AccessTokenController {
    private Logger logger = LoggerFactory.getLogger(SecurityCmsController.class);

    @Resource
    private PollService pollService;
    @Resource
    private NodeService nodeService;

    @RequestMapping("show")
    public String show(Long id, Model model) {
        TbCmsNode node = nodeService.getCmsNodeById(id);
        if (node == null) {
            model.addAttribute("message", "内容不存在");
            return messageBox();
        }
        logger.info("cms内容 {}", node.getType());
        if (node.getType().equalsIgnoreCase(CmsContentType.Poll.name())) {
            model.addAttribute("poll", pollService.getPollByMeta(id));
            model.addAttribute("options", pollService.getAllPollOptions(id));
            if (logger.isInfoEnabled()) {
                logger.info("submit_enable: {}", pollService.isSubmitEnable());
            }
            model.addAttribute("submit_enable", pollService.isSubmitEnable());
        }
        return AccessTokenController.PORTAL_PREFIX + "/cms/show_"+node.getType().toLowerCase();
    }

    @RequestMapping("submit_poll")
    public void submit(@RequestParam("poll_id") Long pollId, HttpServletRequest request, Model model) {
        Map<String, Object> valueParams = HttpRequests.getParametersStartingWith(request, "value_");
        boolean success = true;
        try {
            pollService.savePollRecord(pollId, valueParams);
        } catch (PollException e) {
            model.addAttribute("message", e.getMessage());
            success = false;
        }
        model.addAttribute("success", success);
    }
}
