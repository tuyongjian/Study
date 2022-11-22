package com.tu.study.dao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CfgMetaDataDTO extends CfgBaseDTO {

    private Integer id;

    private String code;

    private String name;

    private String appid;

    private String desc;

    private Integer fatLastVerId;

    private Integer uatLastVerId;

    private Integer prdLastVerId;



}
