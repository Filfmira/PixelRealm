package com.example.lolitos2;

public class Catchable extends Entidade{

	protected int capacidade=0;
	
	//Construtores 
	public Catchable(int x, int y) {
		super(x, y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
	}
	
	public Catchable(int x, int y, int capacidade) {
		super(x, y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
		this.setCapacidade(capacidade);
	}
	
	public Catchable(Monstro m) {
		super(m.x, m.y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
	}
	
	public Catchable(Monstro m, int capacidade) {
		super(m.x, m.y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
		this.setCapacidade(capacidade);
	}

	
	
	
	
	//Gets e Sets
	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	

}
