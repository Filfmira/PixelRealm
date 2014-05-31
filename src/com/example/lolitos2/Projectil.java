package com.example.lolitos2;

import android.util.Log;

public class Projectil extends Entidade{

	float pdx,pdy;
	float a, b;
	int ataque;
	int xInicial,yInicial;
	
	/**
	 * Cria um projectil com determinada posicao
	 * @param x	posicao do projetil
	 * @param y	posicao do projetil
	 * @param pdx	deslocamento x do projetil
	 * @param pdy	deslocamento y do projetil
	 * @param heroi	referencia para o ataque do projetil
	 */
	public Projectil(int x, int y, float pdx, float pdy,Heroi heroi) {
		super(x,y,tamanhoCelula,tamanhoCelula);
		xInicial=x;
		yInicial=y;
		imagem=Imagens.seta;
		this.pdx=pdx;
		this.pdy=pdy;
		a=x;
		b=y;
		ataque=(int) (heroi.ataque*2);
		//if(x<=Entidade.sw/2)
		float angulo=0;
		//Log.e("tintas", xInicial+"-"+Entidade.sw/2);
		if(pdx>0)
			angulo=(float) (135+Math.toDegrees(Math.atan((float)(pdy/pdx))));
		else if(pdx<0)
			angulo=(float) (180+135+Math.toDegrees(Math.atan((float)(pdy/pdx))));
		else
			{if(pdy>0)
			angulo=(float) (135+90);
			else
				angulo=(float) (135-90);
			}
		this.imagem=Imagens.RotateBitmap(this.imagem, angulo);
		/*else
			this.imagem=Imagens.RotateBitmap(this.imagem, (float) (-45+Math.toDegrees(Math.atan((float)(pdy/pdx)))));*/
	}
	
	/**
	 * faz update á posicao da seta para que vá "andando" para o seu destino
	 * @return
	 */
	public boolean update()
	{
		a+=pdx;
		b+=pdy;
		this.x=(int) a;
		this.y=(int) b;
		if(Math.sqrt((Math.pow((x-xInicial),2)+Math.pow((y-yInicial),2)))>400)
			return false;
		return true;
	}
	
	/**
	 * função para atacar um inimigo
	 * @param m
	 */
	public void atacar(Monstro m){
		m.setVida(m.getVida()-this.ataque);
	}

}
