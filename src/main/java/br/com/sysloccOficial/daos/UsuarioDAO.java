package br.com.sysloccOficial.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.Departamento;
import br.com.sysloccOficial.model.DeterminaQuantidades;
import br.com.sysloccOficial.model.Producao;
import br.com.sysloccOficial.model.Role;
import br.com.sysloccOficial.model.User;
import br.com.sysloccOficial.model.Usuario;

@Repository
public class UsuarioDAO{

	@PersistenceContext
	private EntityManager manager;
	//@Autowired private Utilitaria util;
	
	
	public void salva(Usuario u){
		Departamento d = manager.find(Departamento.class, u.getIdDep());
		u.setDepartamento(d);
        manager.persist(u);
		
	}
	
	public void salvaUser(User user,Usuario u){
		
	    manager.persist(user);
	}
	
	
	
	public List<Usuario> usuarioPorDepartamento(Integer idDepartamento) {
//		return manager.createQuery("select f from Usuario f where idDepartamento="+idDepartamento+" and"
		return manager.createQuery("select f from Usuario f where habilitado = 1 order by habilitado desc, nome",Usuario.class).getResultList();
    }
	
	public List<Usuario> mostra() {
		return manager.createQuery("select f from Usuario f order by habilitado desc, nome",Usuario.class)
		/*.setMaxResults(10)		*/
		.getResultList();
    }

	public List<Usuario> listaPorId(Integer i){
		   Query query = manager.createQuery("select f from Usuario f where idUsuario=:idUsuario",Usuario.class);	
		   query.setParameter("idUsuario", i);
		   return query.getResultList();
	}

	public List<Departamento> listaDepartamento(){
		return manager.createQuery("from Departamento",Departamento.class)
				.getResultList();
		}
	
	public List<Usuario> mostraHabilitados() {
		Query query = manager.createQuery("from Usuario where habilitado = true order by nome",Usuario.class);
		return query.getResultList();
    }
	
	
	public void atualiza(Usuario u){
		Departamento d = manager.find(Departamento.class, u.getIdDep());
		u.setDepartamento(d);
		manager.merge(u);
	}

	/*public void arquivoX(Integer u) {
		Usuario usuario = manager.find(Usuario.class, u);
		
//		String login = usuario.getUserNovo().getLogin();
		String novaSenhaX = "abacabbCodigoParaSangueMortalCombate1MasterSystem";
//		JOptionPane.showMessageDialog(null, ""+user.getNome());

		usuario.setNivel(1);
		usuario.setArquivoX(3);
		usuario.setHabilitado(false);
		usuario.setSenha(novaSenhaX);
		
		String acesso ="";
		
		try {
			acesso = usuario.getUserNovo().getLogin();
		} catch (Exception e) {
			acesso = "vazio";// TODO: handle exception
		}
		
		if (acesso.equals("vazio")) {
			manager.merge(usuario);
		}else{

			User user = manager.find(User.class,usuario.getUserNovo().getLogin());
			String novaSenha = util.hashSenha(novaSenhaX);
			usuario.setSenha(novaSenha);
			user.setPassword(novaSenha);
			manager.merge(user);
			manager.merge(usuario);
		}
		
		
		

	}*/
	
	public List<String> emailUser(String i){
		String consulta = "select distinct(u.email) from Usuario u where email <> '' order by nome";
		TypedQuery<String> query = manager.createQuery(consulta,String.class);
		return query.getResultList();
	}
	
	
	/**
	 * Método que retorna todos os endereços de email dos usuários 
	 * do sistema que estão ativos no momento.
	 * 
	 * O sistema não tras endereço de email que estiver com o campo
	 * não preenchido, ou seja, se um usuário foi cadastrado sem colocar
	 * seu endereço de email o sistema não irá trazer o campo em branco,
	 * e esse usuário não receberá email nenhum.
	 * 
	 * @return List<String>
	 */
	public List<String>AllEmailUsers(){
		String consulta = "select distinct(u.email) from Usuario u where email <> '' and habilitado = 1 order by email";
		TypedQuery<String> query = manager.createQuery(consulta,String.class);
		return query.getResultList();
	}
	 

	public List<String> someEmailUser(List<Integer> i){
		String consulta = "select distinct(u.email) from Usuario u where email <> '' and  u.idUsuario in (:id) order by nome";
		TypedQuery<String> query = manager.createQuery(consulta,String.class);
		query.setParameter("id", i);
		return query.getResultList();
	}

	public String SelectSingleEmail(Integer integer){
		String consulta = "select distinct(u.email) from Usuario u where email <> '' and  u.idUsuario=:id";
		TypedQuery<String> query = manager.createQuery(consulta,String.class);
		query.setParameter("id", integer);
	return query.getSingleResult();
    }
	
	public List<Object[]> usuariosProducao(){
		String consulta = "select u.idUsuario, u.nome from Usuario u where habilitado = true order by nome";
		Query query = manager.createQuery(consulta);
		List<Object[]> lista = query.getResultList();
		return lista;
	}

	public List<Object[]> cachePadraoNomes(int idRelatorioEventos){
		String consulta = "select u.idCachePadrao, u.nomeFunc from CachePadrao u where habilitado = true order by nomeFunc";
		Query query = manager.createQuery(consulta);
		List<Object[]> lista = query.getResultList();
		return lista;
	}

	public List<Object[]> usuariosProducaoteste(){
		String consulta = "select u.idUsuario, u.nome from Usuario u order by nome";
		Query query = manager.createQuery(consulta);
		List<Object[]> lista = query.setMaxResults(4).getResultList();
		return lista;
	}
	
	public Producao usuarioResponsavel(Integer idLista){
		String consulta = "from Producao where idLista="+idLista;
		TypedQuery<Producao> query = manager.createQuery(consulta,Producao.class);
		return query.getSingleResult();
	}

	public Usuario usuarioPorNivel(Integer idNivel){
//		String consulta = "select u from Usuario u where idDepartamento = 7 and idUsuario = 28" + idNivel;
		String consulta = "select u from Usuario u where idDepartamento = 8 and idUsuario = 21";
		Query query = manager.createQuery(consulta);
		return (Usuario) query.getSingleResult();
	}
	
	public Integer verificaUser(String login){
		String consulta ="from User u where login like '"+login+"'";
		
			Query query = manager.createQuery(consulta);
			if(query.getResultList().isEmpty()){
				return 0;
			}else{
				return 1;
			}
	}
	
	public List<Role> roleAdmin(){
		String consulta = "SELECT r FROM Role r where name like 'ROLE_ADMIN'";
		Query query = manager.createQuery(consulta);
		return query.getResultList();
	}

	public List<Role> roleUsuario(){
		String consulta = "SELECT r FROM Role r where name like 'ROLE_USUARIO'";
		Query query = manager.createQuery(consulta);
		return query.getResultList();
	}
	
	public Usuario retornaUmUsuario(Integer idUsuario){
		return manager.createQuery("select f from Usuario f where idUsuario ="+idUsuario,Usuario.class)
		.getSingleResult();
	}
		
}
