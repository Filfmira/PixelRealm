package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Heroi extends Personagem implements Serializable{

	private static final long serialVersionUID = -7166431857474447193L;
	
	
	private int dinheiro;
	int incAtaque=0;	//incremento de ataque temporario
	int nivel=1;
	/**
	 * Construtor do heroi, inicializa-o em x,y com vidaInicial e vida determinadas
	 * @param x
	 * @param y
	 */
	public Heroi(int x, int y) {
		super(x, y);
		setVida(10000);
		
		setVidaInicial(10000);
		ataque=200;
	}
	
	/**
	 * Inicializa um heroi, não sendo necessário referir-lhe uma localização
	 */
	public Heroi()
	{
		super(0, 0);
		setVida(10000);
		setVidaInicial(10000);
		ataque=200;
	}

	/**
	 * Luta contra um monstro, tiranto e perdendo vida
	 * @param m
	 */
	public void lutar (Monstro m)
	{
		setVida(getVida() - m.ataque);
		m.setVida(m.getVida() - ataque);
	}
	
	/**
	 * Desenha o heroi no centro do ecra
	 */
	public void draw(Canvas canvas, Paint paint){
		canvas.drawBitmap(getImagem(), this.getX(), this.getY(), paint);
	}
	
	/**
	 * Apanha uma GemsVida
	 * @param gm
	 */
	public void apanharCatchable(GemsVida gm)
	{
		GameActivity.instance.gem.start();
		this.setVida(this.getVida()+gm.getCapacidade());
	}
	
	/**
	 * Apanha uma GemsAtaque
	 * @param gm
	 */
	public void apanharCatchable(GemsAtaque gm)
	{
		GameActivity.instance.gem.start();
		if(incAtaque==0)
		{
			incAtaque=gm.getCapacidade();
			ataque+=gm.getCapacidade();
		}
	}
	
	/**
	 * Apanha uma Moeda
	 * @param gm
	 */
	public void apanharCatchable(Moeda m)
	{
		GameActivity.instance.gem.start();
		this.setDinheiro(this.getDinheiro() + m.getCapacidade());
	}

	int contadorAtaque=0;
	public void update()
	{
		//trata de retirar os incremntos de ataques temporarios
		
		if(incAtaque>0)
		{
			if(contadorAtaque==0)
				contadorAtaque=200;
			contadorAtaque--;
			if(contadorAtaque==1)
			{
				this.ataque-=this.incAtaque;
				this.incAtaque=0;
				this.contadorAtaque=0;
			}
		}
		else if(contadorAtaque==0)
			incAtaque=0;
	}

	/**
	 * Volta a por o ataque do heroi sem o "plus" que tem de apanhar GemsAtaque
	 */
	public void setAtaqueNormal() {
		if(incAtaque!=0)
		{
		this.ataque-=this.incAtaque;
		this.incAtaque=0;
		}
		
	}
	
	

	@Override
	public Bitmap getImagem() {
		if (this.incAtaque > 0)
			return Imagens.heroi2;
		else return Imagens.heroi;
	}

	public int getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}
	
	
	
}
