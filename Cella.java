import java.util.Random;

public class Cella {
	private int valore;
	
	public Cella() {
		valore=0;
	}
	
	public Cella(int valore) {
		this.valore=valore;
	}

	public int getValue() {
		return valore;
	}

	public void setValue(int valore) {
		this.valore = valore;
	}
	
	public boolean equals(Cella cella) {
		return this.valore==cella.getValue();
	}
	
	/*
	 * Metodo utilizzato per sommare le celle in funzione delle
	 * direzione scelta dall'utente.
	 */
	public void merge(Cella cella) {
		valore += cella.getValue();
	}
	
	 public void clear(){
		this.setValue(0);
	 }
	 
	 /*
	 * Metodo utilizzato per generare il valore random delle nuove
	 * celle, che può essere 2 o 4.
	 */
	 public int getNuovoValoreRandom(){
		Random random = new Random();
		int valore=random.nextInt(2)+1;	//genera un valore tra 1 e 2.
		return (valore*2);				//ritorna 2 o 4.
	 }
	 
	 /*
	  * Metodo utilizzato per settare un valore random alla cella che lo richiama.
	  */
	 public void setNuovoValoreRandom() {
		setValue(getNuovoValoreRandom());
	 }
	 
	 /*
	  * Metodo utilizzato per stampare il valore della cella.
	  */
	 public String toString(){
	    return (Integer.toString(this.getValue()));
	 }
}