package br.com.sysloccOficial.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	
	private String nome;
	
	private String email;
	private String ramal;
	private Boolean habilitado;
	private String usuario; //Login
	
	@Column(length= 1550)
	private String senha;   // Senha
	
	private String cargo;
	private String name;
	private String regiao;
	private Integer nivel;
	private Integer arquivoX;
	
	
	
// -------- Transientes -----------------//		
	@Transient	private Integer idDep;
	@Transient  private Integer usuarioInterno;
	@Transient  private String userNovoTransiente;
	@Transient  private Integer nivelTransiente;
// --------Relacionamentos -----------------//	
	/*@ManyToMany(fetch = FetchType.EAGER)private List<Role> roles = new ArrayList<>();*/
	
	
	@ManyToOne @JoinColumn(name="idDepartamento")private Departamento departamento;
	@OneToMany(mappedBy="criadoPor")private List<Interno> interno;
	@OneToMany(mappedBy="criadopor")private List<Anexos> anexos;
	@OneToMany(mappedBy="idUsuario")private List<Interacao> InternoidUsuario;
	@OneToMany(mappedBy="idUsuarioInterno")private List<Interacao> idUsuarioInterno;
	@OneToMany(mappedBy="idUsuario")private List<JobStatus> jobStatus;
	@OneToMany(mappedBy="criadoPor")private List<JobStatus> criadoPor;
	@OneToMany(mappedBy="usuario")private List<Lista>listaTeste;	
	@OneToMany(mappedBy="usuario")private List<CriacaoLista>criacaoLista;

	@OneToMany(mappedBy="usuarioReponsável")private List<CriacaoLista>responsavelLista;
	@OneToMany(mappedBy="liderCriacao")private List<CriacaoItemLista>liderCriacao;
	@OneToMany(mappedBy="responsavelItem")private List<CriacaoItemLista>responsavelItem;
	@OneToMany(mappedBy="responsavelItem")private List<CriacaoItemHistorico>responsavelItemHistorico;
	@OneToMany(mappedBy="responsavelItem")private List<CriacaoItemPendencia>responsavelItemPendencia;
	@OneToMany(mappedBy="responsavelItem")private List<CriacaoItemPendenciaHistorico>responsavelItemPendenciaHistorico;
	
	@OneToMany(mappedBy="usuario")private List<ProdutoGrupo> produtoGrupo;
	
	
	
	
	
//	nivelAcesso
	
	
	@OneToOne @JoinColumn(name="nivelAcesso")private NivelAcesso nivelAcesso;
	
	/*@OneToMany(mappedBy="idUsuarioNotificaEquipe")
	private List<NotificaEquipe> idUsuarioNotificaEquipe;
	*/
	
    @OneToOne @JoinColumn(name="userNovo") private User userNovo;
	
	
	/*@OneToMany(mappedBy="notificadoIdUsuario")
	private List<Interno> notificadoIdUsuario;*/
	
// -----------------------------------------//	

    public Integer getIdDep() {
		return idDep;
	}

	public Integer getArquivoX() {
		return arquivoX;
	}

	public void setArquivoX(Integer arquivoX) {
		this.arquivoX = arquivoX;
	}

	public Integer getNivelTransiente() {
		return nivelTransiente;
	}

	public void setNivelTransiente(Integer nivelTransiente) {
		this.nivelTransiente = nivelTransiente;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public List<ProdutoGrupo> getProdutoGrupo() {
		return produtoGrupo;
	}

	public void setProdutoGrupo(List<ProdutoGrupo> produtoGrupo) {
		this.produtoGrupo = produtoGrupo;
	}

	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public String getUserNovoTransiente() {
		return userNovoTransiente;
	}

	public void setUserNovoTransiente(String userNovoTransiente) {
		this.userNovoTransiente = userNovoTransiente;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	/*public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CriacaoItemPendenciaHistorico> getResponsavelItemPendenciaHistorico() {
		return responsavelItemPendenciaHistorico;
	}

	public void setResponsavelItemPendenciaHistorico(
			List<CriacaoItemPendenciaHistorico> responsavelItemPendenciaHistorico) {
		this.responsavelItemPendenciaHistorico = responsavelItemPendenciaHistorico;
	}

	public List<CriacaoLista> getResponsavelLista() {
		return responsavelLista;
	}

	public void setResponsavelLista(List<CriacaoLista> responsavelLista) {
		this.responsavelLista = responsavelLista;
	}

	public List<CriacaoItemHistorico> getResponsavelItemHistorico() {
		return responsavelItemHistorico;
	}

	public void setResponsavelItemHistorico(
			List<CriacaoItemHistorico> responsavelItemHistorico) {
		this.responsavelItemHistorico = responsavelItemHistorico;
	}

	public List<CriacaoItemPendencia> getResponsavelItemPendencia() {
		return responsavelItemPendencia;
	}

	public void setResponsavelItemPendencia(
			List<CriacaoItemPendencia> responsavelItemPendencia) {
		this.responsavelItemPendencia = responsavelItemPendencia;
	}

	public List<CriacaoItemLista> getLiderCriacao() {
		return liderCriacao;
	}

	public void setLiderCriacao(List<CriacaoItemLista> liderCriacao) {
		this.liderCriacao = liderCriacao;
	}

	public List<CriacaoItemLista> getResponsavelItem() {
		return responsavelItem;
	}

	public void setResponsavelItem(List<CriacaoItemLista> responsavelItem) {
		this.responsavelItem = responsavelItem;
	}

	public List<JobStatus> getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(List<JobStatus> criadoPor) {
		this.criadoPor = criadoPor;
	}

	public List<CriacaoLista> getCriacaoLista() {
		return criacaoLista;
	}

	public void setCriacaoLista(List<CriacaoLista> criacaoLista) {
		this.criacaoLista = criacaoLista;
	}

	public List<JobStatus> getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(List<JobStatus> jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Integer getUsuarioInterno() {
		return usuarioInterno;
	}
	public void setUsuarioInterno(Integer usuarioInterno) {
		this.usuarioInterno = usuarioInterno;
	}
	public List<Interacao> getInternoidUsuario() {
		return InternoidUsuario;
	}
	public void setInternoidUsuario(List<Interacao> internoidUsuario) {
		InternoidUsuario = internoidUsuario;
	}
	public List<Interacao> getIdUsuarioInterno() {
		return idUsuarioInterno;
	}
	public void setIdUsuarioInterno(List<Interacao> idUsuarioInterno) {
		this.idUsuarioInterno = idUsuarioInterno;
	}
	public List<Anexos> getAnexos() {
		return anexos;
	}
	public void setAnexos(List<Anexos> anexos) {
		this.anexos = anexos;
	}
	public void setIdDep(Integer idDep) {
		this.idDep = idDep;
	}

	
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	public List<Interno> getInterno() {
		return interno;
	}
	public void setInterno(List<Interno> interno) {
		this.interno = interno;
	}

	public List<Lista> getListaTeste() {
		return listaTeste;
	}

	public void setListaTeste(List<Lista> listaTeste) {
		this.listaTeste = listaTeste;
	}

	public User getUserNovo() {
		return userNovo;
	}

	public void setUserNovo(User userNovo) {
		this.userNovo = userNovo;
	}

	
}
