package org.yousuowei.web.usercenter.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.yousuowei.base.web.controller.admin.BaseController;
import org.yousuowei.usercenter.ifc.UserIfc;
import org.yousuowei.usercenter.ifc.info.UserInfo;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<UserInfo> {

    @Autowired
    private UserIfc userService;

    @Override
    protected String getViewName() {
	return "user";
    }

    @RequestMapping("regist")
    public String regist(String id) {
	return "redirect:read";
    }

    @RequestMapping("/{uid}/emailActivate/{key}")
    public String emailActivate(@PathVariable String uid,
	    @PathVariable String key) {
	int result = userService.emailActivate(uid, key);
	switch (result) {
	case 0:
	    return "success";
	case -1:
	    return "lose user";
	case -2:
	    return "url expired";
	default:
	    return "success";
	}
    }

    @RequestMapping("/userLogin")
    public ModelAndView login(String uname, String psd, ModelAndView mv) {
	int result = userService.login(uname, psd);
	switch (result) {
	case 0:
	    mv.addObject("tag", "用户登录成功！");
	    break;
	case -1:
	    mv.addObject("tag", "用户不存在！");
	    break;
	case -2:
	    mv.addObject("tag", "密码错误！");
	    break;
	default:
	    mv.addObject("tag", "用户登录成功！");
	    break;
	}
	mv.addObject("resutl", result);
	mv.setViewName("/login-result.jsp");
	return mv;
    }

    public void logout() {

    }

}
