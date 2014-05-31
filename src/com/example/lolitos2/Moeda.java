package com.example.lolitos2;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Moeda extends Entidade{

	int capacidade;
	int t=0;
	public Moeda(int x, int y, int capacidade) {
		super(x, y, Entidade.tamanhoCelula, Entidade.tamanhoCelula);
		this.capacidade=capacidade;
		imagem=Imagens.moeda;
	}
	
	public Moeda(Monstro m)
	{
		super(m.getX(),m.getY(),Entidade.tamanhoCelula,Entidade.tamanhoCelula);
		this.capacidade=(int) (m.vidaInicial/500);
		imagem=Imagens.moeda;
	}
	
	int counter =0;
	public void draw(Canvas canvas, Paint paint){
		
		if(counter==3)
		{
			counter=0;
			if(t==0)
			{
				imagem=Imagens.moeda;
				t=1;
			}
			else
			{
				t=0;
				imagem=Imagens.moeda2;
			}
			
		}
		canvas.drawBitmap(imagem, x * tamanhoCelula+Entidade.dx, y * tamanhoCelula+Entidade.dy, paint);
		
		counter++;
		

	}

}
