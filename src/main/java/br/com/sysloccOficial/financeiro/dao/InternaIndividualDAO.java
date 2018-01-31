package br.com.sysloccOficial.financeiro.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.financeiro.indexlistainternaindividual.VerificaTipoDespesa;
import br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor.CalculaValorFornecedor;
import br.com.sysloccOficial.model.DesIntFinanc;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.InfoInterna;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.OrcamentoFornecedor;
import br.com.sysloccOficial.model.producao.DifImpostoProducaoP;
import br.com.sysloccOficial.model.producao.ObsProducao;
import br.com.sysloccOficial.model.producao.ProducaoP;
import br.com.sysloccOficial.model.producao.ProducaoPDespesas;

@Repository
@Transactional
public class InternaIndividualDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	
	public Set<ProducaoP> internaIndividual(Integer idLista){
		TypedQuery<ProducaoP> q = manager.createQuery("select p from ProducaoP p join fetch p.fornecedorFinanceiro f  join fetch f.idValorPgtoFornecedor  where idLista="+idLista+" order by p.idEmpFornecedor.empresa",ProducaoP.class);
		Set<ProducaoP> itensListaInterna = new HashSet<ProducaoP>(q.getResultList());
		manager.close();
		return itensListaInterna;
	}

	public List<ProducaoP> internaIndividualTeste(Integer idLista){
		TypedQuery<ProducaoP> q = manager.createQuery("select p from ProducaoP p join fetch p.fornecedorFinanceiro f  join fetch f.idValorPgtoFornecedor  where idLista="+idLista+" order by p.idEmpFornecedor.empresa",ProducaoP.class);
		List<ProducaoP> internIndividual= q.getResultList();
		internIndividual = removerDuplicados(internIndividual);
		manager.close();
		
		
		
		/*for (int i = 0; i < internIndividual.size(); i++) {
			
			CalculaValorFornecedor.calculaValores(internIndividual.get(i));
			
		}*/
		
		return internIndividual;
	}
	
	

	public Lista infoLista(Integer idLista) {
		Lista lista = manager.find(Lista.class, idLista);
		return lista;
	}
	
	public InfoInterna infoInterna(Integer idLista){
		try {
			TypedQuery<InfoInterna> q = manager.createQuery("from InfoInterna where idLista="+idLista,InfoInterna.class);
			return q.getSingleResult();
		} catch (Exception e) {
			
			InfoInterna info = new InfoInterna();
			info.setImpostoInterna(0.0);
			
			return info;
		}
		
	}
	
	public BigDecimal totalImpostoDaLista(Integer _idLista){
		String consulta = "SELECT sum(grupoImpostoValor) from Grupo where idlista ="+_idLista+" and opcional <> 1";
		Query q = manager.createQuery(consulta,BigDecimal.class);
		return (BigDecimal) q.getSingleResult();
	}
	
	public List<ProducaoPDespesas> listaDespesasPorIdLista(){
		String consulta = "from ProducaoPDespesas";
		TypedQuery<ProducaoPDespesas> q = manager.createQuery(consulta,ProducaoPDespesas.class);
		return q.getResultList();
	}
	
	public void salvaDespesa(ProducaoPDespesas despesa) throws ParseException{
	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new java.sql.Date(format.parse(despesa.getDataVencimentoTransiente()).getTime());
		
		
		/*if(despesa.isImposto() == true){
			despesa.setFatLocco(new BigDecimal(util.formataValores(despesa.getValorTransiente())));
		}else{
			despesa.setFatDireto(new BigDecimal(util.formataValores(despesa.getValorTransiente())));
		}*/
		
		VerificaTipoDespesa verificaTipoDespesa  = new VerificaTipoDespesa(despesa);
		verificaTipoDespesa.verifica(despesa.isImposto());
		
		despesa.setValorFornecedor(new BigDecimal(util.formataValores(despesa.getValorFornecedorTransiente())));
		despesa.setValorNF(new BigDecimal(util.formataValores(despesa.getValorNFTransiente())));
		
		
		
		despesa.setDataVencimento(data);	
		manager.persist(despesa);

	}

	public void editaValorF(String valor, Integer idProducao) {
		ProducaoP producao = manager.find(ProducaoP.class, idProducao);
		
		BigDecimal zero = new BigDecimal("0.00");
		
		BigDecimal valorCont = new BigDecimal(util.formataValores(valor));
			
		System.out.println(valorCont);
		
		if(valorCont.equals(zero)){
			producao.setValorContratacao(zero);
		}else{
			producao.setValorContratacao(valorCont);
		}
		
		
		producao.setDiferenca(producao.getValorItem().subtract(valorCont));
		
		manager.merge(producao);
		manager.flush();
	}
	
	public void editaObservacao(String texto, Integer idProducao){
		ProducaoP producao = manager.find(ProducaoP.class, idProducao);
		try {
			TypedQuery<ObsProducao> q = manager.createQuery("from ObsProducao where producaoP="+idProducao,ObsProducao.class);
			ObsProducao obs = q.getSingleResult();
			obs.setObs(texto.trim());
			obs.setProducaoP(producao);
			manager.merge(obs);
			manager.close();
			
		} catch (Exception e) {
			
			ObsProducao obs = new ObsProducao();
			obs.setObs(texto.trim());
			obs.setProducaoP(producao);
			manager.persist(obs);
			manager.close();
		}
	}
	
	
	
	public void difImpostoProducaoP(String texto, Integer idProducao) {
		
		ProducaoP producao = manager.find(ProducaoP.class, idProducao);
		try {
			TypedQuery<DifImpostoProducaoP> q = manager.createQuery("from DifImpostoProducaoP where producaoP="+idProducao,DifImpostoProducaoP.class);
			DifImpostoProducaoP obs = q.getSingleResult();
			obs.setObs(texto.trim());
			obs.setProducaoP(producao);
			manager.merge(obs);
			manager.close();
			
		} catch (Exception e) {
			
			DifImpostoProducaoP obs = new DifImpostoProducaoP();
			obs.setObs(texto.trim());
			obs.setProducaoP(producao);
			manager.persist(obs);
			manager.close();
		}
		
	}

	public void nfRecibo(String texto, Integer idProducao) {
		ProducaoP producao = manager.find(ProducaoP.class, idProducao);
		producao.setNfRecibo(texto);
		manager.merge(producao);
		manager.close();
	}
	
	public void dadosBancarios(String texto, Integer idProducao) {
		ProducaoP producao = manager.find(ProducaoP.class, idProducao);
		producao.setDadosBancarios(texto);
		manager.merge(producao);
		manager.close();
	}

	public void nfinterna(String texto, Integer idLista) {
		Lista lista = manager.find(Lista.class, idLista);
		try {
			TypedQuery<InfoInterna> q = manager.createQuery("from InfoInterna where idlista="+idLista,InfoInterna.class);
			InfoInterna infoInterna = q.getSingleResult();
			infoInterna.setNfInterna(texto);
			manager.merge(infoInterna);
			manager.close();
		} catch (Exception e) {
			InfoInterna infoInterna = new InfoInterna();
			infoInterna.setNfInterna(texto);
			infoInterna.setLista(lista);
			manager.persist(infoInterna);
			manager.close();
		}
	}

	public void impostoInterna(String texto, Integer idLista) {
		
		double imposto = util.converteStringParaDouble(texto.replace(",", "."));
		
		Lista lista = manager.find(Lista.class, idLista);
		try {
			TypedQuery<InfoInterna> q = manager.createQuery("from InfoInterna where idlista="+idLista,InfoInterna.class);
			InfoInterna infoInterna = q.getSingleResult();
			infoInterna.setImpostoInterna(imposto);
			manager.merge(infoInterna);
			manager.close();
		} catch (Exception e) {
			System.out.println(e);
			
			InfoInterna infoInterna = new InfoInterna();
			infoInterna.setImpostoInterna(imposto);
			infoInterna.setLista(lista);
			manager.persist(infoInterna);
			manager.close();
		}
	}

	public void dataPagInterna(String texto, Integer idLista) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new java.sql.Date(format.parse(texto).getTime());
		
		
		Lista lista = manager.find(Lista.class, idLista);
		try {
			TypedQuery<InfoInterna> q = manager.createQuery("from InfoInterna where idlista="+idLista,InfoInterna.class);
			InfoInterna infoInterna = q.getSingleResult();
			infoInterna.setDataPagamento(data);
			manager.merge(infoInterna);
			manager.close();
		} catch (Exception e) {
			InfoInterna infoInterna = new InfoInterna();
			infoInterna.setDataPagamento(data);
			infoInterna.setLista(lista);
			manager.persist(infoInterna);
			manager.close();
		}
	}
	
	public List<Object[]> listaDeIdsFornecedores(Integer idLista){
		String consulta ="select  distinct idEmpFornecedor.idEmpresa, count(idEmpFornecedor) from ProducaoP where idlista ="+idLista+" group by idEmpFornecedor";
		Query q = manager.createQuery(consulta);
		return q.getResultList();
	}

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

	
	
	
	
