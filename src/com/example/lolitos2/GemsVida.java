package com.example.lolitos2;

import java.io.Serializable;

public class GemsVida extends Catchable implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2007880068846401041L;

	public GemsVida(int x, int y,int capacidade) {
		super(x,y,capacidade);
		imagem=Imagens.gemsvida;
		sprite= new Sprite(Imagens.gemsVidaSprite,2,4,x,y);
	}
	
	
}
