package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;

public class GemsAtaque extends Catchable{


	/**
	 * Inicializa a GemsAtaque atraves de um Monstro(capacidade e posicao)
	 * @param m
	 */
	public GemsAtaque(Monstro m) {
		super(m,500);
		//imagem=Imagens.gemsataque;
		sprite= new Sprite(getImagem(),2,4,x,y);
	}

	@Override
	public Bitmap getImagem() {
		return Imagens.gemsAtaqueSprite;
	}

}
