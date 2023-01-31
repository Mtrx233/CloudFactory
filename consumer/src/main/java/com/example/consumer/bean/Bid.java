package com.example.consumer.bean;

import lombok.Data;

@Data
public class Bid {
    private int factory_id;
    private int bid_id;
    private int bill_id;
    private String bid_begin_time;
    private String bid_end_time;
    private int bid_money;
    private int bid_quantity;
    private String bid_p_name;
    private int bid_product_id;
    private String bid_account;
    private String bid_state;
}
