package br.com.sysloccOficial.ListaProducao.Excel;

public class ChainTerceiro implements ChainCategoriaImposto{
	
	private ChainCategoriaImposto proximo;

	@Override
	public String verificaCategoria(boolean imposto,boolean incideAdministracao,boolean feeReduzido, boolean reembolsoDespesas) {
		if(imposto == true && incideAdministracao == true && feeReduzido == false){
			return "Terceiro";
		}else{
			return proximo.verificaCategoria(imposto,incideAdministracao,feeReduzido,reembolsoDespesas);
		}
	}
	
	@Override
	public void setProximo(ChainCategoriaImposto proximo) {
		this.proximo = proximo;
	}
}