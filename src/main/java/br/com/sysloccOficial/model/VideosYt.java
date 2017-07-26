package br.com.sysloccOficial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VideosYt {

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idVideo;
	private String tituloVideo;
	private String descricaoVideos;
	private String tagsVideos;
	private String playList;

	
	
	
	public Integer getIdVideo() {
		return idVideo;
	}
	public void setIdVideo(Integer idVideo) {
		this.idVideo = idVideo;
	}
	
	public String getTituloVideo() {
		return tituloVideo;
	}
	public void setTituloVideo(String tituloVideo) {
		this.tituloVideo = tituloVideo;
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
