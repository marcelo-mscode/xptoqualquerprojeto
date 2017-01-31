package br.com.sysloccOficial.controllers;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

public class GeraAbstracaoExcel {
	
	//
	
	
	public WritableCellFormat alinhaAoCentroFont10(WritableFont s,Border b, BorderLineStyle ls, Colour c,boolean wrap){
		try {
			WritableCellFormat verticalAlinhamentoformat = new WritableCellFormat (s);
			verticalAlinhamentoformat.setVerticalAlignment(VerticalAlignment.CENTRE);
			verticalAlinhamentoformat.setWrap(wrap);
			verticalAlinhamentoformat.setBorder(b, ls,c);
			return verticalAlinhamentoformat;
		} catch (Exception e) {
		 //	JOptionPane.showMessageDialog(null, "Deu um erro na mãe ): ");
		}
		return null;
	}
	
}
