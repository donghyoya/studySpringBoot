package com.increpas.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Component - Controller, @Service, @Repository들의 부모 클래스
//명확성을 위해서 Component보다 역할에 맞는 어노테이션을 적어준다

@Controller
public class TestController {
	
	//실행경로를 참조할수 있는 상수를 지정
	public static final String VIEW_PATH = "/WEB-INF/views/test";
	
	public TestController() {
		System.out.println("----TestController 생성자 호출----");
	}
	
	@RequestMapping("/test")
	public String test(Model model, HttpServletRequest request) {
		//Model = 데이터를 저장하고 관리하는 역할정도
		// - request 영역과 바인딩되어 있다
		// - request 영역을 잠시 빌려서 바인딩 해주는 구조이므로 온전한 request가 아니다
		// - request의 고유한 기능을 사용하려면 reqeust가 필요하다
		// 그래서 request기능을 사용하지 못하지만
		// 저장공간은 request공간에 저장된다
		// 불러올때 session이나 다른걸로 불러와야한다
		
		
		//위처럼하면 자동으로 request객체값이 들어온다
		
		String ip = request.getRemoteAddr();
		
		String[] msg = {"안녕하세요",
				"hello",
				"ありがとう",
				"你好"};
		
		model.addAttribute("msg",msg); //model을 통해서 배열을 request영역에 저장한다
		// request.setAttribute와 비슷하다
		
		request.setAttribute("ip", ip);
		
		return VIEW_PATH + "test.jsp";
	}
	
	//ModelAndView
	//model과 view 정보를 하나의 객체로 포장해서 전달해주는 클래스
	@RequestMapping("/test2")
	public ModelAndView test2(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(VIEW_PATH + "test2.jsp");
		
		//mv.setViewName(VIEW_PATH + "test2.jsp");
		//생성할때 PATH값을 설정하거나 위처럼 해도된다
		String ip = request.getRemoteAddr();
		String[] msg = {"안녕하세요",
				"hello",
				"ありがとう",
				"你好"};
		mv.addObject("ip",ip);
		mv.addObject("msg",msg);
		
		return mv;
	
	}
}
