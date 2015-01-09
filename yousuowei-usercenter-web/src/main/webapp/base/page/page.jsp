<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("#mainForm").submit();
	}
	
	function goPage(totalPage,nowPage) {
		var pageNo = $("#goNo").val();
		if(pageNo<=0){
			alert("page can't less than 0!");
			$("#goNo").val(nowPage);
			return;
		}
		if(totalPage<pageNo){
			alert("total page is:"+totalPage);
			$("#goNo").val(nowPage);
			return;
		}
		$("#pageNo").val(pageNo);
		$("#mainForm").submit();
	}

	function sort(orderBy, defaultOrder) {
		if ($("#orderBy").val() == orderBy) {
			if ($("#order").val() == "") {
				$("#order").val(defaultOrder);
			} else if ($("#order").val() == "desc") {
				$("#order").val("asc");
			} else if ($("#order").val() == "asc") {
				$("#order").val("desc");
			}
		} else {
			$("#orderBy").val(orderBy);
			$("#order").val(defaultOrder);
		}

		$("#mainForm").submit();
	}

	function search() {
		$("#order").val("");
		$("#orderBy").val("");
		$("#pageNo").val("1");

		$("#mainForm").submit();
	}
</script>

<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}" />
<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}" />
<input type="hidden" name="order" id="order" value="${page.order}" />

<div align="right">
	第<input id="goNo" value="${page.pageNo}" size="3">页,
	共${page.totalPages}页 |${page.totalCount }结果<a
		href="javascript:jumpPage(1)">首页</a>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:if test="page.hasPre">
		<a href="javascript:jumpPage(${page.prePage})">上一页</a>
	</c:if>
	<c:if test="page.hasNext">
		<a href="javascript:jumpPage(${page.nextPage})">下一页</a>
	</c:if>
	<a href="javascript:jumpPage(${page.totalPages})">末页</a><input
		type="button" value="go"
		onclick="goPage(${page.totalPages},${page.pageNo})">
</div>