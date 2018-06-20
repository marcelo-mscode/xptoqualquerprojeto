package br.com.sysloccOficial.ListaProducao.Excel;

public class ChainVerificaCategoriaImposto {

	public String verifica(boolean imposto,boolean incideAdministracao,boolean feeReduzido,boolean reembolsoDespesas){
		ChainCategoriaImposto T1 = new ChainTerceiro();
		ChainCategoriaImposto T2 = new ChainCustoInterno();
		ChainCategoriaImposto T3 = new ChainFatDireto();
		ChainCategoriaImposto T4 = new ChainContratadoBayer();
		ChainCategoriaImposto T5 = new ChainSemCategoriaPrevista();
		T1.setProximo(T2);
		T2.setProximo(T3);
		T3.setProximo(T4);
		T4.setProximo(T5);
		return T1.verificaCategoria(imposto,incideAdministracao,feeReduzido,reembolsoDespesas);
	}
}
