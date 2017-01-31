package br.com.sysloccOficial.criacao.controllerCriacao.itens;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.JobStatusDAO;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.CriacaoLista;


@Controller
@Transactional
public class CriacaoListaController extends Criacao{
	
	@Autowired AuxModelAndView auxMAV;
	@Autowired UsuarioDAO usuarioDAO;
	@Autowired private JobStatusDAO jobStatusDAO;
	@Autowired private Utilitaria util;
	@Autowired private EmpresaDAO empresaDAO;
	
	
	ModelAndView MV = new ModelAndView();

	@RequestMapping("/testeCriacao")
	public ModelAndView testeCriacao(){
		return  auxMAV.lista("criacao/testeCriacao");
	}
	@RequestMapping("/criaLista")
	public ModelAndView criaLista(Integer idLista, Integer idGrupo){
		MV.setViewName("producao/criacaoLista");
		auxMAV.verificaSeListaExiste(idLista,idGrupo);
		return MV;
	}
	
	@RequestMapping("/fechaListas")
//	@CacheEvict(value="listaCriacao", allEntries=true)
	public ModelAndView fechaLista(Integer idCriacaoLista) throws ParseException{

		MV.setViewName("producao/listaConcluida");
		MV.addObject("categoria", "Lista fechada com sucesso !");
		
		auxMAV.fechaListaCriacao(idCriacaoLista);
		return MV;
	}
	
	@RequestMapping("/exibeLista")
	public ModelAndView exibeLista() throws ParseException{
		MV.setViewName("criacao/criacaoListas");

		List<CriacaoLista> Criacaolista = exibeCriacaoListas();
	
		List<CriacaoLista> CriacaolistasFechadas = exibeCriacaoListasFechadas();
		
// --------------------------------------------------------------------------------------------------------------- //		
		List<Object> dataMenos3 = new ArrayList<Object>();
		
		for (int f =0; f < Criacaolista.size();f++) {
			ArrayList<Object> i = new ArrayList<Object>();
			
			i.add(Criacaolista.get(f).getListaProducao().getIdJob().getIdJob());
		
			Calendar cal = Calendar.getInstance();
			
			if(Criacaolista.get(f).getListaProducao().getIdJob().getPropostaData() == null){
				cal.setTime(util.subtraiDias(Calendar.getInstance().getTime()));
			}else{
				cal.setTime(util.subtraiDias(Criacaolista.get(f).getListaProducao().getIdJob().getPropostaData()));
			}
			
			
			
			i.add(Utilitaria.checaFDS(cal).getTime());
			
			//Date T = util.subtraiDias(Criacaolista.get(f).getListaProducao().getIdJob().getPropostaData());
			
			List<Integer> dataPrazo = util.FormataDataJoda(cal.getTime());
			
			i.add(dataPrazo);
			
			
			dataMenos3.add(i);
		}
		
		
// --------------------------------------------------------------------------------------------------------------- //		
		
		
		List<Object> dataJoda = new ArrayList<Object>();

		for (int f =0; f < Criacaolista.size();f++) { 
			ArrayList<Object> i = new ArrayList<Object>();
			
			i.add(Criacaolista.get(f).getListaProducao().getIdJob().getIdJob()); // Adiciona Id do Job
			i.add(jobStatusDAO.listaDatas(Criacaolista.get(f).getListaProducao().getIdJob().getIdJob())); // Adiciona a data da proposta
			
			
			
			Date dataProposta = jobStatusDAO.listaDatas(Criacaolista.get(f).getListaProducao().getIdJob().getIdJob());
			
			List<Integer> dataPrazo = new ArrayList<Integer>();
			
			if(dataProposta == null){
				dataPrazo = util.FormataDataJoda(Calendar.getInstance().getTime());
			}else{
				dataPrazo = util.FormataDataJoda(dataProposta);
			}
			
			i.add(dataPrazo); // Adiciona a diferenca de datas em minutos, horas e dias

			dataJoda.add(i);
		}
			
//TODO Alerta para exibir quantos dias faltam para Apresentação de proposta do JOB		
		
		List<Object[]> empresas = empresaDAO.mostraEmpresaJob();
		
//		MV.addObject("usuario", userNivel());
		MV.addObject("listaUsuarios", usuarioDAO.usuariosProducao());
		MV.addObject("lista", Criacaolista);
		MV.addObject("listasFechadas", CriacaolistasFechadas);
		MV.addObject("empresas",  empresas);
		
		
		MV.addObject("joda", dataJoda);
		MV.addObject("datas", dataMenos3);
		
		return MV;
	}
	
	@RequestMapping("/mudaResponsavel")
	public String mudaResponsavelR(Integer idResponsavel,Integer idListaCriacao){
		mudaResponsavel(idResponsavel,idListaCriacao);
		return "redirect:exibeLista";
	}
	
	@RequestMapping("/mudaPar")
	public String mudaParP(Integer idResponsavel,Integer idListaCriacao){
		mudaPar(idResponsavel,idListaCriacao);
		return "redirect:exibeLista";
	}
	@RequestMapping("/exibeEmpresaCriacao")
	public ModelAndView exibeEmpresaCriacao(Integer idEmpresa){
		MV.setViewName("criacao/listasFechadas/empresas");
		List<CriacaoLista> CriacaolistasFechadas = exibeCriacaoListasFechadasPorEmpresa(idEmpresa);
        MV.addObject("listaFechadaEmpresa", CriacaolistasFechadas);
		return MV;
	}
	
	@RequestMapping("/revisaoLista")
	public void revisaoLista(Integer idLista){
	//	JOptionPane.showMessageDialog(null, "Revisão Lista"+idLista);
		
	}
	
	
}






















