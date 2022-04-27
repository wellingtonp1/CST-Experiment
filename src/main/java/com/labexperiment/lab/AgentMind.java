package com.labexperiment.lab;
import br.unicamp.cst.core.entities.Mind;
import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.entities.Memory;

import support.MindView;
import codelets.sensors.Attention;
import codelets.perception.CorrelationDetector;
import codelets.planning.NextAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AgentMind extends Mind{

	
	
	
	public AgentMind() {
		
		super(); // creates a mind 
		
		System.out.println(" Creating Memory Objects ");
		MemoryObject attentionMO;
		MemoryObject knownPattersMO;
		Memory newPatternMO;
		Memory nextActionMO;
			
		newPatternMO=createMemoryObject("NEW_PATTERN", "");
		nextActionMO=createMemoryObject("NEXT_ACTION_ID", 10);
		attentionMO=createMemoryObject("ATTENTION",0);
		
	
		List<AttentionMemoryObject> knownPatterns = Collections.synchronizedList(new ArrayList<AttentionMemoryObject>());
		knownPattersMO=createMemoryObject("KNOWN_PATTERS", knownPatterns);
		
		        
        Codelet attention = new Attention();
        attention.addOutput(attentionMO);
        attention.setName("Attention");
        insertCodelet(attention);
                
        Codelet cd = new CorrelationDetector();
        cd.setName("CorrelationDetector");
		cd.addInput(knownPattersMO);
		cd.addInput(attentionMO);
		cd.addOutput(newPatternMO);
        insertCodelet(cd); 
        
        Codelet na = new NextAction();
        na.setName("NextAction");
        na.addInput(attentionMO);
        na.addOutput(nextActionMO);
        insertCodelet(na);
        na.stop();
               
        // Start Cognitive Cycle
        start();
        
        // sets a time step for running the codelets to avoid heating too much your machine
        for (Codelet c : this.getCodeRack().getAllCodelets()) {
            c.setTimeStep(2000);
            System.out.println(" Running Codelets " + c.getName());
        }
        
    
     /* Create and Populate MindViewer
        MindView mv = new MindView("MindView");	
        mv.addMO(attentionMO);
        mv.addMO(knownPattersMO);
        mv.StartTimer();
        mv.setVisible(true);*/
           	
    	}
		
}
