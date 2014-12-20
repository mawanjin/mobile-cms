package com.joinsoft.mobile.cms.web.front.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.joinsoft.framework.util.Json;
import com.joinsoft.mobile.cms.service.GoodsFollowingService;
import com.joinsoft.mobile.cms.web.front.FrontController;
import com.joinsoft.mobile.cms.web.front.vo.JobCargotrackinginfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-11-12.
 */
@Controller
@RequestMapping(AccessTokenController.PORTAL_PREFIX + "/goods/*")
public class GoodsFollowingController extends AccessTokenController {

    @Resource
    private GoodsFollowingService followingService;

    @RequestMapping("index")
    public void index() {
    }


    /**
     * @param model
     * @param yundan 运单号
     * @param box    箱号
     * @return
     */
    @RequestMapping("findGoods")
    public String findGoods(Model model, String yundan, String box) {
//        String json = followingService.findGoods(yundan, box);
//        if (null != json && !json.equals("[]")) {
//            JsonNode jsonNode = Json.parse(json);
//            List<JobCargotrackinginfo> infos = new ArrayList<JobCargotrackinginfo>();
//            for (int i = 0; i < jsonNode.size(); i++) {
////            System.out.println(jsonNode.get(i).toString());
//                infos.add((JobCargotrackinginfo) Json.fromJson(jsonNode.get(i).toString(), JobCargotrackinginfo.class));
//            }
//            model.addAttribute("infos", infos);
//        } else {
//            model.addAttribute("hint", "该条件查询无记录");
//        }
//        model.addAttribute("yundan", yundan);
//        model.addAttribute("box", box);
        return FrontController.PORTAL_PREFIX + "/at/goods/index";
    }
}
