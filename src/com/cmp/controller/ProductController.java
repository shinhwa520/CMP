package com.cmp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmp.MenuItem;
import com.cmp.form.ProductForm;

@Controller
@RequestMapping("/")
public class ProductController extends BaseController {
	private static Log log = LogFactory.getLog(ProductController.class);

	@RequestMapping(value = { "/channel/productInfo/info" }, method = RequestMethod.GET)
    public String fileMain(Model model, @ModelAttribute("ProductForm") ProductForm form, HttpServletRequest request, HttpServletResponse response) {
		setActiveMenu(model, MenuItem.PRODUCT_INFO);
		return "channel/product_info";
    }
}
