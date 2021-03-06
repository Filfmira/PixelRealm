package pixelrealm.entities;

import pixelrealm.graphics.Imagens;
import pixelrealm.graphics.Sprite;
import android.graphics.Bitmap;

public class GemsAtaque extends Catchable{


	/**
	 * Inicializa a GemsAtaque atraves de um Monstro(capacidade e posicao)
	 * @param m
	 */
	public GemsAtaque(Monstro m) {
		super(m,500);
		//imagem=Imagens.gemsataque;
		sprite= new Sprite(getImagem(),2,4,x,y);
	}
	

	public GemsAtaque(int x, int y, int capacidade) {
		super(x,y,capacidade);
	}


	@Override
	public Bitmap getImagem() {
		return Imagens.getGemsAtaqueSprite();
	}

}
