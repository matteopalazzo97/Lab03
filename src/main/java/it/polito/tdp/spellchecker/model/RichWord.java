package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	String word;
	boolean corretta;
	
	public RichWord(String word) {
		super();
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public boolean isCorretta() {
		return corretta;
	}
	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}
	
	@Override
	public String toString() {
		return "RichWord [word=" + word + ", corretta=" + corretta + "]";
	}
	
	

}
