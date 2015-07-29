package main;

import java.io.IOException;
import java.util.Date;

import utils.ImageGridCreator;
import utils.OutputGridProcessor;
import entities.CeldaGrilla;

public class Main {

	public static void main(String[] args) throws IOException {
		long init = new Date().getTime();
		System.out.println("Comenzando proceso...");
		boolean formatoBinario = false;
		final double tamañoCelda = 0.0001;
		int tamañoCeldaPx = 4;

		// PRUEBA
		// String pathInput = "/home/pruebahadoop/Documentos/DataSets/monitores_2/1-output/part-r-00000";
		String pathInput ="/media/pruebahadoop/a0eb64d8-ab5e-41a9-8f15-1a9079769f72/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/outputs/SmoothingGaussian/prueba.txt";

		// Path input con UN monitor (outliers,  con poda)
		// String pathInput = "/home/pruebahadoop/soja/2012-2013/1-grilla/part-r-00000";
		//String pathInput ="/media/pruebahadoop/a0eb64d8-ab5e-41a9-8f15-1a9079769f72/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/outputs/podaPorRindeCelda/soja/2012-2013/2-sort&Poda/part-r-00000";

		// Path input con UN monitor (Smoothing)
		// String pathInput = "/home/pruebahadoop/Documentos/DataSets/monitores_2/3-SmoothingGaussian/part-r-00000";

		// Path input con 20 monitores
		// String pathInput =
		// "/home/pruebahadoop/Documentos/DataSets/monitores/output (copia) RendMedio ancho00001 OK!/part-r-00000";

		OutputGridProcessor outputProcessor = new OutputGridProcessor(pathInput, tamañoCelda, formatoBinario);
		CeldaGrilla[][] grilla = outputProcessor.getCeldaGrilla();

		int cantCeldasX = outputProcessor.getCantCeldasX();
		int cantCeldasY = outputProcessor.getCantCeldasY();

		double maxRend = outputProcessor.getRendMax();

		ImageGridCreator imageCreator = new ImageGridCreator(tamañoCeldaPx, cantCeldasX, cantCeldasY, grilla, maxRend,
				formatoBinario);

		// TODO!
		String pathSave = "";
		String nameSave = "Grilla.png";

		imageCreator.saveImageGrilla(pathSave, nameSave);

		long fin = new Date().getTime();
		System.out.println("Tardó en procesar: " + (fin - init) / 1000 + "segs");

	}

}
