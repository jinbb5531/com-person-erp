package com.person.erp.identity.service;

/**
 * <p>ILoginService.java</p>
 *
 * @author zhuwj
 * @since 2019/5/23 22:14
 */
public interface ILoginService {

    /**
     * 登录
     * @author zhuwj
     * @since 2019/5/23 22:14
     * @param userCode
     * @param systemTag
     * @param userPwd
     * @return java.lang.String
     */
    String loginInto(String userCode, Long systemTag, String userPwd);

    /**
     * 通过手机号登录
     * @author zhuwj
     * @since 2019/5/25 10:26
     * @param mobilePhone
     * @param userPwd
     * @return java.lang.String
     */
    String loginIntoByPhone(String mobilePhone, String userPwd);
}
