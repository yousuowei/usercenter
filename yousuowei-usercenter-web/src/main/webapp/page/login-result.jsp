<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/base/page/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录结果</title>
</head>
<body>

	<table width="100%">
		<tr>
			<td colspan="2" style="background: #5CACEE">
				<div class="top clearfix ">
					<b><a href="list"> 用户注册</a></b><span class="add">>>用户登录结果 </span>
				</div>
			</td>
		</tr>
		<tr>
			<td width="10%" class="tdRight">结果提示：</td>
			<td class="tdLeft">${tag }</td>
		</tr>
		<tr>
			<td class="tdRight"></td>
			<td class="tdLeft"><input name="" type="submit" value="提交"
				style="margin-right: 20px;" /> <input name="" type="reset"
				value="重置" /></td>
		</tr>
	</table>
</body>
</html>