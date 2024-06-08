<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<!-- <script src="/js/mbti.js"></script> -->
<script>

	$(function(){
		
		var type = {};
		 // 이벤트 위임이 필요. 동적으로 추가된 요소에는 이벤트가 자동으로 바인딩 x
		 
		 $(document).on('click', '.questionGroup:checked', function() {
			 	var questionType = this.name;
		        var score = $(this).val();
		        
		        if (type[questionType] !== undefined) {
		            return;
		        }
		        
		        type[questionType] = score;
		        console.log("type = ", type);
		    });
		 
		 $(document).on('click','.questionGroup',  function(){
			 $('.questionGroup:checked').each(function() {
		           $(this).closest('tr td').css('filter', 'blur(1px)');
		       });
		 });
		 
		 $('#nextBtn').on('click', function(){
			 		
				if ($('.questionGroup:checked').length !== 5) {
	                alert('모든 문항을 선택 해주세요.');
	                return false;
	            }
				
			   $.ajax({
		    	 	url: '/saveAndNext.do',
		            type: 'POST',
		            contentType: 'application/json', 
		            data: JSON.stringify(type),
		            success: function(response) {
		         		
	         			location.href="/mbti.do";	
		 						
		            },
		            error: function(jqXHR, textStatus, errorThrown) {
		                alert('실패');
		            }
		    	}); 
			   
		   });
	  
	});

</script>
<style ></style>
<body>
	
	<table id="baseTable" align="center" style="margin:auto; border-bottom: 1px solid gray">
	    <c:forEach items='${questions}' var="qe" varStatus="loop">
	            <tr style="border-collapse: separate; border-spacing: 0 30px">
	                <td>
	                    <div align="center">${qe.question}</div>
	                    <div align="center">동의 
	                        <input type="radio" class="questionGroup" name="${qe.questionType}${loop.index}" value="1"/>
	                        <input type="radio" class="questionGroup" name="${qe.questionType}${loop.index}" value="2"/>
	                        <input type="radio" class="questionGroup" name="${qe.questionType}${loop.index}" value="3"/>
	                        <input type="radio" class="questionGroup" name="${qe.questionType}${loop.index}" value="4"/>
	                        <input type="radio" class="questionGroup" name="${qe.questionType}${loop.index}" value="5"/>
	                        <input type="radio" class="questionGroup" name="${qe.questionType}${loop.index}" value="6"/>
	                        <input type="radio" class="questionGroup" name="${qe.questionType}${loop.index}" value="7"/>
	                    비동의</div>
	                </td>
	               </tr>
	     </c:forEach>
	</table>
	
	<div align="center" style="margin-top:50px"><input id="nextBtn" type="button" value="다음" /></div>

	


</body>
</html>