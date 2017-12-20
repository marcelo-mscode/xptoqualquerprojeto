package br.com.sysloccOficial.financeiro.atualizaInterna;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.daos.GrupoDAO;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualDAO;
import br.com.sysloccOficial.financeiro.dao.InternaIndividualDAO;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.financeiro.funcionario.cacheCalculos.CacheDiretoriaSTelefone;
import br.com.sysloccOficial.financeiro.funcionario.cacheCalculos.CacheFuncionarioSTelefone;
import br.com.sysloccOficial.financeiro.funcionario.cacheCalculos.CacheFuncionarios;
import br.com.sysloccOficial.financeiro.funcionario.cacheCalculos.CalculadoraCaches;
import br.com.sysloccOficial.financeiro.relatorioeventos.CacheDoEventoApoio;
import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioBVS;
import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioEventoIndividualApoio;
import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.GiroEvento;
import br.com.sysloccOficial.model.InfoInterna;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.RelatorioEventos;


@Component
@Transactional
public class AtualizaRelatorioEventoApoio implements CalculoValorTelefone{

	@Autowired RelatorioEventoIndividualApoio relApoio;
	@Autowired InternaIndividualDAO internaIndividualDAO;
	@Autowired RelatorioEventoDAO relatorioDAO;
	@Autowired AnaliticoIndividualDAO analiticoDAO;
	@Autowired UtilitariaDatas utildatas;
	@Autowired GrupoDAO grupoDAO;
	@Autowired ProdutoGrupoDAO produtoGrupoDAO;
	@Autowired CacheDoEventoApoio cacheEvento;
	
	
	
	@PersistenceContext	private EntityManager manager;
	
	
	
