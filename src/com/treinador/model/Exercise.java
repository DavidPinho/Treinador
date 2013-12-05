package com.treinador.model;

import java.io.Serializable;

public class Exercise implements Serializable {
	
	
	private int idExercise;
	private int idAthlete;
	private int idExerciseType;
	private String instructions;
	private String date;
	private float weight;
	private int repetitions;
	private float duration;
	
	public int getIdExercise() {
		return idExercise;
	}
	public void setIdExercise(int idExercise) {
		this.idExercise = idExercise;
	}
	public int getIdAthlete() {
		return idAthlete;
	}
	public void setIdAthlete(int idAthlete) {
		this.idAthlete = idAthlete;
	}
	public int getIdExerciseType() {
		return idExerciseType;
	}
	public void setIdExerciseType(int idExerciseType) {
		this.idExerciseType = idExerciseType;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getRepetitions() {
		return repetitions;
	}
	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
		
	
	
	

}
