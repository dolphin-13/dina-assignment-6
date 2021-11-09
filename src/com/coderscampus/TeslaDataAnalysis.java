package com.coderscampus;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TeslaDataAnalysis {
	
	public Set<Entry<String, List<TeslaInfo>>> getEntrySetFromModelsGroupedByYear(List<TeslaInfo> model) {
		Map<String, List<TeslaInfo>> groupedByYear = model.stream()
				.collect(Collectors.groupingBy((tesla) -> tesla.getDate().format(DateTimeFormatter.ofPattern("yyyy"))));
		Set<Entry<String, List<TeslaInfo>>> entrySet = groupedByYear.entrySet();
		return entrySet;
	}
	
	public long getSumOfSalesByYear(Set<Entry<String, List<TeslaInfo>>> entrySet, String year) {	
        return entrySet.stream()
        		       .filter(entry -> entry.getKey().equals(year)) 	               
		               .flatMap(entry -> entry.getValue().stream())
		               .mapToInt(tesla -> tesla.getSales().intValue())		              
		               .sum();		               		              
	}
	
	public int getMaxOfSalesByYear(Set<Entry<String, List<TeslaInfo>>> entrySet, String year) {
		return entrySet.stream()
	                   .filter(entry -> entry.getKey().equals(year))
	                   .flatMap(entry -> entry.getValue().stream())
	                   .mapToInt(tesla -> tesla.getSales().intValue())
	                   .summaryStatistics().getMax();
	}
	
	public int getMinOfSalesByYear(Set<Entry<String, List<TeslaInfo>>> entrySet, String year) {
		return entrySet.stream()
	                   .filter(entry -> entry.getKey().equals(year))
	                   .flatMap(entry -> entry.getValue().stream())
	                   .mapToInt(tesla -> tesla.getSales().intValue())
	                   .summaryStatistics().getMin();
	}
	
	

}
