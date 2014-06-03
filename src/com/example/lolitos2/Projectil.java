package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Projectil extends Entidade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8163520196636070708L;
	float pdx,pdy;
	float a, b;
	int dxI,dyI;
	int ataque;
	int xInicial,yInicial;
	float angulo;
	
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
		dxI=Entidade.dx;
		dyI=Entidade.dy;
		//imagem=Imagens.seta;
		this.pdx=pdx;
		this.pdy=pdy;
		a=x;
		b=y;
		ataque=(int) (heroi.ataque*2);
		//if(x<=Entidade.sw/2)
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
		//this.imagem=Imagens.RotateBitmap(this.imagem, angulo);
		/*else
			this.imagem=Imagens.RotateBitmap(this.imagem, (float) (-45+Math.toDegrees(Math.atan((float)(pdy/pdx)))));*/
	}
	
	/**
	 * faz update � posicao da seta para que v� "andando" para o seu destino
	 * @return
	 */
	int counter=0;
	public boolean update()
	{
		a+=(pdx);
		b+=(pdy);
		
		this.x=(int) a+(Entidade.dx-dxI);
		this.y=(int) b+(Entidade.dy-dyI);
		if(counter==20)
			return false;
		counter++;
		return true;
	}
	
	/**
	 * fun��o para atacar um inimigo
	 * @param m
	 */
	public void atacar(Monstro m){
		m.setVida(m.getVida()-this.ataque);
	}

	@Override
	public Bitmap getImagem() {
		return Imagens.RotateBitmap(Imagens.seta, angulo);
	}

}
