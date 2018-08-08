package br.com.sysloccOficial.controllerProducao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Producao;
import br.com.sysloccOficial.model.producao.DtPgtoFornecedor;
import br.com.sysloccOficial.model.producao.FornecedorFinanceiro;
import br.com.sysloccOficial.model.producao.ProducaoP;
import br.com.sysloccOficial.model.producao.StatusFinanceiro;
import br.com.sysloccOficial.model.producao.ValorPagtoFornecedor;
import br.com.sysloccOficial.model.producao.ValorPgtoAux;



@Transactional
public class AuxProducao {

	@Autowired Utilitaria util;
	@PersistenceContext	private EntityManager manager;
	
	/**
	 * Método usado para "limpar" os valores que vem como array da tabela de valores por ajax
	 * 
	 * 
	 * @param valores
	 * @return
	 */
	
	public ArrayList<String> pegaValoresTabela(String valores) {
		String[] teste = valores.split(" ");
		
		ArrayList<String> novo = new ArrayList<String>();
		
		for (int i =0; i < teste.length;i++) {
			String valoresLimpar = teste[i].replace(",", "").replace(";",",");
			BigDecimal conv =   util.valoresEmReais(valoresLimpar);

			novo.add(conv.toString());
			
//			novo.add(teste[i].replace(",", "").replace(";",","));
		}
		return novo;
	}
	
	
	/**
	 * Método para buscar o valor total de um item pelo idProdutoGrupo
	 * @param idProdutoGrupo
	 * @return
	 */
	
	public BigDecimal pegaValorTotalItem(Integer idProdutoGrupo) {
		BigDecimal custoTotal;
		double qtditem = pegaQuantidadeProduto(idProdutoGrupo);
		BigDecimal custo = pegaPrecoProduto(idProdutoGrupo);
		custoTotal = multiplicaQutdPeloValor(qtditem, custo);
		return custoTotal;
	}
	
	public BigDecimal pegaCustoProduto(Integer idProdutoGrupo) {
		Query valor = manager.createQuery("SELECT custoProduto FROM ProdutoGrupo where idProdutoGrupo ="+idProdutoGrupo);
		BigDecimal custo = (BigDecimal) valor.getSingleResult();
		return custo;
	}

	public BigDecimal pegaPrecoProduto(Integer idProdutoGrupo) {
		Query valor = manager.createQuery("SELECT precoProduto FROM ProdutoGrupo where idProdutoGrupo ="+idProdutoGrupo);
		BigDecimal custo = (BigDecimal) valor.getSingleResult();
		return custo;
	}
	public double pegaQuantidadeProduto(Integer idProdutoGrupo) {
		Query qtd = manager.createQuery("SELECT sum(quantidade*quantidade2*diarias) FROM ProdutoGrupo where idProdutoGrupo ="+idProdutoGrupo);
		double qtditem = (double) qtd.getSingleResult();
		return qtditem;
	}
	
	
	public BigDecimal multiplicaQutdPeloValor(double qtditem, BigDecimal custo) {
		BigDecimal custoTotal;
		BigDecimal qtdConv = BigDecimal.valueOf(qtditem);
		custoTotal = custo.multiply(qtdConv);
		return custoTotal;
	}

	/**
	 * Método que pega quantas parcelas restam  depois de inserido um valor em uma 
	 * celula de qualquer posicao.
	 * 
	 * 
	 * @param qtdParcelas
	 * @param posicaoItem
	 * @param parcelasRestantes
	 * @return
	 */
	public BigDecimal pegaParcelasRestantesParaDividirValor(Integer qtdParcelas, Integer posicaoItem) {
		Integer parcelasRestantes = 0;
		
		for(int i = posicaoItem+1; i <= qtdParcelas;i++){
			parcelasRestantes = parcelasRestantes+1;
		}
		String g = parcelasRestantes.toString();
		BigDecimal parcelasRest = new BigDecimal(g.toString());
		return parcelasRest;
	}
	
	
	/**
	 * Método que soma os valores das celulas anteriores com o valor da celula atual.
	 * 
	 * @param posicaoItem
	 * @param somaAnt
	 * @param teste
	 * @return
	 */
	public BigDecimal somaValoresParcelasAnterioresComParcelaAtual(Integer posicaoItem, BigDecimal somaAnt,
			List<BigDecimal> teste) {
		for(int i = 0; i < posicaoItem - 1;i++){
			somaAnt = somaAnt.add(teste.get(i));
		}
		somaAnt = somaAnt.add(teste.get(posicaoItem-1));
		return somaAnt;
	}
	
