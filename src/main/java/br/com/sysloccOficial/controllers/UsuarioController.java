package br.com.sysloccOficial.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.DepartamentoDAO;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.Role;
import br.com.sysloccOficial.model.User;
import br.com.sysloccOficial.model.Usuario;



@Controller
@Transactional
public class UsuarioController {
	
	@Autowired private UsuarioDAO usuarioDAO;
	@Autowired private DepartamentoDAO departamentoDAO;
	@Autowired private Utilitaria util;
	@PersistenceContext	private EntityManager manager;

	
	
	@RequestMapping("/usuario")
	public ModelAndView user(Usuario usuario) {
		
		ModelAndView mv = new ModelAndView("usuarios/usuario");
		mv.addObject("departamento",departamentoDAO.mostra());
	    return mv;
	}

	@RequestMapping("/salvaUsuario")
	public ModelAndView salva(Usuario u, RedirectAttributes rd){
		
		u.setHabilitado(util.verificaBoolean(u.getHabilitado()));
	
		String novaSenha = util.hashSenha(u.getSenha());
		
		User user = new User();

		if( u.getNivel() == 2 ){
			List<Role> role = usuarioDAO.roleAdmin();
			user.setRoles(role);
		}else { // Se nivel usuario
			List<Role> role = usuarioDAO.roleUsuario();
			user.setRoles(role);
		}
		
		user.setLogin(u.getUsuario());
		user.setPassword(novaSenha);
		user.setName(u.getNome());
		
		usuarioDAO.salvaUser(user,u);
		
		u.setUserNovo(user);
		u.setSenha(novaSenha);
		
		rd.addFlashAttribute("sucesso", "Usuario Cadastrado com sucesso !");
		
		usuarioDAO.salva(u);
	    ModelAndView mv = new ModelAndView("redirect:usuario");
		return mv;
	}
	
	@RequestMapping("/listausuario")
	public ModelAndView lista(){
		ModelAndView mv = new ModelAndView("usuarios/listaUsuarios");
		mv.addObject("usuarios", usuarioDAO.mostra());
		
		return mv;
	}
	
	@RequestMapping("/informacaoUsuario")
	public ModelAndView usuarios(Integer id) throws InterruptedException{
		ModelAndView mv = new ModelAndView("usuarios/modalUsuarios");
		
		Usuario u = manager.find(Usuario.class, id);
		
		mv.addObject("usuario", u);
/*		mv.addObject("usuarios", usuarioDAO.listaPorId(id));*/		
		mv.addObject("departamentos", usuarioDAO.listaDepartamento());
		
		
		new Thread();Thread.sleep(300);  
		return mv;
	}
	
	@RequestMapping("/atualizaUsuario")
	public ModelAndView atualizaUsuario(Usuario usuario){
		
		
       
		
	    String confere = usuario.getUserNovoTransiente();	
		String nulo = "nulo";
		
		if(confere.equals(nulo)){
			// Cria novo User
			User user = new User();
			if (usuario.getSenha() == null || usuario.getSenha() == "" || usuario.getSenha() == " ") { // Se senha ficar em branco
				
				Usuario u = manager.find(Usuario.class, usuario.getIdUsuario());
				
				String novaSenha = util.hashSenha(u.getSenha());
				user.setPassword(novaSenha);
				usuario.setSenha(novaSenha);
			
			} else { // Se trocar Senha
			
				String novaSenha = util.hashSenha(usuario.getSenha());
				usuario.setSenha(novaSenha);
				user.setPassword(novaSenha);
			
			}

//			 JOptionPane.showMessageDialog(null, "Nivel Acesso: "+ usuario.getNivelTransiente());
			
			
			if (usuario.getNivelTransiente() == 2) {
				List<Role> role = usuarioDAO.roleAdmin();
				user.setRoles(role);
				usuario.setNivel(usuario.getNivelTransiente());
			} else {
				List<Role> role = usuarioDAO.roleUsuario();
				user.setRoles(role);
				usuario.setNivel(usuario.getNivelTransiente());
			}

			user.setLogin(usuario.getUsuario());
			user.setName(usuario.getNome());
			manager.persist(user);

			usuario.setUserNovo(user);
			
		}else{
		
			// Atualiza USer

			User user = manager.find(User.class,usuario.getUserNovoTransiente());
			user.setLogin(usuario.getUsuario());
			user.setName(usuario.getNome());
			
				if (usuario.getSenha() == null || usuario.getSenha() == ""
						|| usuario.getSenha() == " ") { // Se senha ficar em branco
					usuario.setSenha(user.getPassword());
				} else { // Se trocar Senha
					String novaSenha = util.hashSenha(usuario.getSenha());
					usuario.setSenha(novaSenha);
					user.setPassword(novaSenha);
				}
			
				usuario.setUserNovo(user);
	//			JOptionPane.showMessageDialog(null, ""+usuario.getNivelTransiente());			
			
				if (usuario.getNivelTransiente() == 2) { // Se nivel Admin
					List<Role> role = usuarioDAO.roleAdmin();
					usuario.setNivel(usuario.getNivelTransiente());
					user.setRoles(role);
				} else { // Se nivel usuario
					List<Role> role = usuarioDAO.roleUsuario();
					user.setRoles(role);
					usuario.setNivel(usuario.getNivelTransiente());
				}
			manager.merge(user);
		}
		
		usuario.setHabilitado(util.verificaBoolean(usuario.getHabilitado()));
 	    usuarioDAO.atualiza(usuario);
	    return lista();
	}

	
	
	
	/*@RequestMapping("/removeUsuario") // Método inapropriado - O usuário poderá apenas ser desativado.
	public String removeUsuario(Integer id, RedirectAttributes rd){
		usuarioDAO.arquivoX(id);
		
		ArrayList<String> e = new ArrayList<String>();
		e.add("sucesso");
		e.add("Usuario Excluido com sucesso");
		
		rd.addFlashAttribute("msg", e);
		
		
		return "redirect:listausuario";
	}*/

	
	@RequestMapping("/verificaLogin") // Verifica se login para novo usuario está disponível
	@ResponseBody
	public Integer verificaLogin(String loginUsuario){
			Integer confirma = usuarioDAO.verificaUser(loginUsuario);
		return confirma;
	}
	
	
	
	
}
