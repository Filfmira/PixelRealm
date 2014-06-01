package com.example.lolitos2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class Imagens {
	
	
	protected static Bitmap parede;
	protected static Bitmap heroi;
	protected static Bitmap heroi2;
	protected static Bitmap gemsvida;
	protected static Bitmap monstro;
	protected static Bitmap seta;
	protected static Bitmap moeda;
	protected static Bitmap moeda2;
	protected static Bitmap gemsataque;
	protected static Bitmap pausa;
	protected static Bitmap mapa;
	
	static void inicializarImagens(Resources res)
	{
		mapa = BitmapFactory.decodeResource(res, R.drawable.mapa);
		mapa = Bitmap.createScaledBitmap(mapa, Entidade.tamanhoCelula*32, Entidade.tamanhoCelula*32, true);
		
		parede = BitmapFactory.decodeResource(res, R.drawable.parede);
		parede = Bitmap.createScaledBitmap(parede, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		heroi = BitmapFactory.decodeResource(res, R.drawable.heroi);
		heroi = Bitmap.createScaledBitmap(heroi, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		heroi2 = BitmapFactory.decodeResource(res, R.drawable.heroi2);
		heroi2 = Bitmap.createScaledBitmap(heroi2, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		gemsvida = BitmapFactory.decodeResource(res, R.drawable.gemsvida);
		gemsvida = Bitmap.createScaledBitmap(gemsvida, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		monstro = BitmapFactory.decodeResource(res, R.drawable.monstro);
		monstro = Bitmap.createScaledBitmap(monstro, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		seta = BitmapFactory.decodeResource(res, R.drawable.seta);
		seta = Bitmap.createScaledBitmap(seta, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		moeda = BitmapFactory.decodeResource(res, R.drawable.gem1);
		moeda = Bitmap.createScaledBitmap(moeda, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		moeda2 = BitmapFactory.decodeResource(res, R.drawable.gem2);
		moeda2 = Bitmap.createScaledBitmap(moeda2, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		gemsataque = BitmapFactory.decodeResource(res, R.drawable.gemsataque);
		gemsataque = Bitmap.createScaledBitmap(gemsataque, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		pausa = BitmapFactory.decodeResource(res, R.drawable.pausa);
		pausa = Bitmap.createScaledBitmap(pausa, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
	}
	
	
	public static Bitmap RotateBitmap(Bitmap source, float angle)
	{
	      Matrix matrix = new Matrix();
	      matrix.postRotate(angle);
	      return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
	}

}
