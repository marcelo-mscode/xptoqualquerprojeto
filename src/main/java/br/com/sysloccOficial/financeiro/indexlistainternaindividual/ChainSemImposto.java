package br.com.sysloccOficial.financeiro.indexlistainternaindividual;

import java.math.BigDecimal;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.producao.ProducaoPDespesas;

public class ChainSemImposto implements ChainDespesaInterface{
	
	@Autowired private Utilitaria util;
	private ChainDespesaInterface proximo;
	private ProducaoPDespesas despesa;
	
	public ChainSemImposto(){}
	public ChainSemImposto(ProducaoPDespesas despesa){
		this.despesa = despesa;
	}
	
	
	@Override
	public boolean tipo(boolean tipo) {
		
		if(tipo == false && despesa.isCustoLocco() == false){
			despesa.setFatDireto(new BigDecimal(despesa.getValorTransiente()));
			return true;
		}else{
			return proximo.tipo(tipo);
		}
	}

	@Override
	public void setProximo(ChainDespesaInterface proximo) {
		this.proximo = proximo;
	}
	

}
