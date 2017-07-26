package br.com.sysloccOficial.videos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class VideosController {
		
	
	@RequestMapping("videosTeste")
	public String videosTeste (){
		
		System.out.println("Testes");
		
		return null;
	}
	
	
}
