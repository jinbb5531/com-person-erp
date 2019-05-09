package com.person.erp.common.utils;

import java.security.MessageDigest;

/**
 * <p>MD5Utils.java</p>
 *
 * @author zhuwj
 * @since 2019/5/8 14:26
 */
public final class MD5Utils {

    /**
     * MD5加密方法
     * @param str 明文
     * @return 密文(32位)
     */
    public static String getMD5(String str) {
        // 创建加密对象
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            // 加密
            md.update(str.getBytes("utf-8"));
            // 获取加密后的内容 (16位的字符数组)
            byte[] md5Bytes = md.digest();
            // 把加密后字节数组转化成32位字符串 (把每一位转化成16进制的两位)
            StringBuilder res = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                int temp = md5Byte & 0xFF;
                // 把temp值转化成16进制的两位数，如果不够两位前面补零
                if (temp <= 0xF) {
                    res.append("0");
                }
                res.append(Integer.toHexString(temp));
            }
            return res.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
