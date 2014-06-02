package com.example.lolitos2;

import java.io.Serializable;

public class Moeda extends Catchable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -999092554446903040L;

	public Moeda(int x, int y, int capacidade) {
		super(x,y,capacidade);
		init();
	}
	
	public Moeda(Monstro m)
	{
		super(m,(int) (m.vidaInicial/500));
		init();
	}
	
	private void init(){
		imagem=Imagens.gemsvida;
		sprite= new Sprite(Imagens.moedasSprite,2,4,x,y);
	}
	
}
