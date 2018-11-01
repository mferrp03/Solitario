
public class Pila {
	Carta [] pila;
	private int i = 0;
	Pila(){
		pila = new Carta[52];
	}
	boolean addCarta(Carta carta) {
		boolean add = false;
		if(pila[i].mismoNumero(carta) || pila[i].mismoPalo(carta)) {
			add = true;
			i++;
		}
		return add;
	}
	void eliminarCarta() {
		pila[i]=null;
		i--;
	}
	int numCartas() {
		return i;
	}
	Carta getCarta() {
		return pila[i];
	}
}
