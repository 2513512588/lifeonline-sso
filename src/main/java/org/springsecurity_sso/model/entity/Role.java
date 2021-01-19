package org.springsecurity_sso.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "Role对象",description = "角色")
public class Role implements Serializable {

    @ApiModelProperty("角色id")
    private Long id;
    @ApiModelProperty("角色中文名")
    private String nameCn;
    @ApiModelProperty("角色英文名")
    private String nameEn;


}
