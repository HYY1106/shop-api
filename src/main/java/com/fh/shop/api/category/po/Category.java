package com.fh.shop.api.category.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {


    private Long id;
    private String categoryName;
    private Long pid;
    private Integer type;

}
