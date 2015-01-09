package org.yousuowei.usercenter.ifc;

import org.yousuowei.base.ifc.BaseIfc;
import org.yousuowei.usercenter.ifc.info.UserInfo;

public interface UserIfc extends BaseIfc<UserInfo> {

    public UserInfo getUserByName(String userName);

    public UserInfo getUserById(String id);

    /**
     * 用户注册
     * 
     * @param userName
     * @param email
     * @param psd
     * @return 0注册成功 ，其他为各种状态
     * @author: jie
     * @date: 2014-4-20 下午10:33:55
     */
    public int regist(String userName, String email, String psd);

    /**
     * 用户登录
     * 
     * @param loginName
     * @param psd
     * @return 0 登录成功 其它为各种状态
     * @author: jie
     * @date: 2014-4-20 下午2:47:54
     */
    public int login(String loginName, String psd);

    /**
     * 用户激活
     * 
     * @param uid
     * @param key
     * @return 0 激活成功 其它为失败
     * @author: jie
     * @date: 2014-5-3 下午2:44:00
     */
    public int emailActivate(String uid, String key);

}
