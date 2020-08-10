package com.fh.shop.api.area.po;


import lombok.Data;

@Data//不用生成get/set
public class Area {

    private Long id;
    private String areaName;//地区名
    private Long pid;
}
