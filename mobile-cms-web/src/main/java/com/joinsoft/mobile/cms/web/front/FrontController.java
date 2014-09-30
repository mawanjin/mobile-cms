package com.joinsoft.mobile.cms.web.front;

import com.joinsoft.framework.web.controller.UIController;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * xingsen@join-cn.com
 */
public class FrontController extends UIController {
    public static final String PORTAL_PREFIX = "/front";

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }

    protected String messageBox() {
        return FrontController.PORTAL_PREFIX + "/common/message";
    }

    protected void saveErrorNext(HttpServletRequest request, String nextUrl) {
        request.setAttribute("nextUrl", getPortalPrefix() + nextUrl);
    }

    protected void addYearAndMonth(Integer year, Integer month, Model model) {
        Integer currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        model.addAttribute("month", month == null ? currentMonth : month);
        model.addAttribute("year", year == null ? currentYear : year);
        model.addAttribute("currentMonth", currentMonth);
        model.addAttribute("currentYear", currentYear);
    }
}
