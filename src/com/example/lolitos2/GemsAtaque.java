package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;

public class GemsAtaque extends Catchable{


	public GemsAtaque(Monstro m) {
		super(m,5000);
		//imagem=Imagens.gemsataque;
		sprite= new Sprite(getImagem(),2,4,x,y);
	}

	@Override
	public Bitmap getImagem() {
		return Imagens.gemsAtaqueSprite;
	}

}
