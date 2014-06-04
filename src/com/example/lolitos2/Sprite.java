package com.example.lolitos2;

import java.io.Serializable;

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
	int lol=0;
	
	public void update(){
		switch (lol) {
		case 0:
			
			break;
		case 1:
			
			break;

		default:
			break;
		}
		counter++;
		if(counter==2)
		{
			counter=0;
		currentFrame=++currentFrame%colums; //4 collums
		}
	}
	
	
	////////////////////////////////////////////////////////////////////////////
	////ATENCAO, TEMOS DE MUDAR O DST PARA O TAMNHO SER VARIAVEL ///////////////
	////////////////////////////////////////////////////////////////////////////
	
	public void draw(Canvas canvas,int x,int y)
	{
		update();
		int srcY=getDirection()*height;
		int srcX=currentFrame*width;
		Rect src= new Rect(srcX,srcY,srcX+width,srcY+height);
		Rect dst= new Rect(x,y,x+Entidade.tamanhoCelula,y+Entidade.tamanhoCelula);
		canvas.drawBitmap(b,src,dst,null);
	}
	
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


	/*public void draw(Canvas canvas, int x, int y, float angulo) {
		update();
		int srcY=getDirection()*height;
		int srcX=currentFrame*width;
		Rect src= new Rect(srcX,srcY,srcX+width,srcY+height);
		Rect dst= new Rect(x,y,x+Entidade.tamanhoCelula,y+Entidade.tamanhoCelula);
		canvas.drawBitmap(b,src,dst,null);
		
	}*/
	
}
