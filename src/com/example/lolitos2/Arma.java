package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;


public class Arma extends Entidade implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1237312866085569271L;
	//varios tipos?
		//Armas
		//Vida
		//Pontos
	int ataque=500;
	
	public Arma(int x, int y) {
		super(x,y,tamanhoCelula,tamanhoCelula);
	}

	@Override
	public Bitmap getImagem() {
		return null;
	}

}
