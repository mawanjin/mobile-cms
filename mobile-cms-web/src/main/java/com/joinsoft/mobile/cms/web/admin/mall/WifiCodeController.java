package com.joinsoft.mobile.cms.web.admin.mall;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.mobile.cms.entity.enumerate.WifiCodeStatus;
import com.joinsoft.mobile.cms.entity.mall.TbWifiCode;
import com.joinsoft.mobile.cms.service.WifiCodeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wangxulong on 14-9-17.
 */
@Controller
@RequestMapping(MallController.PORTAL_PREFIX + "/wifi/*")
public class WifiCodeController extends MallController {

    @Resource
    private WifiCodeService wifiCodeService;

    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        Page<TbWifiCode> page = wifiCodeService.search(searchParams, buildPageRequest(request));
        model.addAttribute("page", page);
    }

    @RequestMapping("edit")
    public void edit(Long id, Model model) {
        TbWifiCode wifiCode = new TbWifiCode();
        if (null != id) {
            wifiCode = wifiCodeService.getById(id);
        }
        model.addAttribute("command", wifiCode);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Long id, WifiCodeStatus status, RedirectAttributes model) {
        wifiCodeService.save(id, status);
        saveSuccess(model, "保存成功");
        return redirect("/wifi/index.do");
    }

    @RequestMapping(value = "import", method = RequestMethod.GET)
    public void importWifiCode() {

    }

    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importWifiCode(MultipartFile wifiCodeFile,RedirectAttributes model) {
        if(null == wifiCodeFile){
            saveSuccess(model,"选择文件");
            return redirect("/wifi/index.do");
        }
        wifiCodeService.importWifiCode(wifiCodeFile);
        saveSuccess(model,"导入成功");
        return redirect("/wifi/index.do");

    }
}
