package br.com.TagsCustom;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import javax.swing.JOptionPane;

public class MinhaTag extends SimpleTagSupport{


	public void doStartTag() throws JspException, IOException {  
	
		JspWriter out= getJspContext().getOut();  
	    
		out.println("Teste de Jsp");

	}  	
	
}

