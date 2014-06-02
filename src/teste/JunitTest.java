package teste;


public class JunitTest {
//
//	
//	 @Test
//	 public void testeMexeHeroi(){
//		 /*
//		  * Inicializar Jogo
//		  */
//		 
//		 Heroi.setPosicao(5,5);
//		 //mover baixo
//		 int dif=Entidade.dx;
//		 assertEquals(true,Heroi.moverBaixo());
//		 dif-=Entidade.dy;
//		 assertEquals(true, dif=-1);
//		 
//		 //mover cima
//		 dif=Entidade.dx;
//		 assertEquals(true,Heroi.moverCima());
//		 dif-=Entidade.dy;
//		 assertEquals(true, dif=1);
//		 
//		 //mover direita
//		 dif=Entidade.dx;
//		 assertEquals(true,Heroi.moverDireita());
//		 dif-=Entidade.dx;
//		 assertEquals(true, dif=1);
//		 
//		 //mover esquerda
//		 dif=Entidade.dx;
//		 assertEquals(true,Heroi.moverEsquerda());
//		 dif-=Entidade.dx;
//		 assertEquals(true, dif=-1);
//		 
//	 }
//	 
//	 @Test
//	 public void testeContraParede(){
//		 
//		 /*
//		  * Inicializar Jogo
//		  */
//		 
//		 //teremos um mapa inicial onde estaremos este tipo de colisoes, pondo a posicao hard coded
//		 Heroi.setPosicao(2,2);
//		 assertEquals(false,Heroi.moverCima());
//		 assertEquals(false,Heroi.moverBaixo());
//	 }
//	 
//	 @Test
//	 public void testeContraEnimigo(){
//		 
//		 /*
//		  * Inicializar Jogo
//		  */
//		 
//		 //mover para perto do inimigo
//		 Heroi.setPosicao(9,9);
//		 assertEquals(true,Heroi.moverDireita());
//		
//		 int vidaHeroi=Heroi.getVida();
//		//verificar se nao passa pro cima do enimigo
//		 assertEquals(false,Heroi.moverDireita());
//		 //ir contra um enimigo e perder vida
//		 assertEquals(true,vidaHeroi>Heroi.getVida());
//	 }
//	 
//	 @Test
//	 public void testeMatarEnimigo(){
//		 
//		 /*
//		  * Inicializar Jogo
//		  */
//		 
//		 Heroi.getArma().setAtaque(500000);
//		 
//		 Heroi.setPosicao(9,9);
//		 
//		 int numEnimigos=jogo.getEnimigos().size();
//		 assertEquals(true,Heroi.moverDireita());
//		 assertEquals(false,Heroi.moverDireita());
//		 
//		 assertEquals(true,jogo.getEnimigos().size()=numEnimigos-1);
//
//	 }
//	 
//	 @Test
//	 public void tetseSerMorto(){
//		 
//		 /*
//		  * Inicializar Jogo
//		  */
//		 
//		Heroi.setVida(1);
//		Heroi.setPosicao(9,9);
//		 int numEnimigos=jogo.getEnimigos().size();
//		 assertEquals(true,Heroi.moverDireita());
//		 assertEquals(false,Heroi.moverDireita());
//		 assertEquals(false,Heroi.getVida());
//	 }
//	 
//	 @Test
//	 public void testeApanharArma(){
//		 
//		 /*
//		  * Inicializar Jogo
//		  */
//		 
//		 //local onde haverá uma arma ao lado
//		 Heroi.setPosicao(20,20);
//		 int numArmas=Heroi.getNumArmas();
//		 assertEquals(true,Heroi.moverDireita());
//		 assertEquals(true,Heroi.getNumArmas()>numArmas);
//	 }
//	 
//	 @Test
//	 public void testeGems(){
//		 
//		 /*
//		  * Inicializar Jogo
//		  */
//		 
//		 //ir contra um gems e ganhar vida
//		 //onde havera uma gems
//		 Heroi.setPosicao(20,25);
//		 int vidaHeroi=Heroi.getVida();
//		 assertEquals(true,Heroi.moverDireita());
//		 assertEquals(true,Heroi.getVida()>vidaHeroi);
//	 }
//	 
//	 @Test
//	 public void testeDinheiro(){
//		 
//		 /*
//		  * Inicializar Jogo
//		  */
//		 
//		 //ir contra dinheiro e a quantia do heroi aumentar
//		//onde havera dinheiro
//		 Heroi.setPosicao(25,25);
//		 int dinherio=Heroi.getDinheiro();
//		 assertEquals(true,Heroi.moverDireita());
//		 assertEquals(true,Heroi.getDinheiro()>dinherio);
//	 }
//	 
//	 @Test
//	 public void testeMandarSeta(){
//		 
//		 /*
//		  * Inicializar Jogo
//		  */
//		 
//		 heroi.setPosicao(15,17);
//		 int num=Jogo.getProjecteis().size();
//		 jogo.addProjectil(20,25);
//		 assertEquals(true,jogo.getProjecteis().size()>num);
//		 assertEquals(true,jogo.getProjecteis()[jogo.getProjecteis().size()-1].getOriginalX=15);
//		 assertEquals(true,jogo.getProjecteis()[jogo.getProjecteis().size()-1].getOriginalY=17);
//		 assertEquals(true,jogo.getProjecteis()[jogo.getProjecteis().size()-1].getDestinoX=20);
//		 assertEquals(true,jogo.getProjecteis()[jogo.getProjecteis().size()-1].getDestinoY=25);
//	 }
//	 
}
