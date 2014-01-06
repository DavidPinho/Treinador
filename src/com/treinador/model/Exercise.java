package com.treinador.model;

import java.io.Serializable;

public class Exercise implements Serializable {
	
	
	private int idExercise;
	private int idAthlete;
	private int idExerciseType;
	private String instructions;
	private long date;
	private double weight;
	private int repetitions;
	private double duration;
	
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
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getRepetitions() {
		return repetitions;
	}
	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
		


}
