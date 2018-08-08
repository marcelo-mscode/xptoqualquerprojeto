package br.com.sysloccOficial.controllerProducao.salvaItem;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.producao.ProducaoP;
import br.com.sysloccOficial.model.producao.ValorPgtoAux;

@Service
public class PreencheValorPagamentosServices {

	@Autowired private Utilitaria util;
	
	public void preencheValoresPagamentos(ProducaoP producao, Integer parcelas,List<ValorPgtoAux> pagamentos) {
		
		for(int i = 0; i <  parcelas;i++){
			
			BigDecimal valorItem = new BigDecimal("0.00");
			
			ValorPgtoAux valor = new ValorPgtoAux();
		
			valor.setParcela(producao.getParcela().get(i));
			valor.setPrazo(producao.getPrazo().get(i));
			valor.setData(util.formataDatasStringParaCalendar(producao.getData().get(i)+" 08:00"));
			
			if(producao.isTemContratacao() == true){
				
				if(producao.isTemMesmoFornecedor() == true){
					valorItem = util.valoresEmReais(producao.getValorDePagamentoContratacaoTrans());
				}
				if(producao.isTemOutroFornecedor() == true){
					valorItem = util.valoresEmReais(producao.getValorContratacaoTrans());
				}
			}else{
				valorItem = util.valoresEmReais(producao.getValor().get(i));
			}
		
			valor.setValor(valorItem);
			pagamentos.add(valor);
		}
	}
	
}
