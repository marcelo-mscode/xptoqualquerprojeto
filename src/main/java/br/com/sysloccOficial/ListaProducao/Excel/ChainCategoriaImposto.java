package br.com.sysloccOficial.ListaProducao.Excel;

import br.com.sysloccOficial.model.ProdutoGrupo;

public interface ChainCategoriaImposto {
		
	public String verificaCategoria(boolean imposto,boolean incideAdministracao,boolean feeReduzido, boolean reembolsoDespesas);
	void setProximo(ChainCategoriaImposto proximo);
	
}
