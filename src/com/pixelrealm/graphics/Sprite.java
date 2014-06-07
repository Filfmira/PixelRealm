package com.pixelrealm.graphics;

import java.io.Serializable;

import com.pixelrealm.entities.Entidade;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite  {

	int height,width;
	int currentFrame=0;
	private int direction=0;
	int counter=0;
	int lines,colums;
	Bitmap b;
	
	/**
	 * Inicialização de uma sprite
	 * @param bitmap source do sprite
	 * @param lines	n de linhas que tem a sprite(imagens)
	 * @param colums n de colunas que tem a sprite(colunas)
	 * @param w	largura da sprite
	 * @param h comprimento da sprite
	 */
	public Sprite(Bitmap bitmap, int lines , int colums, int w, int h)
	{
		b=bitmap;
		this.lines=lines;
		this.colums=colums;
		this.width=w;
		this.height=h;
		
		height=b.getHeight()/lines;
		width=b.getWidth()/colums;
	}
	
	/**
	 * Faz update ao frame que o sprite deve mostrar na linha
	 */
	public void update(){
		counter++;
		if(counter==2)
		{
			counter=0;
		currentFrame=++currentFrame%colums; //4 collums
		}
	}
	
	/**
	 * Desenha a sprite em x,y
	 * @param canvas
	 * @param x
	 * @param y
	 */
	public void draw(Canvas canvas,int x,int y)
	{
		update();
		int srcY=getDirection()*height;
		int srcX=currentFrame*width;
		Rect src= new Rect(srcX,srcY,srcX+width,srcY+height);
		Rect dst= new Rect(x,y,x+Entidade.tamanhoCelula,y+Entidade.tamanhoCelula);
		canvas.drawBitmap(b,src,dst,null);
	}
	
	/**
	 * Desenha em x,y com tamanho w,h
	 * @param canvas
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void draw(Canvas canvas,int x,int y,int w,int h)
	{
		update();
		int srcY=getDirection()*height;
		int srcX=currentFrame*width;
		Rect src= new Rect(srcX,srcY,srcX+width,srcY+height);
		Rect dst= new Rect(x,y,x+w,y+h);
		canvas.drawBitmap(b,src,dst,null);
	}

	
	
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	
}
