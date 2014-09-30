package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.mobile.cms.entity.ad.TbAd;
import com.joinsoft.mobile.cms.service.AdService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wangxulong on 14-8-17.
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/ad/*")
public class AdController extends AdminController {
    @Resource
    private AdService adService;

    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        Page<TbAd> page = adService.search(searchParams, buildPageRequest(request));
        model.addAttribute("page", page);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public void edit(Long id, Model model) {
        TbAd ad = new TbAd();
        if (null != id) {
            ad = adService.getById(id);
        }
        model.addAttribute("command", ad);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Long id, String title, String description, String picUrl,
                       MultipartFile file, RedirectAttributes model) {
        adService.save(id, title, description, picUrl, file);
        saveSuccess(model, "保存成功");
        return redirect("/ad/index.do");
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(Long id, RedirectAttributes model) {
        if (null == id) {
            saveError(model, "请选择要删除的广告");
            return redirect("/ad/index.do");
        }
        adService.delete(new Long[]{id});
        saveSuccess(model, "删除成功");
        return redirect("/ad/index.do");
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(Long[] ids, RedirectAttributes model) {
        if (null == ids) {
            saveError(model, "请选择要删除的广告");
            return redirect("/ad/index.do");
        }
        adService.delete(ids);
        saveSuccess(model, "删除成功");
        return redirect("/ad/index.do");

    }

    @RequestMapping("show")
    public void show(@ModelAttribute("id") Long id, Model model) {
        model.addAttribute("ad", adService.getById(id));
    }

}
