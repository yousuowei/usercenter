package org.yousuowei.usercenter.common;

import org.junit.Test;
import org.springframework.util.Assert;
import org.yousuowei.usercenter.imp.common.EncryptUtil;

public class EncryptUtilTest {

    @Test
    public void encryptPSD() {
	String psd = "jie";
	String psd1 = EncryptUtil.encrypt("jie");
	Assert.isTrue(EncryptUtil.match(psd, psd1));
    }

}