	/**
	 * Calcula o em partes iguais o valor das parcelas restantes.
	 * 
	 * @param item
	 * @param somaAnt
	 * @param parcelasRest
	 * @return
	 */
	public BigDecimal calculaValorDasParcelasRestantes(BigDecimal item, BigDecimal somaAnt,
			BigDecimal parcelasRest) {
		BigDecimal diferencaItemMenosSomaParcelas;
		BigDecimal valorParcelasRestantes = new BigDecimal("0");
		
		try {
			diferencaItemMenosSomaParcelas = item.subtract(somaAnt);
			valorParcelasRestantes = diferencaItemMenosSomaParcelas.divide(parcelasRest,12,RoundingMode.UP);
			return valorParcelasRestantes;
		} catch (Exception e) {
			return valorParcelasRestantes;
		}
	}
	
	/**
	 * Verifica se um fornecedor tem mais itens cadastrados em uma lista de Produção
	 * Esse méotodo é útil pra poder gerar a carta de contraatação apenas se todos os 
	 * itens desse fornecedor forem consolidados;.
	 * 
	 * 
	 * @param producao
	 * @return
	 */
	public long verificaSeFornTemMaisItens(ProducaoP producao) {
		Query q =  manager.createQuery("select count(idProdutoGrupo) from ProdutoGrupo where fornecedor_item ="
		+producao.getIdEmpFornecedor().getIdEmpresa()+" and  idGrupo.idLista.idLista="+producao.getIdLista());
		long verificaMaisIntens = (long) q.getSingleResult();
		return verificaMaisIntens;
	}

	/**
	 * Método para atualizar o fornecedor financeiro.
	 * Utilizado quando um item de produção é atualizado 
	 * 
	 * 
	 * 
	 * @param producao
	 * @param idFornecedorFinanc
	 */
	public FornecedorFinanceiro atualizaFornecedorFinanceiro(ProducaoP producao, Integer idFornecedorFinanc) {
		long verificaMaisIntens = verificaSeFornTemMaisItens(producao);
		FornecedorFinanceiro fornecedorAtualizar = manager.find(FornecedorFinanceiro.class, idFornecedorFinanc);
		
		fornecedorAtualizar.setIdProducao(producao);
		fornecedorAtualizar.setIdEmpresa(producao.getIdEmpFornecedor());
		fornecedorAtualizar.setCondicaoPagamento(producao.getNumParcelas());
		fornecedorAtualizar.setTipoPagamento(producao.getTipoPagamento());
		
		/**
		 * Verificar se fornecedor tem outros itens
		 */
		
			if(verificaMaisIntens <= 1){
				fornecedorAtualizar.setContratacao(true);
			}else{
				fornecedorAtualizar.setContratacao(false);
			}
	
			manager.merge(fornecedorAtualizar);
			
			
			return fornecedorAtualizar;
			
	}
	
	public void excluiDtPgto(Integer idValorPgForn){
	TypedQuery<Integer> id = manager.createQuery(""
			+ "SELECT dtPagtoForncedor FROM DtPgtoFornecedor where idValorPgForn="+idValorPgForn,Integer.class);
		DtPgtoFornecedor q0 = manager.find(DtPgtoFornecedor.class, id.getSingleResult());
		manager.remove(q0);	
	}
	
