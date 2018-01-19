package com.springmvc.solr.service;


import com.springmvc.solr.pojo.ResultModel;

/**
 * class_name: SearchService
 * package: com.springmvc.solr.service
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/16
 * creat_time: 21:14
 **/

public interface SearchService {
    public ResultModel search(String queryString, String catalog_name, String price, String sort, Integer page, Integer rows) throws Exception;
}
