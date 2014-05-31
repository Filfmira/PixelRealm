package com.example.lolitos2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GemsVida extends Catchable{

	public GemsVida(int x, int y,int capacidade) {
		super(x,y,capacidade);
		imagem=Imagens.gemsvida;
	}
	
}
