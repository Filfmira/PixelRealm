package com.example.lolitos2;

import java.io.Serializable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class Imagens {
	
	
	protected static Bitmap parede;	
	protected static Bitmap heroi;		//sbust por sprite
	protected static Bitmap heroi2;		//subst por srpite
	protected static Bitmap gemsvida;	//a elimniar
	protected static Bitmap monstro;	//subst por sprite
	protected static Bitmap monstro2;	//subst por sprite
	protected static Bitmap seta;		//subst por sprtie
	protected static Bitmap moeda;		//a eliminar
	protected static Bitmap moeda2;		//a eliminar
	protected static Bitmap gemsataque;	//a eliminar
	protected static Bitmap pausa;
	protected static Bitmap mapa;		//a elimniar
	protected static Bitmap gemsVidaSprite;
	protected static Bitmap gemsAtaqueSprite;
	protected static Bitmap moedasSprite;
	protected static Bitmap portalSprite;
	protected static Bitmap relva;
	protected static Bitmap flor;
	protected static Bitmap chao;
	protected static Bitmap nivel1;
	protected static Bitmap nivel2;
	
	static void inicializarImagens(Resources res)
	{
		mapa = BitmapFactory.decodeResource(res, R.drawable.mapa);
		mapa = Bitmap.createScaledBitmap(mapa, Entidade.tamanhoCelula*32, Entidade.tamanhoCelula*32, true);
		
		parede = BitmapFactory.decodeResource(res, R.drawable.parede);
		parede = Bitmap.createScaledBitmap(parede, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);
		
		heroi = BitmapFactory.decodeResource(res, R.drawable.heroi);
		heroi = Bitmap.createScaledBitmap(heroi, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		heroi2 = BitmapFactory.decodeResource(res, R.drawable.heroi2);
		heroi2 = Bitmap.createScaledBitmap(heroi2, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		gemsvida = BitmapFactory.decodeResource(res, R.drawable.gemsvida);
		gemsvida = Bitmap.createScaledBitmap(gemsvida, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		monstro = BitmapFactory.decodeResource(res, R.drawable.monstro);
		monstro = Bitmap.createScaledBitmap(monstro, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);
		
		monstro2= BitmapFactory.decodeResource(res, R.drawable.monstro2);
		monstro2 = Bitmap.createScaledBitmap(monstro2, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);
		
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
		
		gemsVidaSprite= BitmapFactory.decodeResource(res, R.drawable.gemsvidasprite);
		
		gemsAtaqueSprite=BitmapFactory.decodeResource(res, R.drawable.gemsataquesprite);
		
		moedasSprite= BitmapFactory.decodeResource(res, R.drawable.moedassprite);
		
		
		portalSprite=BitmapFactory.decodeResource(res, R.drawable.portalsprite);
		
		
		relva = BitmapFactory.decodeResource(res, R.drawable.relva);
		relva = Bitmap.createScaledBitmap(relva, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);
		
		flor = BitmapFactory.decodeResource(res, R.drawable.relvaflor);
		flor = Bitmap.createScaledBitmap(flor, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);
		
		chao = BitmapFactory.decodeResource(res, R.drawable.chao);
		chao = Bitmap.createScaledBitmap(chao, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);
		
		nivel1=BitmapFactory.decodeResource(res, R.drawable.mappixel1);
		nivel2=BitmapFactory.decodeResource(res, R.drawable.mappixel2);
		
	}
	
	
	public static Bitmap RotateBitmap(Bitmap source, float angle)
	{
	      Matrix matrix = new Matrix();
	      matrix.postRotate(angle);
	      return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
	}

}
