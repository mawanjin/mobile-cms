package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.web.HttpRequests;

import com.joinsoft.mobile.cms.entity.agent.TbSectionAgent;
import com.joinsoft.mobile.cms.entity.news.TbNews;

import com.joinsoft.mobile.cms.service.AgentService;
import com.joinsoft.mobile.cms.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxulong on 14-8-18.
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/news/*")
public class NewsController extends AdminController {
    @Resource
    private NewsService newsService;



    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        Page<TbNews> page = newsService.search(searchParams, buildPageRequest(request));
        model.addAttribute("page", page);
    }



    @RequestMapping("edit")
    public void edit(Long id, Model model) {

        TbNews command = new TbNews();
        if (null != id) {
            command= newsService.getById(id);
        }


        model.addAttribute("command", command);
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbNews form, RedirectAttributes model) {


        newsService.save(form);
        saveSuccess(model, "保存成功");
        return redirect("/news/index.do");
    }




    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(Long id, RedirectAttributes model) {
        if (null == id) {
            saveError(model, "新闻不存在");
            return redirect("/news/index.do");
        }
        try{
            newsService.deleteNews(id);
        }catch(Exception e){
            if(e instanceof org.springframework.dao.DataIntegrityViolationException){
                saveError(model, "被删除的片区下面有网点,请先删除网点");
                return redirect("/news/index.do");
            }else{
                saveError(model, "发生系统异常");
                return redirect("/news/index.do");
            }
        }
         return redirect("/news/index.do");
    }



    @RequestMapping(value = "delete2")
    public String delete(Long[] ids, RedirectAttributes model) {
        if (null == ids) {
            saveError(model, "请选择删除的新闻集合");
            return redirect("/news/index.do");
        }
        try{
                newsService.deleteNews(ids);
        }catch(Exception e){
           // e.printStackTrace();
            if(e instanceof org.springframework.dao.DataIntegrityViolationException){

                saveError(model, "被删除的片区下面有网点,请先删除网点");
                return redirect("/news/index.do");
            }else{
                saveError(model, "发生系统异常");
                return redirect("/news/index.do");
            }
        }
       return redirect("/news/index.do");
    }


    @RequestMapping(value = "fabu")
    public String fabu(Long id, String flag,RedirectAttributes model) {
        if (null == id) {
            saveError(model, "新闻不存在");
            return redirect("/news/index.do");
        }
        try{
            newsService.updateOnlineById(flag,id);

        }catch(Exception e){

                saveError(model, "发生系统异常");


        }
        return redirect("/news/index.do");
    }

    @RequestMapping(value = "fabu2")
    public String fabu(Long[] ids, String flag,RedirectAttributes model) {
        if (null == ids) {
            saveError(model, "新闻集合不存在");
            return redirect("/news/index.do");
        }
        try{
            newsService.updateOnlineById(flag,ids);

        }catch(Exception e){

            saveError(model, "发生系统异常");


        }
        return redirect("/news/index.do");
    }


    @RequestMapping("/getCityByP")
    @ResponseBody
    public ArrayList<String> showAjaxTable(HttpServletRequest request) {

        ArrayList l=new ArrayList();
        l.add("莫干山第三方松岛枫松岛枫松岛枫第三方第三方");
        l.add("杭州");
        return l;
    }


}
