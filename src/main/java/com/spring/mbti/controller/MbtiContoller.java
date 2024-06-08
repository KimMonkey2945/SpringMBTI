package com.spring.mbti.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mbti.service.MbtiService;
import com.spring.mbti.vo.MbtiVo;

@Controller
public class MbtiContoller {
	
	@Autowired
	MbtiService mbtiService;
	
	private static final String[] QUESTION_TYPES = {"EI", "IE", "NS", "SN", "FT", "TF", "PJ","JP"};
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/mbti.do", method = RequestMethod.GET)
	public String mbti(Model model, HttpSession session) {
		
		Integer currentIndex = (Integer) session.getAttribute("currentIndex");
		if(currentIndex == null) {
			currentIndex = 0;
		}
		
		if (currentIndex >= QUESTION_TYPES.length - 1) {
	        return "redirect:/mbtiResult.do";
	    }

//		System.out.println("질문 호출 currentIndex : " + currentIndex);

		String questionType = QUESTION_TYPES[currentIndex];
		
		String type = questionType.substring(0,1);
		
		List<MbtiVo> questions = mbtiService.selectQuestion(type);
		
	    model.addAttribute("questions", questions);
	    session.setAttribute("questionType", questionType);
	    session.setAttribute("currentIndex", currentIndex);
		
		return "mbti";
	} 
	
	
	
	@RequestMapping(value = "/saveAndNext.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveAndNext(@RequestBody Map<String, String> data, HttpSession session) {
		
		String type = (String) session.getAttribute("questionType");
//		System.out.println(type);
		char[] chArr = type.toCharArray();
		Arrays.sort(chArr);
		String sortType = new String(chArr);
		
		
		int typeScore = 0;
		int typeScore2 = 0;
		
		String result = (String) session.getAttribute("result");
		
		if(result == null) {
		   result = "";
		}
		
		for (Map.Entry<String, String> entry : data.entrySet()) {
	        String key = entry.getKey();
	        String value = entry.getValue();
			String typeResult = key.substring(0,2);
			
//			값은 잘 넘어오는 것을 확인.
//			System.out.println(typeResult);
//	        System.out.println("Key: " + key + ", Value: " + value);
	        if(typeResult.equals(type)) {
				
				 switch (value) {
		            case "1":
		            	typeScore += 3;
		                break;
		            case "2":
		            	typeScore += 2;
		                break;
		            case "3":
		            	typeScore += 1;
		                break;
		            case "5":
		            	typeScore2 += 1;
		                break;
		            case "6":
		            	typeScore2 += 2;
		                break;
		            case "7":
		            	typeScore2 += 3;
		                break;
		          }
			} else {
				
				switch (value) {
	            case "1":
	            	typeScore2 += 3;
	                break;
	            case "2":
	            	typeScore2 += 2;
	                break;
	            case "3":
	            	typeScore2 += 1;
	                break;
	            case "5":
	            	typeScore += 1;
	                break;
	            case "6":
	            	typeScore += 2;
	                break;
	            case "7":
	            	typeScore += 3;
	                break;
				
				}
			} 
	        
	    }
		
		if(typeScore > typeScore2) {
			result += type.substring(0,1);
		}else if(typeScore2 > typeScore) {
			result += type.substring(1,2);
		}else {
			result += sortType.substring(0,1);
		}
		
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		Integer currentIndex = (Integer)session.getAttribute("currentIndex");
		currentIndex += 2;

		System.out.println("result : " + result);
//		System.out.println("저장 currentIndex : " + currentIndex);
		
		session.setAttribute("currentIndex", currentIndex);
		session.setAttribute("result", result);

		
		return response;
		
	}
	
	@RequestMapping(value = "/mbtiResult.do", method = RequestMethod.GET)
	public String mbtiResult(HttpSession session, Model model) {
		String result = (String) session.getAttribute("result");	
		String resultImg = "images/" + result + ".png"; 

		model.addAttribute("result", result);
		model.addAttribute("resultImg", resultImg);
		session.invalidate();

		return "mbtiResult";
	}

}
