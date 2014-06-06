package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;

public class GemsVida extends Catchable implements 	Serializable{

	private static final long serialVersionUID = -9071365986335816210L;

	
	/**
	 * Inicializa a GemsVida em x,y com capacidade "capacidade"
	 * @param x
	 * @param y
	 * @param capacidade
	 */
	public GemsVida(int x, int y,int capacidade) {
		super(x,y,capacidade);
		//imagem=Imagens.gemsvida;
		sprite= new Sprite(getImagem(),2,4,x,y);
	}

	@Override
	public Bitmap getImagem() {
		return Imagens.gemsvida;
	}
	
	
}
