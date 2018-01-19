package com.springmvc.solr.service.impl;

import com.springmvc.solr.dao.SearchDao;
import com.springmvc.solr.pojo.ResultModel;
import com.springmvc.solr.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * class_name: SearchServiceImpl
 * package: com.springmvc.solr.service.impl
 * describe: TODO
 *
 * @author: Liuxianglong
 * @date: 2018/1/16
 * creat_time: 21:15
 **/


/*
* 功能：接收action传递过来的参数，根据参数拼装一个查询条件，调用dao层方法，查询商品列表。接收返回的商品列表和商品的总数量，根据每页显示的商品数量计算总页数。
参数：
1、查询条件：字符串
2、商品分类的过滤条件：商品的分类名称，字符串
3、商品价格区间：传递一个字符串，满足格式：“0-100、101-200、201-*”  price:[10 TO 100]
4、排序条件：页面传递过来一个升序或者降序就可以，默认是价格排序。0：升序1：降序   字符串。
5、分页信息：每页显示的记录条数创建一个常量60条。传递一个当前页码就可以了。start rows

* */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public ResultModel search(String queryString, String catalog_name, String price, String sort, Integer page, Integer rows) throws Exception {

        //创建一个查询对象(封装了所有的查询的参数)
        SolrQuery solrQuery = new SolrQuery();
        //判断,如果前台输入的值是空的,则查询所有,否者就是具体的条件
        if (StringUtils.isNotBlank(queryString)) {
            //主查询条件
            solrQuery.setQuery(queryString);

        } else {
            solrQuery.setQuery("*:*");
        }
        //设置过滤条件之一 类别的筛选
        if (StringUtils.isNotBlank(catalog_name)) {
            solrQuery.addFilterQuery("product_catalog_name" + catalog_name);
        }
        //设置商品的价格区间
        if (StringUtils.isNotBlank(price)) {
            String[] split = price.split("-");
            solrQuery.addFilterQuery("product_price:[" + split[0] + "TO" + split[1] + "]");
        }
        //4、排序条件：页面传递过来一个升序或者降序就可以，默认是价格排序。0：升序1：降序   字符串。
        if ("q".equals(sort)) {
            solrQuery.setSort("product_price", SolrQuery.ORDER.desc);
        } else {
            solrQuery.setSort("product_price", SolrQuery.ORDER.asc);
        }
        //5、分页信息：每页显示的记录条数创建一个常量60条。传递一个当前页码就可以了。start rows
        // 2.4
        // 设置分页的条件
        if (page == null)
            page = 1;
        if (rows == null)
            rows = 60;
        solrQuery.setStart((page - 1) * rows);
            solrQuery.setRows(rows);

        //设置默认的搜索域
        solrQuery.set("df", "product_name");
        //设置高亮信息
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("product_name");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");

        ResultModel resultModel = searchDao.search(solrQuery);
        //设置分页信息
        resultModel.setCurPage(page);
        resultModel.setRows(rows);
        //计算总页数
        Long recordCount = resultModel.getRecordCount();
        Long pageCount = (recordCount / rows);
        if (recordCount % rows > 0) pageCount++;
        //设置总页数
        resultModel.setPageCount(pageCount.intValue());
        return resultModel;
    }
}
