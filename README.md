# PixelRealm


##Jogo

O programa desenvolvido foi uma aplicação para Android de um jogo baseado no “Realm of the Mad God”.

O utilizador do jogo controla uma personagem principal (Herói), cujo 
o bjectivo é matar os vários inimigos com que se depara, de forma a poder progredir no
jogo.
<p align="center">
  <img src="http://i.imgur.com/H5sHvdX.png" width="500">
</p>

Video: https://www.youtube.com/watch?v=ttyefxrLIoA

##Interface
O Herói toma uma posição central no ecrã, e para o mover pelo mapa, o utilizador pode recorrer a um
joystick localizado no canto inferior esquerdo do ecrã.

O Herói pode tirar vida aos seus inimigos de duas formas: a primeira é num
“confronto cara-a-cara”, em que o Herói vai ao encontro de um inimigo, e enquanto
estiverem em contacto, vão ambos perdendo vida de acordo com o poder de ataque
do outro; e a segunda é lançando projécteis (na interface do jogo representados como
setas) na direcção do inimigo (bastando para isto tocar na zona do ecrã para onde se
pretende lançar a seta).

<p align="center">
  <img src="http://i.imgur.com/2u0sP1B.png" width="500">
</p>



###Gems
Quando um inimigo morre, pode, ou não, ser gerado um objeto que pode ser
apanhado pelo Herói. Este objeto pode ser de três tipos:

1. Vida – ao ser apanhado, a vida do Herói é incrementada.

2. Ataque – ao ser apanhado, o “poder de ataque” do Herói aumenta,
isto é, cada vez que atacar um inimigo, este perderá mais vida.

3. “Moedas” – ao serem apanhadas moedas, é incrementada uma barra
no topo do ecrã. Quando o utilizador consegue preencher toda a
barra, abre um “portal” que permite o acesso ao nível seguinte.

<p align="center">
  <img src="http://i.imgur.com/ItAoh0O.png" width="500">
</p>

###Objectivo e Niveis
Como referido, um “portal” dá acesso aos níveis seguintes. Um nível superior
caracteriza-se por ter inimigos mais fortes (retiram mais vida ao Herói sempre que o
atacam).

##Design Patterns

Neste projeto foram utilizados diferentes “design patterns”:

Singleton: este padrão foi utilizado na implementação da “GameActivity”. É um
padrão que é utilizado para que não exista mais do que uma instância de um
objeto. A “GameActivity” é um desses objetos. Também na classe “Imagens” se
utilizaram unicamente objetos estáticos, eliminando a necessidade de
repetidamente se instanciarem imagens, o que ocuparia uma quantidade
vastamente superior de memória.

“Template pattern”: a utilização de classes abstratas, e subclasses que
implementam os seus métodos, conforme o comportamento de cada uma,
foram uma das principais técnicas utilizadas no projeto, e características deste
padrão de desenho de software.

##Desenvolvimento

###Desenvolvimento do Joystick

O jogo desenvolvido baseia-se numa jogabilidade
com recorrência ao Joystick, pelo que tivemos de investigar e recolher
informações de várias fontes para percebermos qual seria a maneira mais fácil
e correcta de implementar esse joystick. 

Foi sempre prioridade tornar a  interação do jogador com o herói bastante intuitiva, rápida, "responsive" e
encapsulada, para que com apenas a modificação de algumas variáveis fosse
possível alterar a sua posição, tamanho e impulso/movimento que da ao herói.

###Implementação de multitouch

Para além do joystick, o jogador pode também
clicar em várias partes do ecrã para disparar “projéteis”. Para tornar mais
intuitiva a interação do utilizador com o jogo, decidimos implementar um
handler de Multitouch, desenvolvido por nós, que conseguiria registar dois ou
mais toques ao mesmo tempo, de modo a que fosse possível movimentar o
“herói” e disparar ao mesmo tempo.

###Alocações de memória para Imagens

Na aplicação de gráficos ao jogo, para
ultrapassar o problema de memória com a instanciação de um Bitmap para
cada objeto, decidimos criar uma classe com todas as imagens, inicializada no
início da aplicação. Deste modo, cada Entidade não teria uma Bitmap, mas sim
uma referência para um determinado Bitmap instanciado na classe Imagens.


##Gameplay

###Centered Player Camera

Um dos grandes problemas que nos propusemos a
resolver com este jogo foi o do “Centered Camera Player”, isto é, o jogador
encontra-se sempre no centro do ecrã e tudo o resto é que se movimenta. 

Para tal, decidimos abordar os movimentos do player como um incremento ou
decremento de duas variáveis: deslocamento na horizontal (dx) e
deslocamento na vertical (dy). 

Deste modo, não tivemos de modificar todas as
posições, de todos os objetos no tabuleiro a cada movimento do jogador, mas
sim trabalhar sempre com a posição das Entidades e os deslocamentos no jogo.


###Resizable Gameplay

Sendo que decidimos desenvolver um jogo para Android,
seria mais do que natural começar desde inicio a pensar como iria ser feito o
“scale” para telemóveis de maiores e menores dimensões, bem como tablets.

Para tal, todo o desenho do jogo no ecrã é calculado recorrendo à largura e
comprimento do telemóvel. O algoritmo implementado permite-se adaptar a
telemóveis mais largos ou mais estreitos, em “landscape e portrait” e até em
tablets, nunca distorcendo as imagens.

Foram ainda aplicados vários layouts, dependendo da resolução, e orientação
do ecrã, para tirar mais partido do “screen estate”.
