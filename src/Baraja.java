
public class Baraja {
	Carta [] baraja;
	Baraja() {
		baraja = new Carta[52];
	}
	boolean addCarta(int i, Carta carta) {
		boolean add = false;
		if (carta.existeCarta() && i>=0 && i<52 && !cartaRepetida(carta)) {
			baraja[i]=carta;
			add = true;
		}
		return add;
	}
	Carta getCarta(int i) {
		return baraja[i];
	}
	boolean cartaRepetida(Carta carta){
		boolean repetida = false;
		int i = 0;
		do {
			if (carta == getCarta(i)){
				repetida = true;
			}
			i++;
		}while(!repetida && getCarta(i)!=null && i<52);
		return repetida;
	}
	
	
}
