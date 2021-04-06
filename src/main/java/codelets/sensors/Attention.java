package codelets.sensors;
import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryObject;

public class Attention extends Codelet{
	
		    
		private MemoryObject attentionMO;
	    private String dataset = null;
		
		public Attention (String dtsetpath) {
			dataset= dtsetpath;
		}

		@Override
		public void accessMemoryObjects() {
			
		}

		@Override
		public void proc() {
	           
		}

		@Override
		public void calculateActivation() {}

	
}
