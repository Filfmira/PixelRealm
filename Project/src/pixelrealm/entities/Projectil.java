package pixelrealm.entities;

import pixelrealm.graphics.Imagens;
import android.graphics.Bitmap;

public class Projectil extends Entidade{

	float pdx,pdy;
	float a, b;


	int dxI,dyI;
	int ataque;
	int xInicial,yInicial;
	float angulo;
	int counter=0;

	/**
	 * Cria um projectil com determinada posicao
	 * @param x	posicao do projetil
	 * @param y	posicao do projetil
	 * @param pdx	deslocamento x do projetil
	 * @param pdy	deslocamento y do projetil
	 * @param heroi	referencia para o ataque do projetil
	 */
	public Projectil(int x, int y, float pdx, float pdy,Heroi heroi) {
		super(x,y,tamanhoCelula,tamanhoCelula);
		xInicial=x;
		yInicial=y;
		dxI=Entidade.dx;
		dyI=Entidade.dy;
		//imagem=Imagens.seta;
		this.pdx=pdx;
		this.pdy=pdy;
		a=x;
		b=y;
		ataque=(int) (heroi.getAtaque()*2);
		//if(x<=Entidade.sw/2)
		//Log.e("tintas", xInicial+"-"+Entidade.sw/2);
		if(pdx>0)
			angulo=(float) (135+Math.toDegrees(Math.atan((float)(pdy/pdx))));
		else if(pdx<0)
			angulo=(float) (180+135+Math.toDegrees(Math.atan((float)(pdy/pdx))));
		else
			{if(pdy>0)
			angulo=(float) (135+90);
			else
				angulo=(float) (135-90);
			}
		//sprite= new Sprite( Imagens.setasSprite,2,2,Entidade.tamanhoCelula,Entidade.tamanhoCelula);
		//this.imagem=Imagens.RotateBitmap(this.imagem, angulo);
		/*else
			this.imagem=Imagens.RotateBitmap(this.imagem, (float) (-45+Math.toDegrees(Math.atan((float)(pdy/pdx)))));*/
	}
	
	/**
	 * Faz update á posicao da seta para que vá "andando" para o seu destino
	 * @return
	 */
	public boolean update()
	{
		a+=(pdx);
		b+=(pdy);
		
		this.x=(int) a+(Entidade.dx-dxI);
		this.y=(int) b+(Entidade.dy-dyI);
		if(counter==20)
			return false;
		counter++;
		return true;
	}
	
	/**
	 * função para atacar um inimigo
	 * @param m
	 */
	public void atacar(Monstro m){
		m.setVida(m.getVida()-this.ataque);
	}

	@Override
	public Bitmap getImagem() {
		return Imagens.RotateBitmap(Imagens.getSeta(), angulo);
	}

	
	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
}
