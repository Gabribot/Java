import java.util.Scanner;

public class Gioca {
	private Griglia g;
	private int turno;
	private boolean vittoria;
	private static final int obiettivo=2048;
	
	/*
	 * Costruttore standard che inizializza la griglia di gioco di
	 * dimensione 4.
	 */
	public Gioca() {
		g=new Griglia();
		turno=0;
		vittoria=false;
	}
	
	/*
	 * Costruttore che inizializza la griglia di gioco in funzione del
	 * parametro SIZE passato.
	 */
	public Gioca(int size) {
		g=new Griglia(size);
		turno=0;
		vittoria=false;
	}
	
	public int getTurno() {
		return turno;
	}
	
	/*
	 * Metodo che gestisce la partita, l'interazione con l'utente e la gestione dei turni
	 */
	public boolean partita(){
		g.stampaGriglia();
		System.out.println();
		Scanner in=new Scanner(System.in);
		int stato=0;
		String scelta="";
		Direzione d;
		
		while(stato==0){
			turno++;
			boolean continua=true;
			while(continua) {
				System.out.println("Inserisci scelta: ");
				scelta=in.next();
				try {
					switch(scelta) {
					case "d":
						d=Direzione.DESTRA;
						if(g.move(d)) {
							continua=false;
						}
						break;
					case "a":
						d=Direzione.SINISTRA;
						if(g.move(d)) {
							continua=false;
						}
						break;
					case "w":
						d=Direzione.SU;
						if(g.move(d)) {
							continua=false;
						}
						break;
					case "s":
						d=Direzione.GIU;
						if(g.move(d)) {
							continua=false;
						}
						break;
					default:
						System.out.println("Inserisci un valore valido!");
					}
				}
				catch(NoPossibleMoveException e) {
					System.out.println(e.getMessage());
				}
			}
			
			g.stampaGriglia();
			System.out.println();
			stato=controllaStato();
			if(stato==2) {
				if(g.hasPossibleMoves()) {
					stato=0;
				}
			}
		}
		
		if(stato==1) {
			vittoria=true;
		}
		else if(stato==2) {
			vittoria=false;
		}
		return vittoria;
	}

	/*
	 * Metodo utilizzato per controllare la vittoria, la sconfitta o continuare a giocare.
	 */
	public int controllaStato() {
	int ritorno=2, i=0, j;	//di default ha perso
	while(ritorno!=1 && i<g.getSIZE()) {
		for(j=0; j<g.getSIZE(); j++) {
			if(g.getMatrice()[i][j].getValue()==0) {
				ritorno=0;	//deve continuare a giocare, ma devo continuare che non abbia vinto
			}
			if(g.getMatrice()[i][j].getValue()==obiettivo) {
				ritorno=1;	//ha vinto
				j=g.getSIZE();
			}
		}
		i++;		
	}
	return ritorno;
	}
}