	/**
	 * Método para pagamento de outro fornecedor.
	 * 
	 * 
	 * @param producao
	 */
	
	public void contratacaoOutroFornecedor(ProducaoP producao) {
		
		Empresa empresa = manager.getReference(Empresa.class, producao.getIdFornecedorOutroTrans());
		FornecedorFinanceiro outroFornecedor = new FornecedorFinanceiro();
		outroFornecedor.setCondicaoPagamento(1);
		outroFornecedor.setContratacao(true);
		outroFornecedor.setImposto(util.converteStringParaDouble(producao.getImpostoOutroTrans()));
		outroFornecedor.setIdEmpresa(empresa);
		outroFornecedor.setIdProducao(producao);
		outroFornecedor.setTipoPagamento(producao.getTipoPagamento());

		manager.persist(outroFornecedor);
		
		List<ValorPgtoAux> pagamentoOutroFornecedor = new ArrayList<ValorPgtoAux>();
			
			ValorPgtoAux valor = new ValorPgtoAux();
		
			valor.setParcela(1);
			valor.setData(util.formataDatasStringParaCalendar(producao.getDataPgtoOutroFornecedorTrans()+" 08:00"));
			BigDecimal valorItem = util.valoresEmReais(producao.getValorDePagamentoContratacaoOutroFornecedorTrans());
			valor.setValor(valorItem);
			valor.setPrazo(0);
			pagamentoOutroFornecedor.add(valor);
			
		enviarParaCriarValorPgtoFornecedor(pagamentoOutroFornecedor, outroFornecedor);
	}
	
	
	
	/**
	 * Método auxiliar para preencher dados de pagamento para fornecedor
	 * 
	 * 
	 * @param pagamentos
	 * @param fornecedor
	 */
	public void enviarParaCriarValorPgtoFornecedor(List<ValorPgtoAux> pagamentos, FornecedorFinanceiro fornecedor) {
		
		for(int i =0; i< pagamentos.size();i++){
			
			valorPgtoFornecedor(fornecedor,
			pagamentos.get(i).getParcela(),
			pagamentos.get(i).getValor(),
			pagamentos.get(i).getData(),
			pagamentos.get(i).getPrazo());
		}
	}
	
	/**
	 * Método para persistir dados de pagamento do fornecedor ( ou de outro Fornecedor )
	 * 
	 * 
	 * @param fornecedorFinanceiro
	 * @param parcela
	 * @param valor
	 * @param Pagamento
	 * @param diasPagto
	 */
	public void valorPgtoFornecedor(FornecedorFinanceiro fornecedorFinanceiro,Integer parcela, BigDecimal valor, Date Pagamento,Integer diasPagto){
		Empresa empresa = manager.getReference(Empresa.class, fornecedorFinanceiro.getIdEmpresa().getIdEmpresa());
		
		
		ValorPagtoFornecedor valorpgto = new ValorPagtoFornecedor();
		
		valorpgto.setIdEmpresa(empresa);
		valorpgto.setIdFornecedorFinanceiro(fornecedorFinanceiro);
		valorpgto.setParcela(parcela);
		valorpgto.setValor(valor);
		valorpgto.setDiasPrazoParaPagamento(diasPagto);
		
		manager.persist(valorpgto);
		
		dtPagtoFornecedor(valorpgto.getIdValorFinancForn(), Pagamento);
		
		// Status
		
	}
	
	/**
	 * Método que persisti as datas de pagamento do fornecedor ( ou de outro fornecedor )
	 * 
	 * 
	 * @param IdValorPgtoFornecedor
	 * @param dataPagamento
	 */
	public void dtPagtoFornecedor(Integer IdValorPgtoFornecedor, Date dataPagamento){
		ValorPagtoFornecedor valorpgto = manager.getReference(ValorPagtoFornecedor.class, IdValorPgtoFornecedor);
		
		DtPgtoFornecedor dtPagamento = new DtPgtoFornecedor();
		
		dtPagamento.setValorPgtoForn(valorpgto); // Objeto ValorPgtoFornecedor
		dtPagamento.setDataPagar(dataPagamento);
		dtPagamento.setStatus(StatusFinanceiro.PENDENTE);
		
		manager.persist(dtPagamento);
		
	}
	
