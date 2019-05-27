package com.person.erp.identity.model;

import com.person.erp.common.valid.LoginPhone;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import javax.validation.groups.Default;

/**
 * <p>LoginDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/5/28 2:33
 */
@Getter
@Setter
public class LoginDTO {

    /**
     * 用户帐号
     */
    @NotEmpty(message = "[userCode] 用户帐号不能为空！")
    @Pattern(regexp = "[a-zA-Z0-9_]{4,10}", message = "[userCode] 用户帐号必须由4-10位数字、字母或下划线组成")
    private String userCode;

    /**
     * 用户密码
     */
    @NotEmpty(groups = {Default.class, LoginPhone.class}, message = "[userPwd] 密码不能为空！")
    private String userPwd;

    /**
     * 系统标识
     */
    @NotNull(message = "[systemTag] 系统标识不能为空")
    @Min(value = 0, message = "[systemTag] 系统标识最小值为 0")
    private Long systemTag;

    /**
     * 手机号（需要校验唯一性）
     */
    @NotBlank(groups = {LoginPhone.class}, message = "[mobilePhone] 手机号不能为空")
    private String mobilePhone;

    @NotBlank(message = "[code] 验证码不能为空")
    private String code;

}
