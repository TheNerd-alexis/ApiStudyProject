<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<footer>
<center>
<c:if test="${not empty itemList }">
<table class="page-table"><tr>
	<td>
		<c:if test="${itemList[0].currentPage != 1 }">
			<a
				href="search.do?keyword=${itemList[0].keyword }&searchType=${itemList[0].searchType }&page=${1 }"><<</a>
		</c:if>
	</td>
	<td>
	<c:if test="${itemList[0].currentPage - 10 > 1 }">
			<a
				href="search.do?keyword=${itemList[0].keyword }&searchType=${itemList[0].searchType }&page=${itemList[0].currentPage-10 }"><</a>
		</c:if> 
	</td>
	<c:forEach var="i" begin="${itemList[0].startPage }" end="${itemList[0].currentPage-1 }">
		<td>
			<a
				href="search.do?keyword=${itemList[0].keyword }&searchType=${itemList[0].searchType }&page=${i }">${i }</a>
		</td>
	</c:forEach> 
	<td>
		<b>${itemList[0].currentPage }</b>
	</td>
	<c:forEach var="i" begin="${itemList[0].currentPage + 1 }" end="${itemList[0].endPage }" varStatus="status">
		<td>
			<a
				href="search.do?keyword=${itemList[0].keyword }&searchType=${itemList[0].searchType }&page=${i }">${i }</a>
		</td>
	</c:forEach>
	<td>
		<c:if test="${itemList[0].lastPage > itemList[0].currentPage + 10 }">
			<a
				href="search.do?keyword=${itemList[0].keyword }&searchType=${itemList[0].searchType }&page=${itemList[0].currentPage + 10 }">></a>
		</c:if> 
	</td>
	<td>
		<c:if test="${itemList[0].currentPage < itemList[0].lastPage }">
			<a
				href="search.do?keyword=${itemList[0].keyword }&searchType=${itemList[0].searchType }&page=${itemList[0].lastPage }">>></a>
		</c:if>
	</td>
</tr></table>
</c:if>
</center>
</footer>