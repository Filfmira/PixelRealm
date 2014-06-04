package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Heroi extends Personagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7166431857474447193L;
	private int dinheiro;
//	Arma arma;
	int incAtaque=0;	//incremento de ataque temporario
	int nivel=1;
	
	public Heroi(int x, int y) {
		super(x, y);
		setVida(10000);
		
		setVidaInicial(1000);
		ataque=200;
		movimento=2;
		//color=Color.BLUE;
	 	//imagem = Imagens.heroi;
	}

	/*
	 * luta contra um monstro perdendoe  tirando vida
	 */
	public void lutar (Monstro m)
	{
		setVida(getVida() - m.ataque);
		m.setVida(m.getVida() - ataque);
	}
	
	/*
	 * desenha o heroi no centro do ecra
	 */
	public void draw(Canvas canvas, Paint paint){
		
		
		canvas.drawBitmap(getImagem(), this.getX(), this.getY(), paint);

		
		//paint.setColor(Color.RED);
		//canvas.drawRect(x, y+tamanhoCelula*7/8, x+Entidade.tamanhoCelula, y+Entidade.tamanhoCelula, paint);
		//paint.setColor(Color.CYAN);
		//canvas.drawRect(x , y  +tamanhoCelula*7/8, x+Entidade.tamanhoCelula*(this.getVida()/this.vidaInicial), y  +Entidade.tamanhoCelula, paint);
	}
	
	
	/*
	 * movimenta o heroi
	 */
	public void movimento(int direcao,Jogo jogo)
	{
		deslocX=0;
		deslocY=0;
		switch (direcao) {
		
		case 0:
			x+=	-1;
			break;
		case 1:
			x+=	+1;
			break;
		case 2:
			y+=	-1;
			break;
		case 3:
			y+=	+1;
			break;
		}
		Log.v("heroi", x+".."+y);
		
	}
	//Arma??
	
	
	public void apanharGemsVida(GemsVida gm)
	{
		this.setVida(this.getVida()+gm.getCapacidade());
	}
	
	public void apanharGemsAtaque(GemsAtaque gm)
	{
		if(incAtaque==0)
		{
			incAtaque=gm.getCapacidade();
			ataque+=gm.getCapacidade();
		}
	}
	
	public void apanharMoeda(Moeda m)
	{
		this.setDinheiro(this.getDinheiro() + m.getCapacidade());
	}

	public int getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
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

	@Override
	public Bitmap getImagem() {
		if (this.incAtaque > 0)
			return Imagens.heroi2;
		else return Imagens.heroi;
	}

	public void setAtaqueNormal() {
		if(incAtaque!=0)
		{
		this.ataque-=this.incAtaque;
		this.incAtaque=0;
		}
		
	}
	
}
