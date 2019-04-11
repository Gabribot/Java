import java.util.Random;
import java.util.ArrayList;

public class Griglia {
		private int SIZE;
		private Cella[][] matriceDiGioco;				
		private ArrayList<Cella> vettoreCelleVuote;
		
		/*
		 * Costruttore standard per griglia di gioco 4x4.
		 */
		public Griglia() {
			SIZE = 4;
			
			matriceDiGioco = new Cella[SIZE][SIZE];
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					matriceDiGioco[i][j]=new Cella();
				}
			}
			
			vettoreCelleVuote=new ArrayList<Cella>();
			riempiCellaRandom();
		}

		/*
		 * Costruttore con parametro SIZE per impostare la modalità di gioco.
		 */
		public Griglia(int size) {
			SIZE = size;
			
			matriceDiGioco=new Cella[SIZE][SIZE];
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					matriceDiGioco[i][j]=new Cella();
				}
			}
			
			vettoreCelleVuote=new ArrayList<Cella>();
			riempiCellaRandom();
		}
		
		public Cella[][] getMatrice() {
			return matriceDiGioco;
		}
		
		public int getSIZE() {
			return SIZE;
		}
		
		/*
		 * Metodi che si occupano dell'inserimento delle celle random da generare ogni fine turno.
		 * La generazione avviene utlizzando l'arraylist contenente i riferimenti delle celle vuote, 
		 * effettuando il random su di esse.
		 */
		private void riempiCellaRandom() {
			riempiVettoreCelleVuote();
			Cella vuota=prendiCellaVuotaRandom();
			vuota.setNuovoValoreRandom();
			vettoreCelleVuote.clear();
		}
		
		/*
		 * Metodo che riempi il vettore contenente i riferimenti alle celle vuote della matrice.
		 */
		private void riempiVettoreCelleVuote(){
			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					if(matriceDiGioco[i][j].getValue()==0) {
						vettoreCelleVuote.add(matriceDiGioco[i][j]);
					}
				}
			}
		}
	
		/*
		 * Preleva una cella vuota tra le celle del vettore contenente i riferimenti delle celle vuote.
		 */
		public Cella prendiCellaVuotaRandom() {
			Random random=new Random();
			int scelta=random.nextInt(vettoreCelleVuote.size());
			return vettoreCelleVuote.get(scelta);
		}
		
		public boolean move(Direzione direzione) throws NoPossibleMoveException{
			boolean mosso=false;
			Cella[][] copia=copiaMatrice();		//ottengo una copia della matrice per controllare se ho effettuato o meno degli spostamenti.
			int i, j, scorri;
			
	        for (i = 0; i < SIZE; i++){

	            ArrayList<Cella> tileSet = new ArrayList<Cella>();

	            for (j = 0; j < SIZE; j++){
	            	//Aggiugno al tileSet tutte le celle con valore non nullo in funzione della direzione.
	                switch(direzione){
	                
	                case DESTRA: 
	                	if(matriceDiGioco[i][SIZE-j-1].getValue()!=0) {
	                		tileSet.add(matriceDiGioco[i][SIZE-j-1]);
	                	}
	                	break;
	                case SINISTRA: 
	                		if(matriceDiGioco[i][j].getValue()!=0) {
	                			tileSet.add(matriceDiGioco[i][j]);
	                		}
	                		break;
	                case SU:
	                		if(matriceDiGioco[j][i].getValue()!=0) {
	                			tileSet.add(matriceDiGioco[j][i]);
	                		}
	                		break;
	                case GIU:
	                		if(matriceDiGioco[SIZE - j - 1][i].getValue()!=0) {
	                			tileSet.add(matriceDiGioco[SIZE - j - 1][i]);
	                		}
	                		break;
	                default:
	                	System.out.println("Errore.");
	                	break;
	                }
	            }
	         
	            if(tileSet.size()>0) {
	            	ArrayList<Cella> nuova=shift(tileSet);
	            	
	            	//aggiungo celle vuote all'ArrayList per raggiungere la size.
	            	while(nuova.size()!=SIZE) {
	            		nuova.add(new Cella());
	            	}
	            	
	            	//Inserisco l'array shiftato nella matrice di gioco.
	            	switch(direzione) {
	            	case GIU:
	            		for(j=SIZE-1, scorri=0; j>=0; j--, scorri++) {
	 	  	        	   matriceDiGioco[j][i]=nuova.get(scorri);
	 	  	           	}
	 	  	           	break;
	 	  	           	
	            	case SU:
	            		for(j=0; j<SIZE; j++) {
	            			matriceDiGioco[j][i]=nuova.get(j);
	            		}
	            		break;
	            		
	            	case DESTRA:
	            		for(j=SIZE-1, scorri=0; j>=0; j--, scorri++) {
	            			matriceDiGioco[i][j]=nuova.get(scorri);
	            		}
	            		break;
	            		
	            	case SINISTRA:
	            		for(j=0; j<SIZE; j++) {
	            			matriceDiGioco[i][j]=nuova.get(j);
	            		}
	            		break;
	            		
					default:
						break;
	            	}
	            }
	        }
	        
	        //Controllo se ho effettuato degli spostamenti.
	        if(!hasMoved(copia)) {
	        	throw new NoPossibleMoveException();
	        }
	        else {
	        	mosso=true;
	        }
	        
	        if(mosso) {
	        	riempiCellaRandom();
	        	
	        }
	        
	        return mosso;
	    }
		
		/*
		 * Metodo che se presenti celle con egual valore le somma e le sposta.
		 */
		private ArrayList<Cella> shift(ArrayList<Cella> lista) {			
			boolean effettuato=false;
			while(effettuato==false && ugualiCelleAdiacenti(lista)) {
				for(int i=0; i<lista.size()-1; i++) {
					if(lista.get(i).getValue()==lista.get(i+1).getValue()) {
						lista.get(i).merge(lista.get(i+1));
						lista.remove(i+1);
					}
				}
				effettuato=true;
			}
			return lista;
		}
		
		private boolean hasMoved(Cella[][] prima) {
			boolean ritorno=false;
			for(int i=0; i<SIZE; i++) {
	        	for(int j=0; j<SIZE; j++) {
	        		if(!prima[i][j].equals(matriceDiGioco[i][j])) {
	        			ritorno=true;
	        			j=SIZE;
	        			i=SIZE;
	        		}
	        	}
	        }
			return ritorno;
		}
		
		/*
		 * Metodo che controlla se ci sono uguali celle adiacenti nell'array-list passato come parametro.
		 */
		private boolean ugualiCelleAdiacenti(ArrayList<Cella> lista) {
			boolean ritorno=false;
			for(int i=0; i<lista.size()-1; i++) {
				if(lista.get(i).getValue()==lista.get(i+1).getValue()) {
					ritorno=true;
					i=lista.size();
				}
			}
			return ritorno;
		}
		
		/*
		 * Metodo che controlla se nella matrice di gioco sono possibili delle mosse, ovvero se
		 * ci sono celle adicenti sommabili.
		 */
		public boolean hasPossibleMoves() {
	        for (int i = 0; i < SIZE; i++){
	            for (int j = 0; j < SIZE; j++){
	                //controlla la cella a destra della cella selezionata ignorando l'ultima colonna.
	                if (j < SIZE - 1){
	                    if (matriceDiGioco[i][j].equals(matriceDiGioco[i][j + 1])){
	                        return true;
	                    }
	                }
	                //controlla la cella sotto alla cella selezionata ignorando l'ultima riga.
	                if (i < SIZE - 1){
	                    if (matriceDiGioco[i][j].equals(matriceDiGioco[i + 1][j])){
	                        return true;
	                    }
	                }
	            }
	        }
	        return false;
	    }
		
		/*
		 * Metodo che effettua un deep-copy della matrice di gioco e ne restituisce la copia.
		 */
		private Cella[][] copiaMatrice(){
			Cella[][] copia=new Cella[SIZE][SIZE];

			for(int i=0; i<SIZE; i++) {
				for(int j=0; j<SIZE; j++) {
					copia[i][j]=new Cella(matriceDiGioco[i][j].getValue());
				}
			}
			
			return copia;
		}

		public void stampaGriglia() {
			//Stampo prima riga di trattini..
			for(int j=0; j<SIZE; j++) {
				System.out.printf("------");
			}
			System.out.println();
			//Stampo separatori di celle e valori (se diversi da 0).
			for(int i=0; i<SIZE; i++) {
				System.out.print("|");
				for(int j=0; j<SIZE; j++) {
					if(matriceDiGioco[i][j].getValue()!=0) {
						System.out.printf("%4s |", matriceDiGioco[i][j].toString());
					}
					else {
						System.out.printf("     |");
					}
				}
				System.out.println();
				//Stampo trattini inferiori..
				for(int k=0; k<SIZE; k++) {
					System.out.printf("------");
				}
				System.out.println();
			}
		}
}
