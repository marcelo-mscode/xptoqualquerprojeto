package br.com.sysloccOficial.criacao.controllerCriacao.relatorio.apoio;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.CriacaoItemHistorico;
import br.com.sysloccOficial.model.CriacaoItemPendencia;

@Component
public class ApoioRelatorioSomaTempo {

	@Autowired private Utilitaria util;
	
	
	
	public Integer somaTempo(Calendar inicio, Calendar fim){
		   Integer pegaHora;
		   Long pegaaHora = Utilitaria.diferencaDatasEmMilisegundos(inicio.getTime(), fim.getTime());
		   pegaHora = util.converteLongParaInteger(pegaaHora);
		   return pegaHora;
	}
	
	public Integer somaPendenciaItem(List<CriacaoItemPendencia> histPendItens,Integer somaHora) {
		Integer pegaHoraPendencica;

		if(histPendItens.isEmpty()){
			
		}else{
			/**
			 * Somar horas de historico Pendencia + historico do item
			 */
			for(int i = 0; i < histPendItens.size();i++ ){
				
				for(int j = 0; j < histPendItens.get(i).getCriacaoItemPendenciaHistorico().size();j++){
					
					if(histPendItens.get(i).getCriacaoItemPendenciaHistorico().get(j).getDataTermino() != null){
						Long pegaaHora =  Utilitaria.diferencaDatasEmMilisegundos(
								histPendItens.get(i).getCriacaoItemPendenciaHistorico().get(j).getDataInicio().getTime()
								,histPendItens.get(i).getCriacaoItemPendenciaHistorico().get(j).getDataTermino().getTime());
						pegaHoraPendencica = util.converteLongParaInteger(pegaaHora);
						somaHora = somaHora + pegaHoraPendencica;
					}else{
					
					}
				}
				
			}
		}
		return somaHora;
	}

	public Integer somaHoraHistoricoItem(List<CriacaoItemHistorico> historicoItens, Integer somaHora) {
		Integer pegaHora;
		if(historicoItens.isEmpty()){
			
		}else{
			for (CriacaoItemHistorico criacaoItemHistorico : historicoItens) {
				if(criacaoItemHistorico.getDataTermino() != null){
					pegaHora = somaTempo(criacaoItemHistorico.getDataInicio(),criacaoItemHistorico.getDataTermino());
					somaHora = somaHora + pegaHora;
				}else{
					//	
				}
			}
		}
		return somaHora;
	}
	
	
	
}
