package com.springmvc.solr.pojo;

/**
 * class_name: ProductModel
 * package: com.springmvc.solr.pojo
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/16
 * creat_time: 20:39
 **/

public class ProductModel {
    //商品编号
    private Integer pid;
    // 商品名称
    private String name;
    // 商品分类名称
    private String catalog_name;
    // 价格
    private Float price;
    // 图片名称
    private String picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalog_name() {
        return catalog_name;
    }

    public void setCatalog_name(String catalog_name) {
        this.catalog_name = catalog_name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPid() {

        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
