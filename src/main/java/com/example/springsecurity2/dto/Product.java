package com.example.springsecurity2.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {

private int productId;
private String name;
private int qty;
private int price;


}

