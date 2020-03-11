<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="insertproduct" method="post" enctype="multipart/form-data">
	<table border="1"> 
		<tr>
			<td colspan="2" align="center"><h3>상품 업로드</h3></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="radio" name="p_kind" value="new" checked="checked"><b>신상품</b>
			<input type="radio" name="p_kind" value="best"><b>인기상품</b>
			</td>
		</tr>
		<tr>
		<td>상품명</td>
		<td><input type="text" name="p_name"></td>
		</tr>
		<tr>
		<td>상품가격</td>
		<td><input type="text" name="p_price"></td>
		</tr>
		<tr>
		<td>상품수량</td>
		<td><input type="text" name="p_qty"></td>
		</tr>
		<tr>
		<td>상품설명</td>
		<td><textarea rows="5" cols="50" name="p_contents">  </textarea></td>
		</tr>
		<tr>
		<td>상품 이미지</td>
		<td><input type="file" name="p_file"></td>
		</tr>
		<tr>
		<td colspan="2" align="center">
		<button>상품 등록</button>
		<input type="reset" value="취소">
		</td>
		</tr>
	
	</table>
	
	
	
	</form>
</body>
</html>