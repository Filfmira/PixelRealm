package com.example.lolitos2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Monstro extends Personagem{

	private int originalX;
	private int originalY;
	public Monstro(int x, int y) {
		super(x, y);
		setOriginalX(x);
		setOriginalY(y);
		setVida(5000);
		vidaInicial=5000;
		ataque=40;
		movimento=1;
		imagem=Imagens.monstro;
		
		/*imagem = BitmapFactory.decodeResource(res, R.drawable.monstro);
	 	imagem = Bitmap.createScaledBitmap(imagem, Entidade.tamanhoCelula, Entidade.tamanhoCelula, true);*/
	}

	public void setMovimento(int mov) {
		movimento=mov;
		
	}
	

	public void draw(Canvas canvas, Paint paint){
		super.draw(canvas, paint);
		paint.setColor(Color.RED);
		canvas.drawRect(x*Entidade.tamanhoCelula+Entidade.dx, y*tamanhoCelula+tamanhoCelula*7/8+Entidade.dy, x*tamanhoCelula+Entidade.tamanhoCelula+Entidade.dx, y*tamanhoCelula+Entidade.tamanhoCelula+Entidade.dy, paint);
		paint.setColor(Color.CYAN);
		canvas.drawRect(x*Entidade.tamanhoCelula+Entidade.dx, y*tamanhoCelula+tamanhoCelula*7/8+Entidade.dy, ((float)(x*tamanhoCelula+(Entidade.tamanhoCelula*((float)(getVida()/vidaInicial)))+Entidade.dx)), y*tamanhoCelula+Entidade.tamanhoCelula+Entidade.dy, paint);
	}
	
	public void movimento(int direcao)
	{
		/*setOriginalX(getOriginalX() + deslocX);
		setOriginalY(getOriginalY() + deslocY);*/
		int xAntigo=this.getX();
		int yAntigo=this.getY();
		super.movimento(direcao);
		if(this.getX()>getOriginalX()+tamanhoCelula||this.getX()<getOriginalX()-tamanhoCelula || this.getY()>getOriginalY()+tamanhoCelula || this.getY()<getOriginalY()-tamanhoCelula)
		{
		this.setX(xAntigo);
		this.setY(yAntigo);
		}
	}

	public int getOriginalX() {
		return originalX;
	}

	public void setOriginalX(int originalX) {
		this.originalX = originalX;
	}

	public int getOriginalY() {
		return originalY;
	}

	public void setOriginalY(int originalY) {
		this.originalY = originalY;
	}

}
