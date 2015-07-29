package entities;

/**
 * Representa una única celda de una grilla
 *
 * @author pruebahadoop
 *
 */
public class CeldaGrilla {

	private double valorRend;

	public CeldaGrilla(double valorRend) {
		this.valorRend = valorRend;
	}

	public void setRend(double valorRend) {
		this.valorRend = valorRend;
	}

	public float getRend() {
		return (float) this.valorRend;
	}

}
