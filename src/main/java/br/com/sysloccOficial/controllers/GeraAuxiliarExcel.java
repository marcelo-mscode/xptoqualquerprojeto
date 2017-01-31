package br.com.sysloccOficial.controllers;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

public class GeraAuxiliarExcel extends GeraAbstracaoExcel{
	
	
	public WritableCellFormat formataCabecalho(){
		   try {
			   
			   WritableFont fontArial10 = new WritableFont(WritableFont.ARIAL,13);
			   fontArial10.setBoldStyle(WritableFont.BOLD);	
			   return super.alinhaAoCentroFont10(fontArial10,Border.ALL, BorderLineStyle.THIN,Colour.BLACK,false);
			   
		} catch (Exception e) {
			// TODO: Desconsiderar
		}
		return null;
	}
	
	
	public WritableCellFormat alinhaCentroComTodasBordasFontBold(){
		try {
			WritableFont fontArial10 = new WritableFont(WritableFont.ARIAL,10);
			fontArial10.setBoldStyle(WritableFont.BOLD);	
			return super.alinhaAoCentroFont10(fontArial10,Border.BOTTOM, BorderLineStyle.THIN,Colour.BLACK,true);
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Deu um erro na sub mãe ): ");
		}
		return null;
	} 

	public WritableCellFormat alinhaCentroComTodasBordas(){
		    try {
		    	WritableFont fontArial10 = new WritableFont(WritableFont.ARIAL,10);
				return super.alinhaAoCentroFont10(fontArial10,Border.ALL, BorderLineStyle.THIN,Colour.BLACK,true);
		    	
			} catch (Exception e) {
				//JOptionPane.showMessageDialog(null, "Deu um erro em Super!");
			} 
			return null;
	  }
	   
	  public WritableCellFormat formataNumeroParaReal(){
		   try {
			
			    WritableCellFormat formataNumeroParaReal = new WritableCellFormat(new NumberFormat("#,##0.00"));
				formataNumeroParaReal.setVerticalAlignment(VerticalAlignment.CENTRE);
				formataNumeroParaReal.setWrap(true);
				formataNumeroParaReal.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK);
			   
				return formataNumeroParaReal;
			   
		} catch (Exception e) {
			// TODO: Desconsiderar
		}
		return null;
	 }
  
  public WritableCellFormat formataNumeroParaRealComBold(){
	   
	   try {
		
		   WritableCellFormat formataNumeroParaRealComBold =
						new WritableCellFormat(new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD), new NumberFormat("#,##0.00"));
					    formataNumeroParaRealComBold.setVerticalAlignment(VerticalAlignment.CENTRE);
					    formataNumeroParaRealComBold.setWrap(true);
					    formataNumeroParaRealComBold.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK);
			   
				return formataNumeroParaRealComBold;
			   
		} catch (Exception e) {
			// TODO: Desconsiderar
		}
		return null;
	 }
	  
	  public WritableCellFormat formataSubTotal(){
		   
		   try {
			
			   WritableCellFormat formataSubTotal =
				  		new WritableCellFormat(new WritableFont(WritableFont.ARIAL,13, WritableFont.BOLD),new NumberFormat("#,##0.00"));
						    formataSubTotal.setVerticalAlignment(VerticalAlignment.CENTRE);
						    formataSubTotal.setWrap(true);
						    formataSubTotal.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK);	 
				return formataSubTotal;
			   
		} catch (Exception e) {
			// TODO: Desconsiderar
		}
		return null;
	}
	  
	  public WritableCellFormat BordaCimaBaixo(){
		   try {
			    WritableCellFormat BordaCimaBaixo = new WritableCellFormat();
				BordaCimaBaixo.setBorder(Border.TOP,BorderLineStyle.THIN,Colour.BLACK);
				BordaCimaBaixo.setBorder(Border.BOTTOM,BorderLineStyle.THIN,Colour.BLACK);	 	 
				return BordaCimaBaixo;
			   
		} catch (Exception e) {
			// TODO: Desconsiderar
		}
		return null;
	}
	  public WritableCellFormat BordaCimaBaixoDireita(){
		   try {
			    WritableCellFormat BordaCimaBaixoDireita = new WritableCellFormat();
				BordaCimaBaixoDireita.setBorder(Border.TOP,BorderLineStyle.THIN,Colour.BLACK);
				BordaCimaBaixoDireita.setBorder(Border.BOTTOM,BorderLineStyle.THIN,Colour.BLACK);	    
				BordaCimaBaixoDireita.setBorder(Border.RIGHT,BorderLineStyle.THIN,Colour.BLACK);	   	 
				return BordaCimaBaixoDireita;
			   
		} catch (Exception e) {
			// TODO: Desconsiderar
		}
		return null;
	}

}
