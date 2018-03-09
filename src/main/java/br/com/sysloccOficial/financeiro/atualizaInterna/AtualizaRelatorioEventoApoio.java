package br.com.sysloccOficial.financeiro.atualizaInterna;

import java.math.BigDecimal;
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
import br.com.sysloccOficial.financeiro.dao.SalvaCacheDoEvento;
import br.com.sysloccOficial.financeiro.funcionario.cacheCalculos.CalculadoraCachesTotais;
import br.com.sysloccOficial.financeiro.relatorioeventos.CacheDoEventoApoio;
import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioBVS;
import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioEventoIndividualApoio;
import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.financeiro.relatorioeventos.calculocache.CalculadoraCaches;
import br.com.sysloccOficial.financeiro.relatorioeventos.calculogiro.CalculadoraGiro;
import br.com.sysloccOficial.financeiro.relatorioeventos.calculoimposto.CalculadoraImposto;
import br.com.sysloccOficial.financeiro.relatorioeventos.calculotelefone.CalculadoraTelefone;
import br.com.sysloccOficial.financeiro.relatorioeventos.totalDiferencaTelefone.CalculadoraDiferencaTelefone;
import br.com.sysloccOficial.financeiro.relatorioeventos.totalPagarFornecedores.CalculadoraTotalPagarFornecedores;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.GiroEvento;
import br.com.sysloccOficial.model.InfoInterna;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.RelatorioEventos;


@Component
@Transactional
public class AtualizaRelatorioEventoApoio{

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
	
			RelatorioEventos relatorio = relatorioDAO.relatorioEventoPorIdLista(idLista);
			
			RelatorioEventos novoRelatorio = new RelatorioEventos();
			
			novoRelatorio.setAnoEvento(ano);
			novoRelatorio.setMesEvento(mes);
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
			
			novoRelatorio.setImpostoSobreValorLoccoAgencia(CalculadoraImposto.calculaImpostoSobrevalorLoccoAgencia(
															novoRelatorio.getValorLoccoAgenc(), 
															new BigDecimal(infoInterna.getImpostoInterna()/100)));

			novoRelatorio.setValorLiquido(novoRelatorio.getValorLoccoAgenc().subtract(novoRelatorio.getImpostoSobreValorLoccoAgencia()));
			novoRelatorio.setImpostoClienteDiferenca(novoRelatorio.getImpostoCliente().subtract(novoRelatorio.getImpostoSobreValorLoccoAgencia()));
			novoRelatorio.setLiquidoImposto(novoRelatorio.getValorLoccoAgenc().subtract(novoRelatorio.getImpostoSobreValorLoccoAgencia()));
			novoRelatorio.setTotalAPagarFornecedores(CalculadoraTotalPagarFornecedores.calculaTotal(relatorioBVS));
			
			BigDecimal totalDiferencaSemTelefone = CalculadoraDiferencaTelefone.totalDiferencaSemTelefone(
														relatorioBVS,
														novoRelatorio.getFee(),
														novoRelatorio.getFeeReduzido(), 
														novoRelatorio.getImpostoClienteDiferenca());
			
// -------- Calculo de Caches sem telefone	---- //		
			novoRelatorio.setTotalCachesSemTelefone(CalculadoraCachesTotais.totalCachesSemTelefone(listaRelatorioCaches, totalDiferencaSemTelefone));
			
//--------- Giro Sem Telefone ------------------ //
			BigDecimal giroSTelef = CalculadoraGiro.calculadoraGiroSemTelefone(novoRelatorio.getValorLiquido(),
	                          												   novoRelatorio.getTotalCachesSemTelefone(),
	                          												   CalculadoraTotalPagarFornecedores.calculaTotal(relatorioBVS));
//--------- Margem Contribuição ---------------- //
			novoRelatorio.setMargemContribuicao(giroSTelef.multiply(new BigDecimal("0.2")));
			
//  ------  Calcula o Custo do Telefone do Mês corrente
			 
			Integer idRelatorioParaGiroTelefone = null;
			idRelatorioParaGiroTelefone = VerificaSeRelatorioEventoExiste.verificaSeRelaStorioEventoExiste(relatorio, novoRelatorio);
			
			//Custo 
			BigDecimal custoTelefone = CalculadoraTelefone.calculaValorTelefone(analiticoDAO, relatorioDAO,giroSTelef, idRelatorioParaGiroTelefone, mes, ano);
			
			novoRelatorio.setCustoTelefone(custoTelefone);
			
// -------  Calcula totalExternas
			BigDecimal totalExterna = novoRelatorio.getMargemContribuicao().add(novoRelatorio.getCustoTelefone().add(CalculadoraTotalPagarFornecedores.calculaTotal(relatorioBVS)));
			novoRelatorio.setPgtoExternas(totalExterna);
			
			BigDecimal totalDiferencaComTelefone =  CalculadoraDiferencaTelefone.totalDiferencaComTelefone(
													   relatorioBVS, novoRelatorio.getFee(),
													   novoRelatorio.getFeeReduzido(),novoRelatorio.getImpostoClienteDiferenca(),
													   novoRelatorio.getMargemContribuicao(), custoTelefone);

			novoRelatorio.setTotalDiferenca(totalDiferencaComTelefone);

// -------  Total Cache			
			novoRelatorio.setCacheEquipIn(CalculadoraCaches.calculaCacheEquipeInterna(listaRelatorioCaches, totalDiferencaComTelefone));
			novoRelatorio.setTotalCachesIntExt(CalculadoraCaches.calculaCacheEquipeInterna(listaRelatorioCaches, totalDiferencaComTelefone));
			
			novoRelatorio.setDiretoria1(CalculadoraCaches.calculaCacheDiretoria(listaRelatorioCaches, totalDiferencaComTelefone, TipoCache.DIRETORIA1));
			novoRelatorio.setDiretoria2(CalculadoraCaches.calculaCacheDiretoria(listaRelatorioCaches, totalDiferencaComTelefone, TipoCache.DIRETORIA2));
			
			novoRelatorio.setDiferencaCacheFuncionariosTotalPgto(
					novoRelatorio.getTotalDiferenca().subtract(novoRelatorio.getTotalCachesIntExt()));
			
			novoRelatorio.setTotalCache(novoRelatorio.getDiretoria1().add(novoRelatorio.getDiretoria2().add(novoRelatorio.getTotalCachesIntExt())));
			novoRelatorio.setTotalCachesComTelefone(novoRelatorio.getCacheEquipIn().add(novoRelatorio.getDiretoria1().add(novoRelatorio.getDiretoria2())));
			
			//Calcula giro Com telefone
			BigDecimal giroComTelefone = novoRelatorio.getValorLiquido()
									    .subtract(novoRelatorio.getTotalCachesComTelefone())
					                    .subtract(novoRelatorio.getPgtoExternas());
			
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
			try {
				relatorioDAO.salvaCacheDoEvento(novoRelatorio);
			} catch (Exception e) {
				System.out.println("Deu um erro aqui ao atualizar Cache do Vento");
			}
			
			
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
			try {
				relatorioDAO.salvaCacheDoEvento(novoRelatorio);
			} catch (Exception e) {
				System.out.println("Deu um erro aqui ao atualizar Cache do Vento");
			}
			
		}
		
	}
}
