
public class Carta {
	char[] carta;
	Carta(char numero, char palo){
		carta = new char[2];
		carta[0] = numero;
		carta[1] = palo;
	}
	char getPalo(){
		char palo;
		palo = carta[1];
		return palo;
	}
	char getNum() {
		char num;
		num = carta[0];
		return num;
	}
	boolean existeCarta() {
		boolean existe = false;
		if ((this.getPalo()=='C'||this.getPalo()=='H'||this.getPalo()=='D'||this.getPalo()=='S') && (this.getNum()=='A'||this.getNum()=='2'||this.getNum()=='3'||this.getNum()=='4'||this.getNum()=='5'||this.getNum()=='6'||this.getNum()=='7'||this.getNum()=='8'||this.getNum()=='9'||this.getNum()=='T'||this.getNum()=='J'||this.getNum()=='Q'||this.getNum()=='K')) {
			existe = true;
		}
		return existe;
	}
	boolean mismoPalo(Carta carta) {
		boolean palo = false;
		if(this.getPalo()==carta.getPalo()) {
			palo = true;
		}
		return palo;
	}
	boolean mismoNumero(Carta carta) {
		boolean num = false;
		if(this.getNum()==carta.getNum()) {
			num = true;
		}
		return num;
	}

}
