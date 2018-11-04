import java.util.Scanner;
import java.util.ArrayList;
public class Main {
	private int numBarajas = 0;
	public Main(int n) {
		this.numBarajas = n;
	}
	public static void addCarta(Baraja baraja,char numero, char palo, int i) {
		Carta carta = new Carta(numero, palo);
		baraja.addCarta(i,carta);
	}
	public static void main(String[]args){
		int lineas = 0;
		int numBaraja = 0;
		int numCarta = 0;
		ArrayList<Baraja> barajas = new ArrayList<Baraja>();
		Scanner sc = new Scanner(System.in);
		Baraja baraja = null;
		//try {	
			do {
				int contador = 0;
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
				System.out.println("Entrada incorrecta.1");
			}
			else {
				for(int i=0; numCarta<26+26*lineas;i+=3) {
						addCarta(baraja,linea.charAt(i),linea.charAt(i+1),numCarta);
						numCarta++;					
				}
				lineas++;
			}
			if (lineas == 2) {
				barajas.add(baraja);
				numBaraja++;
				lineas = 0;
				numCarta = 0;
			}
			
			}while(!sc.hasNext("#"));
	//	}catch(Exception e) {
		//	System.out.println("Entrada incorrecta.2");
		//}
		sc.close();
		if (lineas != 0)
			System.out.println("Entrada incorrecta.3");
		
		Main main = new Main(numBaraja);
		for (int i=0;i<barajas.size();i++) {
			Baraja aux =barajas.get(i);
			main.solucionar(aux);
		}
	}
	boolean moverCartas(int k, int distancia,ArrayList<Pila> pilas) {
		boolean mover = false;
		if (pilas.get(k-distancia).getCarta().mismoNumero(pilas.get(k).getCarta()) || pilas.get(k-distancia).getCarta().mismoPalo(pilas.get(k).getCarta())){

			pilas.get(k-distancia).addCarta(pilas.get(k).getCarta());
			pilas.get(k).eliminarCarta();
			if (pilas.get(k).numCartas()==0) {
				pilas.remove(k);
			}
			mover = true;
		}
		return mover;
	}
	
	void solucionar(Baraja baraja) {
		ArrayList<Pila> pilas = new ArrayList<Pila>();
		int k = 0;
			int c = 0;
			
			do {
				Pila aux = new Pila();
				aux.addCarta(baraja.getCarta(c));
				pilas.add(aux);
				c++;
			}while(c<52);
			do {
				if (k > 0) {
					if(k > 2) {
						if (moverCartas(k,3,pilas)){
							k=0;	
						}
				
						else if(moverCartas(k,1,pilas)) {
							k=0;	
						}
						else
							k++;
						
					}
					else if	(moverCartas(k,1,pilas)){
							k=0;
						}
					else
						k++;
				}
				else
					k++;
			}while(k<pilas.size());
			if(pilas.get(0).numCartas() == 52)
				System.out.println("Ha quedado 1 pila: 52");
			else {		
				String salida = new String();
				salida = "Han quedado "+ (pilas.size()) + " pilas:";
				for(int j = 0; j<pilas.size(); j++) {
					salida += " "+ (pilas.get(j).numCartas());
				}
				System.out.println(salida);
			}
			
			
	}
}


