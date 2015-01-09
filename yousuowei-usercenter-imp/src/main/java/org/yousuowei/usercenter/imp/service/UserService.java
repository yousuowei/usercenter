package org.yousuowei.usercenter.imp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yousuowei.base.imp.service.BaseService;
import org.yousuowei.common.additionservices.EmailEngine;
import org.yousuowei.common.utils.Utils;
import org.yousuowei.usercenter.ifc.UserIfc;
import org.yousuowei.usercenter.ifc.info.UserInfo;
import org.yousuowei.usercenter.imp.common.EncryptUtil;
import org.yousuowei.usercenter.imp.entity.UserEntity;

@Service
public class UserService extends BaseService<UserEntity, UserInfo> implements
	UserIfc {

    @Autowired
    private EmailEngine sender;
    @Value("${mail.subject}")
    private String subject;
    @Value("${mail.content}")
    private String content;

    public UserInfo getUserByName(String userName) {
	return (UserInfo) Utils.toObject(infoClass,
		findBy("userName", userName));
    }

    @Override
    public UserInfo getUserById(String id) {
	return (UserInfo) Utils.toObject(infoClass, read(id));
    }

    @Override
    public int regist(String userName, String email, String psd) {
	long time = System.currentTimeMillis();

	UserEntity user = new UserEntity();
	user.setUserName(userName);
	user.setEmail(email);
	user.setPsd(EncryptUtil.encrypt(psd));
	user.setRegistTime(time);
	save(user);

	// 发送激活邮件
	String dmpKey = EncryptUtil.encrypt(String.valueOf(time));
	sender.sendTextMail(email, subject,
		String.format(content, user.getId(), dmpKey));
	return 0;
    }

    @Override
    public int login(String loginName, String psd) {
	Object obj = findUniqueBy("email", loginName);
	if (null == obj) {// 用户不存在
	    return -1;
	}

	UserEntity user = (UserEntity) obj;
	if (EncryptUtil.match(psd, user.getPsd())) {
	    return 0;
	} else {// 密码错误
	    return -2;
	}
    }

    @Override
    public int emailActivate(String uid, String key) {
	UserEntity user = get(uid);
	if (null == user) {// 用户不存在
	    return -1;
	}

	if (EncryptUtil.match(key, user.getRegistTime().toString())) {// 激活成功
	    return 0;
	} else {// 激活链接过期失效
	    return -2;
	}

    }
}
