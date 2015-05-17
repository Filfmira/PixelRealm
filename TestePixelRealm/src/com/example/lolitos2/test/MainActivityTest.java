package com.example.lolitos2.test;

import pixelrealm.entities.Entidade;
import pixelrealm.entities.GemsAtaque;
import pixelrealm.entities.GemsVida;
import pixelrealm.entities.Heroi;
import pixelrealm.entities.Moeda;
import pixelrealm.entities.Monstro;
import pixelrealm.entities.Parede;
import pixelrealm.entities.Portal;
import pixelrealm.entities.Projectil;
import pixelrealm.game.GameSurface;
import pixelrealm.game.Jogo;
import pixelrealm.graphics.Imagens;
import pixelrealm.logic.GameLogic;
import pixelrealm.states.GameActivity;
import pixelrealm.states.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	GameSurface GS;
	Jogo jogo;

	public MainActivityTest() {
		super(MainActivity.class);
	} // end of SpinnerActivityTest constructor definition

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Entidade.tamanhoCelula = 1;
		Entidade.sw = 100;
		Entidade.sh = 100;
		Imagens.inicializarImagens(this.getActivity().getResources());
		jogo = new Jogo();

	} // end of setUp() method definitio

	public void testCriarJogador() {

		// Posicao Inicial do Jogador
		assertEquals(50, jogo.getHeroi().getX());
		assertEquals(50, jogo.getHeroi().getY());

		// criacao do heroi
		assertEquals(jogo.getHeroi().getVidaInicial(), jogo.getHeroi()
				.getVida());

	}

	public void testContraParede() {
		jogo.getParedes()[49][49] = new Parede(49, 49);

		// TesteContraParede
		assertEquals(false, jogo.getHeroi().colide(jogo.getParedes()[49][49]));
		assertEquals(true, jogo.getHeroi()
				.colideNear(jogo.getParedes()[49][49]));
	}

	public void testContraEnimigo() {
		// TesteContraEnimigo
		jogo.getInimigos().add(new Monstro(49, 50));
		assertEquals(false, jogo.getHeroi().colide(jogo.getInimigos().get(0)));
		assertEquals(true, jogo.getHeroi()
				.colideNear(jogo.getInimigos().get(0)));
	}

	public void testLutarEnimigo() {
		// Luta Enimigo
		jogo.getInimigos().add(new Monstro(49, 50));
		assertEquals(true, GameLogic.lutar(jogo));
		assertEquals(true, jogo.getHeroi().getVidaInicial() != jogo.getHeroi()
				.getVida());
	}

	public void testSerMorto() {
		jogo.getInimigos().add(new Monstro(49, 50));
		jogo.getInimigos().get(0).setAtaque(100000);
		assertEquals(true, GameLogic.lutar(jogo));
		assertEquals(true, jogo.getHeroi().getVida() < 0);
	}

	public void testApanharGemsAtaque() {
		// ApanharGemAtaque
		jogo.getGemsAtaque().add(new GemsAtaque(49, 50, 20));
		assertEquals(true, 0 == jogo.getHeroi().getIncAtaque());
		assertEquals(true, GameLogic.apanharGems(jogo));
		assertEquals(true, jogo.getHeroi().getIncAtaque() > 0);
		assertEquals(false, GameLogic.apanharGems(jogo));
	}

	public void testApanharGemsVida() {
		jogo.getGemsVida().add(new GemsVida(49, 50, 20));
		jogo.getHeroi().setVida(50);
		int vidaHeroiInicial = (int) jogo.getHeroi().getVida();
		assertEquals(true, GameLogic.apanharGems(jogo));
		assertEquals(true, vidaHeroiInicial < jogo.getHeroi().getVida());
	}

	public void testApanharMoeda() {
		jogo.getMoedas().add(new Moeda(49, 50, 20));
		int dinheiroHeroiInicial = (int) jogo.getHeroi().getDinheiro();
		assertEquals(true, GameLogic.apanharMoedas(jogo));
		assertEquals(true, dinheiroHeroiInicial < jogo.getHeroi().getDinheiro());
	}
	
	public void testPortalMudarNivel()
	{
		jogo.setPortal(new Portal(50,50));
		jogo.setMoedasApanhadas(50);
		jogo.setPortalNum(10);
		int nivel=jogo.getHeroi().getNivel();
		assertEquals(true,GameLogic.colidePortal(jogo));
		assertEquals(true,nivel<jogo.getHeroi().getNivel());
	}

	public void testSetasMexer(){
		jogo.getInimigos().add(new Monstro(49, 50));
		jogo.getSetas().add(new Projectil(49,49,-1,-1,jogo.getHeroi()));
		assertEquals(1,jogo.getSetas().size());
		GameLogic.setasUpdate(jogo);
		assertEquals(true,jogo.getInimigos().get(0).getVida()<jogo.getInimigos().get(0).getVidaInicial());
		
	}
	
	public void testSetasMatarInimigo(){
		jogo.getInimigos().add(new Monstro(49, 49));
		jogo.getSetas().add(new Projectil(49,49,-1,-1,jogo.getHeroi()));
		jogo.getSetas().get(0).setAtaque(500000000);
		assertEquals(1,jogo.getSetas().size());
		//matou
		GameLogic.setasUpdate(jogo);
		//cria um novo monstro ficando o numero de inimigos constante
		assertEquals(1,jogo.getInimigos().size());

	}
	
}
