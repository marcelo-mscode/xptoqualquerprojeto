package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.User;

@Repository
public class UserDAO implements UserDetailsService{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		String jpql = "select u from User u where u.login = :login";
		
		List<User> users = manager.createQuery(jpql,User.class).setParameter("login", username).getResultList();
		
		if(users.isEmpty()){
			throw new UsernameNotFoundException("O Usuário "+username+" não está cadastrado.");
		}
		return users.get(0);
	}

}
