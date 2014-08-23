package com.kellyfj.classifier.data;

import java.util.ArrayList;
import java.util.List;

public class DataHolder extends ArrayList<DataRow>{

	String[] columnLabels;
	
	public DataHolder(String[] colLabels) {
		columnLabels = new String[colLabels.length];
		System.arraycopy(colLabels, 0, columnLabels, 0, colLabels.length);
	}
}
