package com.example.consumer.bean;

import lombok.Data;

@Data
public class Bill {

    private int user_id;
    private String user_name;

    private int destination_id;
    private String d_name;
    private String d_phone;
    private String d_adress;

    private int product_id;
    private String p_name;

    private int bill_id;
    private String b_account;
    private int quantity;
    private String bid_end;
    private String end_time;
    private String b_state;



}