	/**
	 * Método usado para verificar se existe cadastrado uma contração com outro Fornecedor.
	 * Útil para atualizar ou apagar os dados.
	 * 
	 * @param idProducao
	 * @param idFornecedor
	 * @return
	 */
	
	public FornecedorFinanceiro verifeicaSeExisteOutroFornecedorContratacao(Integer idProducao,
		Integer idFornecedor) {
		FornecedorFinanceiro outro = new FornecedorFinanceiro();
		try {
			String consultaOutroFornecedor = "SELECT f FROM FornecedorFinanceiro f where idProducao = "+idProducao+" and idEmpresa <> "+idFornecedor;
			Query qq = manager.createQuery(consultaOutroFornecedor);
			outro = (FornecedorFinanceiro) qq.getSingleResult();
			return outro;
		} catch (Exception e) {
			return outro;
		}
	}
	
	
	/**
	 * Método usado para calcular a soma de valores dos impostos
	 * 
	 * Quando um fornecedor tem valor de contratacao, será calculado um imposto 
	 * sobre o valor da diferença. Esse valor vai pra conta do Fornecedor + a soma total dos itens
	 * 
	 */
	
	public BigDecimal somaApenasValoresImpostoComContratacao(List<ProducaoP> ff) {
		BigDecimal valorItem = new BigDecimal("0");
		BigDecimal valorCont = new BigDecimal("0");
		BigDecimal result = new BigDecimal("0");
		
		BigDecimal calculoImposto = new BigDecimal("0");
		BigDecimal cem = new BigDecimal("100");
		BigDecimal totalImposto = new BigDecimal("0");
		
		for(int i=0; i < ff.size();i++){
			//Se tiver contratacao
			if(ff.get(i).isTemContratacao() == true){
				double imposto = ff.get(i).getImposto();
				BigDecimal ii = new BigDecimal(imposto);
				valorItem = ff.get(i).getValorItem();
				valorCont = ff.get(i).getValorContratacao();
				result = valorItem.subtract(valorCont);
				calculoImposto = result.multiply((ii).divide(cem));
				
				totalImposto = totalImposto.add(calculoImposto);
				
			}
		}
		return totalImposto;
	}
	
	/**
	 * Método para regra de calculo de parcelamento
	 * 
	 * 
	 */
	
	public BigDecimal regraParaCalculoParcelamento(Integer parcelas,String valorContratacaoCalculado, boolean infoPag,
			boolean infoForn, String valorContratacao, BigDecimal qtdParcelas,
			BigDecimal valorParcela, BigDecimal custoTotal,
			List<Integer> numParcelas) {
		if(parcelas != 0){

			valorParcela = custoTotal.divide(qtdParcelas,12,RoundingMode.UP);
				

//Regra de parcelamento antiga			
			/*if(infoPag == true){
						//se contratacao mesmo fornecedor
						if(infoForn == false){
							BigDecimal valorContratacaoConv = new BigDecimal(util.formataValores(valorContratacaoCalculado).toString());
							valorParcela = valorContratacaoConv.divide(qtdParcelas,12,RoundingMode.UP);
						}
						// se contratacao outro fornecedor	
						else{
							BigDecimal valorContratacaoConv = new BigDecimal(util.formataValores(valorContratacao).toString());
							valorParcela = valorContratacaoConv.divide(qtdParcelas,12,RoundingMode.UP);
						}
							
				}else{
					valorParcela = custoTotal.divide(qtdParcelas,12,RoundingMode.UP);
				}*/
		
			for(int i = 1; i <= parcelas;i++){
				numParcelas.add(i);
			}
		
		}else{
		
		}
		return valorParcela;
	}
	
	
	
	
	
	
	
	
	
	
}
