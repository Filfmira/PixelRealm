package com.example.lolitos2;

import java.io.Serializable;

public abstract class Personagem extends Entidade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2527421795633542694L;
	private float vida=1000000;
	float vidaInicial=1000000;
	float ataque=0;
	protected int movimento= 0;
	
	public Personagem(int x, int y) {
		super(x,y,tamanhoCelula,tamanhoCelula);
	}
	
	public void mexe(int w, int h){
		switch (movimento) {
		case 0:
			mexeBaixo(w,h);
			break;
		case 1:
			mexeCima(w,h);
		case 2:
			mexeDireita(w,h);
			break;
		case 3:
			mexeEsquerda(w,h);
			break;
		default:
			break;
		}
		return;
	}
	
	
	
	public void mexeBaixo(int w, int h){
		//if(getY()<h)
			this.setY(getY()+1);
		/*else
		{
			setY(-5);
			int x= (int) (Math.random()*(w-tamanhoCelula)); //numero aleatorio da tela
			setX(x);
		}*/
	}
	
	public void mexeCima(int w, int h){
		//if(getY()>0)
			this.setY(getY()-1);
		/*else
		{
			setY(h+5);
			int x= (int) (Math.random()*(w-tamanhoCelula)); //numero aleatorio da tela
			setX(x);
		}*/
	}
	
	public void mexeDireita(int w, int h){
		//if(getX()<h)
			this.setX(getX()+1);
		/*else
		{
			setX(-5);
			int y= (int) (Math.random()*(h-tamanhoCelula)); //numero aleatorio da tela
			setY(y);
		}*/
	}
	
	private void mexeEsquerda(int w, int h) {
		//if(getX()+getWidth()>0)
			this.setX(getX()-1);
		/*else
		{
			setX(w+5);
			int y= (int) (Math.random()*(h-tamanhoCelula)); //numero aleatorio da tela
			setY(y);
		}*/
		
	}

	float getVida() {
		return vida;
	}

	public void setVida(float vida) {
		this.vida = vida;
	}
	
	
	
}
