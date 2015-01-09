package org.yousuowei.usercenter.imp.common;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * 
 * @ClassName: EncryptUtil
 * @Description: 密码加密算法
 * @author: jie
 * @date: 2014-4-20 下午2:20:50
 */
public class EncryptUtil {
    // 从配置文件中获得
    private static final String SITE_WIDE_SECRET = "youwei";
    private static final PasswordEncoder encoder = new StandardPasswordEncoder(
	    SITE_WIDE_SECRET);

    public static String encrypt(String rawPassword) {
	return encoder.encode(rawPassword);
    }

    public static boolean match(String rawPassword, String password) {
	return encoder.matches(rawPassword, password);
    }
}
