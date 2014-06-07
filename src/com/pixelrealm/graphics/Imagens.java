package com.pixelrealm.graphics;

import java.io.Serializable;

import com.example.lolitos2.R;
import com.example.lolitos2.R.drawable;
import com.pixelrealm.entities.Entidade;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

public class Imagens {
	protected static Bitmap parede;	
	protected static Bitmap heroi;		//sbust por sprite
	protected static Bitmap heroi2;		//subst por srpite
	protected static Bitmap gemsvida;	//a elimniar
	protected static Bitmap monstro;	//subst por sprite
	protected static Bitmap monstro2;	//subst por sprite
	protected static Bitmap seta;		//subst por sprtie
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
	protected static Bitmap joystickBig;
	protected static Bitmap joystickSmall;
	protected static Bitmap sombraMapa;
	protected static Bitmap arvore;

	
	public static void inicializarImagens(Resources res)
	{
		mapa = BitmapFactory.decodeResource(res, R.drawable.mapa);
		mapa = Bitmap.createScaledBitmap(mapa, Entidade.tamanhoCelula*32, Entidade.tamanhoCelula*32, true);
		
		parede = BitmapFactory.decodeResource(res, R.drawable.parede);
		parede = Bitmap.createScaledBitmap(parede, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);
		
		heroi = BitmapFactory.decodeResource(res, R.drawable.heroi);
		heroi = Bitmap.createScaledBitmap(heroi, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		heroi2 = BitmapFactory.decodeResource(res, R.drawable.heroi2);
		heroi2 = Bitmap.createScaledBitmap(heroi2, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
		monstro = BitmapFactory.decodeResource(res, R.drawable.monstro);
		monstro = Bitmap.createScaledBitmap(monstro, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);
		
		monstro2= BitmapFactory.decodeResource(res, R.drawable.monstro2);
		monstro2 = Bitmap.createScaledBitmap(monstro2, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);
		
		seta = BitmapFactory.decodeResource(res, R.drawable.seta);
		seta = Bitmap.createScaledBitmap(seta, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);
		
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
		

		arvore = BitmapFactory.decodeResource(res, R.drawable.arvore);
		arvore = Bitmap.createScaledBitmap(arvore, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);

		
		chao = BitmapFactory.decodeResource(res, R.drawable.chao);
		chao = Bitmap.createScaledBitmap(chao, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);
		
		nivel1=BitmapFactory.decodeResource(res, R.drawable.mappixel1);
		nivel2=BitmapFactory.decodeResource(res, R.drawable.mappixel2);
		
		
		joystickBig=BitmapFactory.decodeResource(res, R.drawable.joystick_big);
		joystickBig = Bitmap.createScaledBitmap(joystickBig, Entidade.tamanhoCelula*2, Entidade.tamanhoCelula*2, false);		
		joystickSmall=BitmapFactory.decodeResource(res, R.drawable.joystick);
		joystickSmall=Bitmap.createScaledBitmap(joystickSmall, Entidade.tamanhoCelula, Entidade.tamanhoCelula, false);
	
	
		sombraMapa=BitmapFactory.decodeResource(res, R.drawable.sombra);
		//sombraMapa = Bitmap.createScaledBitmap(sombraMapa, Entidade.tamanhoCelula*mapa.getWidth(), Entidade.tamanhoCelula*mapa.getHeight(), false);		
		
		
	}
	
	
	/**
	 * Roda uma imagem source um angle
	 * @param source
	 * @param angle
	 * @return
	 */
	public static Bitmap RotateBitmap(Bitmap source, float angle)
	{
	      Matrix matrix = new Matrix();
	      matrix.postRotate(angle);
	      return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
	}


	public static Bitmap getParede() {
		return parede;
	}


	public static Bitmap getHeroi() {
		return heroi;
	}


	public static Bitmap getHeroi2() {
		return heroi2;
	}


	public static Bitmap getGemsvida() {
		return gemsvida;
	}


	public static Bitmap getMonstro() {
		return monstro;
	}


	public static Bitmap getMonstro2() {
		return monstro2;
	}


	public static Bitmap getSeta() {
		return seta;
	}


	public static Bitmap getPausa() {
		return pausa;
	}


	public static Bitmap getMapa() {
		return mapa;
	}


	public static Bitmap getGemsVidaSprite() {
		return gemsVidaSprite;
	}


	public static Bitmap getGemsAtaqueSprite() {
		return gemsAtaqueSprite;
	}


	public static Bitmap getMoedasSprite() {
		return moedasSprite;
	}


	public static Bitmap getPortalSprite() {
		return portalSprite;
	}


	public static Bitmap getRelva() {
		return relva;
	}


	public static Bitmap getFlor() {
		return flor;
	}


	public static Bitmap getChao() {
		return chao;
	}


	public static Bitmap getNivel1() {
		return nivel1;
	}


	public static Bitmap getNivel2() {
		return nivel2;
	}


	public static Bitmap getJoystickBig() {
		return joystickBig;
	}


	public static Bitmap getJoystickSmall() {
		return joystickSmall;
	}


	public static Bitmap getSombraMapa() {
		return sombraMapa;
	}


	public static Bitmap getArvore() {
		return arvore;
	}


	public static void setParede(Bitmap parede) {
		Imagens.parede = parede;
	}


	public static void setHeroi(Bitmap heroi) {
		Imagens.heroi = heroi;
	}


	public static void setHeroi2(Bitmap heroi2) {
		Imagens.heroi2 = heroi2;
	}


	public static void setGemsvida(Bitmap gemsvida) {
		Imagens.gemsvida = gemsvida;
	}


	public static void setMonstro(Bitmap monstro) {
		Imagens.monstro = monstro;
	}


	public static void setMonstro2(Bitmap monstro2) {
		Imagens.monstro2 = monstro2;
	}


	public static void setSeta(Bitmap seta) {
		Imagens.seta = seta;
	}


	public static void setPausa(Bitmap pausa) {
		Imagens.pausa = pausa;
	}


	public static void setMapa(Bitmap mapa) {
		Imagens.mapa = mapa;
	}


	public static void setGemsVidaSprite(Bitmap gemsVidaSprite) {
		Imagens.gemsVidaSprite = gemsVidaSprite;
	}


	public static void setGemsAtaqueSprite(Bitmap gemsAtaqueSprite) {
		Imagens.gemsAtaqueSprite = gemsAtaqueSprite;
	}


	public static void setMoedasSprite(Bitmap moedasSprite) {
		Imagens.moedasSprite = moedasSprite;
	}


	public static void setPortalSprite(Bitmap portalSprite) {
		Imagens.portalSprite = portalSprite;
	}


	public static void setRelva(Bitmap relva) {
		Imagens.relva = relva;
	}


	public static void setFlor(Bitmap flor) {
		Imagens.flor = flor;
	}


	public static void setChao(Bitmap chao) {
		Imagens.chao = chao;
	}


	public static void setNivel1(Bitmap nivel1) {
		Imagens.nivel1 = nivel1;
	}


	public static void setNivel2(Bitmap nivel2) {
		Imagens.nivel2 = nivel2;
	}


	public static void setJoystickBig(Bitmap joystickBig) {
		Imagens.joystickBig = joystickBig;
	}


	public static void setJoystickSmall(Bitmap joystickSmall) {
		Imagens.joystickSmall = joystickSmall;
	}


	public static void setSombraMapa(Bitmap sombraMapa) {
		Imagens.sombraMapa = sombraMapa;
	}


	public static void setArvore(Bitmap arvore) {
		Imagens.arvore = arvore;
	}

	
}
