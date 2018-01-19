package com.springmvc.solr.controller;

import com.springmvc.solr.pojo.ResultModel;
import com.springmvc.solr.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class_name: ProductController
 * package: com.springmvc.solr.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/16
 * creat_time: 22:02
 **/

@Controller
public class ProductController {

    private static final Integer ROWS=60;

    @Autowired
    private SearchService searchService;

    @RequestMapping("/list")
    public String search(String queryString, String catalog_name, String price, String sort, Integer page, Model model)throws  Exception{


        ResultModel resultModel = searchService.search(queryString, catalog_name, price, sort, page, ROWS);

        model.addAttribute("result", resultModel);
        model.addAttribute("price", price);
        model.addAttribute("catalog_name", catalog_name);
        model.addAttribute("sort", sort);
        model.addAttribute("queryString", queryString);
        //4.返回页面
        return "product_list";


    }
}
