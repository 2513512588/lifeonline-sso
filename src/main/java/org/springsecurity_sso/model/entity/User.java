package org.springsecurity_sso.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "User对象",description = "用户")
public class User implements Serializable {

    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户密码")
    @JsonIgnore
    private String password;
    @ApiModelProperty("用户昵称")
    private String nickname;
    @ApiModelProperty("创建时间")
    private Date create;
    @ApiModelProperty("用户头像")
    private String avatar;

}
