package com.example.lolitos2;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Moeda extends Catchable{

	public Moeda(int x, int y, int capacidade) {
		super(x,y,capacidade);
		imagem=Imagens.moeda;
	}
	
	public Moeda(Monstro m)
	{
		super(m,(int) (m.vidaInicial/500));
		imagem=Imagens.moeda;
	}
	
	int t=0;
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
