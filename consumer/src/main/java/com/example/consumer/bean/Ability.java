package com.example.consumer.bean;

import lombok.Data;

@Data
public class Ability {
    private int ability_id;
    private int equipment_id;
    private int product_id;
    private int quantity;
    private String p_name;

    public Ability(int equipment_id, int product_id, int quantity) {
        this.equipment_id = equipment_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }
}
