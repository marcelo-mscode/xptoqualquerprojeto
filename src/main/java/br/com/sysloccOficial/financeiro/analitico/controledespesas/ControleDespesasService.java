package br.com.sysloccOficial.financeiro.analitico.controledespesas;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysloccOficial.financeiro.model.ControleDespesas;

@Service
public class ControleDespesasService {

	
	@Autowired ControleDespesasRepository controleRepository;
	
	
	public List<ControleDespesas> listaControleDespesas () throws NullPointerException{
		List<ControleDespesas> lista = controleRepository.listaControleDespesas();
		return lista;
	}
	
	
	
}
