package Experiment;
import br.unicamp.cst.core.entities.Mind;
import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryObject;

import support.MindView;
import codelets.sensors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class AgentMind extends Mind{

	public AgentMind(Environment env) {
		
		super(); // creates a mind 
		
		MemoryObject attentionMO;
		
		List<String> attention_list = Collections.synchronizedList(new ArrayList<String>());
		attentionMO=createMemoryObject("ATTENTION",attention_list);
		
		// Create and Populate MindViewer
        MindView mv = new MindView("MindView");	
        mv.addMO(attentionMO);
        mv.StartTimer();
        
        //Creating Codelets
        Codelet attention=new Attention(env.datasetpath);
        
        start();
		
	}
		
}
