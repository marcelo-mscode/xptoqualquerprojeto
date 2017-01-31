package br.com.sysloccOficial.model.prospeccao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.conf.UtilitariaDatas;

public class FiltraProspeccao extends FiltraInteracao{
	
	@Autowired UtilitariaDatas utilDatas;
	
	private boolean probabilidadeConfere;
	private String probabilidade;
	private String porcentagem;
	
	private boolean sInteracaoCon;
	private boolean semInteracaoConfere;
	private String semInteracao;
	
// ------------------------------------------------------------------------------------ //
	public String testaCampos(FiltraProspeccao f, String consulta)throws ParseException {
		consulta = testaEmpresa(consulta, f);
     	consulta = testaConcluidoProspeccao(consulta, f.isConcluidos());
		consulta = testaProbabilidade(consulta, f);
		consulta = testaSemInteracao (consulta, f);
		consulta = testaDataProspeccao(consulta, f);
		
		if(f.isDataConfere() != true){
			consulta = consulta +" order by ii.dataInteracao desc";
		}
		return consulta;
	}
	
	public String testaConcluidoProspeccao(String consulta, boolean concluido) {
		if(concluido == true){
			consulta = consulta + " and i.concluido = 1 ";
		}
		return consulta;
	}

	public String testaDataProspeccao(String consulta, FiltraInteracao f){
		if(f.isDataConfere() == true && f.getData() != null && f.getQtoDias() != null){
			if(f.getTipoData().equals("interacao")){
				consulta = consulta + "and ii.dataInteracao >= '"+f.getData()+"' order by ii.dataInteracao desc";
			}
			if(f.getTipoData().equals("agendamento")){
				consulta = consulta + "and ii.dataInteracao >= '"+f.getData()+"' and ii.dataProximaInteracao != '0001-01-01 00:00:00' order by dataInteracao desc";
			}
		}
		return consulta;
	}	
	
	public String testaProbabilidade(String consulta, FiltraProspeccao f) {
		if(f.isProbabilidadeConfere() == true && f.getProbabilidade() != "" && f.getPorcentagem()!= "") {
			consulta = consulta + " and i.probabilidade "+f.getProbabilidade()+" "+f.getPorcentagem()+" ";
		}
		return consulta;
	}

	public String testaSemInteracao(String consulta, FiltraProspeccao f) throws ParseException {
		if(f.issInteracaoCon() == true && f.getSemInteracao() != "") {
		
			
			Integer dias = Integer.parseInt(f.getSemInteracao());	
		
			Calendar calendarData = Calendar.getInstance();  
			
			int numeroDiasParaSubtrair = -dias;  
			
			calendarData.add(Calendar.DATE,numeroDiasParaSubtrair);  
			Calendar dataInicial = calendarData; 
		
			SimpleDateFormat dataf = new SimpleDateFormat("yyyy-MM-dd");  
			String result = dataf.format(dataInicial.getTime());  
			
			consulta = consulta + "and ii.dataInteracao <= '"+result+"'";
			
			if(f.isDataConfere() == false){
				consulta = consulta + " order by ii.dataInteracao desc";
			}
			
		}
		return consulta;
	}


	public List<Prospeccao> removeValoresRepetidosLista(List<Prospeccao> p) {
		for (int i = 0; i < p.size(); i++) {
			Object a = p.get(i);
			for (int j = i+1; j < p.size(); j++) {
				Object b = p.get(j);
				if (a.equals(b)) {
					p.remove(j);
					j--;
				}
			}
		}
		return p;
	}

	public List<Object> removeValoresRepetidosListaQualquer(List<Object> p) {
		for (int i = 0; i < p.size(); i++) {
			Object a = p.get(i);
			for (int j = i+1; j < p.size(); j++) {
				Object b = p.get(j);
				if (a.equals(b)) {
					p.remove(j);
					j--;
				}
			}
		}
		return p;
	}
	
// ------------------------------------------------------------------------------------ //

	public boolean isProbabilidadeConfere() {
		return probabilidadeConfere;
	}

	public boolean issInteracaoCon() {
		return sInteracaoCon;
	}

	public void setsInteracaoCon(boolean sInteracaoCon) {
		this.sInteracaoCon = sInteracaoCon;
	}

	public boolean isSemInteracaoConfere() {
		return semInteracaoConfere;
	}

	public void setSemInteracaoConfere(boolean semInteracaoConfere) {
		this.semInteracaoConfere = semInteracaoConfere;
	}

	public String getSemInteracao() {
		return semInteracao;
	}

	public void setSemInteracao(String semInteracao) {
		this.semInteracao = semInteracao;
	}

	public void setProbabilidadeConfere(boolean probabilidadeConfere) {
		this.probabilidadeConfere = probabilidadeConfere;
	}
	
	public String getProbabilidade() {
		return probabilidade;
	}
	
	public void setProbabilidade(String probabilidade) {
		this.probabilidade = probabilidade;
	}
	
	public String getPorcentagem() {
		return porcentagem;
	}
	
	public void setPorcentagem(String porcentagem) {
		this.porcentagem = porcentagem;
	}
	
















}
