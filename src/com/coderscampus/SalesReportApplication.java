package com.coderscampus;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public class SalesReportApplication {

	public static void main(String[] args) throws IOException  {   
				
		teslaReport("Model 3");
		teslaReport("Model S");
		teslaReport("Model X");
				 
	}      
		public static void teslaReport (String model) throws IOException {
			
			List<TeslaInfo> model3 = FileService.readTeslasFromFile("model3.csv");
			List<TeslaInfo> modelS = FileService.readTeslasFromFile("modelS.csv");
			List<TeslaInfo> modelX = FileService.readTeslasFromFile("modelX.csv");
		
		TeslaDataAnalysis analysedData = new TeslaDataAnalysis();
		Set<Entry<String, List<TeslaInfo>>> entrySetModel3 = analysedData.getEntrySetFromModelsGroupedByYear(model3);
		Set<Entry<String, List<TeslaInfo>>> entrySetModelS = analysedData.getEntrySetFromModelsGroupedByYear(modelS);
		Set<Entry<String, List<TeslaInfo>>> entrySetModelX = analysedData.getEntrySetFromModelsGroupedByYear(modelX);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		Comparator<TeslaInfo> comparator = Comparator.comparing(t -> t.getSales());
		
		
		System.out.println(model + " Yearly Sales Report");
		System.out.println("---------------------------");
		
       
		if (model.equals("Model 3")) {
		entrySetModel3.stream()
       .forEach(entry -> System.out.println(
    		    entry.getKey() + " -> " + entry.getValue().stream().mapToInt(tesla -> tesla.getSales().intValue()).sum()));

		TeslaInfo max = model3.stream()
	              .max(comparator)
	              .orElseThrow(NoSuchElementException::new);
		TeslaInfo min = model3.stream()
	              .min(comparator)
	              .orElseThrow(NoSuchElementException::new);
		System.out.println(" ");
		System.out.println("The best month for " + model + " was: " + max.getDate().format(formatter));
		System.out.println("The worst month for " + model + " was: " + min.getDate().format(formatter));
		System.out.println(" ");
		
		} else if (model.equals("Model S")) {
		entrySetModelS.stream()
	       .forEach(entry -> System.out.println(
	    		    entry.getKey() + " -> " + entry.getValue().stream().mapToInt(tesla -> tesla.getSales()).sum()));
		TeslaInfo max = modelS.stream()
	              .max(comparator)
	              .orElseThrow(NoSuchElementException::new);
		TeslaInfo min = modelS.stream()
	              .min(comparator)
	              .orElseThrow(NoSuchElementException::new);
		System.out.println(" ");
		System.out.println("The best month for " + model + " was: " + max.getDate().format(formatter));
		System.out.println("The worst month for " + model + " was: " + min.getDate().format(formatter));
		System.out.println(" ");
		} else {
		entrySetModelX.stream()
	       .forEach(entry -> System.out.println(
	    		    entry.getKey() + " -> " + entry.getValue().stream().mapToInt(tesla -> tesla.getSales()).sum()));
		TeslaInfo max = modelX.stream()
	              .max(comparator)
	              .orElseThrow(NoSuchElementException::new);
		TeslaInfo min = modelX.stream()
	              .min(comparator)
	              .orElseThrow(NoSuchElementException::new);
		System.out.println(" ");
		System.out.println("The best month for " + model + " was: " + max.getDate().format(formatter));
		System.out.println("The worst month for " + model + " was: " + min.getDate().format(formatter));
		System.out.println(" ");
							 
		}				
	}
}