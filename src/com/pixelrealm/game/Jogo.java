package com.pixelrealm.game;

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

import com.pixelrealm.entities.Entidade;
import com.pixelrealm.entities.GemsAtaque;
import com.pixelrealm.entities.GemsVida;
import com.pixelrealm.entities.Heroi;
import com.pixelrealm.entities.Moeda;
import com.pixelrealm.entities.Monstro;
import com.pixelrealm.entities.Parede;
import com.pixelrealm.entities.Passivo;
import com.pixelrealm.entities.Portal;
import com.pixelrealm.entities.Projectil;
import com.pixelrealm.graphics.Imagens;
import com.pixelrealm.states.GameActivity;

import junit.framework.TestCase;

public class Jogo extends TestCase  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6340455120385832207L;
	private Heroi heroi=null;
	private int moedasApanhadas=0;
	private int portalNum=20;
	private ArrayList<Monstro> monstros;
	// private ArrayList<Parede> paredes;
	private ArrayList<GemsVida> gemsVida;
	private ArrayList<GemsAtaque> gemsAtaque;
	private ArrayList<Projectil> setas;
	private ArrayList<Moeda> moedas;
	private ArrayList<Passivo> fundo;
	private Parede[][] paredes = new Parede[50][50];
	private Portal portal;

	/**
	 * Inicializa o Jogo
	 * @param tamanhoCelula	tamanho da celula unitaria 
	 * @param resources	para ter acesso as imagens
	 */
	public Jogo(int tamanhoCelula, Resources resources) {
		iniciarHeroi();

		loadMapa(1);
		gerarMonstros();
		GameActivity.getInstance().start.start();
	}


	/**
	 * Inicializa o Jogo com recurso a um Heroi
	 * @param heroi
	 */
	public Jogo(Heroi heroi) {
		this.setHeroi(heroi);
		iniciarHeroi();
		loadMapa(heroi.getNivel());
		gerarMonstros();
		GameActivity.getInstance().start.start();
	}



	/**
	 * Inicizalizador do Jogo para efeitos de teste
	 */
	public Jogo() {
		iniciarHeroi();
		setSetas(new ArrayList<Projectil>());
		setInimigos(new ArrayList<Monstro>());
		setGemsVida(new ArrayList<GemsVida>());
		setGemsAtaque(new ArrayList<GemsAtaque>());
		setMoedas(new ArrayList<Moeda>());
		setFundo(new ArrayList<Passivo>());
	}



	/**
	 * gera monstros aleatoriamente
	 */
	public void gerarMonstros()
	{
		for(int x=0;x<50;x++)
		{
			gerarMonstro(heroi.getNivel());
		}
	}
	
	public void gerarMonstro(int nivel)
	{
		int rL,rC;
		do {
			rL=(int) (Math.random()*(paredes.length));
			rC= (int) (Math.random()*paredes[rL].length);
		} while (paredes[rL][rC]!=null);
		if(nivel==2)
		{
			int m=(int) (Math.random()*5);
			if(m==4)
			{Log.e("lorion", "lorion");
				
				monstros.add(new Monstro(rL,rC,Imagens.getMonstro2(),500,5000));
			}
			else
				monstros.add(new Monstro(rL,rC));
				
		}
		else
			monstros.add(new Monstro(rL,rC));
	}

	// ////////////////A IMPLEMTENTAR PARA OS TESTES //////////////////////////
	public void movimentarHeroi(int x, int y) {
		// TODO Auto-generated method stub

	}

	public void matarMonstro(int index)
	{
		this.monstros.remove(index);
		this.gerarMonstro(heroi.getNivel());
	}
	
	public void iniciarHeroi()
	{
		Entidade.dx=0;
		Entidade.dy=0;
		Log.e("display", "w"+Entidade.sw+" h"+Entidade.sh);
		if(heroi==null)
		{
				setHeroi(new Heroi((Entidade.sw / 2) - (Entidade.tamanhoCelula / 2),
						(Entidade.sh / 2) - (Entidade.tamanhoCelula / 2)));
				
		}
		
		else
		{
				heroi.setX((Entidade.sw / 2) - (Entidade.tamanhoCelula / 2));
				heroi.setY((Entidade.sh / 2) - (Entidade.tamanhoCelula / 2));
		}
		
		Log.e("herooooooooooi",heroi.getX()+"."+heroi.getY());
	}
	
	public void loadMapa(int nivel)
	{
		setSetas(new ArrayList<Projectil>());
		setInimigos(new ArrayList<Monstro>());
		setGemsVida(new ArrayList<GemsVida>());
		setGemsAtaque(new ArrayList<GemsAtaque>());
		setMoedas(new ArrayList<Moeda>());
		setFundo(new ArrayList<Passivo>());
		
		Log.e("nivel", "niv:"+nivel);
		Bitmap tab = null;
		switch (nivel) {
		case 1:
			tab=Imagens.getNivel1();
			break;
		case 2:
			tab=Imagens.getNivel2();
			break;

		default:
			tab=Imagens.getNivel2();;
		}
		
		for (int i = 0; i < tab.getWidth(); i++) {
			for (int j = 0; j < tab.getHeight(); j++) {
				int x = j;
				int y = i;
				switch (tab.getPixel(x, y)) {
				// se o � uma parede
				case Color.BLACK:
					if(tab==Imagens.getNivel2())
						getParedes()[x][y]= new Parede(x,y,1);
						else
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

	public int getPortalNum() {
		return portalNum;
	}

	public void setPortalNum(int portalNum) {
		this.portalNum = portalNum;
	}


	public int getMoedasApanhadas() {
		return moedasApanhadas;
	}


	public void setMoedasApanhadas(int moedasApanhadas) {
		this.moedasApanhadas = moedasApanhadas;
	}

}