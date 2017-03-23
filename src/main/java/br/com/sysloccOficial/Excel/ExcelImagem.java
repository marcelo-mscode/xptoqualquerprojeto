package br.com.sysloccOficial.Excel;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImagem {
	
	
	public static void InsereImagem(XSSFWorkbook excel,XSSFSheet aba,String caminhoImagem,double tamanhoImagem) throws FileNotFoundException, IOException {
//		InputStream is = new FileInputStream("C:/SYSLOC/upload/logoEmpresas/logoExcelAgencia.png");
		InputStream is = new FileInputStream(caminhoImagem);
	    byte[] bytes = IOUtils.toByteArray(is);
	    int pictureIdx = excel.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
	    is.close();

	    CreationHelper helper = excel.getCreationHelper();

	    // Create the drawing patriarch.  This is the top level container for all shapes. 
	    Drawing drawing = aba.createDrawingPatriarch();

		//add a picture shape
	    ClientAnchor anchor = helper.createClientAnchor();
	    //set top-left corner of the picture,
	    //subsequent call of Picture#resize() will operate relative to it
	    anchor.setAnchorType(3);
/*	    anchor.setCol2(1);
	    anchor.setRow1(1);
	    anchor.setRow2(3);
*/	    
	    Picture pict = drawing.createPicture(anchor, pictureIdx);

	    //auto-size picture relative to its top-left corner
	    pict.resize(0.5,0.5);
	    //JOptionPane.showMessageDialog(null, ""+tamanhoImagem);
	   /* pict.resize(0.67);*/
	}
	
}
