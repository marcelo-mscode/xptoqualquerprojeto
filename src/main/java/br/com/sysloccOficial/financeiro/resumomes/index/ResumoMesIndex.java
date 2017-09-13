package br.com.sysloccOficial.financeiro.resumomes.index;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.model.FinancAnalitico;


@Controller
public class ResumoMesIndex {
	
	@PersistenceContext	private EntityManager manager;
	
	@RequestMapping("resumoMesIndex")
	public ModelAndView resumoMesIndex(){
		ModelAndView MV = new ModelAndView("financeiro/resumoMes/index/resumoMesIndex");
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> c = cb.createQuery(Tuple.class);
		Root<FinancAnalitico> financAnalit = c.from(FinancAnalitico.class);
		c.multiselect(financAnalit.<String>get("anoA").alias("analitico.ano"),
 					  financAnalit.<String>get("mesA").alias("analitico.mes"),
				      financAnalit.<String>get("mesReferencia").alias("analitico.mesReferencia")
					  );
	
		c.orderBy(cb.desc(financAnalit.<String> get("mesReferencia")));
		
		
		TypedQuery<Tuple> query = manager.createQuery(c);
		List<Tuple> resultado = query.getResultList();

		MV.addObject("analitico", resultado);
		
		
		TypedQuery<String> aaa = manager.createQuery("select distinct(anoA) from FinancAnalitico order by anoA desc", String.class);
		List<String> anos = aaa.getResultList();
		
		MV.addObject("anos", anos);
		
		
		
		
		
		
		
		
		
		
		
		return MV;
	}
	
	
	
}
