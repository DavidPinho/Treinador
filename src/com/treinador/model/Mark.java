package com.treinador.model;

public class Mark {
	private int idMark;
	private int idAthlete;
	private String description;
	private String initialDate;
	private String finalDate;
	private boolean selected;
	
	public Mark() {
		this.selected = false;
	}


	public int getIdMark() {
		return idMark;
	}


	public void setIdMark(int idMark) {
		this.idMark = idMark;
	}

	
	public int getIdAthlete() {
		return idAthlete;
	}


	public void setIdAthlete(int idAthlete) {
		this.idAthlete = idAthlete;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getInitialDate() {
		return initialDate;
	}


	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}


	public String getFinalDate() {
		return finalDate;
	}


	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return description;
	}
	
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	
	
}
