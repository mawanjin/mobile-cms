package com.joinsoft.mobile.cms.web.front.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.joinsoft.framework.util.Json;
import com.joinsoft.mobile.cms.entity.agent.TbAgent;
import com.joinsoft.mobile.cms.service.AgentService;
import com.joinsoft.mobile.cms.service.SailingDateService;
import com.joinsoft.mobile.cms.web.front.FrontController;
import com.joinsoft.mobile.cms.web.front.vo.SailingVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-11-11.
 */
@Controller
@RequestMapping(AccessTokenController.PORTAL_PREFIX + "/sailing/*")
public class SailingDateController extends AccessTokenController {
    @Resource
    private SailingDateService dateService;

    @Resource
    private AgentService agentService;

    private List<TbAgent> getAgents(){
        List<TbAgent> agents = agentService.getAllAgents();
        for(TbAgent agent : agents){
            if(agent.getAgentName().endsWith("办"))agent.setAgentName(agent.getAgentName().substring(0,agent.getAgentName().length()-1));
        }
        return agents;
    }

    @RequestMapping("index")
    public void index(HttpServletRequest request) {
        request.setAttribute("agents",getAgents());
    }


    /**
     * @param model
     * @param sport 始发港名称
     * @param port  目的港名城
     * @return
     */
    @RequestMapping("findPortScope")
    public String findPortScope(Model model, String sport, String port) {
        try{

        model.addAttribute("agents",getAgents());
        String json = dateService.findPortScope(sport, port);
        if (null != json && !json.equals("[]")) {
            JsonNode jsonNode = Json.parse(json);
            List<SailingVo> sailings = new ArrayList<SailingVo>();
            for (int i = 0; i < jsonNode.size(); i++) {
                sailings.add((SailingVo) Json.fromJson(jsonNode.get(i).toString(), SailingVo.class));
            }
            model.addAttribute("sailings", sailings);
        } else {
            model.addAttribute("hint", "该条件查询无记录");
        }
        }catch(Exception e){
            model.addAttribute("hint", "该条件查询无记录");
        }
        model.addAttribute("type", 1);
        model.addAttribute("sport", sport);
        model.addAttribute("port", port);
        return FrontController.PORTAL_PREFIX + "/at/sailing/index";
    }

    /**
     * @param model
     * @param mort  港口
     * @return
     */
    @RequestMapping("findPort")
    public String findPort(Model model, String mort) {
        model.addAttribute("agents",getAgents());
        String json = dateService.findPort(mort);
        if (null != json&&!json.equals("") && !json.equals("[]")) {
            JsonNode jsonNode = Json.parse(json);
            List<SailingVo> sailings = new ArrayList<SailingVo>();
            for (int i = 0; i < jsonNode.size(); i++) {
                sailings.add((SailingVo) Json.fromJson(jsonNode.get(i).toString(), SailingVo.class));
            }
            model.addAttribute("sailings", sailings);
        } else {
            model.addAttribute("hint", "该条件查询无记录");
        }
        model.addAttribute("type", 2);
        model.addAttribute("mport", mort);
        return FrontController.PORTAL_PREFIX + "/at/sailing/index";
    }

    /**
     * @param model
     * @param fight 船名
     * @param ship  航次
     * @return
     */
    @RequestMapping("findShips")
    public String findShips(Model model, String fight, String ship) {

        String json = dateService.findShips(fight, ship);
        if (null != json && !json.equals("[]")) {
            JsonNode jsonNode = Json.parse(json);
            List<SailingVo> sailings = new ArrayList<SailingVo>();
            for (int i = 0; i < jsonNode.size(); i++) {
                sailings.add((SailingVo) Json.fromJson(jsonNode.get(i).toString(), SailingVo.class));
            }
            model.addAttribute("sailings", sailings);
        } else {
            model.addAttribute("hint", "该条件查询无记录");
        }
        model.addAttribute("agents",getAgents());
        model.addAttribute("type", 3);
        model.addAttribute("fight", fight);
        model.addAttribute("ship", ship);
        return FrontController.PORTAL_PREFIX + "/at/sailing/index";
    }
}
