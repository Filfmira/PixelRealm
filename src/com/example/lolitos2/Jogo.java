package com.example.lolitos2;

import java.util.ArrayList;
import java.util.Vector;

import android.graphics.Color;
import android.util.Log;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

import javax.imageio.ImageIO;
public class Jogo {
	
	private Heroi heroi;
	private ArrayList<Monstro> monstros;
	//private ArrayList<Parede>  paredes;
	private ArrayList<GemsVida>  gemsVida;
	private ArrayList<GemsAtaque>  gemsAtaque;
	private ArrayList<Projectil>  setas;
	private ArrayList<Moeda>  moedas;
	private Parede[][] paredes= new Parede[200][200];
	private InputStream inputStream;
	

	
	//inicializa o jogo
	public Jogo(int tamanhoCelula,InputStream inputStream){
		this.inputStream=inputStream;
		ArrayList<String>tab=readText();
		setSetas(new ArrayList<Projectil>());
		setInimigos(new ArrayList<Monstro>());
		setGemsVida(new ArrayList<GemsVida>());
		setGemsAtaque(new ArrayList<GemsAtaque>());
		setMoedas(new ArrayList<Moeda>());
		
		//percorre o ficheiro do mapa
		for(int i =0;i<tab.size();i++)
		{
			for(int j=0;j<tab.get(i).length();j++)
			{
				int x=j;
				int y=i;
				switch (tab.get(i).charAt(j)) {
				// se x é um inimigo
				case 'x':
					getInimigos().add (new Monstro(x,y));
					getInimigos().get(getInimigos().size()-1).setMovimento(0);
					break;
				//se o é uma parede
				case 'o':
					getParedes()[x][y]=new Parede(x,y);
					break;
				//se v é uma gem de vida
				case 'v':
					getGemsVida().add(new GemsVida(x,y,5000));
					break;
				default:
					break;
				}
				
			}
		}
		//setHeroi(new Heroi(5,7));
		setHeroi(new Heroi((Entidade.sw/2)-(Entidade.tamanhoCelula/2),(Entidade.sh/2)-(Entidade.tamanhoCelula/2)));
		//if(getInimigos().isEmpty())getHeroi().color=Color.GREEN;
	}


	
	
	public Jogo(int i) {

	}




	//le o texto para um array list
	private ArrayList<String> readText()
	{
		
		ArrayList<String> s = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				s.add(line.toString());

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	
	
	
	//////////////////			GETS e SETS 				//////////////////
	
	
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////A IMPLEMTENTAR PARA OS TESTES //////////////////////////
	public void movimentarHeroi(int x, int y) {
		// TODO Auto-generated method stub
		
	}




	public ArrayList<Projectil> getSetas() {
		return setas;
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








	



}
