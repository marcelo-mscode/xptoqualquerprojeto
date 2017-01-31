package br.com.sysloccOficial.criacao.controllerCriacao.relatorio.apoio;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.criacao.controllerCriacao.dao.RelatorioDAO;
import br.com.sysloccOficial.model.CriacaoItemHistorico;
import br.com.sysloccOficial.model.CriacaoItemPendencia;
import br.com.sysloccOficial.model.CriacaoLista;
import br.com.sysloccOficial.model.criacao.CalculoHorasLista;

@Component
public class ApoioRelatorio {

	@Autowired private RelatorioDAO relatorioDAO;
	@Autowired private ApoioRelatorioSomaTempo relatorioSomaTempo;
	@Autowired private Utilitaria util;
	
	public List<String[]> selecionaMesPorCriacaoLista(String termo) throws ParseException {
		List<String> dataMes = relatorioDAO.mesCriacao(termo);
		List<String[]>mes = new ArrayList<String[]>();
	
		for(int i =0;i< dataMes.size();i++){
			String[]  novo = new String[2];
			novo[0] = dataMes.get(i);
			novo[1] = UtilitariaDatas.converterInteiroParaMes(dataMes.get(i));
			mes.add(novo);
		}
		
		return mes;
	}
	
    public List<CalculoHorasLista> somaTempoTotalListaIndividual(List<Integer> idsListasFechadas) throws ParseException{
    	List<CalculoHorasLista> dataFinalTeste = new ArrayList<CalculoHorasLista>();
    	double inicial = 0;
   		inicial = calculaTempoPorIdsCriacaoLista(dataFinalTeste, idsListasFechadas, inicial);
		return dataFinalTeste;
    }	

    public List<CalculoHorasLista> somaTempoVariosItens(Integer idCriacaoLista){
    	double t = 0;
    	List<CalculoHorasLista> dataFinalTeste = new ArrayList<CalculoHorasLista>();
    	List<Integer> ids = relatorioDAO.pegaIdCriacaoItemListaPorLista(idCriacaoLista); // passa o id da lista
    	
    	for (int i = 0; i < ids.size(); i++) {
    		CalculoHorasLista novo = new CalculoHorasLista();
    		t = somaTempoTotal(ids.get(i));
    		
    		novo.setTeste(util.somaTempoTotal(t));
    		novo.setIdCriacaoLista(ids.get(i));
    		dataFinalTeste.add(novo);
    		
		}
    	return dataFinalTeste;
    }
    
    /**
     * Método para pegar a soma dos itens de uma lista
     * 
     */
    public CalculoHorasLista somaTempoUmaLista(Integer idLista){
    		double t=0;
   			List<Integer> ids = relatorioDAO.pegaIdCriacaoItemListaPorLista(idLista); // passa o id da lista

   			CalculoHorasLista novo = new CalculoHorasLista();
    		for(int j =0; j < ids.size();j ++){
    			t = t + somaTempoTotal(ids.get(j));
    		}
    		
    		if(t < 60000){
	    	   t = 60000;
    		}
    		novo.setTeste(util.somaTempoTotal(t));
    		novo.setIdCriacaoLista(idLista);
    	
    	return novo;
    }
	
	/**
	 * Método para pegar o tempo de produção de cada lista
	 * 
	 * @param listas
	 * @return
	 */
    public List<CalculoHorasLista> somaTempoItensCadaLista(List<CriacaoLista> listas){
    	List<CalculoHorasLista> dataFinalTeste = new ArrayList<CalculoHorasLista>();
    	
    	for(int i =0; i < listas.size();i++){
		
    		if(listas.get(i).getTempoTotal() != null){
    			
    		}else{
    		double t=0;
   			List<Integer> ids = relatorioDAO.pegaIdCriacaoItemListaPorLista(listas.get(i).getIdCriacaoLista()); // passa o id da lista

   			CalculoHorasLista novo = new CalculoHorasLista();
    		for(int j =0; j < ids.size();j ++){
    			t = t + somaTempoTotal(ids.get(j));
    		}
    		
    		if(t < 60000){
	    			t = 60000;
    		}
    		novo.setTeste(util.somaTempoTotal(t));
    		novo.setIdCriacaoLista(listas.get(i).getIdCriacaoLista());
    		dataFinalTeste.add(novo);
    		}
    	}
    	return dataFinalTeste;
    }
    
    
    
    //Pegar esse 
    public ArrayList<Integer> somaTempoListaVariosItens(List<Integer> idCriacaoItem){
    	double t = 0;
    	
    	for (int i = 0; i < idCriacaoItem.size(); i++) {
    		t = t + somaTempoTotal(idCriacaoItem.get(i));
		}
    	
    	ArrayList<Integer> tempo = util.somaTempoTotal(t);
    	
    	return tempo;
    }

    public ArrayList<Integer> somaTempoItemIndividual(Integer idCriacaoItem){
    	double t;
    	t = somaTempoTotal(idCriacaoItem);
    	ArrayList<Integer> tempo = util.somaTempoTotal(t);
    	return tempo;
    }
    
    

	private double calculaTempoPorIdsCriacaoLista(List<CalculoHorasLista> dataFinalTeste,List<Integer> idsListasFechadas, double inicial) {
		double t;
		
		for(int i =0; i < idsListasFechadas.size();i++){
   			CalculoHorasLista novo = new CalculoHorasLista();
   			List<Integer> ids = relatorioDAO.pegaIdCriacaoItemListaPorLista(idsListasFechadas.get(i)); // passa o id da lista

    		for(int j =0; j < ids.size();j ++){
    			t = somaTempoTotal(ids.get(j));
    			inicial = inicial + t;
    		}
    		
    		novo.setTeste(util.somaTempoTotal(inicial));
    		novo.setIdCriacaoLista(idsListasFechadas.get(i));
    		dataFinalTeste.add(novo);
    		
    	}
		return inicial;
	}

    	
    public double somaTempoTotal(Integer idCriacaoItem){
		
		// Verificar se esse item teve pendencia
		// Se tiver calcular esse tempo junto com o tempo regular.
		
		List<CriacaoItemHistorico>  historicoItens = relatorioDAO.verificaHistoricoItemCompleto(idCriacaoItem);
		List<CriacaoItemPendencia>  histPendItens = relatorioDAO.verifHistItemPendenc(idCriacaoItem);
		
		Integer somaHoraItem = 0;
		Integer somaHoraPendencia = 0;
		double t = 0;
		
		//Pega as datas inicio e data do fim do item, converte para milisegundos e vai somando
		//na variável somaHora
		somaHoraItem = relatorioSomaTempo.somaHoraHistoricoItem(historicoItens, somaHoraItem);
		somaHoraPendencia = relatorioSomaTempo.somaPendenciaItem(histPendItens, somaHoraPendencia);

		t = somaHoraItem + somaHoraPendencia;
		
		return t;
	}
	
}
