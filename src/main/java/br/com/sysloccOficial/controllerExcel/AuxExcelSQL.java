package br.com.sysloccOficial.controllerExcel;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.consultas.consultasAvancadas.ConsultasPassaSql;
import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.LocalEvento;

@Repository
@Transactional
public class AuxExcelSQL {
	
	@PersistenceContext	private EntityManager manager;
	
	ConsultasPassaSql c = new ConsultasPassaSql();

	
	/**
	 * Método para auxiliar na montagem das Categorias no corpo do Excel.
	 * 
	 * Caso exista um grupo "opcional" com o valor boolean TRUE, indica
	 * que esse grupo será opcional na planilha.  
	 * 
	 * Existem casos em que todos os itens da Categoria são marcados como
	 * Opcional ( true ), nesse caso o método retornará o valor Inteiro = 0  
	 */
	 public Integer opcional(Integer idCategoria){

		String g = "from Grupo where idcategoria = "+ idCategoria;
		TypedQuery<Grupo> q = manager.createQuery(g, Grupo.class);
		List<Grupo> grupos = q.getResultList();
		Integer r = 0;
		for(int i =0; i < grupos.size(); i++){
			if(grupos.get(i).isOpcional() == false){
				r = 1;
			}
		}
		return r;
	}
		
    
	/**
	* Método para retornar uma lista de Categorias de acordo com o idLista da Lista
	* 
	*/
	public List<Categoria> retornaCategoria(Integer idLista){
		TypedQuery<Categoria> q = manager.createQuery("select c from Categoria c where idLista = "+idLista+" order by categoriaOrdem", Categoria.class);
		return q.getResultList();
	}

	
	
	/**
	 * Método para retornar uma lista de Grupos de acordo com o idLista da Lista
	 * não Opcionais
	 */
	public List<Grupo> retornaGrupos(Integer idLista){
		TypedQuery<Grupo> grupos = manager.createQuery(
		"from Grupo where idLista ="+idLista+" and opcional = 0 order by ordemGrupo", Grupo.class);
		return grupos.getResultList();
	}

	/**
	 * Método para retornar uma lista de Grupos de acordo com o idLista da Lista
	 * ------> Opcionais
	 */
	public List<Grupo> retornaGruposOpcionais(Integer idLista){
		TypedQuery<Grupo> grupos = manager.createQuery(
				"from Grupo where idLista ="+idLista+" and opcional = 1 order by ordemGrupo", Grupo.class);
		return grupos.getResultList();
	}


	/**
	 * Método para retornar a soma do FEE por categoria
	 * 
	 * @param idCategoria
	 * @return BigDecimal
	 */
	public BigDecimal totalFeeCategoria(Integer idCategoria){
	TypedQuery<BigDecimal> bg = manager.createQuery(
	"select sum(grupoAdministracaoValor) from Grupo where idcategoria = "+idCategoria+"and opcional = 0", BigDecimal.class);
	return bg.getSingleResult();
	}

	/**
	 * Método para retornar a soma do Imposto por categoria
	 * 
	 * @param idCategoria
	 * @return BigDecimal
	 */
	public BigDecimal totalImposto(Integer idCategoria){
		TypedQuery<BigDecimal> bg = manager.createQuery(
		"select sum(grupoImpostoValor) from Grupo where idcategoria = "+idCategoria+" and opcional = 0", BigDecimal.class);
		return bg.getSingleResult();
	}

	
	/**
	 * Método para retornar a soma do CustoAgencia da Categoria
	 * São Linha que não incide Administracao e não são opcionais
	 * 
	 * @param idCategoria
	 * @return BigDecimal
	 */
	public BigDecimal totalCustoAgencia(Integer idCategoria){
		BigDecimal result = new BigDecimal("0");
		TypedQuery<BigDecimal> bg = manager.createQuery(
				"select sum(grupoValorIncideImposto+grupoValorNaoIncideImposto) from Grupo where idcategoria = "+idCategoria+""
						+ " and opcional = 0"
						+ " and incideAdministracao = false", BigDecimal.class);
		
		if(bg.getSingleResult() == null){
			return result;
		}else{
			result = bg.getSingleResult();
			return result;
		}
	}
	
	
	
	/**
	 * Método para retornar a soma de SubContratado
	 * São Linhas que incide Administracao e não são opcionais
	 * Refere-se a grupoValorIncideImposto no Banco de dados
	 * 
	 * @param idCategoria
	 * @return BigDecimal
	 */
	public BigDecimal totalSubContratado(Integer idCategoria){
		BigDecimal result = new BigDecimal("0");
		TypedQuery<BigDecimal> bg = manager.createQuery(
				"select sum(grupoValorIncideImposto) from Grupo where idcategoria = "+idCategoria+""
						+ " and opcional = 0"
						+ " and incideAdministracao = true", BigDecimal.class);
		
		if(bg.getSingleResult() == null){
			return result;
		}else{
			result = bg.getSingleResult();
			return result;
		}
	}

	
	
