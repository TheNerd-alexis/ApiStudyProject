<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<header>
<br>
	<center>
		<form action="search.do">
		<input type="hidden" name="page" value="1">
			<table>
				<tr>
					<td><select name="searchType">
<!-- 							<option value="100" -->
<%-- 								${itemList[0].searchType == 100 ? 'selected="selected"' : ''}>통합검색</option> --%>
							<option value="101"
								${itemList[0].searchType == 101 ? 'selected="selected"' : ''}>블로그</option>
							<option value="102"
								${itemList[0].searchType == 102 ? 'selected="selected"' : ''}>뉴스</option>
							<option value="103"
								${itemList[0].searchType == 103 ? 'selected="selected"' : ''}>책</option>
							<option value="105"
								${itemList[0].searchType == 105 ? 'selected="selected"' : ''}>백과사전</option>
							<option value="106"
								${itemList[0].searchType == 106 ? 'selected="selected"' : ''}>영화</option>
							<option value="107"
								${itemList[0].searchType == 107 ? 'selected="selected"' : ''}>카페게시글</option>
							<option value="108"
								${itemList[0].searchType == 108 ? 'selected="selected"' : ''}>지식인</option>
							<option value="109"
								${itemList[0].searchType == 109 ? 'selected="selected"' : ''}>지역</option>
							<option value="111"
								${itemList[0].searchType == 111 ? 'selected="selected"' : ''}>웹페이지</option>
							<option value="112"
								${itemList[0].searchType == 112 ? 'selected="selected"' : ''}>이미지</option>
							<option value="113"
								${itemList[0].searchType == 113 ? 'selected="selected"' : ''}>쇼핑</option>
							<option value="114"
								${itemList[0].searchType == 114 ? 'selected="selected"' : ''}>문서</option>
						</select></td>
					<td><input type="text" name="keyword" value="${itemList[0].keyword }"></td>
					<td><input type="submit" value="search"></td>
				</tr>
			</table>
		</form>
	</center>
</header>