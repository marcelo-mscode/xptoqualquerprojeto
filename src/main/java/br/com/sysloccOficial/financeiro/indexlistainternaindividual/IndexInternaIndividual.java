package br.com.sysloccOficial.financeiro.indexlistainternaindividual;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.InternaIndividualDAO;
import br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor.CalculaValorFornecedor;
import br.com.sysloccOficial.financeiro.model.CalculoValoresInterna;
import br.com.sysloccOficial.model.DesIntFinanc;
import br.com.sysloccOficial.model.InfoInterna;
import br.com.sysloccOficial.model.OrcamentoFornecedor;
import br.com.sysloccOficial.model.producao.ProducaoP;
import br.com.sysloccOficial.model.producao.ProducaoPDespesas;

@Controller
public class IndexInternaIndividual {

	@Autowired InternaIndividualDAO internaIndividualDAO;
	
	
	@RequestMapping("internaIndividual")
	public ModelAndView internaIndividual(Integer idLista){
		ModelAndView MV = new ModelAndView("financeiro/interna/internaindividual/internindividual");
		
		InfoInterna infoInterna =  internaIndividualDAO.infoInterna(idLista);
		
		Set<ProducaoP> listaProducaoP = internaIndividualDAO.internaIndividual(idLista);

		
		List<ProducaoP> listaProducaoPTeste = internaIndividualDAO.internaIndividualTeste(idLista);

		
		
		BigDecimal impostoDaLista = internaIndividualDAO.totalImpostoDaLista(idLista);
		
		List<ProducaoP> listaItensIndividuais = new ArrayList<ProducaoP>(listaProducaoP);
		
		
		CalculoValoresInterna calculaValoresListaIndividual = new CalculoValoresInterna(listaItensIndividuais,idLista,
																      impostoDaLista,infoInterna.getImpostoInterna());
		
		List<Object[]> listaDeIdsFornecedores = internaIndividualDAO.listaDeIdsFornecedores(idLista);
		
		
		boolean verif = false;
		for (int i = 0; i < listaProducaoPTeste.size(); i++) {
			if(listaProducaoPTeste.get(i).getRowSpan() != null){
				verif = true;
			}
			//listaProducaoPTeste.get(i).setValorFornecedor(CalculaValorFornecedor.calculaValores(listaProducaoPTeste.get(i)));
			//listaProducaoPTeste.get(i).setDiferencaParaLocco(new BigDecimal("54.459"));
		}
		
		
		
		if(verif == false){
			internaIndividualDAO.logicaCalculaRowSpanTabela(listaProducaoPTeste, listaDeIdsFornecedores);
		}
		
		MV.addObject("infoInterna", infoInterna);
		
		MV.addObject("infoColunas", listaDeIdsFornecedores);
		

		
		
		
		MV.addObject("itensInterna",listaProducaoPTeste);

	
		
		
		
		MV.addObject("infoLista",internaIndividualDAO.infoLista(idLista));
		MV.addObject("calculadora",calculaValoresListaIndividual);
		MV.addObject("impostoLista",impostoDaLista);
		
		List<ProducaoPDespesas> lista = internaIndividualDAO.listaDespesasPorIdLista();
		MV.addObject("despesas", lista);
		
		BigDecimal totalDespesas = internaIndividualDAO.totalDespesas(idLista);
		MV.addObject("totalDepesas", totalDespesas);
		
		List<DesIntFinanc> d = internaIndividualDAO.despTeste(idLista);
		MV.addObject("teste", d);
		
		return MV;
	}
	
	@RequestMapping("despesas")
	public String despesa(ProducaoPDespesas despesas) throws ParseException{
		internaIndividualDAO.salvaDespesa(despesas);
		return "redirect:internaIndividual?idLista="+despesas.getIdLista();
	}
	
	@RequestMapping("valorF")
	@ResponseBody
	public String valorF(String valor, Integer idProducao){
		try {
		internaIndividualDAO.editaValorF(valor,idProducao);
			return "ok";
		} catch (Exception e) {
			return "erro";
		}
	}
	
	@RequestMapping("observacao")
	@ResponseBody
	public String observacao(String texto, Integer idProducao){
		String teste = texto.replace("XXXX", "\n");
		try {
			internaIndividualDAO.editaObservacao(teste, idProducao);
			return "ok";
		} catch (Exception e) {
			return "erro";
		}
	}

	@RequestMapping("difImpostoProducaoP")
	@ResponseBody
	public String difImpostoProducaoP(String texto, Integer idProducao){
		String teste = texto.replace("XXXX", "\n");
		try {
			internaIndividualDAO.difImpostoProducaoP(teste, idProducao);
			return "ok";
		} catch (Exception e) {
			return "erro";
		}
	}
	
	@RequestMapping("nfRecibo")
	@ResponseBody
	public String nfRecibo(String texto, Integer idProducao){
		String teste = texto.replace("XXXX", "\n");
		try {
			internaIndividualDAO.nfRecibo(teste, idProducao);
			return "ok";
		} catch (Exception e) {
			return "erro";
		}
	}
	
	@RequestMapping("dadosBancarios")
	@ResponseBody
	public String dadosBancarios(String texto, Integer idProducao){
		String teste = texto.replace("XXXX", "\n");
		try {
	  	internaIndividualDAO.dadosBancarios(teste, idProducao);
			return "ok";
		} catch (Exception e) {
			return "erro";
		}
	}

	@RequestMapping("infoInternaNf")
	@ResponseBody
	public String infoInternaNf(String texto, Integer idProducao){
		try {
			internaIndividualDAO.nfinterna(texto, idProducao);
			return "ok";
		} catch (Exception e) {
			return "erro";
		}
	}
	
	@RequestMapping("infoInternaData")
	@ResponseBody
	public String infoInterna(String texto, Integer idProducao){
	
		try {
			internaIndividualDAO.dataPagInterna(texto, idProducao);
			return "ok";
		} catch (Exception e) {
			System.out.println("Insira uma data válida: "+e);
//			JOptionPane.showMessageDialog(null, ""+e);
			return "erro";
		}
	}
	
	@RequestMapping("impostoInterna")
	@ResponseBody
	public String impostoInterna(String texto, Integer idProducao){
		
		try {
			internaIndividualDAO.impostoInterna(texto, idProducao);
			return "ok";
		} catch (Exception e) {
			System.out.println("Insira um imposto válido: "+e);
			return "erro";
		}
		
	}
	
	
	/*@RequestMapping("salvaOrcamentoFornecedor")
	@ResponseBody
	private String salvaOrcamentoFornecedor(String valor, Integer idFornecedor,Integer idGrupo){
		internaIndividualDAO.salvaNovoOrcamento(valor, idFornecedor,idGrupo);
		return "ok";
	}
	
	
	*/
	
	
	
	public <E> List<E> removerDuplicados(List<E> combinacoes) {
		for (int i = 0; i < combinacoes.size(); i++) {
			Object a = combinacoes.get(i);
			for (int j = i+1; j < combinacoes.size(); j++) {
				Object b = combinacoes.get(j);
				if (a.equals(b)) {
					combinacoes.remove(j);
					j--;
				}
			}
		}
		return combinacoes;
	}
	
	
	
}