// ----------------------------------------------------------------------------------------------------------------------- //
// Métodos para calcular quantidade de RowSpan 
	public void logicaCalculaRowSpanTabela(List<ProducaoP> listaProducaoPTeste, List<Object[]> listaDeIdsFornecedores) {
		boolean verif = false;
		
		for (int i = 0; i < listaProducaoPTeste.size(); i++) {
			if(listaProducaoPTeste.get(i).getRowSpan() != null){
				verif = true;
			}
		}
		
		if(verif == false){
			for (int i = 0; i < listaDeIdsFornecedores.size(); i++) {
				Integer idforn = (Integer) listaDeIdsFornecedores.get(i)[0]; 	
				Long rows1 = (Long) listaDeIdsFornecedores.get(i)[1]; 	
				Integer rows = Integer.valueOf((int) rows1.longValue());	
				boolean verificaRows = false;
				for (int j = 0; j < listaProducaoPTeste.size(); j++) {
					Integer idEmpresa = listaProducaoPTeste.get(j).getIdEmpFornecedor().getIdEmpresa();
					if(idforn.equals(idEmpresa)){
						verificaRows = verificaSeTemValoresSetadosEmRowSpan(listaProducaoPTeste, idforn,verificaRows);
						setaValoresNaColunaRowSpan(listaProducaoPTeste, rows, verificaRows, j);
					}
					
				}
			}
		}
		
	}

	private boolean verificaSeTemValoresSetadosEmRowSpan(List<ProducaoP> listaProducaoPTeste,Integer idforn, boolean verificaRows) {
		for (int k = 0; k < listaProducaoPTeste.size(); k++) {
			Integer idEmpresa2 = listaProducaoPTeste.get(k).getIdEmpFornecedor().getIdEmpresa();
			if(idforn.equals(idEmpresa2)){
				
				if(listaProducaoPTeste.get(k).getRowSpan() != null){
					verificaRows = true;
				}
			}
		}
		return verificaRows;
	}

	private void setaValoresNaColunaRowSpan(List<ProducaoP> listaProducaoPTeste, Integer rows,
			boolean verificaRows, int j) {
		int posicao = j;
		for (int j2 = j; j2 < listaProducaoPTeste.size(); j2++) {
			if(posicao == j2 && verificaRows == false){
				listaProducaoPTeste.get(j2).setRowSpan(rows);
				manager.merge(listaProducaoPTeste.get(j2));
				manager.close();
			}
			
		}
	}

	public List<DesIntFinanc> despTeste(Integer idLista) {
		try {
			TypedQuery<DesIntFinanc> q = manager.createQuery("from DesIntFinanc where idLista="+idLista,DesIntFinanc.class);
			List<DesIntFinanc> despesas = q.getResultList();
			return despesas;
		} catch (Exception e) {
			return null;
		}
	}

	public void salvaDespesas(DesIntFinanc despesas) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new java.sql.Date(format.parse(despesas.getDataTransiente()).getTime());
		despesas.setData(data);
		
		despesas.setValor1(new BigDecimal(util.formataValoresAprimorado(despesas.getValor1Transiente())));
		despesas.setValor2(new BigDecimal(util.formataValoresAprimorado(despesas.getValor2Transiente())));
		despesas.setValor3(new BigDecimal(util.formataValoresAprimorado(despesas.getValor3Transiente())));
		despesas.setLocco(new BigDecimal(util.formataValoresAprimorado(despesas.getLoccoTransiente())));
		despesas.setcCredito(new BigDecimal(util.formataValoresAprimorado(despesas.getcCreditoTransiente())));
		
		manager.persist(despesas);
		
	}

	public BigDecimal totalDespesas(Integer idLista) {
		String consulta = "SELECT sum(valor1)+sum(valor2)+sum(valor3)+sum(locco)+sum(cCredito) FROM DesIntFinanc where idlista ="+idLista;
		Query q = manager.createQuery(consulta);
		BigDecimal valorTotalDespesas = (BigDecimal) q.getSingleResult();
		return valorTotalDespesas;
	}

	public void atualizaDespesasValores(String valor, Integer idDesp,String name) {
		BigDecimal valorFinal = null;
		
		if(name.equals("observacao") || name.equals("descricao2") || name.equals("data")){
		}else{
		
			if(valor.equals("") || valor.equals(null) || valor.equals("")){
				valorFinal= new BigDecimal("0.00");
			}else{
				valorFinal = new BigDecimal(util.formataValores(valor));
			}
		}
		
		try {
			TypedQuery<DesIntFinanc> q = manager.createQuery("from DesIntFinanc where idDesp="+idDesp,DesIntFinanc.class);
			DesIntFinanc despesa = q.getSingleResult();
			
			
			if(name.equals("valor1")){
				despesa.setValor1(valorFinal);
			}
			if(name.equals("valor2")){
				despesa.setValor2(valorFinal);
			}
			if(name.equals("valor3")){
				despesa.setValor3(valorFinal);
			}
			if(name.equals("locco")){
				despesa.setLocco(valorFinal);
			}
			if(name.equals("cCredito")){
				despesa.setcCredito(valorFinal);
			}
			if(name.equals("descricao2")){
				despesa.setDescricao2(valor);
			}
			if(name.equals("observacao")){
				despesa.setObservacao(valor);
			}
			if(name.equals("data")){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date data = new java.sql.Date(format.parse(valor).getTime());
				despesa.setData(data);
			}
			
			manager.merge(despesa);
			manager.close();
		} catch (Exception e) {
			System.out.println(""+e);
		}
		
		
	}
	
	/*public void salvaNovoOrcamento(String valor,Integer idFornecedor,Integer idGrupo){
		TypedQuery<Empresa> e = manager.createQuery("select e from Empresa e where idEmpresa=6937", Empresa.class); 
		Empresa empresa = e.getSingleResult();
		Grupo grupo= manager.getReference(Grupo.class, idGrupo);
		OrcamentoFornecedor orcamento = new OrcamentoFornecedor();
		
		orcamento.setValorOrcamento(new BigDecimal(util.formataValoresAprimorado(valor)));
		orcamento.setEmpresa(empresa);
		orcamento.setGrupo(grupo);
		manager.persist(orcamento);
		manager.close();
	}*/

}














