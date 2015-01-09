<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/base/page/base.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
	<f:form id="mainForm" action="list" method="get" commandName="page">
		<table width="100%">
			<tr>
				<td colspan="4" style="background: #5CACEE">
					<div class="top clearfix">
						<b>用户列表</b>
						<div>
							[<a href="null">添加用户</a>]
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="search clearfix">
						<div class="form">
							ID:<input name="filter_EQS_id" value="${param['filter_EQS_id']}">
							名称：<input name="filter_LIKES_name"
								value="${param['filter_LIKES_name']}"><input
								type="button" value="搜索" onclick="search();" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>ID</td>
				<td>名称</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${page.result}" var="e">
				<tr>
					<td>${e.id}</td>
					<td>${e.name}</td>
					<td><a href="${e.id}">编辑</a> <a
						href="javascript:base.remove('${e.id }');">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
		</table>
		<%@include file="/base/page/page.jsp"%>
	</f:form>

</body>
</html>