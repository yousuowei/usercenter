<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/base/page/base.jsp"%>
<%@include file="/base/page/validate.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户基本信息</title>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#inputForm")
								.validate(
										{
											rules : {
												"name" : {
													required : true,
													digits : true,
													maxlength : (12),
													minlength : (1),
													remote : "checkUnique?property=name"
															+ "&oldValue="
															+ encodeURIComponent('${model.name}')
												}
											},
											messages : {
												"name" : {
													remote : "name已经存在！"
												}
											}
										});
					});
</script>
</head>
<body>

	<c:choose>
		<c:when test="${empty model.id}">
			<c:set var="tbl_title" value="用户注册"></c:set>
			<c:set var="tbl_url" value="add"></c:set>
			<c:set var="tbl_method" value="post"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="tbl_title" value="修改用户基本信息"></c:set>
			<c:set var="tbl_url" value="update"></c:set>
			<c:set var="tbl_method" value="put"></c:set>
		</c:otherwise>
	</c:choose>

	<f:form action="${tbl_url }" method="${tbl_method }" id="inputForm"
		commandName="model">
		<f:hidden path="id" />
		<table width="100%">
			<tr>
				<td colspan="2" style="background: #5CACEE">
					<div class="top clearfix ">
						<b><a href="list"> 用户列表 </a></b><span class="add">>>${tbl_title}
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tdRight">用户昵称：</td>
				<td class="tdLeft"><f:input path="name" /></td>
			</tr>
			<tr>
				<td width="10%" class="tdRight">用户email：</td>
				<td class="tdLeft"><f:input path="email" /></td>
			</tr>
			<tr>
				<td width="10%" class="tdRight">密码：</td>
				<td class="tdLeft"><f:input path="psd" /></td>
			</tr>
			<tr>
				<td width="10%" class="tdRight">确认密码：</td>
				<td class="tdLeft"><f:input path="rePsd" /></td>
			</tr>
			<tr>
				<td class="tdRight"></td>
				<td class="tdLeft"><input name="" type="submit" value="提交"
					style="margin-right: 20px;" /> <input name="" type="reset"
					value="重置" /></td>
			</tr>
		</table>
	</f:form>
</body>
</html>