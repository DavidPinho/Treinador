package com.treinador.model;

import java.io.Serializable;

import android.R.bool;

public class Athlete  implements Serializable{
	
	private int idAthlete;
	private String name;
	private String gender;
	private String birthDate;
	private Boolean selected;
	
	public Athlete(){
		this.selected =false;
	}
	
	public int getIdAthlete() {
		return idAthlete;
	}
	public void setIdAthlete(int idAthlete) {
		this.idAthlete = idAthlete;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	
	
	
	

}
