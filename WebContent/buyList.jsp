<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style type="text/css">
table,tr,td{
         border:1px solid black;
         border-collapse: collapse;
         text-align: center;
         padding: 10px 30px;
         margin-left: 230px;
   }
   img{
      width: 200px;
      height: 200px;
   }
   h1{
   text-align: center;
   }
   .tot{
   background-color: white;;
   }
   </style>
</head>
<body>
	<h1>구매 목록</h1>
<form method="post" >
<div>
<table id="list">
   <tr>
   <td>주문 번호</td>
   <td>주문 가격</td>
   <td>주문 ID</td>
   <td>주문 날짜</td>
   </tr>
   </table>
   
   <input type="submit" value="홈" formaction="del"/>
</div>
</form>	
<div id="detail"> </div>
	
	<script type="text/javascript">
	var json = ${buyList}
	console.log(json);
	var str="";
	$.each(json,function(key,value){
		   str+="<tr>";
		   str+="<td><a href='#' value='"+json[key].b_num+"'>"+ json[key].b_num+"</a></td>";
		   str+="<td>"+ json[key].b_total+"</td>";
		   str+="<td>"+ json[key].b_id+"</td>";
		   str+="<td>"+ json[key].b_date+"</td>";
		   str+="</tr>";
		});
	$("#list").append(str);
	$("a").click(function () {
		console.log($(this).attr("value"));
		$.ajax({
			type:"post",
			url:"buydetail",
			data:{data:$(this).attr("value")},
			dataType:"json",
			success:function(data){
				console.log(data);
				$("#detail").append(data);
			},
			error: function (error) {
				
			}
		});
	})
	</script>
</body>
</html>