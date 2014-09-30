package com.joinsoft.mobile.cms.web.front;

import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.service.content.ArticleGroupManager;
import com.joinsoft.mobile.cms.service.content.ArticleManager;
import com.joinsoft.mobile.cms.service.content.CmsContentType;
import com.joinsoft.mobile.cms.service.content.NodeService;
import com.joinsoft.mobile.cms.web.front.security.AccessTokenController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * xingsen@join-cn.com
 */
@Controller
@RequestMapping(FrontController.PORTAL_PREFIX + "/cms/*")
public class FrontCmsController extends FrontController {
    @Resource
    private ArticleManager articleManager;
    @Resource
    private NodeService nodeService;
    @Resource
    private ArticleGroupManager articleGroupManager;

    @RequestMapping("show")
    public String show(Long id, Model model) {
        TbCmsNode node = nodeService.getCmsNodeById(id);
        if (node.getType().equalsIgnoreCase(CmsContentType.Poll.name())) {
            //投票需要安全
            return "redirect:" + AccessTokenController.PORTAL_PREFIX + "/cms/show.do?id=" + id;
        }
        model.addAttribute("article", articleManager.getContent(id));
        return FrontController.PORTAL_PREFIX + "/cms/show";
    }

    @RequestMapping("index")
    public void index(Model model) {
        model.addAttribute("articles", articleGroupManager.getArticlesForFront());
    }

}
