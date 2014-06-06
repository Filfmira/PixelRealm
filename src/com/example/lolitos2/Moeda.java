package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Moeda extends Catchable{

	
	/**
	 * Inicializa uma Moeda em x,y com capacidade
	 * @param x
	 * @param y
	 * @param capacidade
	 */
	public Moeda(int x, int y, int capacidade) {
		super(x,y,capacidade);
		init();
	}
	
	/**
	 * Inicializa uma Moeda na posicao do Monstro m, e a capacidade dependendo da Vida Inicial do monstro
	 * @param m
	 */
	public Moeda(Monstro m)
	{
		super(m,(int) (m.getVidaInicial()/500));
		init();
	}
	
	/**
	 * Inicializa a Sprite
	 */
	private void init(){
		sprite= new Sprite(getImagem(),2,4,x,y);
	}

	@Override
	public Bitmap getImagem() {
		return Imagens.moedasSprite;
	}
	
}
