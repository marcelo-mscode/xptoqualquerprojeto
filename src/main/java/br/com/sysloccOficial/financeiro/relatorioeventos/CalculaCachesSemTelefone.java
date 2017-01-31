package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.model.CachePadrao;

@Repository
@Transactional
public class CalculaCachesSemTelefone {
	
	@Autowired RelatorioEventoDAO relatorioDAO;
	
	private BigDecimal totalDiferencaSemTelefone;

	private List<CachePadrao> listaRelatorioCaches;

	public CalculaCachesSemTelefone(){}
	
	public CalculaCachesSemTelefone(BigDecimal totalDiferencaSemTelefone,List<CachePadrao> listaRelatorioCaches){
		this.totalDiferencaSemTelefone = totalDiferencaSemTelefone;
		this.listaRelatorioCaches = listaRelatorioCaches;
	}
	
	
	public BigDecimal calcula(){
		BigDecimal totalCacheFuncionarios = relatorioDAO.calculaTotalCachesFuncionarios(totalDiferencaSemTelefone, listaRelatorioCaches);
		JOptionPane.showMessageDialog(null, ""+totalCacheFuncionarios);
		return totalCacheFuncionarios;
	}
	
	
	
	
	
	
	
	//pegarTotaldiferenca 
	
	//Pegar Lista Funcionarios Caches
	
	//Calcular caches Funcionarios
	
	//calcular caches Diretoria1
	
	//calcular caches Diretoria2
	
	//Devolver totalCaches
	
	

}
