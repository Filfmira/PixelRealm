package com.example.lolitos2;

import java.io.Serializable;

public abstract class Personagem extends Entidade implements Serializable{

	private static final long serialVersionUID = -2527421795633542694L;
	
	private float vida=1000000;
	private float vidaInicial=1000000;
	float ataque=0;
	protected int movimento= 0;
	
	/**
	 * Inicializa uma persinagem em x,y
	 * @param x
	 * @param y
	 */
	public Personagem(int x, int y) {
		super(x,y,tamanhoCelula,tamanhoCelula);
	}

	
	

	float getVida() {
		return vida;
	}

	public void setVida(float vida) {
		this.vida = vida;
	}

	public float getVidaInicial() {
		return vidaInicial;
	}

	public void setVidaInicial(float vidaInicial) {
		this.vidaInicial = vidaInicial;
	}
	
	
	
}
