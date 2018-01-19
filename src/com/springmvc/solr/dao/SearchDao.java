package com.springmvc.solr.dao;

import com.springmvc.solr.pojo.ProductModel;
import com.springmvc.solr.pojo.ResultModel;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class_name: SearchDao
 * package: com.springmvc.solr.dao
 * describe: TODO
 *
 * @author: Liuxianglong
 * @date: 2018/1/16
 * creat_time: 20:44
 **/
@Repository
public class SearchDao {


    @Autowired
    public SolrServer solrServer;


    public ResultModel search(SolrQuery query) throws Exception {
        //构建sorlserver对象这个对象有spring托管
        QueryResponse queryResponse = solrServer.query(query);

        SolrDocumentList results = queryResponse.getResults();
        //构建result model对象
        ResultModel model = new ResultModel();
        //设置总记录数
        model.setRecordCount(results.getNumFound());
        //获取高亮
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        //构建一个Productmodel的list集合对象
        ArrayList<ProductModel> productmodels = new ArrayList<>();
        for (SolrDocument result : results) {
            ProductModel productModel = new ProductModel();
            productModel.setPid(result.get("id") == null ? null : Integer.parseInt(result.get("id").toString()));
            //获取高亮显示的信息
            String hightLightingProductName = "";
            List<String> list = highlighting.get(result.get("id")).get("product_name");
            if (list != null && list.size() > 0) {
                hightLightingProductName = list.get(0);
            } else {
                hightLightingProductName = result.get("product_name") == null ? null : result.get("product_name").toString();
            }

            productModel.setName(hightLightingProductName);

            productModel.setCatalog_name(result.get("product_catalog_name") == null ? null : result.get("product_catalog_name").toString());

            productModel.setPrice(result.get("product_price")==null?null: (Float) result.get("product_price"));

            productmodels.add(productModel);
        }

        model.setProductList(productmodels);

        return model;
    }
}
