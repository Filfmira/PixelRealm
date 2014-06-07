package com.pixelrealm.entities;

import java.io.Serializable;

import com.pixelrealm.graphics.Imagens;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Heroi extends Personagem implements Serializable{

	private static final long serialVersionUID = -7166431857474447193L;
	
	
	private int dinheiro;
	private int incAtaque=0;	//incremento de ataque temporario
	private int nivel=1;
	private int contadorAtaque=0;
	/**
	 * Construtor do heroi, inicializa-o em x,y com vidaInicial e vida determinadas
	 * @param x
	 * @param y
	 */
	public Heroi(int x, int y) {
		super(x, y);
		setVida(10000);
		
		setVidaInicial(10000);
		setAtaque(200);
	}
	
	/**
	 * Inicializa um heroi, não sendo necessário referir-lhe uma localização
	 */
	public Heroi()
	{
		super(0, 0);
		setVida(10000);
		setVidaInicial(10000);
		setAtaque(200);
	}

	/**
	 * Luta contra um monstro, tiranto e perdendo vida
	 * @param m
	 */
	public void lutar (Monstro m)
	{
		setVida(getVida() - m.getAtaque());
		m.setVida(m.getVida() - getAtaque());
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
		this.setVida(this.getVida()+gm.getCapacidade());
		if(this.getVida()>this.getVidaInicial())
			this.setVida(this.getVidaInicial());
	}
	
	/**
	 * Apanha uma GemsAtaque
	 * @param gm
	 */
	public void apanharCatchable(GemsAtaque gm)
	{
		if(getIncAtaque()==0)
		{
			setIncAtaque(gm.getCapacidade());
			setAtaque(getAtaque() + gm.getCapacidade());
		}
	}
	
	/**
	 * Apanha uma Moeda
	 * @param gm
	 */
	public void apanharCatchable(Moeda m)
	{
		this.setDinheiro(this.getDinheiro() + m.getCapacidade());
	}

	
	public void update()
	{
		//trata de retirar os incremntos de ataques temporarios
		
		if(getIncAtaque()>0)
		{
			if(getContadorAtaque()==0)
				setContadorAtaque(200);
			setContadorAtaque(getContadorAtaque() - 1);
			if(getContadorAtaque()==1)
			{
				this.setAtaque(this.getAtaque() - this.getIncAtaque());
				this.setIncAtaque(0);
				this.setContadorAtaque(0);
			}
		}
		else if(getContadorAtaque()==0)
			setIncAtaque(0);
	}

	/**
	 * Volta a por o ataque do heroi sem o "plus" que tem de apanhar GemsAtaque
	 */
	public void setAtaqueNormal() {
		if(getIncAtaque()!=0)
		{
		this.setAtaque(this.getAtaque() - this.getIncAtaque());
		this.setIncAtaque(0);
		}
		
	}
	
	/**
	 * Aumentar nivel do heroi
	 */
	public void aumentarNivel() {
		this.nivel++;
		
	}
	
	

	@Override
	public Bitmap getImagem() {
		if (this.getIncAtaque() > 0)
			return Imagens.getHeroi2();
		else return Imagens.getHeroi();
	}

	public int getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}

	public int getIncAtaque() {
		return incAtaque;
	}

	public void setIncAtaque(int incAtaque) {
		this.incAtaque = incAtaque;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getContadorAtaque() {
		return contadorAtaque;
	}

	public void setContadorAtaque(int contadorAtaque) {
		this.contadorAtaque = contadorAtaque;
	}


	
	
	
}
