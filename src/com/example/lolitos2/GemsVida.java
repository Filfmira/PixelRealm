package com.example.lolitos2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GemsVida extends Entidade{

	private int capacidade=0;
	public GemsVida(int x, int y,int capacidade) {
		super(x,y,tamanhoCelula,tamanhoCelula);
		this.color=Color.CYAN;
		this.setCapacidade(capacidade);
		imagem=Imagens.gemsvida;
		/*imagem = BitmapFactory.decodeResource(res, R.drawable.gemsvida);
	 	imagem = Bitmap.createScaledBitmap(imagem, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);*/
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	
}
