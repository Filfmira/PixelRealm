package com.example.lolitos2;

import java.io.Serializable;

public class GemsAtaque extends Catchable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4575359894127348294L;

	public GemsAtaque(Monstro m) {
		super(m,5000);
		imagem=Imagens.gemsataque;
		sprite= new Sprite(Imagens.gemsAtaqueSprite,2,4,x,y);
	}

}
