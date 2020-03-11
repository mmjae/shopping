<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="jquery.serializeObject.js"></script>
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
<h1>내 장바구니</h1>
<form method="post" >
<div>
<table id="cart">
   <tr>
   <td>상품 사진</td>
   <td>상품 번호</td>
   <td>상품 이름</td>
   <td>총수량</td>
   <td>가격</td>
   <td>총가격</td>
   <td>선택</td>
   </tr>
   </table>
   
  	총금액:<input type='text' class="alltot" disabled/>
   <input type="submit" value="구매" formaction="buy" id='buy'/>
   <input type="submit" value="삭제" formaction="del"/>
   <input type="hidden" name="sum" class="alltot">
</div>
</form>
	<script type="text/javascript">
	var json=${showcart}
	//console.log(json);
	var cnt=0;
	var str="";
	var sum=0;
	var totPrice=new Array();
	var onePrice=new Array();
	$.each(json,function(key,value){
		   str+="<tr>";
		   str+="<td><img src='upload/"+json[key].p_sysFileName+"'></td>";
		   str+="<td>"+ json[key].c_code+"</td>";
		   str+="<td>"+ json[key].p_name+"</td>";
		   str+="<td><input type='text' class='qty' name='c_qty' value='"+json[key].c_qty+"'><input type='button' value='▲' class='up'>"+
		   "<input type='button' value='▼' class='sub'></td>";
		   str+="<td><input type='text' class='oneprice' value='"+json[key].p_price+"'disabled>원</td>";
		   str+="<td><input type='text' class='tot' name='c_tot' value='"+json[key].p_price*json[key].c_qty+"'disabled>원</td>";
		   str+="<td><input type='checkbox' class='check' checked/></td>";
		   str+="</tr>";
		  	totPrice[key]=json[key].p_price;
		   sum+=json[key].p_price*json[key].c_qty;
		   
		  
		});
		    $("#cart").append(str);
	 var a = $(".oneprice");
	 var b= $(".check")
	 b.each(function () {
		console.log(b.prop("checked"));
	});
	console.log("b  =",b);
		console.log($(".tot").val());
		$(".alltot").val(sum);
		$(".up").on("click",function(){   
		    var n=$(".up").index(this);
		    console.log("n="+n);
		   var num=$(".qty:eq("+n+")").val();
		   console.log("num="+num);
		   if(num>=json.p_qty)
		      num=json.p_qty;
		   else{
		    $(".qty:eq("+n+")").val(num*1+1);
		    
		    for(var j=0;j<totPrice.length;j++){
		       if(n==j){
		    $(".tot:eq("+n+")").val((num*1+1)*totPrice[j]);
		       sum+=totPrice[j];}
	           $(".alltot").val(sum);
		    }
		}

		}); 
		
		
		    $(".sub").on("click",function(){   
		        var n=$(".sub").index(this);
		       var num=$(".qty:eq("+n+")").val();
		       if(num<=0)
		          num=0;
		       else{
		        $(".qty:eq("+n+")").val(num*1-1);
		        for(var j=0;j<totPrice.length;j++){
		           if(n==j){
		        $(".tot:eq("+n+")").val((num*1-1)*totPrice[j]);
		           sum-=totPrice[j];}
		           $(".alltot").val(sum);
		       }
		    }
		  
		    });
		console.log($(".alltot").val());
	</script>
</body>
</html>