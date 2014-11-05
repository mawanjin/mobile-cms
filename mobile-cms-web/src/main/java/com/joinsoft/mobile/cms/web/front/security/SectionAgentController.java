package com.joinsoft.mobile.cms.web.front.security;

import com.joinsoft.mobile.cms.service.AgentSectionService;
import com.joinsoft.mobile.cms.service.AgentService;
import com.joinsoft.mobile.cms.web.front.FrontController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 14-11-1.
 */
@Controller
@RequestMapping(AccessTokenController.PORTAL_PREFIX + "/agent/*")
public class SectionAgentController extends AccessTokenController {
    private Logger logger = LoggerFactory.getLogger(SectionAgentController.class);
    @Resource
    private AgentSectionService sectionService;
    @Resource
    private AgentService agentService;

    @RequestMapping("index")
    public void index(Model model) {
        model.addAttribute("sections", sectionService.getAllAgents());
        model.addAttribute("agents", agentService.getAllAgents());
    }

    @RequestMapping("findByTbSectionAgentId")
    public String findByTbSectionAgentId(Long id, Model model) {
//        List<TbAgent> agents = agentService.findByTbSectionAgentId(id);
        model.addAttribute("sections", sectionService.getAllAgents());
        model.addAttribute("agents", agentService.findByTbSectionAgentId(id));
        model.addAttribute("sections_id", id);
//        StringBuffer json = new StringBuffer("[");
//        for (int i = 0; i < agents.size(); i++) {
//            json.append("{");
//            json.append("agentName:'" + agents.get(i).getAgentName() + "',");
//            json.append("header:'" + agents.get(i).getAgentName() + "',");
//            json.append("telephone:'" + agents.get(i).getAgentName() + "',");
//            json.append("fax:'" + agents.get(i).getAgentName() + "',");
//            json.append("post:'" + agents.get(i).getAgentName() + "',");
//            json.append("addr:'" + agents.get(i).getAgentName() + "'");
//            if (i == agents.size() - 1) {
//                json.append("}");
//            } else {
//                json.append("},");
//            }
//        }
//        json.append("]");
        return FrontController.PORTAL_PREFIX + "/at/agent/index";
    }
}
