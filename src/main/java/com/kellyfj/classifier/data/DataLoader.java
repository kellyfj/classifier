package com.kellyfj.classifier.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class DataLoader {

	public static DataHolder load(String filename) throws IOException {
		// Reader in = new FileReader(filename);
		InputStream is = null;
		Reader in = null;
		try {
			is = DataLoader.class.getResourceAsStream("/iris.data");
			in = new InputStreamReader(is);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			
			DataHolder d=null;
			for (CSVRecord record : records) {
				if(record.getRecordNumber() ==1 ) {
					//Load column labels
					String[] columnLabels = new String[record.size()];
					for(int col=0; col< record.size(); col++) {
						columnLabels[col] = record.get(col);
					}
					d = new DataHolder(columnLabels);
				} else {
					double[] values = new double[record.size()-1]; 
					for(int col=0; col< record.size()-1; col++) {
						values[col] = new Double(record.get(col)).doubleValue();
					}
					String classification = record.get(record.size()-1);
					DataRow dataRow = new DataRow(values, classification, record.getRecordNumber());
					d.add(dataRow);			
				}
				
				//System.out.println(record.getRecordNumber() + " has "
				//		+ record.size() + " columns");
			}
			return d;
		} finally {
			if(in!=null) in.close();
			if(is!=null) is.close();
		}
	}
}
