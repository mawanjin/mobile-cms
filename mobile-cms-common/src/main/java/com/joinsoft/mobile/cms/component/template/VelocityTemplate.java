package com.joinsoft.mobile.cms.component.template;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
@Component
public class VelocityTemplate {
    @Resource
    private VelocityEngine velocityEngine;

    public String renderText(String text, Map<String, Object> model) {
        StringWriter sw = new StringWriter();
        VelocityEngineUtils.mergeTemplate(velocityEngine, text, "UTF-8", model, sw);
        return sw.toString();
    }
}