	public void montaObjetoRelatorio(Integer idLista,Lista infoLista,String mes,String ano) throws ParseException{
	
		
			InfoInterna infoInterna = relatorioDAO.pegaInfoInterna(idLista);
			
			List<RelatorioBVS> relatorioBVS = relApoio.relatorioBVS(idLista);
	
			List<CachePadrao> listaRelatorioCaches = cacheEvento.listaRelatorioCaches(idLista);
			
			
			BigDecimal custoTelefone = new BigDecimal("0");
	
			RelatorioEventos relatorio = relatorioDAO.relatorioEventoPorIdLista(idLista);
			
			RelatorioEventos novoRelatorio = new RelatorioEventos();
			
			novoRelatorio.setAnoEvento(ano);
			novoRelatorio.setMesEvento(mes);
			//
			novoRelatorio.setMesReferencia(utildatas.referenciaMesAnalitico(mes));
			
			novoRelatorio.setBvs(new BigDecimal("0.00"));
			novoRelatorio.setCacheEquipEx(new BigDecimal("0.00"));
			
			novoRelatorio.setValorLoccoAgenc(infoLista.getValorTotal().subtract(grupoDAO.valorGrupoSemImposto(idLista)));
			
			novoRelatorio.setServicos(infoLista.getValorTotal().subtract(grupoDAO.valorGrupoSemImposto(idLista)));
			
// -------  FeeReduzido			
			BigDecimal feeReduzido = produtoGrupoDAO.calculaSomaFeeLista(idLista);
			novoRelatorio.setFee(infoLista.getAdministracaoValor().subtract(feeReduzido));
			novoRelatorio.setFeeReduzido(feeReduzido);
// ------- //			
			
			novoRelatorio.setImpostoCliente(infoLista.getImpostoValor());
			
			
			novoRelatorio.setDataAtualizacao(Calendar.getInstance());
			novoRelatorio.setIdLista(idLista);
			
			novoRelatorio.setImpostoSobreValorLoccoAgencia(calculaImpostoSobreValorLoccoAgencia(novoRelatorio.getValorLoccoAgenc(), new BigDecimal(infoInterna.getImpostoInterna()/100)));
			
			
			novoRelatorio.setValorLiquido(novoRelatorio.getValorLoccoAgenc().subtract(novoRelatorio.getImpostoSobreValorLoccoAgencia()));
			novoRelatorio.setImpostoClienteDiferenca(novoRelatorio.getImpostoCliente().subtract(novoRelatorio.getImpostoSobreValorLoccoAgencia()));
			
			
			novoRelatorio.setLiquidoImposto(novoRelatorio.getValorLoccoAgenc().subtract(novoRelatorio.getImpostoSobreValorLoccoAgencia()));
			
			novoRelatorio.setTotalAPagarFornecedores(totalAPAgarFornecedores(relatorioBVS));
			
			BigDecimal totalDiferencaSemTelefone = totalDiferencaSemTelefone(relatorioBVS,novoRelatorio.getFee(),novoRelatorio.getFeeReduzido(), novoRelatorio.getImpostoClienteDiferenca());
			
			
// -------- Calculo de Caches sem telefone	---- //		
	
			/*BigDecimal totalCacheFuncionariosSemTelefone = relatorioDAO.calculaTotalCachesFuncionarios(totalDiferencaSemTelefone, listaRelatorioCaches);
			
			CacheFuncionarios funcionarioSTelefone = new CacheFuncionarioSTelefone();
			CacheFuncionarios diretoriaSTelefone = new CacheDiretoriaSTelefone();
										
			BigDecimal totalCacheFuncionariosSemTelefone = funcionarioSTelefone.calculaCacheSemtelefone(listaRelatorioCaches, totalDiferencaSemTelefone);
			BigDecimal totalCacheDiretoriaSemTelefone = diretoriaSTelefone.calculaCacheSemtelefone(listaRelatorioCaches, totalDiferencaSemTelefone);

			//BigDecimal totalCacheDiretoriaSemTelefone = relatorioDAO.caculaValorSeDiretoria(listaRelatorioCaches,totalDiferencaSemTelefone,totalCacheFuncionariosSemTelefone);
			
			BigDecimal calculoCacheFinalSemTelefone = CalculadoraCaches.calculaCachesSemTelefone(listaRelatorioCaches, totalDiferencaSemTelefone);
			
			System.out.println(calculoCacheFinalSemTelefone);*/
			
			novoRelatorio.setTotalCachesSemTelefone(CalculadoraCaches.totalCachesSemTelefone(listaRelatorioCaches, totalDiferencaSemTelefone));
			
//--------- Giro Sem Telefone ------------------ //
		
			BigDecimal giroSTelef = caculaGiroSemTelefone(novoRelatorio.getValorLiquido(),
					   novoRelatorio.getTotalCachesSemTelefone(),totalApagar(relatorioBVS));
			
			//System.out.println("Giro sem telefone: "+giroSTelef);

//--------- Margem Contribuição ---------------- //
			
			novoRelatorio.setMargemContribuicao(giroSTelef.multiply(new BigDecimal("0.2")));
			
//  ------  Calcula o Custo do Telefone do Mês corrente
			// --- Verifica se Relatório existe
			 
			Integer idRelatorioParaGiroTelefone = null;
			idRelatorioParaGiroTelefone = verificaSeRelatorioEventoExiste(relatorio, novoRelatorio);
			
			//Custo 
			custoTelefone = calculoValorTelefone(giroSTelef, idRelatorioParaGiroTelefone, mes, ano);
			
			novoRelatorio.setCustoTelefone(custoTelefone);
			
// -------  Calcula totalExternas
			BigDecimal totalExterna = novoRelatorio.getMargemContribuicao().add(novoRelatorio.getCustoTelefone().add(totalApagar(relatorioBVS)));
			novoRelatorio.setPgtoExternas(totalExterna);
			
			
			
			BigDecimal totalDiferencaComTelefone =  totalDiferencaComTelefone(relatorioBVS, novoRelatorio.getFee(),
					   novoRelatorio.getFeeReduzido(),novoRelatorio.getImpostoClienteDiferenca(),
					   novoRelatorio.getMargemContribuicao(), custoTelefone);
		
			novoRelatorio.setTotalDiferenca(totalDiferencaComTelefone);
			
			
			
			BigDecimal totalCacheFuncComTelefone = CalculadoraCaches.totalCacheFuncionario(listaRelatorioCaches, totalDiferencaSemTelefone);
			BigDecimal totalCacheDiretoriaComTelefone = CalculadoraCaches.totalCacheDiretoria(listaRelatorioCaches, totalDiferencaSemTelefone);
			
			novoRelatorio.setTotalCachesComTelefone(totalCacheDiretoriaComTelefone.add(totalCacheFuncComTelefone));
			novoRelatorio.setCacheEquipIn(totalCacheFuncComTelefone);

			
			/*BigDecimal pgtoExternasSemImpostossTESTE = novoRelatorio.getPgtoExternas().subtract(novoRelatorio.getMargemContribuicao()).
					subtract(novoRelatorio.getCustoTelefone());
			*/
			
			
			//Calcula giro Com telefone
			BigDecimal giroComTelefone = novoRelatorio.getValorLiquido()
					.subtract(novoRelatorio.getTotalCachesComTelefone())
					
			// Esse valor agora !!!!!
			.subtract(novoRelatorio.getPgtoExternas());
			//.subtract(novoRelatorio.getPgtoExternas());
			
			novoRelatorio.setCacheEquipIn(calculacacheEquipeInterna(listaRelatorioCaches, totalDiferencaComTelefone));
			novoRelatorio.setTotalCachesIntExt(calculacacheEquipeInterna(listaRelatorioCaches, totalDiferencaComTelefone));
			
			novoRelatorio.setDiretoria1(calculacacheDiretoria(listaRelatorioCaches, totalDiferencaComTelefone, TipoCache.DIRETORIA1));
			novoRelatorio.setDiretoria2(calculacacheDiretoria(listaRelatorioCaches, totalDiferencaComTelefone, TipoCache.DIRETORIA2));
			
			novoRelatorio.setDiferencaCacheFuncionariosTotalPgto(
					novoRelatorio.getTotalDiferenca().subtract(novoRelatorio.getTotalCachesIntExt()));
			
			novoRelatorio.setTotalCache(novoRelatorio.getDiretoria1().add(novoRelatorio.getDiretoria2().add(novoRelatorio.getTotalCachesIntExt())));
			
			
		if(relatorio == null){	
		
			manager.persist(novoRelatorio);

			GiroEvento novoGiro = new GiroEvento();
			novoGiro.setGiroSemTelefone(giroSTelef);
			novoGiro.setGiroComTelefone(giroComTelefone);
			novoGiro.setMesEvento(mes);
			novoGiro.setAnoEvento(ano);
			novoGiro.setRelatorioEvento(novoRelatorio);

			manager.persist(novoGiro);
			
			
// ------------------ > SALVA CACHE DO EVENTO 			
			relatorioDAO.salvaCacheDoEvento(novoRelatorio);
			
			
		}else{
			novoRelatorio.setIdRelatorioEvento(relatorio.getIdRelatorioEvento());
			manager.merge(novoRelatorio);
			
			Integer idRelatorio = relatorio.getIdRelatorioEvento();
			
			GiroEvento giro = relatorioDAO.giroPorIdLista(idRelatorio);
			
			giro.setGiroSemTelefone(giroSTelef);
			giro.setGiroComTelefone(giroComTelefone);
			giro.setMesEvento(mes);
			giro.setAnoEvento(ano);
			giro.setRelatorioEvento(novoRelatorio);
			manager.merge(giro);
			
// ------------------ > SALVA CACHE DO EVENTO 			
			relatorioDAO.salvaCacheDoEvento(novoRelatorio);
			
		}
		
	}



