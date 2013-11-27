package com.treinador.model;

import java.io.Serializable;

public class Muscle implements Serializable{
	
	private int idMuscle;
	private String description;
	private Boolean selected;
	
	public Muscle(){
		this.selected = false;
	}
	
	public int getIdMuscle() {
		return idMuscle;
	}
	public void setIdMuscle(int idMuscle) {
		this.idMuscle = idMuscle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	
	@Override
	public String toString(){
		return description;
	}

}
