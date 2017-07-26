package br.com.sysloccOficial.videos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DescricaoVideos {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idVideo;
	private String tituloVIdeo;
	private String descricaoVideos;
	private String tagsVideos;
	private String playList;

	
	public Integer getIdVideo() {
		return idVideo;
	}
	public void setIdVideo(Integer idVideo) {
		this.idVideo = idVideo;
	}
	public String getTituloVIdeo() {
		return tituloVIdeo;
	}
	public void setTituloVIdeo(String tituloVIdeo) {
		this.tituloVIdeo = tituloVIdeo;
	}
	public String getDescricaoVideos() {
		return descricaoVideos;
	}
	public void setDescricaoVideos(String descricaoVideos) {
		this.descricaoVideos = descricaoVideos;
	}
	
	public String getTagsVideos() {
		return tagsVideos;
	}
	public void setTagsVideos(String tagsVideos) {
		this.tagsVideos = tagsVideos;
	}
	public String getPlayList() {
		return playList;
	}
	public void setPlayList(String playList) {
		this.playList = playList;
	}
	
}