	/**
	 * Método para retornar a soma de SubContratado
	 * São Linhas que incide Administracao e não são opcionais
	 * Refere-se a grupoValorNaoIncideImposto no Banco de dados
	 * 
	 * @param idCategoria
	 * @return BigDecimal
	 */
	public BigDecimal totalFatDireto(Integer idCategoria){
		BigDecimal result = new BigDecimal("0");
		TypedQuery<BigDecimal> bg = manager.createQuery(
				"select sum(grupoValorNaoIncideImposto) from Grupo where idcategoria = "+idCategoria+""
						+ " and opcional = 0"
						+ " and incideAdministracao = true", BigDecimal.class);
		
		if(bg.getSingleResult() == null){
			return result;
		}else{
			result = bg.getSingleResult();
			return result;
		}
	}

	
	/**
	 * Método para retornar o SubTotal de todas as linhas da Categoria
	 * O sub Total é composto da soma de:
	 * custoAgencia + subContratado + FatDireto
	 * de todas as linhas da Categoria
	 * 
	 * @param idCategoria
	 * @return BigDecimal
	 */
	public BigDecimal subTotal(Integer idCategoria){
		
		BigDecimal custoAgencia = totalCustoAgencia(idCategoria);
		BigDecimal subContratado = totalSubContratado(idCategoria);
		BigDecimal FatDireto = totalFatDireto(idCategoria);
		
		BigDecimal total = custoAgencia.add(subContratado).add(FatDireto);
		
		return total;
	}

	
	/**
	 * Método para retornar o Total de todas as linhas da Categoria
	 * O Total é composto da soma de:
	 * subTotal + SomaFee + SomaFatDireto
	 * de todas as linhas da Categoria
	 * 
	 * @param idCategoria
	 * @return BigDecimal
	 */
	public BigDecimal total(Integer idCategoria){
		
		BigDecimal total;
		
		BigDecimal custoAgencia = totalCustoAgencia(idCategoria);
		BigDecimal subContratado = totalSubContratado(idCategoria);
		BigDecimal FatDireto = totalFatDireto(idCategoria);
	
		BigDecimal subTotal = custoAgencia.add(subContratado).add(FatDireto);
		BigDecimal totalFee = totalFeeCategoria(idCategoria);
		BigDecimal totalImposto = totalImposto(idCategoria);
		
		total = subTotal.add(totalFee).add(totalImposto);
		
		return total;
	}
	
	/**
	 * Método para retornar a soma do CustoAgencia de todas as Categoria
	 * São Linha que não incide Administracao e não são opcionais
	 * 
	 * @param idCategoria
	 * @return BigDecimal
	 */
	public BigDecimal somatotalCustoAgenciaTodasCategorias(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		String consulta = "select sum(grupoValorIncideImposto) from Grupo where idLista=1906 and opcional = 0 and incideAdministracao = false";

		
		try {
			TypedQuery<BigDecimal> bg = manager.createQuery(consulta, BigDecimal.class);
			result = bg.getSingleResult();
			return result;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Rsuklt: "+e);
			return result;
		}
	}
	
	/**
	 * Método para retornar a soma de SubContratado de todas as Categoria
	 * São Linha que Incide Administracao e não são opcionais
	 * 
	 * @param idCategoria
	 * @return BigDecimal
	 */
	public BigDecimal somatotalSubContratadoTodasCategorias(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		try {
			TypedQuery<BigDecimal> bg = manager.createQuery(
					"select sum(grupoValorIncideImposto) from Grupo where idLista="+idLista
					+" and opcional = 0 and incideAdministracao = true", BigDecimal.class);
			result = bg.getSingleResult();
			return result;
		} catch (Exception e) {
			return result;
		}
	}

	
	/**
	 * Método para retornar as datas cadastradas em Local do Evento de Job
	 * 
	 * @param idCategoria
	 * @return BigDecimal
	 */
	public List<LocalEvento> listaEventosJob(Integer idJob){
		String consulta = "select l  from LocalEvento l where l.idJob="+idJob;

		try {
			TypedQuery<LocalEvento> q  = manager.createQuery(consulta,LocalEvento.class);
			List<LocalEvento> lista = q.getResultList();
			return lista;
		
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	
	
	
	/**
	 * Retonar uma Lista pelo Id
	 * 
	 */
	public Lista lista(Integer idLista){
		Lista q = manager.find(Lista.class, idLista);
		return q;
	}
	

}
