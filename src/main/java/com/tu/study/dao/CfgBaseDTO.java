package com.tu.study.dao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
public class CfgBaseDTO {

    private Date createDate;


    private String createUser;

    private Date updateDate;

    private String updateUser;



}
