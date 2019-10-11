package com.notes.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class BrandInfo implements Serializable {

    @Excel(name = "brandGuid", width = 25,orderNum = "0")
    private String brandGuid;

    @Excel(name = "brandName", width = 25,orderNum = "0")
    private String brandName;

    @Excel(name = "ytFullcode", width = 25,orderNum = "0")
    private String ytFullcode;

    @Excel(name = "formatGuid", width = 25,orderNum = "0")
    private String formatGuid;

    @Excel(name = "flag", width = 25,orderNum = "0")
    private String flag;

    @Excel(name = "customerid", width = 25,orderNum = "0")
    private String customerid;

    @Excel(name = "createDatetime",width = 20,exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "1")
    private String createDatetime;

    @Excel(name = "updateDatetime",width = 20,exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "1")
    private String updateDatetime;

    @Excel(name = "source", width = 25,orderNum = "0")
    private Integer source;

}