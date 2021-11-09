package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileService {
	static String line;

	public static List<TeslaInfo> readTeslasFromFile(String filename) throws IOException {
		List<TeslaInfo> teslas = new ArrayList<>();
		BufferedReader fileReader = null;

		try {
			fileReader = new BufferedReader(new FileReader(filename));
			String line = fileReader.readLine();
			while ((line = fileReader.readLine()) != null) {
				String[] info = line.split(",");
				TeslaInfo teslaInfo = createTesla(info);
				teslas.add(teslaInfo);
			}
		} finally {
			if (fileReader != null)
				fileReader.close();
		}
		return teslas;
	}

	private static TeslaInfo createTesla(String[] attributes) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
		YearMonth date = YearMonth.parse(attributes[0], formatter);
		Integer sales = Integer.parseInt(attributes[1]);
		return new TeslaInfo(date, sales);
	}
}
