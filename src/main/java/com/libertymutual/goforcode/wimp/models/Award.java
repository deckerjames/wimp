//package com.libertymutual.goforcode.wimp.models;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//
//@Entity
//public class Award {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//
//	@Column(length = 500, nullable = false)
//	private String title;
//
//	private int year;
//
//	@Column(length = 200, nullable = false)
//	private String organization;
//
//	public Award() {
//	}
//
//	public Award(String title, String organization) {
//		this.title = title;
//		this.organization = organization;
//	}
//
//	@ManyToOne
//	private List<Actor> actors;
//
//	public void addActor(Actor actor) {
//		if (actors == null) {
//			actors = new ArrayList<Actor>();
//		}
//		actors.add(actor);
//	}
//
//	/**
//	 * @return the id
//	 */
//	public Long getId() {
//		return id;
//	}
//
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	/**
//	 * @return the title
//	 */
//	public String getTitle() {
//		return title;
//	}
//
//	/**
//	 * @param title the title to set
//	 */
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	/**
//	 * @return the year
//	 */
//	public int getYear() {
//		return year;
//	}
//
//	/**
//	 * @param year the year to set
//	 */
//	public void setYear(int year) {
//		this.year = year;
//	}
//
//	/**
//	 * @return the organization
//	 */
//	public String getOrganization() {
//		return organization;
//	}
//
//	/**
//	 * @param organization the organization to set
//	 */
//	public void setOrganization(String organization) {
//		this.organization = organization;
//	}
//
//	/**
//	 * @return the actors
//	 */
//	public List<Actor> getActors() {
//		return actors;
//	}
//
//	/**
//	 * @param actors the actors to set
//	 */
//	public void setActors(List<Actor> actors) {
//		this.actors = actors;
//	}
//	
//}
