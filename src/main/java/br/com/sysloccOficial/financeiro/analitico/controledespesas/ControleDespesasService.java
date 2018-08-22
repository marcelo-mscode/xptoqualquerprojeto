package br.com.sysloccOficial.financeiro.analitico.controledespesas;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	
	
	public void salvaNovoControleDespesas(ControleDespesas controleNovo) throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new java.sql.Date(format.parse(controleNovo.getDataTransiente()).getTime());
		
		controleNovo.setData(data);
		
		try {
			controleRepository.salvaNovoControle(controleNovo);
		} catch (Exception e) {
			System.out.println("Ocorre um erro ao salvar novo Controle de despesas: " +e);
		}
		
	}
	
	
}
