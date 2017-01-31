package br.com.sysloccOficial.controller.notificacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.DetalhesItemProducao;
import br.com.sysloccOficial.model.Usuario;



@Controller
public class ControllerNotificao {

	@Autowired private Utilitaria util;
	@PersistenceContext	private EntityManager manager;
	
	
	@RequestMapping("/notificacao")
	@ResponseBody
	public String notificacao(){
		
		Gson gson = new Gson();
		DetalhesItemProducao pendenc = new DetalhesItemProducao();	
		
		Usuario u = util.retornaUsuarioLogado();
		
		String consulta = "SELECT count(idcriacaoitem) FROM CriacaoItemPendencia"
					    + " where responsavelItem ="+u.getIdUsuario()+" and idCriacaoItemStatus <> 2";
	    TypedQuery<Long> q = 	manager.createQuery(consulta, Long.class);
	    long qtdPendencia = q.getSingleResult();
	    

	    String consultatarefas = "SELECT count(idcriacaoitemLista) FROM CriacaoItemLista"
			    + " where itemDelegado = 1 and responsavelItem ="+u.getIdUsuario()+" and idCriacaoItemStatus <> 2 and idCriacaoItemStatus <> 5";
		TypedQuery<Long> tq = 	manager.createQuery(consultatarefas, Long.class);
		
		long qtdTarefas = tq.getSingleResult();

		
		
		String pendencias = String.valueOf(qtdPendencia);
		String tarefas = String.valueOf(qtdTarefas);
	    
		Integer somaPend = Integer.parseInt(pendencias);
		Integer somaTar = Integer.parseInt(tarefas);
		Integer soma = somaTar + somaPend;
		
	    pendenc.setX(pendencias);
	    pendenc.setY(tarefas);
	    pendenc.setZ(soma.toString());
	    
	    return gson.toJson(pendenc);
	}
	
	
	
	
	
	
}
