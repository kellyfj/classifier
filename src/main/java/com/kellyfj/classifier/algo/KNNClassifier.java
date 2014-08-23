package com.kellyfj.classifier.algo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kellyfj.classifier.data.DataHolder;
import com.kellyfj.classifier.data.DataRow;

public class KNNClassifier {
	private DataHolder trainingData;
	private int k;
	private static final Logger log = LogManager.getLogger();
	
	public KNNClassifier(int k) {
		this.k = k;
	}
	
	public void train(DataHolder d) {
		trainingData = d;
	}
	
	public String test(DataRow testRow) {
		
		List<ComparatorHolder> listOfErrors = new ArrayList<ComparatorHolder>();
		for(DataRow trainingRow: trainingData) {
			double error = trainingRow.euclideanDistance(testRow);
			log.info("Distance: "+error +" to "+ trainingRow.getClassificationLabel());
			ComparatorHolder c = new ComparatorHolder();
			c.error = error;
			c.dataRow = trainingRow;
			listOfErrors.add(c);
		}
		
		//Sort the list according to error
		Collections.sort(listOfErrors);
		
		log.info("Comparing Rec#="+testRow.getRowNumber()+" Vals="+Arrays.toString(testRow.getValues()));
		//Get the k closes matches
		for (int i = 0; i < k; i++) {
			DataRow d = listOfErrors.get(i).dataRow;
			String label = d.getClassificationLabel();
			double[] vals = d.getValues();
			long recNumber = d.getRowNumber();
			double error = listOfErrors.get(i).error;
			log.info("k=" + i + " Distance="
					+ new DecimalFormat("#.00").format(error) + "  Label="
					+ label + " Rec#=" + recNumber + " Vals="
					+ Arrays.toString(vals));
		}
		
		return "";
	}

	public class ComparatorHolder implements Comparable<ComparatorHolder> {
	    public double error;
	    public DataRow dataRow;

		public int compareTo(ComparatorHolder c2) {
			if(this.error < c2.error)
				return -1;
			else if(this.error == c2.error) 
				return 0;
			else 
				return 1;
		}
	}
}
