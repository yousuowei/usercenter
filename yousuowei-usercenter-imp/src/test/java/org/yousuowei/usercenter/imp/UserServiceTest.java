package org.yousuowei.usercenter.imp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.yousuowei.usercenter.ifc.UserIfc;

// 加入标记则数据将不添加到数据库
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:db-context.xml",
	"classpath:email-context.xml" })
public class UserServiceTest {

    @Autowired
    private UserIfc service;

    @Test
    public void testRegistLogin() {
	String userName = "admin";
	String email = "xljie2@163.com";
	String psd = "admin";
	Assert.assertEquals(0, service.regist(userName, email, psd));
	Assert.assertEquals(0, service.login(email, psd));
	Assert.assertEquals(-1, service.login("haha", psd));
	Assert.assertEquals(-2, service.login(email, "mima"));
    }

}
