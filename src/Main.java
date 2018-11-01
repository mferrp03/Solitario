import java.util.Scanner;
import java.util.ArrayList;
public class Main {
	private Pila[] pilas = new Pila[52];
	private int numBarajas = 0;
	public Main(int n) {
		this.numBarajas = n;
	}
	public static void addCarta(Baraja baraja,char numero, char palo, int i) {
		Carta carta = new Carta(numero, palo);
		baraja.addCarta(i,carta);
	}
	public static void main(String[]args){
		int contador = 0;
		int lineas = 0;
		int numBaraja = 0;
		ArrayList<Baraja> barajas = new ArrayList<Baraja>();
		Scanner sc = new Scanner(System.in);
		Baraja baraja = null;
		try {
			do {
			String linea = sc.nextLine();
			if(lineas == 0) {
				baraja = new Baraja();
			}
			for(int i = 0; i<linea.length();i++) {
				 if(linea.charAt(i)!=' ') {
					 contador++;
				 }	
			}
			if (contador!=52) {
				System.out.println("Entrada incorrecta.");
			}
			else {
				for(int i=0; i<linea.length();i++) {
					int numCarta = 0;
					if(linea.charAt(i)!=' ') {
						addCarta(baraja,linea.charAt(i),linea.charAt(i+1),numCarta);
						numCarta++;
					}
				}
				lineas++;
			}
			if (lineas == 2) {
				barajas.add(baraja);
				numBaraja++;
				lineas = 0;
			}
			
			}while(!sc.hasNext("#"));
		}catch(Exception e) {
			System.out.println("Entrada incorrecta.");
		}
		sc.close();
		if (lineas != 0)
			System.out.println("Entrada incorrecta.");
		
		Main main = new Main(numBaraja);
		for (int i=0;i<barajas.size();i++) {
			Baraja aux =barajas.get(i);
			main.solucionar(aux);
		}
	}
	boolean moverCartas(int k, int distancia) {
		boolean mover = false;
		if (pilas[k-distancia].getCarta().mismoNumero(pilas[k].getCarta()) || pilas[k-distancia].getCarta().mismoPalo(pilas[k].getCarta())){

			pilas[k-distancia].addCarta(pilas[k].getCarta());
			pilas[k].eliminarCarta();
			mover = true;
		}
		return mover;
	}
	void desplazarPilas(int k) {
		do {
			if(pilas[k+1]==null || k==51) {
				pilas[k]=null;
			}
			else {
				pilas[k]=pilas[k+1];
			
			}
			k++;
		}while(pilas[k+3]==null || k == 51);	
	}
	void solucionar(Baraja baraja) {
		int num = 0;
		int k = 0;
		int movimientos;
		do {
			pilas[num].addCarta(baraja.getCarta(num));	
		}while(num<52);
		do {
			movimientos = 0;
			if (pilas[k]==null) {
				desplazarPilas(k);
			}
			else if (k-1 >= 0) {
				if(k-3 >= 0) {
					if (moverCartas(k,3)){
						movimientos++;	
					}
				
					else if(moverCartas(k,1)) {
						movimientos++;	
					}
						
				}
				else if	(moverCartas(k,1)){
						movimientos++;
					}
			}
			if (movimientos == 0) {
				k++;
			}
			if (movimientos == 1) {
				k=0;
			}
		}while(movimientos !=0 && k==52);
	}
}