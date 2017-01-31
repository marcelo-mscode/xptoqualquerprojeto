package br.com.sysloccOficial.ListaProducao.Excel;

public class ChainFatDireto implements ChainCategoriaImposto{

private ChainCategoriaImposto proximo;
	@Override
	public String verificaCategoria(boolean imposto,boolean incideAdministracao,boolean feeReduzido) {
		if(imposto == false && incideAdministracao == true && feeReduzido ==  false){
			return "Faturamento Direto";
		}else{
			return proximo.verificaCategoria(imposto,incideAdministracao,feeReduzido);
		}
	}
	@Override
	public void setProximo(ChainCategoriaImposto proximo) {
		this.proximo = proximo;
	}
}
