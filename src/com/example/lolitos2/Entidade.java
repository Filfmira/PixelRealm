package com.example.lolitos2;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Entidade implements Serializable{


	private static final long serialVersionUID = 452594602610883689L;
	protected int x;
	protected int y;
	private int width;
	private int height;
	public static int tamanhoCelula=200;
	protected static int sw=0;//screenwidth
	protected static int sh=0;//screenheight
	protected static int dx=0;
	protected static int dy=0;
	
	/**
	 * Construtor da classe Entidade
	 * @param x	posicao horizontal
	 * @param y	posicao vertical
	 * @param width	largura do objecto
	 * @param height altura do objecto
	 */
	public Entidade(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	
	/**
	 * Funcao de colisao entre Entidades
	 * @param r Entidade com que colide
	 * @return true se colide, ou false se nao colide
	 */
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
	
	/**
	 * Funcao de quase colisao entre Entidades (para quando uma entidade "encostar à outra")
	 * @param r Entidade com que colide
	 * @return true se colide, ou false se nao colide
	 */
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
	
	/**
	 * Funcao que desenha num canvas a imagem da entidade, tendo em conta o tamanho da celula
	 * @param canvas onde desenha
	 * @param paint com as defenicoes com que desenha
	 */
	public void draw(Canvas canvas, Paint paint){
		
		canvas.drawBitmap(getImagem(), x * tamanhoCelula+Entidade.dx, y * tamanhoCelula+Entidade.dy, paint);
	}
	
	/**
	 * Funcao que desenha num canvas a imagem da entidade, directamente atraves do x e y
	 * @param canvas onde desenha
	 * @param paint	com as defenicoes com que desenha
	 */
	public void drawDirect(Canvas canvas, Paint paint){
		/*x=x+deslocX;
		y=y+deslocY;*/
		/*paint.setColor(color);
		canvas.drawRect(x , y , this.getWidth(), this.getHeight(), paint);*/
		canvas.drawBitmap(getImagem(), x, y , paint);
	}
	
	
	/**
	 * Movimento com que mexe uma entidade
	 * @param direcao 0 é esquerda, 1 é direita, 2 é cima, 3 é baixo   
	 */
	public void movimento(int direcao)
	{
		switch (direcao) {
		case 0:
			setX(getX()-1);
			break;
		case 1:
			setX(getX()+1);
			break;
		case 2:
			setY(getY()-1);
			break;
		case 3:
			setY(getY()+1);
			break;

		default:
			break;
		}
	}
	
	/**
	 * funcao para obter a imagem da entidade da classe Imagens
	 * @return
	 */
	public abstract Bitmap getImagem();
	
	
	//////////////////////////////////////////
	/////////// GETTERS E SETTERS  ///////////
	//////////////////////////////////////////	
	
	
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
}
