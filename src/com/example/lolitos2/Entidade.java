package com.example.lolitos2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Entidade {

	protected int x;
	protected int y;
	protected static int deslocX=0;
	protected static int deslocY=0;
	private int width;
	private int height;
	public static int tamanhoCelula=200;
	protected static int sw=0;//screenwidth
	protected static int sh=0;//screenheight
	protected static int dx=0;
	protected static int dy=0;
	protected int color=Color.RED;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Entidade(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	
	//Se vai contra alguma Entidade
	public boolean colide(Entidade r){
		//esta a direita
		if(r.getX()>=x+width)return false;
		//esta a baixo
		if(r.getY()>=y+height)return false;
		//esta a esquerda
		if(r.getX()+r.getWidth()<=x)return false;
		//esta a cima
		if( r.getY()+r.getHeight()<=y)return false;
		
		return true;
	}
	
	public boolean colideNear(Entidade r){
		//esta a direita
		if(r.getX()>x+width)return false;
		//esta a baixo
		if(r.getY()>y+height)return false;
		//esta a esquerda
		if(r.getX()+r.getWidth()<x)return false;
		//esta a cima
		if( r.getY()+r.getHeight()<y)return false;
		
		return true;
	}
	
	//Se clicamos em cima dela
	public boolean colide(int x2, int y2){
		//esta a direita
		if(x2>x+width)return false;
		//esta a baixo
		if(y2>y+height)return false;
		//esta a esquerda
		if(x2<x)return false;
		//esta a cima
		if(y2<y)return false;
		
		return true;
	}
	
	public void draw(Canvas canvas, Paint paint){
		/*x=x+deslocX;
		y=y+deslocY;*/
		paint.setColor(color);
		canvas.drawRect(x * tamanhoCelula+Entidade.dx, y * tamanhoCelula+Entidade.dy, tamanhoCelula + x * tamanhoCelula+Entidade.dx, tamanhoCelula + y * tamanhoCelula+Entidade.dy, paint);
	}
	
	
	public void movimento(int direcao)
	{
		switch (direcao) {
		case 0:
			setX(getX()-getWidth());
			break;
		case 1:
			setX(getX()+getWidth());
			break;
		case 2:
			setY(getY()-getHeight());
			break;
		case 3:
			setY(getY()+getHeight());
			break;

		default:
			break;
		}
	}
	
	
}
