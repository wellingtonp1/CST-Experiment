package codelets.planning;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;


import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.ArrayList;

import com.labexperiment.lab.*;



public class NextAction extends Codelet {

	private Memory ntMO;
	private Memory nxtSuggestion;
   	public String result = "";

    private Instances data;
    private J48 tree;
    private Boolean dataLoaded = false;
	
	@Override
	public void accessMemoryObjects() {
		 this.ntMO=this.getInput("ATTENTION");
		 
			
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void proc() {
	
		
	 }
	

	@Override
	public void calculateActivation() {
		
		
			try {
			nextActionProposition();
				dataLoaded=true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
				
	}

	
	public void nextActionProposition() throws Exception {
		
	//method implemented at IndexController
		
	}
	
}