	private Integer verificaSeRelatorioEventoExiste(RelatorioEventos relatorio, RelatorioEventos novoRelatorio) {
		Integer idRelatorioParaGiroTelefone;

		if(relatorio == null){
			idRelatorioParaGiroTelefone = novoRelatorio.getIdRelatorioEvento();
		}else{
			idRelatorioParaGiroTelefone = relatorio.getIdRelatorioEvento();
		}
		return idRelatorioParaGiroTelefone;
	}
	

	
	public BigDecimal calculaImpostoSobreValorLoccoAgencia(BigDecimal valorLoccoAgencia, BigDecimal porcentagemSobreImposto){
		BigDecimal valorImposto = new BigDecimal("0");
		valorImposto = valorLoccoAgencia.multiply(porcentagemSobreImposto);
		return valorImposto;
	}
	
	public BigDecimal totalDiferencaSemTelefone(List<RelatorioBVS> relatorioBVS,BigDecimal fee,BigDecimal feeReduzido, BigDecimal impostoClienteDiferenca){
		BigDecimal totalDiferenca = new BigDecimal("0");
		
		for (int i = 0; i < relatorioBVS.size(); i++) {
			totalDiferenca = totalDiferenca.add(relatorioBVS.get(i).getDiferenca());
		}
		totalDiferenca = totalDiferenca.add(fee).add(impostoClienteDiferenca).add(feeReduzido);
		return totalDiferenca;
	}
	
	public BigDecimal totalAPAgarFornecedores(List<RelatorioBVS> relatorioBVS){
		BigDecimal total = new BigDecimal("0");
		for (int i = 0; i < relatorioBVS.size(); i++) {
			total = total.add(relatorioBVS.get(i).getValorFornecedor()).subtract(relatorioBVS.get(i).getDiferenca());
		}
		return total;
	}

	
	public BigDecimal totalDiferencaComTelefone(List<RelatorioBVS> relatorioBVS,
			BigDecimal fee,
			BigDecimal feeReduzido,
			BigDecimal impostoClienteDiferenca,
			BigDecimal margemContribuicao,
			BigDecimal valorTelefone){
		
		BigDecimal totalDiferenca = new BigDecimal("0");
		
		for (int i = 0; i < relatorioBVS.size(); i++) {
			totalDiferenca = totalDiferenca.add(relatorioBVS.get(i).getDiferenca());
		}
		totalDiferenca = totalDiferenca.add(fee).add(feeReduzido).add(impostoClienteDiferenca).subtract(margemContribuicao).subtract(valorTelefone);
		return totalDiferenca;
	}
	
