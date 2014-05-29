package com.example.lolitos2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class Parede extends Entidade {

	public Parede(int x, int y) {
		super(x,y,tamanhoCelula,tamanhoCelula);
		color=Color.GRAY;
		imagem=Imagens.parede;
		/*imagem = BitmapFactory.decodeResource(res, R.drawable.parede);
	 	imagem = Bitmap.createScaledBitmap(imagem, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);*/
		
	}

}
