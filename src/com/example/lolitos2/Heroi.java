package com.example.lolitos2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Heroi extends Personagem{

	int dinheiro;
	Arma arma;
	
	public Heroi(int x, int y) {
		super(x, y);
		setVida(20000);
		vidaInicial=20000;
		ataque=50;
		movimento=2;
		color=Color.BLUE;
	 	imagem = Imagens.heroi;
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
		
		canvas.drawBitmap(imagem, this.getX(), this.getY(), paint);
		
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
		/*case 0:
			Entidade.deslocX=	-Entidade.tamanhoCelula;
			break;
		case 1:
			Entidade.deslocX=	+Entidade.tamanhoCelula;
			break;
		case 2:
			Entidade.deslocY=	-Entidade.tamanhoCelula;
			break;
		case 3:
			Entidade.deslocY=	+Entidade.tamanhoCelula;
			break;*/
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
		/*for(int i =0;i<jogo.getParedes().length;i++)
		{
			for(int j =0;j<jogo.getParedes().length;j++)
			{
				if(jogo.getParedes()[i][j]!=null)
				{
					jogo.getParedes()[i][j].setX(jogo.getParedes()[i][j].getX()+deslocX);
					jogo.getParedes()[i][j].setY(jogo.getParedes()[i][j].getY()+deslocY);
				}
			}
		}
		for(int i =0;i<jogo.getGemsVida().size();i++)
		{
			jogo.getGemsVida().get(i).setX(jogo.getGemsVida().get(i).getX()+deslocX);
			jogo.getGemsVida().get(i).setY(jogo.getGemsVida().get(i).getY()+deslocY);
		}
		for(int i =0;i<jogo.getInimigos().size();i++)
		{
			jogo.getInimigos().get(i).setX(jogo.getInimigos().get(i).getX()+deslocX);
			jogo.getInimigos().get(i).setY(jogo.getInimigos().get(i).getY()+deslocY);
			jogo.getInimigos().get(i).setOriginalX(jogo.getInimigos().get(i).getOriginalX()+deslocX);
			jogo.getInimigos().get(i).setOriginalY(jogo.getInimigos().get(i).getOriginalY()+deslocY);
			
		}
		deslocX=0;
		deslocY=0;*/
	}
	//Arma??
	
	
	public void apanharGemsVida(GemsVida gm)
	{
		this.setVida(this.getVida()+gm.getCapacidade());
	}
	
}
