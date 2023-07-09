package com.atguigu.ssyx.vo.acl;

import com.atguigu.ssyx.model.acl.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-09 12:50
 **/
@Data
@Builder
public class AdminRoleVo {

    @Tolerate
    public AdminRoleVo() {
    }

    @ApiModelProperty("所有角色列表")
    private List<Role> allRoleList;

    @ApiModelProperty("用户的角色列表")
    private List<Role> assignRoles;


}
