<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script>

	$(function(){
		
		sessionStorage.setItem('result', '${result}');
		sessionStorage.setItem('resultImg', '${resultImg}');
		
		var result = sessionStorage.getItem('result');
		var resultImg = sessionStorage.getItem('resultImg');
		
		$('h2').text('당신의 MBTI : ' + result);
	    $('img').attr('src', resultImg);
		
	})

</script>
<body>

	<h2 align="center"></h2>
	<img align="center" src=""/>

</body>
</html>