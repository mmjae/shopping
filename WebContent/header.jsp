<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	span{
	color: red;
	}
</style>
</head>
<body>
	<c:if test="${id==null}"> <!-- 로그인 안된경우 -->
	<form action="access" name="loginFrm" method="post">
		아이디:<input type="text" name="id" placeholder="아이디"><br/>
		비밀번호 : <input type="password" name="pw" placeholder="비밀번호"><br/>
		<span>${msgAccess}</span><br/>
		<button>로그인</button>
		<a href="joinfrm">회원가입</a>
	</form>
	</c:if>
	<c:if test="${id!=null}">
		<a href="logout">로그아웃</a>
		<a href="proupfrm">상품 등록</a>
	</c:if>
</body>
</html>