package com.example.consumer.bean;

import lombok.Data;

@Data
public class Equipment {
    private Integer equipment_id;
    private Integer equipment_kind_id;
    private Integer factory_id;
    private String kind;
    private String e_name;
    private String e_brief;
    private String e_account;
    private String e_specification;
    private String e_state;
    private Integer is_owned;
}
