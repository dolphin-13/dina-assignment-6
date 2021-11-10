package com.coderscampus;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public class SalesReportApplication {

	public static void main(String[] args) throws IOException  { 		
				
		TeslaDataAnalysis analysedData = new TeslaDataAnalysis();
		
		List<TeslaInfo> model3 = FileService.readTeslasFromFile("model3.csv");
		List<TeslaInfo> modelS = FileService.readTeslasFromFile("modelS.csv");
		List<TeslaInfo> modelX = FileService.readTeslasFromFile("modelX.csv");	
	
	Set<Entry<String, List<TeslaInfo>>> entrySetModel3 = analysedData.getEntrySetFromModelsGroupedByYear(model3);
	Set<Entry<String, List<TeslaInfo>>> entrySetModelS = analysedData.getEntrySetFromModelsGroupedByYear(modelS);
	Set<Entry<String, List<TeslaInfo>>> entrySetModelX = analysedData.getEntrySetFromModelsGroupedByYear(modelX);
		
		teslaReport(entrySetModel3, model3, "Model 3");
		teslaReport(entrySetModelS, modelS, "Model S");
		teslaReport(entrySetModelX, modelX, "Model X");
				 
	}      

		public static void teslaReport(Set<Entry<String, List<TeslaInfo>>> entrySet, List<TeslaInfo> teslaModel, String modelName) {
			
			Comparator<TeslaInfo> comparator = Comparator.comparing(tesla -> tesla.getSales());
						
			System.out.println(modelName + " Yearly Sales Report");
			System.out.println("---------------------------");
			
			entrySet.stream()
		       .forEach(entry -> System.out.println(entry
		                                            .getKey() + " -> " + entry
		                                                                  .getValue()
		                                                                  .stream()
		                                                                  .mapToInt(tesla -> tesla
		    		                                                                          .getSales()
		    		                                                                          .intValue())
		                                                                                      .sum()));

				TeslaInfo maxSales = teslaModel.stream()
			                              .max(comparator)
			                              .orElseThrow(NoSuchElementException::new);
				TeslaInfo minSales = teslaModel.stream()
			                              .min(comparator)
			                              .orElseThrow(NoSuchElementException::new);
				System.out.println(" ");
				System.out.println("The best month for " + modelName + " was: " + maxSales.getDate());

				System.out.println("The worst month for " + modelName + " was: " + minSales.getDate());
				                                                                
				System.out.println(" ");		
			
		}
}