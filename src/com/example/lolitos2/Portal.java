package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Portal extends Entidade {


	int numGems;
	Sprite sprite;
	
	/**
	 * Inicializa um Portal em x,y
	 * @param x
	 * @param y
	 */
	public Portal(int x, int y) {
		super(x, y, Entidade.tamanhoCelula*2, Entidade.tamanhoCelula*2);
		//imagem=Imagens.portal;
		numGems=0;
		sprite= new Sprite(getImagem(),2,4,x,y);
	}

	
	/**
	 * Desenho do Portal na sua localização
	 * @param canvas
	 * @param paint
	 * @param jogo
	 */
	public void draw(Canvas canvas, Paint paint, Jogo jogo){
		if(jogo.getMonstrosMortos()>=jogo.getPortalNum())
			sprite.setDirection(1);
		sprite.draw(canvas, x * tamanhoCelula+Entidade.dx, y * tamanhoCelula+Entidade.dy,tamanhoCelula*2,tamanhoCelula*2 );
	}
	
	/**
	 * Contador de Gems
	 * @param x
	 */
	public void addGems(int x)
	{
		numGems+=x;
	}

	@Override
	public Bitmap getImagem() {
		return Imagens.portalSprite;
	}
}
