package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.mobile.cms.entity.TbPointDetail;
import com.joinsoft.mobile.cms.entity.TbTrafficDetail;
import com.joinsoft.mobile.cms.service.RewardService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wangxulong on 14-8-27.
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/log/*")
public class LogController extends AdminController {
    @Resource
    private RewardService rewardService;

    @RequestMapping("point")
    public void score(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        searchParams.put("NEQ_point",0);
        Page<TbPointDetail> page = rewardService.searchScoreDetailLog(searchParams, buildPageRequest(request));
        model.addAttribute("page", page);
    }

    @RequestMapping("traffic")
    public void traffic(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        searchParams.put("NEQ_traffic",0);
        Page<TbTrafficDetail> page = rewardService.searchTrafficDetailLog(searchParams, buildPageRequest(request));
        model.addAttribute("page", page);
    }


}
