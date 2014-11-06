package com.joinsoft.mobile.cms.web.front;

import com.joinsoft.mobile.cms.entity.news.TbNews;
import com.joinsoft.mobile.cms.service.TbNewsService;
import com.joinsoft.mobile.cms.web.front.security.AccessTokenController;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 14-11-5.
 */
@Controller
@RequestMapping(AccessTokenController.PORTAL_PREFIX + "/news/*")
public class TbNewsController extends AccessTokenController {
    @Resource
    private TbNewsService tbNewsService;

    @RequestMapping("index")
    public void index(Model model) {
        Page<TbNews> pages = tbNewsService.findByTbNews(0);
        model.addAttribute("news", pages.getContent());
        int page = pages.getTotalPages();
        model.addAttribute("pages", page > 5 ? 5 : page);
        model.addAttribute("end", page);
        model.addAttribute("page_number", 0);
        model.addAttribute("begin", 1);
    }

    @RequestMapping("findByTbNewsId")
    public String findByTbNewsId(Model model, Long id) {
        model.addAttribute("new", tbNewsService.findByTbNewsId(id));
        return FrontController.PORTAL_PREFIX + "/at/news/newsdetails";
    }

    @RequestMapping("findPage")
    public String findPage(Model model, int page_number) {
        Page<TbNews> newses = tbNewsService.findByTbNews(0);
        model.addAttribute("news", newses.getContent());
        int page = newses.getTotalPages();
        int pages = page > 5 ? 5 : page;
        int begin = 1;
        if (page_number > 3) {
            begin = page_number - 2 <= 1 ? 1 : page_number - 2;
            pages = page_number + 2 >= page ? page : page_number + 2;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("end", page);
        model.addAttribute("page_number", page_number);
        model.addAttribute("begin", begin);
        return FrontController.PORTAL_PREFIX + "/at/news/index";
    }
}
