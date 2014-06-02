package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Parede extends Entidade  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4081076373230936972L;

	public Parede(int x, int y) {
		super(x,y,Entidade.tamanhoCelula,Entidade.tamanhoCelula);
		color=Color.GRAY;
		//imagem=Imagens.parede;
		/*imagem = BitmapFactory.decodeResource(res, R.drawable.parede);
	 	imagem = Bitmap.createScaledBitmap(imagem, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);*/
		
	}

	public Parede(int x, int y, int x1, int y1) {
		super(x,y,x1,y1);

	}

	@Override
	public Bitmap getImagem() {
		return Imagens.parede;
	}

}
