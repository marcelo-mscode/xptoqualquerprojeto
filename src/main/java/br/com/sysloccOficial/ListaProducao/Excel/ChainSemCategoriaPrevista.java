package br.com.sysloccOficial.ListaProducao.Excel;

public class ChainSemCategoriaPrevista implements ChainCategoriaImposto{

private ChainCategoriaImposto proximo;
	
	@Override
	public String verificaCategoria(boolean imposto,boolean incideAdministracao,boolean feeReduzido, boolean reembolsoDespesas) {
		if(imposto == true && incideAdministracao == true && feeReduzido ==  true || imposto == false && incideAdministracao == false && feeReduzido ==  false ){
			return "Sem categoria de serviço cadastrado ( Tem Fee Reduzido e Imposto )";
		}else{
			return proximo.verificaCategoria(imposto,incideAdministracao,feeReduzido,reembolsoDespesas);
		}
	}
	
	@Override
	public void setProximo(ChainCategoriaImposto proximo) {
		this.proximo = proximo;
	}
}