	public BigDecimal totalApagar(List<RelatorioBVS> relatorioBVS){
		BigDecimal totalPagar = new BigDecimal("0");
		for (int i = 0; i < relatorioBVS.size(); i++) {
			totalPagar = totalPagar.add(relatorioBVS.get(i).getValorFornecedor().subtract(relatorioBVS.get(i).getDiferenca()));
		}
		return totalPagar;
	}

// ---- REFATORAR- calculoValorTelefone	 
	private BigDecimal calculoValorTelefone(BigDecimal giroSemTelefoneEvento, Integer idRelatorioAtual,String mes,String ano) {

			BigDecimal razaoCalculoTelefone = new BigDecimal("0.00");
			BigDecimal validador = new BigDecimal("0.00");
			
			// Pegar o soma de todos os giros
			/**
			 * Posso pegar o soma dos outros giros que estiverem cadastrados no banco o somar com o giro atual
			 * assim teremos a soma de todos os giros do mes
			 */
			BigDecimal somaGirosEventosMes = relatorioDAO.somaGirosPorAnoMes(ano, mes, idRelatorioAtual);
			
			BigDecimal valorTelefone = analiticoDAO.somaValorTelefonePorMesAno(mes,ano);
			
			// Pegar o valor do giro sem telefone
			BigDecimal valorGiroDesseEvento = giroSemTelefoneEvento;
			
			// Dividir o giro desse evento pela soma de todos os eventos
			if(somaGirosEventosMes.equals(validador) || somaGirosEventosMes == null){
				 razaoCalculoTelefone = valorGiroDesseEvento.divide( valorGiroDesseEvento,12,RoundingMode.UP);
			}else{
				 razaoCalculoTelefone = valorGiroDesseEvento.divide( somaGirosEventosMes,12,RoundingMode.UP);
			}
			
			// Pegar o resultado e multiplicar pelo valor mensal do telefone
			BigDecimal valorTelefoneEvento = valorTelefone.multiply(razaoCalculoTelefone);
			
			return valorTelefoneEvento;
			
	}	
	
// ---- REFATORAR	
	public BigDecimal calculacacheDiretoria(List<CachePadrao> listaRelatorioCaches, BigDecimal totalDiferencaComTelefone, TipoCache tipoCache) {
		BigDecimal totalCache = new BigDecimal("0");
		BigDecimal totalCacheFuncionarios = calculacacheEquipeInterna(listaRelatorioCaches, totalDiferencaComTelefone);
		BigDecimal totalCacheCalculo = totalDiferencaComTelefone.subtract(totalCacheFuncionarios);
		
		for (int i = 0; i < listaRelatorioCaches.size(); i++) {
			if(listaRelatorioCaches.get(i).getTipoCache() == tipoCache){
				totalCache = totalCache.add(totalCacheCalculo.multiply(
						listaRelatorioCaches.get(i).getRazaoPorcentagem()));
			}
			
		}
		return totalCache;
	}
	
// ---- REFATORAR	
	public BigDecimal calculacacheEquipeInterna(List<CachePadrao> listaRelatorioCaches, BigDecimal totalDiferencaComTelefone){
		BigDecimal totalCache = new BigDecimal("0");
		
		for (int i = 0; i < listaRelatorioCaches.size(); i++) {
			if(listaRelatorioCaches.get(i).getTipoCache() == TipoCache.FUNCIONARIO){
				totalCache = totalCache.add(totalDiferencaComTelefone.multiply(
						listaRelatorioCaches.get(i).getRazaoPorcentagem()));
			}
			
		}
		return totalCache;
	}

	
// ---- REFATORAR	
	public BigDecimal caculaGiroSemTelefone(BigDecimal valorLiquido, BigDecimal cacheSemTelefone, BigDecimal externas) {
		
		BigDecimal giroSemTelefone = new BigDecimal("0");
		BigDecimal bvs = new BigDecimal("0");
		BigDecimal internas = new BigDecimal("0");
		giroSemTelefone = valorLiquido.subtract(cacheSemTelefone).subtract(externas).subtract(internas).add(bvs);
		
		return giroSemTelefone;
	}

}
