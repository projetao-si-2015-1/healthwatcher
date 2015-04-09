package br.cin.ufpe.healthwatcher.model;

public enum ComplaintType {

	Alimentar(1), Animal(2), Diversa(3);
	
	private final int code;
	
	int code(){ return this.code;}
	
	private ComplaintType(int code) {
		this.code = code;
	}
}
