import java.util.Scanner;

public class Main_2048 {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int numero=0;
		boolean flag = false;
		
		//Benvenuto
		System.out.println("BENVENUTO NEL GIOCO 2048.");
		System.out.println("L'OBIETTIVO DEL GIOCO È OTTENERE IL NUMERO 2048 SOMMANDO LE CELLE CONTENENTI LO STESSO VALORE.");
		System.out.println("PER MUOVERSI È POSSIBILE UTILIZZARE I TASTI W(alto), A(sinistra), S(sotto), D(destra).\n");
		
		//Gestisco inserimento dimensione matrice di gioco
		System.out.println("Inserisci la dimensione della matrice di gioco quadrata (dimensione massima --> 10)");
		do {
			try{
				System.out.print("Inserisci valore: ");
				numero=Integer.parseInt(in.nextLine());
				if(numero>2 && numero<11) {
					flag=true;
				}
				else {
					System.out.println("INSERISCI UN VALORE COMPRESO TRA 3 E 10!\n");
				}
			}
			catch(NumberFormatException e) {
				System.out.println("INSERISCI UN VALORE VALIDO!\n");
				flag=false;
			}
		}while(!flag);
		
		Gioca g=new Gioca(numero);
		if(g.partita()) {
			System.out.println("COMPLIMENTI HAI VINTO!	TURNI UTILIZZATI: "+g.getTurno());
		}
		else{
			System.out.println("HAI PERSO!");
		}
	}	
}