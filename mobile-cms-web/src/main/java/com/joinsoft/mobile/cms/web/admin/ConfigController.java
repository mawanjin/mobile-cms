package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.mobile.cms.entity.TbConfig;
import com.joinsoft.mobile.cms.service.ConfigService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wangxulong on 14-9-1.
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/config/*")
public class ConfigController extends AdminController {
    @Resource
    private ConfigService configService;

    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        Page<TbConfig> page = configService.searchConfig(searchParams, buildPageRequest(request));
        model.addAttribute("page", page);
    }

    @RequestMapping("edit")
    public void edit(Long id, Model model) {
        TbConfig config = new TbConfig();
        if (null != id) {
            config = configService.getConfig(id);
        }
        model.addAttribute("command", config);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Long id, String name, String cnName, String val, RedirectAttributes model) {
        configService.saveConfig(id, name, cnName, val);
        saveSuccess(model, "保存成功");
        return redirect("/config/index.do");
    }

    @RequestMapping("delete")
    public String delete(RedirectAttributes model, Long... ids) {
        if (null == ids || ids.length <= 0) {
            saveError(model, "请选择要删除的配置");
        }
        configService.deleteConfig(ids);
        saveSuccess(model, "删除成功");
        return redirect("/config/index.do");
    }

}
