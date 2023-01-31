package com.example.ProductProvider.bean;

import lombok.Data;

@Data
public class Product {
    private Integer product_id;
    private Integer product_kind_id;
    private String p_name;
    private String kind;
    private String p_account;
    private String p_brief;
    private String p_specification;
    private double p_price;
}
