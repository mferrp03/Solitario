
public class Baraja {
	Carta [] baraja;
	Baraja() {
		baraja = new Carta[52];
	}
	/*
	 * Este metodo recibe una carta y el indice de la baraja en el que introducirla,
	 * comprueba si es una carta que exista (numero y palo validos), comprueba que el indice
	 * sea valido y si la carta ya esta en la baraja, si todo va bien se añade la carta a la 
	 * baraja y el metodo devuelve true, si no devuelve un false
	 */
	boolean addCarta(int i, Carta carta) {
		boolean add = false;
		if (i==0 && carta.existeCarta()) {
			baraja[i]=carta;
			add = true;
		}
		if (carta.existeCarta() && i>=1 && i<52 && !cartaRepetida(carta)) {
			baraja[i]=carta;
			add = true;
		}
		return add;
	}
	Carta getCarta(int i) {
		return baraja[i];
	}
	/*
	 * Este metodo recibe una carta y comprueba si esta es identica a alguna que ya se encuentre
	 * dentro de esta baraja comparando su numero y palo con los de todas las cartas que hay
	 * actualmente en la baraja, si esta repetida devuelve un true y si no devuelve un false
	 */
	boolean cartaRepetida(Carta carta){
		boolean repetida = false;
		int i = 0;
		do {
			if (carta.getNum() == getCarta(i).getNum()){
				if(carta.getPalo() == getCarta(i).getPalo()) {
					repetida = true;
					
				}
			}
			i++;
		}while(!repetida && getCarta(i)!=null && i<52);
		return repetida;
	}
	
	
}

