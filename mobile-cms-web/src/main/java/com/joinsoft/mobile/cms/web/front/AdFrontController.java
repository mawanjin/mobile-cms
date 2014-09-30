package com.joinsoft.mobile.cms.web.front;

import com.joinsoft.mobile.cms.entity.ad.TbAdStats;
import com.joinsoft.mobile.cms.service.AdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by wangxulong on 14-8-18.
 */
@Controller
@RequestMapping(FrontController.PORTAL_PREFIX + "/ad/*")
public class AdFrontController extends FrontController {
    @Resource
    private AdService adService;

    @RequestMapping("ad")
    public void publish(Long id, Model model,
                        @RequestParam(value = "h", required = true, defaultValue = "200") Double h,
                        @RequestParam(value = "w", required = true, defaultValue = "300") Double w) {
        adService.increaseRefCount(id);
        model.addAttribute("h", h);
        model.addAttribute("w", w);
        model.addAttribute("ad", adService.getById(id));
    }

    @RequestMapping("visit")
    public String visit(Long id) {
        TbAdStats adStats = adService.increaseVisitCount(id);
        return "redirect:" + adStats.getAd().getPicUrl();
    }
}
