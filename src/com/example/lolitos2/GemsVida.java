package com.example.lolitos2;

import android.graphics.Color;

public class GemsVida extends Entidade{

	private int capacidade=0;
	public GemsVida(int x, int y,int capacidade) {
		super(x,y,tamanhoCelula,tamanhoCelula);
		this.color=Color.CYAN;
		this.setCapacidade(capacidade);
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

}
