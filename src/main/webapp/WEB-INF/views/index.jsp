<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="_comum/header.jsp" />


<!-- <p style="font-size:72px;text-align:center;text-shadow: 2px 2px 8px #333;">WELCOME TO SYSLOCC</p>
<p style="font-size:24px;text-align:center;text-shadow: 2px 2px 8px #B2B2B2;">Less Excel, More Production !</p> -->


<br><br><br><br><br><br>
	
<p style="font-size:80px;text-align:center;font-family: 'Oxygen', sans-serif;color: #f05736">BEM-VINDO AO SYSLOCC</p>

<a href="excelGalderma" class="btn btn-default btn-lg col-md-offset-5" >Excel</a>

<!-- <p style="font-size:24px;text-align:center;font-family: 'Oxygen', sans-serif;color: #999999">Less Excel, More Production !</p>	
 -->
<!-- <p style="font-size:12px;text-align:center;text-shadow: 2px 2px 8px #B2B2B2;">Versão Beta 2.5</p> -->	


<div class="divisor" style="height: 100px;"></div>

<security:authorize access="hasRole('ROLE_DESENVOLVEDOR')">

		<div class="row col-md-offset-2">
				<div class="col-md-10 painel ajuste-left" style="box-shadow: 1px 6px 5px #ccc;">
					<ul>
						<li >Notas da versão
							<ul style="padding-top: 15px">
								<li style="font-size: 12px">Versão 3.1  -  21/03/2016
									<ul>
										<li></li>
										<li></li>
									</ul>	
								</li>
							</ul>
							<br />		
							<ul style="padding-top: 15px">
								<li style="font-size: 12px">Versão 2.5  -  22/01/2016
									<ul>
										<li>Agora é possível identificar qual linha não tem Fee. Borda vermelha lateral indentifica linha sem Fee.</li>
										<li>Criado arquivo morto para usuários, apenas administradores podem "excluir" esses usuários.</li>
									</ul>	
								</li>
							</ul>
							<br />		
							<ul>
								<li style="font-size: 12px">Versão 2.4  -  20/01/2016
									<ul>
										<li>Criado ícone especial para abrir o sistema diretamente.</li>
										<li>Aprimoramento do sistema para não aparecer a barra de endereços.</li>
										<li>Novos tamanhos e ajustes das fontes no relatório de Job.</li>
										<li>Todos os locais e datas de eventos cadastrados agora constam no relatório de Job.</li>
										<li>Em "cadastrar Local do Evento" em Job foi adicionado o campo "Local do Evento".</li>
										<li>Agora é obrigatório colocar no mínimo o nome do evento para cadastrar um novo local.</li>
										<li>Corrigido bug que ao gerar relatório de Job estava repetindo o Produtor 1 em produtor 2.</li>
										<li>Corrigido bug que não permitia enviar email para toda a equipe em novo Job.</li>
										
									</ul>
								</li>
							</ul>
							<br />
							<ul>
								<li style="font-size: 12px">Versão 2.3
									<ul>
										<li>Ordenação de Categorias e Linhas aprimoradas.</li>
										<li>Copia de Lista corrigida.</li>
										<li>Inserido novo logo da Locco em carta de contratação.</li>
										<li>Nova tela de Login aprimorada.</li>
									</ul>
								</li>
							</ul>
					    </li>
					</ul>		
				</div>
		</div>

</security:authorize>

<!-- <div class="row col-md-offset-2">


<div class="col-md-10 painel ajuste-left" style="box-shadow: 1px 6px 5px #ccc;">
        <div class="col-md-10 update">
        	<h4>Atualização - 12/06/2015 17:00</h4>
        	
        	<b>Criação de Componentes Internos</b>
        	<p>Criado a estrutura para extração de dados e migração para o novo sistema.<br />
        	Dados de usuários, produtos e empresas já é possível criar novos e atualizar os existentes.
        	</p>
        	<br />
        	
        	<b>Outras atualizações</b>
        	<p>Melhorias internas do código para aumento de desempenho</p>
        	
		</div>
</div>
</div>


<div class="divisor"></div>


<div class="row col-md-offset-2">


<div class="col-md-10 painel ajuste-left" style="box-shadow: 1px 6px 5px #ccc;">
        <div class="col-md-10 update">
        	<h4>Atualização - 07/05/2015 18:45</h4>
        	
        	<b>Criação de Componentes Internos</b>
        	<p>Nos próximos dias haverá pouca mudança visual no sistema,<br >porque estamos criando os componentes internos
        	para exibirem os dados na tela.<br />
        	Dados como a lista de clientes, usuários, jobs etc. 
        	</p>
        	<br />
        	
        	<b>Outras atualizações</b>
        	<p>Melhorias internas do código para aumento de desempenho</p>
        	
		</div>
</div>
</div>


<div class="divisor"></div>


<div class="row col-md-offset-2">


<div class="col-md-10 painel ajuste-left" style="box-shadow: 1px 6px 5px #ccc;">
        <div class="col-md-10 update">
        	<h4>Atualização - 04/05/2015 18:05</h4>
        	
        	<b>Criação de Componentes Internos</b>
        	<p>Criado os primeiros componentes de comunicação entre a camada visual ( as telas )
        	e os componentes internos ( a plataforma de código Java ).</p>
        	<br />
        	
        	<b>Outras atualizações</b>
        	<p>Melhoria no cadastro de imposto
        	<br>Melhoria na edição de Tags<br>
        	Melhorias internas de código para aumento de desempenho</p>
        	
		</div>
</div>
</div>


<div class="divisor"></div>

<div class="row col-md-offset-2">
<div class="col-md-10 painel ajuste-left" style="box-shadow: 1px 6px 5px #ccc;">
        <div class="col-md-10 update">
        	<h4>Atualização - 30/04/2015 18:25</h4>
        	
        	<b>Cadastros</b>
        	<p>Criado tela de Tags<br />
        	Melhorias internas de código para aumento de desempenho</p>
		</div>
</div>
</div>


<div class="divisor"></div>

<div class="row col-md-offset-2">
<div class="col-md-10 painel ajuste-left" style="box-shadow: 1px 6px 5px #ccc;">
        <div class="col-md-10 update">
        	<h4>Atualização - 29/04/2015 18:25</h4>
        	
        	<b>Atualizado tela de cadastro de produtos</b>
        	<p>Inserido opção de marcas para o produto</p>
        	
        	
        	
        	<b>Cadastros</b>
        	<p>Criado tela pra cadastro de usuários<br />
        	Criado tela para cadastro de Impostos (Configuração)</p>
		</div>
</div>
</div>

<div class="row col-md-offset-2">
<div class="divisor"></div>


<div class="col-md-10 painel ajuste-left" style="box-shadow: 1px 6px 5px #ccc;">
        <div class="col-md-10 update">
        	<h4>Atualização - 28/04/2015 18:15</h4>
        	
        	<b>Atualizado planilha de produção</b>
        	<p> Mecanismo de inserir linha se encontra no fim de cada linha</p>
        	
        	
        	
        	<b>Cadastros</b>
        	<p>Criado tela pra cadastro de Empresas</br>
        	Criado tela para cadastro de Produtos</p>
		</div>
</div>
</div> -->
<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />

	

<c:import url="_comum/footer.jsp" />