package com.example.lolitos2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Imagens {
	
	
	protected static Bitmap parede;
	protected static Bitmap heroi;
	protected static Bitmap gemsvida;
	protected static Bitmap monstro;
	protected static Bitmap seta;
	
	static void inicializarImagens(Resources res)
	{
		parede = BitmapFactory.decodeResource(res, R.drawable.parede);
		parede = Bitmap.createScaledBitmap(parede, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		heroi = BitmapFactory.decodeResource(res, R.drawable.heroi);
		heroi = Bitmap.createScaledBitmap(heroi, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		gemsvida = BitmapFactory.decodeResource(res, R.drawable.gemsvida);
		gemsvida = Bitmap.createScaledBitmap(gemsvida, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		monstro = BitmapFactory.decodeResource(res, R.drawable.monstro);
		monstro = Bitmap.createScaledBitmap(monstro, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		seta = BitmapFactory.decodeResource(res, R.drawable.seta);
		seta = Bitmap.createScaledBitmap(seta, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
	}

}
