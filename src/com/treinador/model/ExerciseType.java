package com.treinador.model;

import java.io.Serializable;

public class ExerciseType implements Serializable{
	
	private int idExerciseType;
	private String description;
	private boolean selected;
	
	
	public ExerciseType() {
	
		this.selected = false;
	}

	
	public int getIdExerciseType() {
		return idExerciseType;
	}

	public void setIdExerciseType(int idExerciseType) {
		this.idExerciseType = idExerciseType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return super.toString();
	}
	
	
	
}
