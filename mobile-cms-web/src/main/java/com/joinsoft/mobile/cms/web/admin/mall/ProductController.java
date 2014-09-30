package com.joinsoft.mobile.cms.web.admin.mall;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.framework.web.json.JsonObject;
import com.joinsoft.mobile.cms.entity.enumerate.ProductStatus;
import com.joinsoft.mobile.cms.entity.mall.TbProduct;
import com.joinsoft.mobile.cms.form.ProductEditForm;
import com.joinsoft.mobile.cms.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wangxulong on 14-8-19.
 */
@Controller
@RequestMapping(MallController.PORTAL_PREFIX + "/product/*")
public class ProductController extends MallController {
    @Resource
    private ProductService productService;


    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        Page<TbProduct> page = productService.searchProduct(searchParams, buildPageRequest(request));
        model.addAttribute("page", page);
    }

    @RequestMapping("edit")
    public void edit(Long id, Model model) {
        ProductEditForm form = new ProductEditForm();
        if (null != id) {
            form = ProductEditForm.formEntity(productService.getProductById(id));
        }
        model.addAttribute("productStatus", ProductStatus.values());
        model.addAttribute("command", form);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(ProductEditForm form, RedirectAttributes model) {
        productService.saveOrUpdateProduct(form);
        saveSuccess(model, "保存成功");
        return redirect("/product/index.do");
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonObject delete(Long id, RedirectAttributes model) {
        if (null != id) {
            productService.deleteProduct(id);
        }
        saveSuccess(model, "删除成功");
        return JsonObject.refresh();
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(Long[] ids, RedirectAttributes model) {
        if (null == ids) {
            saveError(model, "请选择要删除的商品");
            return redirect("/product/index.do");
        }
        productService.deleteProduct(ids);
        saveSuccess(model, "删除成功");
        return redirect("/product/index.do");
    }


}
