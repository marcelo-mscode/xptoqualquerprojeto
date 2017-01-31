package br.com.sysloccOficial.ListaProducao.Excel;

public class ChainCustoInterno implements ChainCategoriaImposto{

	private ChainCategoriaImposto proximo;
	
	@Override
	public String verificaCategoria(boolean imposto,boolean incideAdministracao,boolean feeReduzido) {
		if(imposto == true && incideAdministracao == false){
			return "Custo Interno";
		}else{
			return proximo.verificaCategoria(imposto,incideAdministracao,feeReduzido);
		}
	}
	@Override
	public void setProximo(ChainCategoriaImposto proximo) {
		this.proximo = proximo;
	}
}
