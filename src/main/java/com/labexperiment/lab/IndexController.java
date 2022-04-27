package com.labexperiment.lab;

import java.util.Random;

import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import br.unicamp.cst.core.entities.CodeRack;
import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.entities.Mind;

import org.springframework.ui.Model;
import codelets.planning.*;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

@Controller


public class IndexController {

String temp = null;
private String tempStage = "could not be processed";
Object tempId =null;
private Instances data;
private J48 tree;


  @GetMapping()
  public String greetingForm(Model model) {
    model.addAttribute("greeting", new Result());
    return "index";
  }

  @PostMapping("/patient")
  public String greetingSubmit(@ModelAttribute Result greeting, Model model) {
	
	temp=getSuggestion(greeting.getId());
	
	
	//setting the screen
	greeting.setContent(temp);
	greeting.setStage(tempStage);
		
	model.addAttribute("greeting", greeting);
    return "patient";
  }
  

public IndexController() throws Exception {
//public IndexController(Mind m) throws Exception {
		DataSource source = new DataSource("oasis-db.arff");
		data = source.getDataSet();
		System.out.println(data.numInstances() + " instances loaded!");
		//m.getRawMemory().getAllMemoryObjects()
}
  

public String getSuggestion(String l) {
	
	long startTime = System.nanoTime();
	
		
	try {
		IndexController weka = new IndexController();
		weka.removeFirstAttribute();
	    weka.selectFeatures();
		weka.buildDecisionTree();
		tempStage = weka.classifyData(l);
		weka.showErrorMetrics();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	long stopTime = System.nanoTime();
	System.out.println("EXECUTION TIME: "+ (stopTime - startTime));
	
	switch (tempStage) {
     case "normal":
    	 return "no result.";
     case "mci":
    	 return "In the early stage of Alzheimer's, most people function independently. He or she may still drive, take part in social activities, volunteer and even work. Your role as care partner is an important one: to provide support and companionship, and help plan for the future.\r\n"
    	 		+ "\r\n"
    	 		+ "\"Early stage\" refers to people, irrespective of age, who are diagnosed with Alzheimer�s disease or a related disorder, and are in the beginning stage of the disease. The early stage of Alzheimer�s can last for years. A diagnosis of early stage Alzheimer�s disease doesn�t just affect those with the disease; it affects everyone who loves and cares about them. Learn more about the stages of Alzheimer�s.";
     case "moderate":
    	 return "Being a caregiver for someone in the middle stages of Alzheimer's requires flexibility and patience. As the abilities of the person with Alzheimer's change and functioning independently becomes more difficult, you will have to take on greater responsibility. Daily routines will need to be adapted, and structure will become more important.\r\n"
    	 		+ " \r\n"
    	 		+ "As you gain experience as a middle-stage caregiver, you will develop strategies and ways of coping that work for you and the person with dementia. When abilities diminish further, these will need to be modified. The Alzheimer's Association� offers educational workshops and resources educational workshops and resources that can provide you with the caregiving skills needed to deal with changing needs of someone in this stage of the disease. Sharing information with other Alzheimer's caregivers also can be a great source of information and support. Other caregivers truly understand the complex feelings associated with caring for a person with dementia.";
     case "moderately_severe":
    	 return "As caregiving responsibilities become more demanding, it's important to take care of yourself. Take breaks, even if it is only for a few moments. Make sure not to isolate yourself. Learn what respite services are available in your community, and take friends and family up on offers to help. Since paying for long-term care can be a big concern and source of stress, research all your options, if plans are not already in place. To find local services, resources and programs, use our free online Community Resource Finder. Quick tips from other caregivers\r\n"
    			 
    	 		+ "\r\n"
    	 		+ "Learn what to expect in the middle stages of the disease so you can be prepared.\r\n"
    	 		+ "Use a calm voice when responding to repeated questions.\r\n"
    	 		+ "Respond to the emotion, instead of the specific question; the person may simply need reassurance.\r\n"
    	 		+ "Use simple written reminders if the person can still read.\r\n"
    	 		+ "If you notice changes, check with the doctor to rule out other physical problems or medication side effects.";
     case "severe":
    	 return "During the late stages, your role as a caregiver focuses on preserving quality of life and dignity. Although a person in the late stage of Alzheimer's typically loses the ability to talk and express needs, research tells us that some core of the person's self may remain. This means you may be able to continue to connect throughout the late stage of the disease.\r\n"
    	 		+ " \r\n"
    	 		+ "At this point in the disease, the world is primarily experienced through the senses. You can express your caring through touch, sound, sight, taste and smell. For example, try:\r\n"
    	 		+ "\r\n"
    	 		+ "Playing his or her favorite music\r\n"
    	 		+ "Reading portions of books that have meaning for the person\r\n"
    	 		+ "Looking at old photos together\r\n"
    	 		+ "Preparing a favorite food\r\n"
    	 		+ "Rubbing lotion with a favorite scent into the skin\r\n"
    	 		+ "Brushing the person's hair\r\n"
    	 		+ "Sitting outside together on a nice day";
     case "eol":
    	return "Since care needs are extensive during the late stage, they may exceed what you can provide at home, even with additional assistance. This may mean moving the person into a facility in order to get the care needed.\r\n"
    			+ "Deciding on late-stage care can be one of the most difficult decisions families face. Families that have been through the process tell us that it is best to gather information and move forward, rather than second guessing decisions after the fact. There are many good ways to provide quality care. Remember, regardless of where the care takes place, the decision is about making sure the person receives the care needed.\r\n"
    			+ "\r\n"
    			+ "At the end of life, another option is hospice. The underlying philosophy of hospice focuses on quality and dignity by providing comfort, care and support services for people with terminal illnesses and their families. To qualify for hospice benefits under Medicare, a physician must diagnosis the person with Alzheimer's disease as having less than six months to live.\r\n"
    			+ "\r\n"
    			+ "Ideally, discussions about end-of-life care wishes should take place while the person with the dementia still has the capacity to make decisions and share wishes about life-sustaining treatment.";
     default:
       return "Have you had the opportunity to make decisions about the future together, including legal, financial, and long-term care planning. The person living with dementia can take advantage of available treatments or participation in clinical trials and you both can benefit from local resources and support services. Being able to take advantage of all these benefits can reduce anxiety about the unknown and lead to better outcomes for everyone involved";
  }
	
}
 
public String classifyData(String x) throws Exception {
	double[] vals = new double[data.numAttributes()];
	
	int a = Integer.parseInt(x);
					
	vals[0] = data.get(a).value(0);	//group {false, true}
	vals[1] = data.get(a).value(1);	//visit {false, true}
	vals[2] = data.get(a).value(2);	//mrdelay {false, true} definidor
	vals[3] = data.get(a).value(3);	//mf {false, true} definidor
	vals[4] = data.get(a).value(4);	//hand {false, true} definidor
	vals[5] = data.get(a).value(5);	//age {false, true}
	vals[6] = data.get(a).value(6);	//educ {false, true}
	vals[7] = data.get(a).value(7);	//mmse {false, true}
	vals[8] = data.get(a).value(8);	//etiv {false, true}

	Instance theStage = new DenseInstance(1.0, vals);
	theStage.setDataset(data);
	double result = tree.classifyInstance(theStage);
	
	System.out.println("And the stage is... " + data.classAttribute().value((int) result));
	return data.classAttribute().value((int) result);
}

public void showErrorMetrics() throws Exception {
	Classifier c1 = new J48();
	Evaluation evalRoc = new Evaluation(data);
	evalRoc.crossValidateModel(c1, data, 10, new Random(1), new Object[] {});
	System.out.println(evalRoc.toSummaryString());
	System.out.println(evalRoc.toCumulativeMarginDistributionString());
	System.out.println(evalRoc.toClassDetailsString());
	System.out.println(evalRoc.toMatrixString());
}

public void buildDecisionTree() throws Exception {
	tree = new J48();
	String[] options = new String[1];
	options[0] = "-U"; // un-pruned tree option
	tree.setOptions(options);
	tree.buildClassifier(data);
	System.out.println(tree.toString());
}

public void removeFirstAttribute() throws Exception {
	Remove remove = new Remove();
	String[] opts = new String[] { "-R", "1" };
	remove.setOptions(opts);
	remove.setInputFormat(data);
	data = Filter.useFilter(data, remove);
}

public void selectFeatures() throws Exception {
	InfoGainAttributeEval evaluator = new InfoGainAttributeEval();
	Ranker ranker = new Ranker();
	AttributeSelection attSelect = new AttributeSelection();
	attSelect.setEvaluator(evaluator);
	attSelect.setSearch(ranker);
	attSelect.SelectAttributes(data);
	int[] selectedAttributes = attSelect.selectedAttributes();
	System.out.println(Utils.arrayToString(selectedAttributes));
	}
}
/*public class IndexController extends AgentMind {
	
	
	
	String temp = null;
	private String tempStage = "could not be processed";
	Object tempId =null;
	private Instances data;
	private J48 tree;

	
	  @GetMapping()
	  public String greetingForm(Model model) {
	    model.addAttribute("greeting", new Result());
	    return "index";
	  }

	  @PostMapping("/patient")
	  public String greetingSubmit(@ModelAttribute Result greeting, Model model) {
		
		temp=getSuggestion(greeting.getId());
		
		
		//setting the screen
		greeting.setContent(temp);
		greeting.setStage(tempStage);
			
		model.addAttribute("greeting", greeting);
	    return "patient";
	  }
	  
	
	public IndexController() throws Exception {
	//public IndexController(Mind m) throws Exception {
		 	getCodelets();
			DataSource source = new DataSource("C:\\Users\\wellf\\eclipse-workspace\\teste01\\src\\main\\java\\teste01\\oasis02.arff");
			data = source.getDataSet();
			System.out.println(data.numInstances() + " instances loaded!");
			//m.getRawMemory().getAllMemoryObjects()
	}
	  
	
	public void getCodelets() {
		
		
	}
	
	public String getSuggestion(String l) {
		
		long startTime = System.nanoTime();
		
		
		
		Codelet x = this.codeRack.getAllCodelets().get(2);
		x.start();
		Memory m = x.getBroadcast().get(1);
		
		
		Memory y = this.getRawMemory().getAllMemoryObjects().get(2);
		System.out.println("Agora pareee VALUE!"+y.getI().toString()); 
		
		Object ww= 33;
	
		System.out.println("Agora pareee VALUE!set"+ y.setI(ww)); 
		System.out.println("Agora pareee VALUE33!"+y.getI().toString()); 
		x.stop();
		
		
		
		try {
			IndexController weka = new IndexController();
			weka.removeFirstAttribute();
		    weka.selectFeatures();
			weka.buildDecisionTree();
			tempStage = weka.classifyData(l);
			weka.showErrorMetrics();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long stopTime = System.nanoTime();
		System.out.println("EXECUTION TIME: "+ (stopTime - startTime));
		
		switch (tempStage) {
	     case "normal":
	    	 return "no result.";
	     case "mci":
	    	 return "In the early stage of Alzheimer's, most people function independently. He or she may still drive, take part in social activities, volunteer and even work. Your role as care partner is an important one: to provide support and companionship, and help plan for the future.\r\n"
	    	 		+ "\r\n"
	    	 		+ "\"Early stage\" refers to people, irrespective of age, who are diagnosed with Alzheimer’s disease or a related disorder, and are in the beginning stage of the disease. The early stage of Alzheimer’s can last for years. A diagnosis of early stage Alzheimer’s disease doesn’t just affect those with the disease; it affects everyone who loves and cares about them. Learn more about the stages of Alzheimer’s.";
	     case "moderate":
	    	 return "Being a caregiver for someone in the middle stages of Alzheimer's requires flexibility and patience. As the abilities of the person with Alzheimer's change and functioning independently becomes more difficult, you will have to take on greater responsibility. Daily routines will need to be adapted, and structure will become more important.\r\n"
	    	 		+ " \r\n"
	    	 		+ "As you gain experience as a middle-stage caregiver, you will develop strategies and ways of coping that work for you and the person with dementia. When abilities diminish further, these will need to be modified. The Alzheimer's Association® offers educational workshops and resources educational workshops and resources that can provide you with the caregiving skills needed to deal with changing needs of someone in this stage of the disease. Sharing information with other Alzheimer's caregivers also can be a great source of information and support. Other caregivers truly understand the complex feelings associated with caring for a person with dementia.";
	     case "moderately_severe":
	    	 return "As caregiving responsibilities become more demanding, it's important to take care of yourself. Take breaks, even if it is only for a few moments. Make sure not to isolate yourself. Learn what respite services are available in your community, and take friends and family up on offers to help. Since paying for long-term care can be a big concern and source of stress, research all your options, if plans are not already in place. To find local services, resources and programs, use our free online Community Resource Finder. Quick tips from other caregivers\r\n"
	    			 
	    	 		+ "\r\n"
	    	 		+ "Learn what to expect in the middle stages of the disease so you can be prepared.\r\n"
	    	 		+ "Use a calm voice when responding to repeated questions.\r\n"
	    	 		+ "Respond to the emotion, instead of the specific question; the person may simply need reassurance.\r\n"
	    	 		+ "Use simple written reminders if the person can still read.\r\n"
	    	 		+ "If you notice changes, check with the doctor to rule out other physical problems or medication side effects.";
	     case "severe":
	    	 return "During the late stages, your role as a caregiver focuses on preserving quality of life and dignity. Although a person in the late stage of Alzheimer's typically loses the ability to talk and express needs, research tells us that some core of the person's self may remain. This means you may be able to continue to connect throughout the late stage of the disease.\r\n"
	    	 		+ " \r\n"
	    	 		+ "At this point in the disease, the world is primarily experienced through the senses. You can express your caring through touch, sound, sight, taste and smell. For example, try:\r\n"
	    	 		+ "\r\n"
	    	 		+ "Playing his or her favorite music\r\n"
	    	 		+ "Reading portions of books that have meaning for the person\r\n"
	    	 		+ "Looking at old photos together\r\n"
	    	 		+ "Preparing a favorite food\r\n"
	    	 		+ "Rubbing lotion with a favorite scent into the skin\r\n"
	    	 		+ "Brushing the person's hair\r\n"
	    	 		+ "Sitting outside together on a nice day";
	     case "eol":
	    	return "Since care needs are extensive during the late stage, they may exceed what you can provide at home, even with additional assistance. This may mean moving the person into a facility in order to get the care needed.\r\n"
	    			+ "Deciding on late-stage care can be one of the most difficult decisions families face. Families that have been through the process tell us that it is best to gather information and move forward, rather than second guessing decisions after the fact. There are many good ways to provide quality care. Remember, regardless of where the care takes place, the decision is about making sure the person receives the care needed.\r\n"
	    			+ "\r\n"
	    			+ "At the end of life, another option is hospice. The underlying philosophy of hospice focuses on quality and dignity by providing comfort, care and support services for people with terminal illnesses and their families. To qualify for hospice benefits under Medicare, a physician must diagnosis the person with Alzheimer's disease as having less than six months to live.\r\n"
	    			+ "\r\n"
	    			+ "Ideally, discussions about end-of-life care wishes should take place while the person with the dementia still has the capacity to make decisions and share wishes about life-sustaining treatment.";
	     default:
	       return "Have you had the opportunity to make decisions about the future together, including legal, financial, and long-term care planning. The person living with dementia can take advantage of available treatments or participation in clinical trials and you both can benefit from local resources and support services. Being able to take advantage of all these benefits can reduce anxiety about the unknown and lead to better outcomes for everyone involved";
	  }
				
	
	}
	 
	public String classifyData(String x) throws Exception {
		double[] vals = new double[data.numAttributes()];
		
		int a = Integer.parseInt(x);
						
		vals[0] = data.get(a).value(0);	//group {false, true}
		vals[1] = data.get(a).value(1);	//visit {false, true}
		vals[2] = data.get(a).value(2);	//mrdelay {false, true} definidor
		vals[3] = data.get(a).value(3);	//mf {false, true} definidor
		vals[4] = data.get(a).value(4);	//hand {false, true} definidor
		vals[5] = data.get(a).value(5);	//age {false, true}
		vals[6] = data.get(a).value(6);	//educ {false, true}
		vals[7] = data.get(a).value(7);	//mmse {false, true}
		vals[8] = data.get(a).value(8);	//etiv {false, true}
	
		Instance theStage = new DenseInstance(1.0, vals);
		theStage.setDataset(data);
		double result = tree.classifyInstance(theStage);
		
		System.out.println("And the stage is... " + data.classAttribute().value((int) result));
		return data.classAttribute().value((int) result);
	}

	public void showErrorMetrics() throws Exception {
		Classifier c1 = new J48();
		Evaluation evalRoc = new Evaluation(data);
		evalRoc.crossValidateModel(c1, data, 10, new Random(1), new Object[] {});
		System.out.println(evalRoc.toSummaryString());
		System.out.println(evalRoc.toCumulativeMarginDistributionString());
		System.out.println(evalRoc.toClassDetailsString());
		System.out.println(evalRoc.toMatrixString());
	}
	
	public void buildDecisionTree() throws Exception {
		tree = new J48();
		String[] options = new String[1];
		options[0] = "-U"; // un-pruned tree option
		tree.setOptions(options);
		tree.buildClassifier(data);
		System.out.println(tree.toString());
	}
	
	public void removeFirstAttribute() throws Exception {
		Remove remove = new Remove();
		String[] opts = new String[] { "-R", "1" };
		remove.setOptions(opts);
		remove.setInputFormat(data);
		data = Filter.useFilter(data, remove);
	}

	public void selectFeatures() throws Exception {
		InfoGainAttributeEval evaluator = new InfoGainAttributeEval();
		Ranker ranker = new Ranker();
		AttributeSelection attSelect = new AttributeSelection();
		attSelect.setEvaluator(evaluator);
		attSelect.setSearch(ranker);
		attSelect.SelectAttributes(data);
		int[] selectedAttributes = attSelect.selectedAttributes();
		System.out.println(Utils.arrayToString(selectedAttributes));
	}

}*/
