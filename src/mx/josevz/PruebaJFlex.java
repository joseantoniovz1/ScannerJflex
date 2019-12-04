package mx.josevz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class PruebaJFlex {

	public static void main(String[] args) {

		try {

			String archivo = "prueba.txt";
			PruebaJFlex pFlex = new PruebaJFlex();
			File file = pFlex.getFileFromResources(archivo);

			if (null != file) {
				try (FileReader reader = new FileReader(file); BufferedReader buffer = new BufferedReader(reader)) {
					AnalizadorLexico analizadorJFlex = new AnalizadorLexico(buffer);

					while (true) {
						
						TokenPersonalizado token = analizadorJFlex.yylex();

						if (!analizadorJFlex.existenTokens())
							break;

						System.out.println(token.toString());
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}


	private File getFileFromResources(String fileName) {

		ClassLoader classLoader = getClass().getClassLoader();

		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			return new File(resource.getFile());
		}

	}

}
