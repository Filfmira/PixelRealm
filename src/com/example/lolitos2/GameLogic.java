package com.example.lolitos2;

import java.util.ArrayList;

import jogo.view.GameView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class GameLogic {

	// trata de lutar o heroi contra os monstros
	static public void lutar(ArrayList<Monstro> inimigos, Heroi heroi) {
		for (int i = 0; i < inimigos.size(); i++) {

			if (inimigos.get(i).colideNear(heroi)) {
				heroi.lutar(inimigos.get(i));
				// Log.e("lutar", ""+heroi.getVida());
				if (inimigos.get(i).getVida() < 0)
					inimigos.remove(i);
			}
		}
	}

	public static void adjustDx(int[] x){
		
	}
	public static boolean testaColisao(Entidade heroi, Entidade objecto, int[] x){
		int tamanho = Entidade.tamanhoCelula;
		int xHeroi = heroi.getX(), yHeroi = heroi.getY();
		int xObjecto = objecto.getX()*tamanho + Entidade.dx;
		int yObjecto = objecto.getY()*tamanho + Entidade.dy;
		
		boolean colideHorDir = xObjecto+x[0] <= xHeroi+tamanho && xObjecto+x[0] >= xHeroi;
		boolean colideHorEsq = xObjecto+tamanho+x[0] >= xHeroi && xObjecto+tamanho+x[0] <= xHeroi+tamanho;
		boolean colideVerBaixo = yObjecto+x[1] <= yHeroi+tamanho && yObjecto+x[1] >= yHeroi;
		boolean colideVerCima = yObjecto+tamanho+x[1] >= yHeroi && yObjecto+tamanho+x[1] <= yHeroi+tamanho;
		
		
		return ((colideVerCima || colideVerBaixo) && (colideHorEsq || colideHorDir));
		
	}
	//public static boolean col(int ax1,int ay1, int bx1,int by1, int[] x)
	public static boolean col(Entidade heroi, Entidade objecto, int[] x)
	{
		int tamanho = Entidade.tamanhoCelula;
		int xHeroi = heroi.getX(), yHeroi = heroi.getY();
		int xObjecto = objecto.getX()*tamanho + Entidade.dx;
		int yObjecto = objecto.getY()*tamanho + Entidade.dy;
		
		boolean colideHorDir = xObjecto+x[0] <= xHeroi+tamanho && xObjecto+x[0] >= xHeroi;
		boolean colideHorEsq = xObjecto+tamanho+x[0] >= xHeroi && xObjecto+tamanho+x[0] <= xHeroi+tamanho;
		boolean colideVerBaixo = yObjecto+x[1] <= yHeroi+tamanho && yObjecto+x[1] >= yHeroi;
		boolean colideVerCima = yObjecto+tamanho+x[1] >= yHeroi && yObjecto+tamanho+x[1] <= yHeroi+tamanho;
		
		
		if (! ((colideVerCima || colideVerBaixo) && (colideHorEsq || colideHorDir)))
			return false;
		
		if (!testaColisao(heroi, objecto, x))
			return false;
			
		
		//testa ajuste horizontal
	/*	if (colideHorDir)
			dx += xHeroi+tamanho - (xObjecto + dx);
		else dx += xHeroi - (xObjecto+tamanho + dx);*/

		
		int[] temp = {0, x[1]};
		//teste
		if (!testaColisao(heroi,objecto,temp)){ //se for suficiente
			x[0] = 0; //ajusta x[0] e retorna
			Log.e("ajusteDX", x[0]+ "#" + x[1] + "#" + Entidade.dx + "#" + Entidade.dy);
			return true;
		}
		
		//senao tem de testar ajuste vertical
		//ajusta
		/*if (colideVerBaixo)
			dy += yHeroi + tamanho - (yObjecto + dy);
		else dy += yHeroi - (xObjecto+tamanho + dy);*/

		
		//testa novamente
		temp[0] = x[0]; temp[1] = 0;		
		if (!testaColisao(heroi,objecto,temp)){
			x[1] = 0;//se funcionar, retorna
			Log.e("ajusteDY", x[0]+ "#" + x[1] + "#" + Entidade.dx + "#" + Entidade.dy);
			return true;
		}
		
		/*if (colideHorDir)
			x[0] += xHeroi+tamanho - (xObjecto + x[0]);
		else x[0] += xHeroi - (xObjecto+tamanho + x[0]);*/
		x[0] = 0;
		x[1] = 0;
		Log.e("ajusteDXDY", x[0]+ "#" + x[1] + "#" + Entidade.dx + "#" + Entidade.dy);;
		return true;
	}
	
	public static boolean verificaMovimento(Jogo jogo, int[] x)
	{
		
		//precorre todas as paredes e compara
		for(int i =0;i<jogo.getParedes().length;i++)
			for(int j=0;j<jogo.getParedes()[i].length;j++)
			{
				if(jogo.getParedes()[i][j]!=null)
				{
				/*if(col((Entidade.sw/2)-(Entidade.tamanhoCelula/2)
						,(Entidade.sh/2)-(Entidade.tamanhoCelula/2)
						,jogo.getParedes()[i][j].getX()*Entidade.tamanhoCelula+Entidade.dx
						(,jogo.getParedes()[i][j].getY()*Entidade.tamanhoCelula+Entidade.dy,x))*/
				if (col (jogo.getHeroi(), jogo.getParedes()[i][j], x))
					{
					jogo.getParedes()[i][j].color=Color.CYAN;
					Log.e("parede", "lol");
					/*Log.e("putas cu crlh", "----"+Entidade.dx+" ~ "
					+Entidade.dy);
					Log.e("putas cu crlh", "----"+(jogo.getParedes()[i][j].getX()*Entidade.tamanhoCelula+Entidade.dx)+" ~ "
					+(jogo.getParedes()[i][j].getY()*Entidade.tamanhoCelula+Entidade.dy));*/
					//return false;
					}
				}
			}
		return true;
		//se algum der true, poe o dx e dy a 0
		
	}
	
	
	// trata de movimentar o heroi, nao o deixa chocar com coisas
	public static void movimentaHeroi(Jogo jogo, int x, int y, GameView gameView) {
		int movimento = 0;
		int movimentoBack = 0;
		if (x < gameView.getWidth() / 5) {
			movimento = 1;
			movimentoBack = 0;
		}
		if (x > (gameView.getWidth() / 5) * 4) {
			movimento = 0;
			movimentoBack = 1;
		}
		if (y < gameView.getHeight() / 5) {
			movimento = 3;
			movimentoBack = 2;
		}
		if (y > (gameView.getHeight() / 5) * 4) {
			movimento = 2;
			movimentoBack = 3;
		}
		boolean colide = false;
		int xVelho = jogo.getHeroi().getX();
		int yVelho = jogo.getHeroi().getY();
		jogo.getHeroi().movimento(movimento, jogo);

		// verifica se foi contra qq coisa
		/*
		 * //verifica se foi contra uma parede for (int i = 0; i <
		 * jogo.getParedes().length; i++) { for (int j = 0; j <
		 * jogo.getParedes()[i].length; j++) { if(jogo.getParedes()[i][j]!=null)
		 * if (jogo.getParedes()[i][j].colide(jogo.getHeroi())) { colide = true;
		 * } } } //verifica se foi contra um inimigo for(int
		 * i=0;i<jogo.getInimigos().size();i++) {
		 * 
		 * if(jogo.getInimigos().get(i).colide(jogo.getHeroi())) { colide=true;
		 * } } //se colifiu nao anda if(colide) {
		 * jogo.getHeroi().movimento(movimentoBack,jogo); } //verifica se tem
		 * algum gems for(int i=0;i<jogo.getGemsVida().size();i++) {
		 * 
		 * if(jogo.getGemsVida().get(i).colide(jogo.getHeroi())) {
		 * jogo.getHeroi(
		 * ).setVida(jogo.getHeroi().getVida()+jogo.getGemsVida().get
		 * (i).getCapacidade()); jogo.getGemsVida().remove(i); } }
		 */
	}

	// ///////////////desenhar mini mapa ///////////////////////////////////
	public static void drawMiniMap(Jogo jogo, Canvas canvas, Paint paint) {
		
		
		int x1 = jogo.getHeroi().getX();
		int y1 = jogo.getHeroi().getY();
		
		for (int i = 0; i < jogo.getInimigos().size(); i++) {
			int x = jogo.getInimigos().get(i).getX();
			int y = jogo.getInimigos().get(i).getY();
			canvas.drawRect(x * 4, y * 4, x * 4 + 4, y * 4 + 4, paint);
		}
		paint.setColor(Color.WHITE);
		paint.setTextSize(30);
		canvas.drawText(jogo.getInimigos().get(0).getX() + "-"
				+ jogo.getInimigos().get(0).getY(), 60, 200, paint);

		for (int i = 0; i < jogo.getParedes().length; i++) {
			for (int j = 0; j < jogo.getParedes()[i].length; j++) {
				paint.setColor(Color.RED);
				if (jogo.getParedes()[i][j] != null) {
					int x = jogo.getParedes()[i][j].getX();
					int y = jogo.getParedes()[i][j].getY();
					canvas.drawRect(x * 4, y * 4, 4 + x * 4, 4 + y * 4, paint);

				}
				paint.setColor(Color.BLUE);
				if (i == x1 && j == y1)
					canvas.drawRect(x1 * 4, y1 * 4, x1 * 4 + 4, y1 * 4 + 4,
							paint);
			}
		}

		paint.setColor(Color.WHITE);
		paint.setTextSize(30);
		canvas.drawText(x1 + "-" + y1, 60, 250, paint);
	}

public static void drawMap(Jogo jogo, Canvas canvas, Paint paint) {
		
		
		int x1 = jogo.getHeroi().getX();
		int y1 = jogo.getHeroi().getY();
		
		for (int i = 0; i < jogo.getInimigos().size(); i++) {
			jogo.getInimigos().get(i).draw(canvas, paint);
		}
		for (int i = 0; i < jogo.getGemsVida().size(); i++) {
			jogo.getGemsVida().get(i).draw(canvas, paint);
		}

		for (int i = 0; i < jogo.getParedes().length; i++) {
			for (int j = 0; j < jogo.getParedes()[i].length; j++) {
				if (jogo.getParedes()[i][j] != null) {
					jogo.getParedes()[i][j].draw(canvas, paint);

				}
			}
		}

	}
	
	
	// desenha tudo no mapa
	public static void desenharEntidades(Jogo jogo, Canvas canvas, Paint paint) {
		//desenhar mini mapa
		drawMap(jogo,canvas,paint);
		//drawMiniMap(jogo,canvas,paint);
		
		
		jogo.getHeroi().draw(canvas, paint);
		
		

	}
}
