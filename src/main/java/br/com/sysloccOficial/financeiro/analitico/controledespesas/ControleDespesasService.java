package br.com.sysloccOficial.financeiro.analitico.controledespesas;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysloccOficial.financeiro.model.ControleDespesas;

@Service
public class ControleDespesasService {

	@PersistenceContext	private EntityManager manager;
	@Autowired ControleDespesasRepository controleRepository;
	
	
	public List<ControleDespesas> listaControleDespesas () throws NullPointerException{
		List<ControleDespesas> lista = controleRepository.listaControleDespesas();
		return lista;
	}
	
	
	
	public void salvaNovoControleDespesas(ControleDespesas controleNovo) throws ParseException{
		
		
		try {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date data = new java.sql.Date(format.parse(controleNovo.getDataTransiente() + " 00:00:00").getTime());
		controleNovo.setData(data);
		
		manager.persist(controleNovo);
	
//		controleRepository.salvaNovoControle(controleNovo);
		} catch (Exception e) {
			System.out.println("Ocorre um erro ao salvar novo Controle de despesas: " +e);
		}
		
	}
	
	
}
