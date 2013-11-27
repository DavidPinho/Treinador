package com.treinador.model;

import java.io.Serializable;

public class Size implements Serializable{
	
	private int idSize;
	private int idAthlete;
	private int idMuscle;
	private String date;
	private double sizeValue;
	private Boolean selected;
	
	public Size(){
		this.selected = false;
	}
		
	public int getIdSize() {
		return idSize;
	}
	public void setIdSize(int idSize) {
		this.idSize = idSize;
	}
	public int getIdAthlete() {
		return idAthlete;
	}
	public void setIdAthlete(int idAthlete) {
		this.idAthlete = idAthlete;
	}
	public int getIdMuscle() {
		return idMuscle;
	}
	public void setIdMuscle(int idMuscle) {
		this.idMuscle = idMuscle;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getSizeValue() {
		return sizeValue;
	}
	public void setSizeValue(double sizeValue) {
		this.sizeValue = sizeValue;
	}

	
	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	

}
