package br.com.sysloccOficial.ListaProducao.Excel;

public class ChainContratadoBayer implements ChainCategoriaImposto{
	private ChainCategoriaImposto proximo;
	@Override
	public String verificaCategoria(boolean imposto, boolean incideAdministracao, boolean feeReduzido,boolean reembolsoDespesas) {
		if(feeReduzido == true && incideAdministracao == true && imposto == false){
			return "Contratado pela Bayer";
		}else{
			return proximo.verificaCategoria(imposto,incideAdministracao,feeReduzido,reembolsoDespesas);
		}
	}
	@Override
	public void setProximo(ChainCategoriaImposto proximo) {
		this.proximo = proximo;
	}
}
