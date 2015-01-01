package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.mobile.cms.entity.TbLoginout;
import com.joinsoft.mobile.cms.entity.news.TbNews;
import com.joinsoft.mobile.cms.service.AgentService;
import com.joinsoft.mobile.cms.service.LoginoutService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by abcd on 15/1/1.
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/loginout/*")
public class LoginoutStataController extends AdminController {
    @Resource
    private LoginoutService loginoutService;

    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        PageRequest pageRequest = buildPageRequest(request,sort);

        Page<TbLoginout> page = loginoutService.search(searchParams, pageRequest);
        model.addAttribute("page", page);
    }


}
