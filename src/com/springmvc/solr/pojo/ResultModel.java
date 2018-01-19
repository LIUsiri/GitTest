package com.springmvc.solr.pojo;

import java.util.List;

/**
 * class_name: ResultModel
 * package: com.springmvc.solr.pojo
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/16
 * creat_time: 20:41
 * 返回值对象模型
 **/

public class ResultModel {
    // 商品列表
    private List<ProductModel> productList;
    // 商品总数
    private Long recordCount;
    // 总页数
    private Integer pageCount;
    // 当前页
    private Integer curPage;
    // 每页的行数
    private Integer rows;

    public List<ProductModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductModel> productList) {
        this.productList = productList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
