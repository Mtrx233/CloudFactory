package com.example.consumer.bean;

import lombok.Data;

@Data
public class Rental {
    private int rental_id;
    private int equipment_id;
    private int factory_id;
    private String r_begin_time;
    private int duration;
}
