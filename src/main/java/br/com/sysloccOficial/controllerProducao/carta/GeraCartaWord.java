package br.com.sysloccOficial.controllerProducao.carta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.sysloccOficial.conf.UtilitariaConversoes;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.conf.UtilitariaValores;
import br.com.sysloccOficial.controllerProducao.AuxProducao;
import br.com.sysloccOficial.model.CadastroLocco;
import br.com.sysloccOficial.model.CartaContratacao;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.producao.CartaContFornecedor;
import br.com.sysloccOficial.model.producao.ObjetoApoioValorPgto;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Component
public class GeraCartaWord {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private UtilitariaConversoes util;
	@Autowired private UtilitariaValores utilvalores;
	@Autowired private UtilitariaDatas utilData;
	
	public void formatoWord(CartaContFornecedor cartaContratacao,String nomeCaminhhoCarta,List<ObjetoApoioValorPgto> pgtoLista) throws IOException{
		   Lista lista = manager.find(Lista.class, cartaContratacao.getIdLista());	
		   String empresaDoJob = lista.getIdJob().getEmpresa().getEmpresa();
		   
		    String tst = "from ProducaoP p where idlista = "+cartaContratacao.getIdLista()+" and idEmpFornecedor= "+cartaContratacao.getFornecedor().getIdEmpresa();
		    Query qw = manager.createQuery(tst);
		    List<ProducaoP> ff = qw.getResultList();
		
		   CartaContratacao cartaApoio = manager.find(CartaContratacao.class, 1);

//		   CadastroLocco cadastroLocco = manager.find(CadastroLocco.class, 1);	
		   XWPFDocument document= new XWPFDocument(); 
		   //Write the Document in file system
		   FileOutputStream out = new FileOutputStream(new File(nomeCaminhhoCarta));
		   
  		   //create Paragraph
		   XWPFParagraph paragraph = document.createParagraph();
		   
		   
		   
		   XWPFRun imagemLogo = paragraph.createRun();
		   XWPFRun dataHoje = paragraph.createRun();
		   XWPFRun a = paragraph.createRun();
		   XWPFRun empresa = paragraph.createRun();
		   XWPFRun fornecedorContratado = paragraph.createRun();
		   XWPFRun contato = paragraph.createRun();
		   XWPFRun email = paragraph.createRun();
		   
		   XWPFRun prezado = paragraph.createRun();
		   XWPFRun textoCabelhoEvento = paragraph.createRun();
		   XWPFRun tipoCarta = paragraph.createRun();
		   XWPFRun realizaEvento = paragraph.createRun();
		   XWPFRun evento = paragraph.createRun();
		   XWPFRun eventoRealizacao = paragraph.createRun();
		   XWPFRun eventoRealizacaoEm = paragraph.createRun();
		   XWPFRun eventoConforme = paragraph.createRun();
		   
		   
		   ArrayList<XWPFRun>cab = new ArrayList<XWPFRun>();
		   cab.add(tipoCarta);
		   cab.add(realizaEvento);
		   cab.add(prezado);
		   cab.add(textoCabelhoEvento);
		   cab.add(evento);
		   cab.add(eventoRealizacao);
		   cab.add(eventoRealizacaoEm);
		   cab.add(eventoConforme);
		   cab.add(dataHoje);
		   cab.add(a);
		   cab.add(empresa);
		   cab.add(fornecedorContratado);
		   cab.add(contato);
		   cab.add(email);
		   
		   XWPFRun equipeLoccoAgencia = paragraph.createRun();
		   XWPFRun observacoes = null;
		   if(cartaContratacao.getObservacoes().equals(null) || cartaContratacao.getObservacoes().equals("")){}else{
			   observacoes = paragraph.createRun();
		   }
		   
		   
		   XWPFRun dataEntrega = paragraph.createRun();
		   XWPFRun localEntrega = paragraph.createRun();
		   XWPFRun descricao = paragraph.createRun();
		   XWPFRun descricaoItem = paragraph.createRun();
//		   XWPFRun outros = paragraph.createRun();
//   	   XWPFRun condicoes = paragraph.createRun();
		   
		   
		   
		   
		  
		   
/* ----------------------- Descricao dos Itens ------------------------------- */		   
		   //XWPFRun descricaoItens = paragraph.createRun();
/* ----------------------- Descricao dos Itens ------------------------------- */		   
		   XWPFRun valorTotal = paragraph.createRun();
		   XWPFRun faturamento = paragraph.createRun();
		   List<XWPFRun> item =  new ArrayList<XWPFRun>();
		   Integer parcelamento = 0;
		   for(int i =0;i<pgtoLista.size();i++){
			   item.add(paragraph.createRun());
			   parcelamento = parcelamento+1;
		   }
		   
		   
		   
		   XWPFRun dadosEmissao = paragraph.createRun();
		   XWPFRun emailParaEmissao = paragraph.createRun();
		   XWPFRun dadosLocco = paragraph.createRun();
		   
		   XWPFRun informacoesImportantes = paragraph.createRun();
		   XWPFRun textoComum = paragraph.createRun();
		   
		   XWPFParagraph paragraphLocco = document.createParagraph();
		   paragraphLocco.setAlignment(ParagraphAlignment.RIGHT);
		   XWPFRun cadLocco = paragraphLocco.createRun();
		   
		   imagemLogo(imagemLogo);

/* ----------------------- cabecalho evento ------------------------------- */		   
		   cabecalhoEvento(cartaContratacao,cab, cartaApoio);
/* ----------------------- Descricao evento ------------------------------- */		   
		   equipeLoccoAgencia(equipeLoccoAgencia,cartaApoio);
		   
/* ----------------------- Descricao dos Itens ------------------------------- */		   
		   if(cartaContratacao.getObservacoes().equals(null) || cartaContratacao.getObservacoes().equals("")){}
		   else{ observacoes(observacoes,cartaContratacao);}
		   
		   
		   dataEntrega(dataEntrega,cartaContratacao,cartaApoio);
		   localEntrega(localEntrega,cartaApoio,cartaContratacao);

		   descricao(descricao,descricaoItem,cartaApoio,cartaContratacao,ff);
		   
		   faturamento(item,faturamento,cartaApoio, parcelamento,pgtoLista);
//		   outros(outros,ff);
		   
	//	   item(item,cartaContratacao,pgtoLista);
		   
/* ----------------------- Valor Total --------------------------------------- */		   
		   valorTotal(valorTotal,cartaApoio,cartaContratacao);
/* ----------------------- Faturamento --------------------------------------- */		   
//		   faturamento(faturamento,cartaContratacao);					
/* ----------------------- Dados Locco Emissao Nota -------------------------- */		   
		   dadosLoccoEmissaoNota(dadosEmissao,emailParaEmissao,cartaApoio);
		   dadosLocco(dadosLocco,cartaApoio);
		   
/* ----------------------- informacoesImportantes ---------------------------- */		   
		   informacoesImportantes(informacoesImportantes, cartaApoio,empresaDoJob);
/* ----------------------- Rodapé -------------------------------------------- */		   
		   rodape(textoComum,cartaApoio,cartaContratacao);
/* ----------------------- Cadastro Locco ------------------------------------ */		   
		   cadastroLocco(cadLocco,cartaApoio);
/* --------------------------------------------------------------------------- */		   
		   
		   document.write(out);
		   out.close();
	}
	private void imagemLogo(XWPFRun imagemLogo){
     
     String imgFile = "C:/SYSLOC/locco.png";
/*        String imgFile = "C:/SYSLOC/locco.jpg";
*/        FileInputStream is = null;
		try {
			is = new FileInputStream(imgFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
     try {
			imagemLogo.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(135), Units.toEMU(45));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
     try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

     imagemLogo.addBreak();
	}
	
	
	private void localEntrega(XWPFRun localEntrega,CartaContratacao cartaContratacao, CartaContFornecedor cartaForn) {
		localEntrega.setUnderline(UnderlinePatterns.SINGLE);
		localEntrega.setColor("ff0000");
		localEntrega.setBold(true);
		localEntrega.setText(cartaContratacao.getLocalEntrega()+" "+cartaForn.getLocalEntregaTexto());
		localEntrega.addBreak();
		localEntrega.addBreak();
	}
	private void dataEntrega(XWPFRun dataEntrega,CartaContFornecedor cartaForn,CartaContratacao cartaContratacao) {
		dataEntrega.setUnderline(UnderlinePatterns.SINGLE);
		dataEntrega.setColor("ff0000");
		dataEntrega.setBold(true);
		dataEntrega.addBreak();
		dataEntrega.setText(cartaContratacao.getDataEntregaTexto()+" "+cartaForn.getDataEntrega());
		dataEntrega.addBreak();
	}


	private void item(List<XWPFRun> item, CartaContFornecedor cartaContratacao,List<ObjetoApoioValorPgto> pgtoLista) {

		for(int i =0;i < pgtoLista.size();i++){

			item.get(i).addBreak();
			item.get(i).setUnderline(UnderlinePatterns.SINGLE);
			item.get(i).setColor("ff0000");
			item.get(i).setBold(true);
			item.get(i).setText("Parcela: "+pgtoLista.get(i).getParcela().toString());
			item.get(i).addBreak();
			item.get(i).setText("Para: "+pgtoLista.get(i).getDataPagar().toString()+" ("+pgtoLista.get(i).getDias()+" ),");
			item.get(i).addBreak();
			
			BigDecimal valor = utilvalores.valoresEmReais(pgtoLista.get(i).getValorPagar());
			
			String valorExtenso = utilvalores.converteValoresPorExtenso(valor);
			
			item.get(i).setText("Valor: R$"+pgtoLista.get(i).getValorPagar()+" ( "+valorExtenso+" )");
			item.get(i).addBreak();
			item.get(i).addBreak();
			
		}
		
	}


	private void observacoes(XWPFRun observacoes,CartaContFornecedor cartaContratacao) {
		observacoes.addBreak();
		observacoes.setFontSize(10);
		observacoes.setBold(true);
		observacoes.setText(cartaContratacao.getObservacoes());
		observacoes.addBreak();
		observacoes.addBreak();
	}

	private void outros(XWPFRun outros,List<ProducaoP> ff ){
		
		outros.addBreak();
		outros.setColor("ff0000");
		outros.setUnderline(UnderlinePatterns.SINGLE);
		outros.setFontSize(9);
		
		AuxProducao auxproducao = new AuxProducao();
		
		BigDecimal totalImposto = auxproducao.somaApenasValoresImpostoComContratacao(ff);
		
		String valorExtenso = utilvalores.converteValoresPorExtenso(totalImposto);
		
		String conv = util.ConverteDolarParaReal(totalImposto.toString());
		outros.setText("Outros valores: R$"+conv+ " ( "+valorExtenso+" )");
		outros.addBreak();
	}
	
	
	private void descricao(XWPFRun descricao,XWPFRun descricaoItem, CartaContratacao cartaContratacao, CartaContFornecedor carta,List<ProducaoP> ff) {
		
		descricao.setBold(true);
		descricao.setText(cartaContratacao.getDescricao());
		descricao.addBreak();
		
		descricaoItem.setColor("000000");
		descricaoItem.setUnderline(UnderlinePatterns.SINGLE);
		descricaoItem.setFontSize(9);
		
		for(int i=0;i<ff.size();i++){
		
		descricaoItem.addBreak();	
		descricaoItem.setText("Item: "+ff.get(i).getProdutoGrupo().getProduto());
		descricaoItem.addBreak();	
		descricaoItem.setText("Quantidade: "+ ff.get(i).getProdutoGrupo().getQuantidade()*
				ff.get(i).getProdutoGrupo().getQuantidade2()*
				ff.get(i).getProdutoGrupo().getDiarias());
		descricaoItem.addBreak();	
		descricaoItem.setText("Descrição: "+ff.get(i).getProdutoGrupo().getIdGrupo().getInformacoes());
		descricaoItem.addBreak();	
		
		BigDecimal valorItem = ff.get(i).getValorItem();
		BigDecimal diferenca = ff.get(i).getDiferenca();
	
		//BigDecimal valorFinal = valorItem.subtract(diferenca);

		BigDecimal valorFinal = valorItem;

		String valorConv = "";
		String valorExtenso = "";

// ------------------------------------------------------------------------------------------ //
// Usado para quando tinha valor de contratacao com mesmo fornecedor
/*
 
  		if(ff.get(i).isTemContratacao() == false){
			valorConv = util.ConverteDolarParaReal(valorFinal.toString());
			valorExtenso = utilvalores.converteValoresPorExtenso(valorFinal);
		}
		if(ff.get(i).isTemContratacao() == true && ff.get(i).isTemMesmoFornecedor() == true){
			valorConv = util.ConverteDolarParaReal(ff.get(i).getValorContratacao().toString());
			valorExtenso = utilvalores.converteValoresPorExtenso(ff.get(i).getValorContratacao());
		}

		if(ff.get(i).isTemContratacao() == true && ff.get(i).isTemOutroFornecedor() == true){
			valorConv = util.ConverteDolarParaReal(ff.get(i).getValorContratacao().toString());
			valorExtenso = utilvalores.converteValoresPorExtenso(ff.get(i).getValorContratacao());
		}

*/
		
		valorConv = util.ConverteDolarParaReal(valorFinal.toString());
		valorExtenso = utilvalores.converteValoresPorExtenso(valorFinal);
		
		descricaoItem.setText("Valor: R$"+valorConv+" ( "+valorExtenso+" )");
		descricaoItem.addBreak();	
		}
		
		
	}

	public void cabecalhoEvento(CartaContFornecedor carta,ArrayList<XWPFRun>ct, CartaContratacao cartaApoio){

	 ct.get(0).setBold(true);
	 ct.get(4).setBold(true);
	 ct.get(6).setBold(true);
	 ct.get(9).setBold(true);
	 ct.get(10).setBold(true);
	
	 ct.get(0).setFontSize(10);
	 ct.get(1).setFontSize(10);
	 ct.get(2).setFontSize(10);
	 ct.get(3).setFontSize(10);
	 ct.get(4).setFontSize(10);
	 ct.get(5).setFontSize(10);
	 ct.get(6).setFontSize(10);
	 ct.get(7).setFontSize(10);
	
	 ct.get(2).setBold(true);
	 ct.get(2).addBreak();
	 ct.get(2).setText(cartaApoio.getEmpCab1()+" "+carta.getContatoResponsavel());
	 ct.get(2).addBreak();
	 ct.get(3).setText(cartaApoio.getEmpCab2()+" ");
	 ct.get(0).setText(carta.getEmpCab3()+" ");
	 ct.get(1).setText(cartaApoio.getEmpCab4());
	 ct.get(4).setText(" "+carta.getEmpCab5());
	 ct.get(5).setText(" "+cartaApoio.getEmpCab6());
	 ct.get(6).setText(" "+carta.getEmpCab7());
	 ct.get(7).setText(", "+cartaApoio.getEmpCab8());
	
	 ct.get(8).addBreak();
	 ct.get(8).setText(carta.getDataCabecalho());
	 ct.get(8).addBreak();
	 ct.get(9).setText(cartaApoio.getA());
	 ct.get(9).addBreak();
	 ct.get(10).setText(carta.getEmpresa().toUpperCase()+" ");
	 ct.get(11).setBold(true);
	 ct.get(11).setText(carta.getFornecedorContratado());
	 ct.get(11).addBreak();
	 ct.get(12).setText(carta.getFornecedorContato());
	 ct.get(12).addBreak();
	 ct.get(13).setText(carta.getFornecedorContatoEmail());
	 ct.get(13).addBreak();
  
	}	
	public void equipeLoccoAgencia(XWPFRun eL,CartaContratacao carta){
		eL.setFontSize(10);
		eL.addBreak();
		eL.setText(carta.getEmpCab9());
		eL.addBreak();
	}
	
	public void valorTotal(XWPFRun vt,CartaContratacao cartaAporio, CartaContFornecedor carta){
		vt.addBreak();
		vt.setFontSize(10);
		vt.setColor("ff0000");
		vt.setBold(true);
		vt.setUnderline(UnderlinePatterns.SINGLE);
		vt.setText(cartaAporio.getValorTotalTexto());
		vt.setText(" "+carta.getValorTotal());
		vt.setText(" "+cartaAporio.getValorTotalTexto2());
		vt.addBreak();
	}
	
	public XWPFRun faturamento(List<XWPFRun> item,XWPFRun faturamento,CartaContratacao carta, Integer parcelamento,List<ObjetoApoioValorPgto> pgtoLista){
		faturamento.addBreak();
		faturamento.setColor("000000");
//		faturamento.setUnderline(UnderlinePatterns.SINGLE);
		faturamento.setFontSize(10);
		faturamento.setBold(true);
		faturamento.setText(carta.getCondicaofaturamento());
		faturamento.addBreak();
		faturamento.setText("Parcelado em: "+parcelamento+"x");
		faturamento.setText(" "+carta.getDataFaturamento());
		faturamento.addBreak();
		
		
		for(int i =0;i < pgtoLista.size();i++){
			
			item.get(i).addBreak();
			item.get(i).setColor("ff0000");
			item.get(i).setUnderline(UnderlinePatterns.SINGLE);
			item.get(i).setFontSize(9);
			item.get(i).setText("Parcela: "+pgtoLista.get(i).getParcela().toString());
			item.get(i).addBreak();
			
			String data="";
			try {
				data = utilData.converteDateParaString(pgtoLista.get(i).getDataPagar());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			item.get(i).setText("Para: "+data+" ( "+pgtoLista.get(i).getDias()+" ),");
			item.get(i).addBreak();
			item.get(i).setText("Valor: R$"+pgtoLista.get(i).getValorPagar());
			item.get(i).addBreak();
		}
		
		return null;
	}
	
	public XWPFRun dadosLoccoEmissaoNota(XWPFRun dadosEmissao,XWPFRun emailParaEmissao,CartaContratacao cartaContratacao){
		dadosEmissao.addBreak();
		dadosEmissao.setBold(true);
		dadosEmissao.setUnderline(UnderlinePatterns.SINGLE);
		emailParaEmissao.setUnderline(UnderlinePatterns.SINGLE);
		dadosEmissao.setColor("ff0000");
		emailParaEmissao.setColor("0066ff");
		dadosEmissao.setText(cartaContratacao.getDadosEmissaoNota());
		dadosEmissao.addBreak();
		emailParaEmissao.setText(cartaContratacao.getDadosEmissaoNotaResponsavel());
		emailParaEmissao.addBreak();
		return null;
	}

	public XWPFRun dadosLocco(XWPFRun dadosLocco,CartaContratacao cartaContratacao){
		dadosLocco.setText(cartaContratacao.getLoccoRazaoSocial());
		dadosLocco.addBreak();
		dadosLocco.setText(cartaContratacao.getLoccoEndereco());
		dadosLocco.addBreak();
		dadosLocco.setText(cartaContratacao.getLoccoCnpj());
		dadosLocco.addBreak();
		return dadosLocco;
	}
	
	
	
	public XWPFRun informacoesImportantes(XWPFRun informacoesImportantes,CartaContratacao cartaContratacao,String empresaDoJob){
		
		informacoesImportantes.addBreak();
		informacoesImportantes.setText(cartaContratacao.getInformacoesImportantes().replace("/GTAV-MR", empresaDoJob));
		informacoesImportantes.setFontSize(9);
		informacoesImportantes.setFontFamily("Calibri");
		informacoesImportantes.setBold(true);
		informacoesImportantes.setUnderline(UnderlinePatterns.SINGLE);
		informacoesImportantes.addBreak();
		return informacoesImportantes;
	}
	
	public XWPFRun rodape(XWPFRun textoComum,CartaContratacao cartaContratacao, CartaContFornecedor carta){
		   textoComum.setFontSize(10);
		   textoComum.setFontFamily("Calibri");
		   textoComum.addBreak();
		   textoComum.setText(cartaContratacao.getEsclarecimentos());
		   textoComum.addBreak();
		   textoComum.addBreak();
		   textoComum.setText(cartaContratacao.getAtenciosamente());
		   textoComum.addBreak();
		   textoComum.setText(carta.getResponsavelContratacao());
		   textoComum.addBreak();
		   textoComum.addBreak();
		   textoComum.setText(cartaContratacao.getDeAcordo());
		   textoComum.addBreak();
		   textoComum.addBreak();
		   textoComum.setText("_____________________________________");	
		   textoComum.addBreak();
		return textoComum;
	}
		
	public XWPFRun cadastroLocco(XWPFRun cadLocco,CartaContratacao cartaContratacao){
		   CadastroLocco cadastroLocco = manager.find(CadastroLocco.class, 1);
		   cadLocco.setFontSize(9);
		   cadLocco.setColor("808080");	
		   cadLocco.setText(cadastroLocco.getEndereco());
		   cadLocco.addBreak();
		   cadLocco.setText(cadastroLocco.getCidade()+"/"+cadastroLocco.getUf()+" - CEP: "+cadastroLocco.getCep());
		   cadLocco.addBreak();
		   cadLocco.setText("Tel.: "+cadastroLocco.getTelef());
		   cadLocco.addBreak();
		   cadLocco.setText(cadastroLocco.getSite());
		   cadLocco.setFontFamily("Calibri");
		return cadLocco;
	}
	
	
}
