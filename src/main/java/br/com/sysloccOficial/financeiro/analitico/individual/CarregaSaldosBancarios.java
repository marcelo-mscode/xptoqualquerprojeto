package br.com.sysloccOficial.financeiro.analitico.individual;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualMovimentoFinanceiro;
import br.com.sysloccOficial.financeiro.model.AnaliticoTotalBancos;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancos;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancosSaidas;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancosSaldoAnterior;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancosTarifas;

public class CarregaSaldosBancarios {

	
	@Autowired AnaliticoIndividualMovimentoFinanceiro analiticoMovFinanceiroDAO;
	
	
	public Integer pegaIdAnalitico (String mes, String ano){
		return analiticoMovFinanceiroDAO.pegaIdAnalitico(mes, ano);
	}
	
	
	public CarregaSaldosBancarios() {
		super();
	}

	protected AnaliticoTotalBancos carregaSaldosBancarios(Integer idAnalitico, int idBanco) {
	
		AnaliticoTotalBancos novo = new AnaliticoTotalBancos();
		
		HashSet<MovimentacaoBancos> movBancosCreditos = analiticoMovFinanceiroDAO.totalEntradasBanco(idAnalitico,idBanco);
		MovimentacaoBancosSaldoAnterior movBancosSaldoAnterior = analiticoMovFinanceiroDAO.totalSaldoAnteriorBanco(idAnalitico,idBanco);
		HashSet<MovimentacaoBancosSaidas> movBancosSaidas = analiticoMovFinanceiroDAO.totalSaidasBanco(idAnalitico,idBanco);
		HashSet<MovimentacaoBancosTarifas> movBancosTarifas = analiticoMovFinanceiroDAO.totalTarifasBanco(idAnalitico,idBanco);
		
		novo.setNomeBanco("Itau");
		novo.setDataAberturaCaixa(movBancosSaldoAnterior.getDataAberturaCaixa());
		novo.setValorAbertura(movBancosSaldoAnterior.getValorAbertura());
		novo.setTotalDebitos(movBancosSaidas);
		novo.setTotalTarifas(movBancosTarifas);
		novo.setTotalCreditos(movBancosCreditos);
		novo.setDataFechamentoCaixa(movBancosSaldoAnterior.getDataAberturaFechamento());
		novo.setValoresDefinir(movBancosSaldoAnterior.getValorAlternativo());
		return novo;
	}

}