package org.springsecurity_sso.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "Api接口对象",description = "Api接口")
public class Menu implements Serializable {

    @ApiModelProperty("接口id")
    private Long id;
    @ApiModelProperty("接口请求路径")
    private String path;
    @ApiModelProperty("接口详情")
    private String detail;

}
