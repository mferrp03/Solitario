import java.util.Scanner;
import java.util.ArrayList;
public class Main {
	private int numBarajas = 0;
	public Main(int n) {
		this.numBarajas = n;
	}
	/*
	 * Este metodo recibe una baraja, una carta con numero y palo y la posicion de esa 
	 * carta en la baraja, crea una nueva carta a partir del numero y palo recibidos
	 * y llama al metodo addCarta de la clase baraja, devuelve un true si se añade correctamente
	 * y un false si no se ha podido añadir
	 */
	public static boolean addCarta(Baraja baraja,char numero, char palo, int i) {
		boolean add = false;
		Carta carta = new Carta(numero, palo);
		if(baraja.addCarta(i,carta))
			add = true;
		return add;
	}
	public static void main(String[]args){
		boolean correcto = true;
		int lineas = 0;
		int numBaraja = 0;
		int numCarta = 0;
		ArrayList<Baraja> barajas = new ArrayList<Baraja>();
		Scanner sc = new Scanner(System.in);
		Baraja baraja = null;
		try {	
			/*
			 * se inicializa el contador de caracteres, se lee la linea actual y se guarda en la 
			 * variable linea y si lineas (el numero de lineas pertenecientes a la baraja actual
			 * que se han leido con anterioridad) es igual a 0 eso significa que esta es la 
			 * primera linea de una nueva baraja, por lo que se crea
			 */
			do {
				int contador = 0;
				String linea = sc.nextLine();
				if(lineas == 0) {
					baraja = new Baraja();
				}
				/*se comprueba que la linea actual contiene los 52 caracteres necesarios
				para formar media baraja, se va comprobando caracter a caracter y se suma 1
				al contador de caracteres por cada uno distinto de un espacio en blanco */
				for(int i = 0; i<linea.length();i++) {
					if(linea.charAt(i)!=' ') {
						contador++;
				 }	
			}
				/*se comprueba el numero de caracteres que se han leido, si se ha leido un numero 
				 * de caracteres distinto a 52 la entrada es incorrecta, ya que esto significa que se
				 * ha introducido mas o menos caracteres de los que contiene media baraja
				 */
			if (contador!=52) {
				System.out.println("Entrada incorrecta.1");
			}
			else {
				/*
				 * se recorre la linea que se acaba de leer, el indice i se coloca sobre el numero de
				 * la carta, se añade a la baraja utilizando el metodo adCarta, al que se le pasa 
				 * la baraja que estamos rellenando,el caracter actual sobre el que esta i 
				 * (numero de la carta), el siguiente caracter(palo) y la posicion de la carta
				 * en la baraja (numCarta) que se aumenta en cada iteracion, si el metodo 
				 * addCarta devuelve un false el boolean correcto se pone a false, esto
				 * se usara luego para enviar un mensaje de error, al final de cada iteracion
				 * se le suma 3 a i (para colocarlo sobre el numero de la siguiente carta, 
				 * saltandonos el palo de la carta actual y el espacio entre cartas se le suma 1 
				 * a numCarta para que la siguiente carta se intente guardar en el siguiente espacio
				 * de la baraja (si se ha podido añadir la anterior carta), al acabar el bucle 
				 * se le suma 1 a lineas
				 */
				for(int i=0; numCarta<26+26*lineas;i+=3) {
						if(!addCarta(baraja,linea.charAt(i),linea.charAt(i+1),numCarta)) {
							correcto = false;
						}
						else
							numCarta++;					
				}
				lineas++;
			}
			/*
			 * si lineas es = a 2 esto significa que se ha leido una baraja completa, por lo que 
			 * se añade esa baraja a barajas, se le suma 1 al numero de barajas y las variables
			 * lineas y numcartas se ponen a 0
			 */
			if (lineas == 2) {
				barajas.add(baraja);
				numBaraja++;
				lineas = 0;
				numCarta = 0;
			}
			
			}while(!sc.hasNext("#"));
			//si no se ha podido incluir una carta pro cualquier motivo correcto estara a false
			//y se emitira un mensaje de error
			if (!correcto) {
				System.out.println("Entrada incorrecta.4");
			}
			// tambien se emitira un mensaje de error si se ha cerrado la entrada con una baraja
			//a medias
			else if (lineas != 0)
				System.out.println("Entrada incorrecta.3");
	}catch(Exception e) {
			System.out.println("Entrada incorrecta.2");
		}
		sc.close();
		
		/*
		 * se entra en un bucle en el que se resuelven todas las barajas una a una, guardandolas
		 * temporalmente en aux e invocando al metodo solucionar sobre ella
		 */
		Main main = new Main(numBaraja);
		for (int i=0;i<barajas.size();i++) {
			Baraja aux =barajas.get(i);
			main.solucionar(aux);
		}
	}
	/* 
	 * Este metodo es el encargado de mover las cartas de una pila a otra cuando un movimiento
	 * es factible, recibe la posicion de la carta a mover (k), la distancia del salto
	 * (1 o 3) y el arraylist de pilas
	 */
	boolean moverCartas(int k, int distancia,ArrayList<Pila> pilas) {
		boolean mover = false;
		/* 
		 * se comprueba que la carta de la posicion k y la de k-distancia tengan el mismo
		 * palo o el mismo numero, es decir, que un movimiento a esta posicion sea 
		 * factible, en el caso de que sea posible realizar el movimiento se añade la 
		 * carta de la pila k a la pila k-distancia y se elimina la carta de la pila anterior
		 * para evitar duplicar la carta, si la pila de la que se ha movido la carta
		 * queda vacia tras eliminar esta carta se elimina la pila vacia,
		 * este metodo devuelve un true si se ha podido mover la carta o un false si no
		 * ha sido posible
		 */
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
			/* 
			 * el primer paso para solucionar una baraja consiste en colocar cada carta en 
			 * una pila, por lo que se crea una pila nueva y se añade a esta la siguiente carta
			 * de la baraja mientras queden cartas en esta
			 */
			do {
				Pila aux = new Pila();
				aux.addCarta(baraja.getCarta(c));
				pilas.add(aux);
				c++;
			}while(c<52);
			do {
				/*
				 * k es la pila sobre la que nos encontramos, si k es mayor que 2 esto significa
				 * que existe una pila tres posiciones a la izquierda, ya que los movimientos
				 * a tres posiciones tienen prioridad sobre los de 1 se intenta mover la carta
				 * si no se puede se intenta mover a la posicion inmediatamente a su izquierda
				 * tras realizar un movimiento k se pone a 0 para volver a comprobar movimientos
				 * desde la primera pila, si no es posible realizar ningun movimiento se
				 * suma 1 a k y se pasa a comprobar movimientos para esa pila, el bucle se para
				 * cuando se llega a la ultima pila y no se realiza ningun movimiento en esta
				 */
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
				/*
				 * se imprime el numero de pilas que han quedad al acabar mirando el tamaño
				 * del arraylist de pilas y se imprime el numero de cartas de cada una
				 * utilizando el metodo numCartas
				 */
				String salida = new String();
				salida = "Han quedado "+ (pilas.size()) + " pilas:";
				for(int j = 0; j<pilas.size(); j++) {
					salida += " "+ (pilas.get(j).numCartas());
				}
				System.out.println(salida);
			}
			
			
	}
}


