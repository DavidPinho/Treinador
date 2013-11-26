package com.treinador.model;

public class Mark {
	private int idMark;
	private String description;
	private String date;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return description;
	}
	
	
	
	
	
}
