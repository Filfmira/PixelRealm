package com.example.lolitos2;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

public class Jogo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6340455120385832207L;
	private Heroi heroi;
	private ArrayList<Monstro> monstros;
	// private ArrayList<Parede> paredes;
	private ArrayList<GemsVida> gemsVida;
	private ArrayList<GemsAtaque> gemsAtaque;
	private ArrayList<Projectil> setas;
	private ArrayList<Moeda> moedas;
	private ArrayList<Passivo> fundo;
	private Parede[][] paredes = new Parede[100][100];
	private Portal portal;

	// inicializa o jogo
	public Jogo(int tamanhoCelula, Resources resources) {
		setSetas(new ArrayList<Projectil>());
		setInimigos(new ArrayList<Monstro>());
		setGemsVida(new ArrayList<GemsVida>());
		setGemsAtaque(new ArrayList<GemsAtaque>());
		setMoedas(new ArrayList<Moeda>());
		setFundo(new ArrayList<Passivo>());

		// percorre o ficheiro do mapa
		Bitmap tab=BitmapFactory.decodeResource(resources, com.example.lolitos2.R.drawable.mappixel);
		Log.e("w", tab.getWidth()+"."+tab.getHeight());
		for (int i = 0; i < tab.getWidth(); i++) {
			for (int j = 0; j < tab.getHeight(); j++) {
				int x = j;
				int y = i;
				switch (tab.getPixel(x, y)) {
				// se o é uma parede
				case Color.BLACK:
					getParedes()[x][y] = new Parede(x, y);
					break;
				case Color.YELLOW:
					fundo.add(new Passivo(x,y,1));
					break;
				case Color.CYAN:
					fundo.add(new Passivo(x,y,2));
					break;
				case Color.RED:
					this.setPortal(new Portal(x,y));
					break;
				default:
					fundo.add(new Passivo(x,y,0));
				}

			}
		}
		//this.setPortal(new Portal(10,10));
		// setHeroi(new Heroi(5,7));
		setHeroi(new Heroi((Entidade.sw / 2) - (Entidade.tamanhoCelula / 2),
				(Entidade.sh / 2) - (Entidade.tamanhoCelula / 2)));
		// if(getInimigos().isEmpty())getHeroi().color=Color.GREEN;
		gerarMonstros();
	}

	public Jogo(int i) {

	}

	

	
	/**
	 * gera monstros aleatoriamente
	 */
	public void gerarMonstros()
	{
		for(int x=0;x<300;x++)
		{
			int rL,rC;
			do {
				rL=(int) (Math.random()*(paredes.length/4));
				rC= (int) (Math.random()*paredes[rL].length);
			} while (paredes[rL][rC]!=null);
			Log.e("monstro", rL+"."+rC);
			monstros.add(new Monstro(rL,rC));
		}
	}

	// ////////////////A IMPLEMTENTAR PARA OS TESTES //////////////////////////
	public void movimentarHeroi(int x, int y) {
		// TODO Auto-generated method stub

	}

	
	
	// ////////////////GETS e SETS //////////////////


	public ArrayList<Projectil> getSetas() {
		return setas;
	}

	
	public ArrayList<Monstro> getInimigos() {
		return monstros;
	}

	public void setInimigos(ArrayList<Monstro> inimigos) {
		this.monstros = inimigos;
	}

	public Heroi getHeroi() {
		return heroi;
	}

	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
	}

	public ArrayList<GemsVida> getGemsVida() {
		return gemsVida;
	}

	public void setGemsVida(ArrayList<GemsVida> gemsVida) {
		this.gemsVida = gemsVida;
	}

	public Parede[][] getParedes() {
		return paredes;
	}

	public void setParedes(Parede[][] paredes) {
		this.paredes = paredes;
	}

	public void setSetas(ArrayList<Projectil> setas) {
		this.setas = setas;
	}

	public ArrayList<Moeda> getMoedas() {
		return moedas;
	}

	public void setMoedas(ArrayList<Moeda> moedas) {
		this.moedas = moedas;
	}

	public ArrayList<GemsAtaque> getGemsAtaque() {
		return gemsAtaque;
	}

	public void setGemsAtaque(ArrayList<GemsAtaque> gemsAtaque) {
		this.gemsAtaque = gemsAtaque;
	}

	public Portal getPortal() {
		return portal;
	}

	public void setPortal(Portal portal) {
		this.portal = portal;
	}

	public ArrayList<Passivo> getFundo() {
		return fundo;
	}

	public void setFundo(ArrayList<Passivo> fundo) {
		this.fundo = fundo;
	}

}
