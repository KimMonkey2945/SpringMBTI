

 	$(function(){
		 
//		alert("확인용");	
		 var total = 0;
		 
		$('input:radio[name="${questionType}"]').on('change', function(){
			console.log($(this).val());
		});
    
	    // 선택된 라디오 버튼의 값을 가져옵니다.
	    $('input:radio[name="${questionType}"]').each(function() {
	        if ($(this).is(':checked')) {
	            total += parseInt($(this).val());
	        }
	    });
	    
	    console.log('total : ', total);
		
	});
