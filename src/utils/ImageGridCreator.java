package utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.CeldaGrilla;

public class ImageGridCreator {
	private int tamañoCeldapx;
	private int cantCeldasX;
	private int cantCeldasY;
	private CeldaGrilla[][] celdaGrilla;
	private double maxRend;
	private boolean formatoBinario;

	public ImageGridCreator(int tamañoCeldapx, int cantCeldasX, int cantCeldasY, CeldaGrilla[][] celdaGrilla,
			double maxRend, boolean binario) {
		super();
		this.tamañoCeldapx = tamañoCeldapx;
		this.cantCeldasX = cantCeldasX;
		this.cantCeldasY = cantCeldasY;
		this.celdaGrilla = celdaGrilla;
		this.maxRend = maxRend;
		this.formatoBinario = binario;
	}

	public void saveImageGrilla(String pathSave, String nameSave) {
		System.out.println("Comenzando proceso y guardado de imagen...");
		// 1 es totalmente opaco. 0 es es totalmente transparente
		float saturacion = 0;

		// Constructs a BufferedImage of one of the predefined image types.
		BufferedImage bufferedImage = new BufferedImage(cantCeldasX * tamañoCeldapx, cantCeldasY * tamañoCeldapx,
				BufferedImage.TYPE_INT_RGB);

		// Create a graphics which can be used to draw into the buffered image
		Graphics2D g2 = bufferedImage.createGraphics();
		Color c;
		for (int i = 0, ipx = 0; i < cantCeldasX; i++, ipx += tamañoCeldapx) {
			for (int j = 0, jpx = 0; j < cantCeldasY; j++, jpx += tamañoCeldapx) {
				
				CeldaGrilla celdaActual = celdaGrilla[i][j];

				// Si es formato binario, noi importa el valor del rinde, sólo importa si hay datos o no en la celda
				if (celdaActual != null && formatoBinario) {
					saturacion = 1f;
				} else {
					saturacion = celdaActual == null ? 0f : (float) celdaActual.getRend();
					saturacion = (float) ((saturacion) / maxRend);
				}

				// TODO: NO DEBERÍA PASAR NUNCA JAMAS (salvo que este devolviendo mal el maxrend...)
				if (saturacion > 1f) {
					System.err.println("<" + i + "," + j + "> con REND: " + saturacion);
					saturacion = 1f;
				}

				if (celdaActual == null) {
					// Por defecto fondo verde si no hay datos!
					g2.setColor(Color.GREEN);
				} else {
					// Por defecto fondo blanco si no hay datos!
					g2.setColor(Color.WHITE);
				}
				g2.fillRect(ipx, jpx, tamañoCeldapx, tamañoCeldapx);

				// El tono del rinde: mientras más oscuro, mayor rinde
//				if (celdaActual != null && celdaActual.getRend() == 0f && !formatoBinario) {
//					// Color rojo si el rinde es cero en la celda
//					c = new Color(1f, 0.0f, 0.0f, 1f);
//					System.out.println("cerooooo");
//				} else {
					c = new Color(0.0f, 0.0f, 0.0f, saturacion);
//				}

				g2.setColor(c);
				g2.fillRect(ipx, jpx, tamañoCeldapx, tamañoCeldapx);
			}
		}

		// Save as PNG
		String path = pathSave != null && !pathSave.isEmpty() ? pathSave + nameSave : nameSave;
		File file = new File(path);
		try {
			ImageIO.write(bufferedImage, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
