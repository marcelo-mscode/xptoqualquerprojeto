package br.com.sysloccOficial.Excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.env.JOptCommandLinePropertySource;

public class ExcelImagem {
	
	
	public static void InsereImagem(XSSFWorkbook excel,XSSFSheet aba,String caminhoImagem,double tamanhoImagem) throws FileNotFoundException, IOException {
//		InputStream is = new FileInputStream("C:/SYSLOC/upload/logoEmpresas/logoExcelAgencia.png");
		InputStream is = new FileInputStream(caminhoImagem);
	    byte[] bytes = IOUtils.toByteArray(is);
	    int pictureIdx = excel.addPicture(bytes, excel.PICTURE_TYPE_JPEG);
	    is.close();

	    CreationHelper helper = excel.getCreationHelper();

	    // Create the drawing patriarch.  This is the top level container for all shapes. 
	    Drawing drawing = aba.createDrawingPatriarch();

		//add a picture shape
	    ClientAnchor anchor = helper.createClientAnchor();
	    //set top-left corner of the picture,
	    //subsequent call of Picture#resize() will operate relative to it
	    anchor.setCol1(0);
	    anchor.setCol2(0);
	    anchor.setRow1(3);
	    
	    Picture pict = drawing.createPicture(anchor, pictureIdx);

	    //auto-size picture relative to its top-left corner
	    pict.resize(tamanhoImagem);
	    //JOptionPane.showMessageDialog(null, ""+tamanhoImagem);
	   /* pict.resize(0.67);*/
	}
	
}
