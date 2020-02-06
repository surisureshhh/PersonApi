package com.embl.person.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "Persons")
@Inheritance(strategy=InheritanceType.JOINED)
public class Person {
	
	private long id;
	@Column(name = "first_name", nullable = false)
    private String first_name;
	 @Column(name = "last_name", nullable = false)
    private String last_name;
	 @Column(name = "age", nullable = false)
    private String age;
	 @Column(name = "favourite_colour", nullable = false)
    private String favourite_colour;
	 @Transient 
	 @Column(name="hobby")
	 private String[] hobby;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getFavourite_colour() {
		return favourite_colour;
	}
	public void setFavourite_colour(String favourite_colour) {
		this.favourite_colour = favourite_colour;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	
	
	
	
    
    
}
