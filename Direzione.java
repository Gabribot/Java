/*
 * Classe enumerativa per gestire la direzione di gioco.
 */
public enum Direzione {
	SU, GIU, DESTRA, SINISTRA;
	
	public int toInteger() {
		switch(this) {
		case SU:
			return 0;
		case GIU:
			return 1;
		case DESTRA:
			return 2;
			
		case SINISTRA:
			return 3;
		default:
			return -1;
		}
	}
	
	public String toString() {
		switch(this) {
		case SU:
			return "su";
		case GIU:
			return "giù";
		case DESTRA:
			return "destra";
		case SINISTRA:
			return "sinistra";
		default:
			return "error";
		}
	}
}
