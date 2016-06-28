<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ include file="header.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Naver Search API</title>
<link rel="stylesheet" type="text/css" href="style/tableStyle.css">
</head>
<body>
<c:if test="${not empty itemList }">
	<center>
		<table class="item-table">
			<tr>
				<td>
					No
				</td>
				<td>
					검색 결과
				</td>
			</tr>
			<c:forEach items="${itemList }" var="item" begin="1" varStatus="status">
				<tr>
					<td>
					${itemList[0].display*(itemList[0].currentPage-1) + status.count }
					</td>
					<td>
						<c:if test="${not empty item.link}">
							<a href="${item.link }" target="_blank" >
								<c:if test="${not empty item.thumbnail }">
									<img src="${item.thumbnail }" border="0" width="50px" height="50px" style="vertical-align:middle">
								</c:if>
								<c:if test="${not empty item.image }">
									<img src="${item.image }" border="0" width="50px" height="50px" style="vertical-align:middle">
								</c:if> ${item.title }
							</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</c:if>
</body>
<%@ include file="footer.jsp" %>
</html>