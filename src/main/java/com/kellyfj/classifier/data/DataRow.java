package com.kellyfj.classifier.data;

public class DataRow {

	private double[] values;
	private String targetClassification;
	private long rowNumber;
	
	public DataRow(double[] v, String classification, long rowNumber) {
		values = new double[v.length];
		System.arraycopy(v, 0, this.values, 0, values.length);
		targetClassification = classification;
		this.rowNumber = rowNumber;
	}
	
	public double euclideanDistance(DataRow d2) {
		if(this.values.length != d2.getValues().length)
			throw new IllegalArgumentException("Cannot compare DataRow objects");
		
		double distance=0.0;
		double[] d2values = d2.getValues();
		
		for(int i=0; i< d2values.length; i++) {
			double delta = values[i] - d2values[i];
			distance += delta*delta;
		}
		return Math.sqrt(distance);
	}
	
	public double[] getValues() {
		return this.values;
	}
	
	public String getClassificationLabel() {
		return this.targetClassification;
	}
	
	public long getRowNumber() {
		return this.rowNumber;
	}
}
