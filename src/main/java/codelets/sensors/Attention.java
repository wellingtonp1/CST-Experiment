package codelets.sensors;
import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;

import java.io.*;
import java.util.Scanner;  
import java.util.List;
import java.util.ArrayList;

import com.labexperiment.lab.*;

public class Attention extends Codelet{
	
		    
		private Memory aMO;
	    private String row = null;
		private static final String COMMA_DELIMITER = ",";
	    private List<AttentionMemoryObject> lt = new ArrayList<AttentionMemoryObject>();
	    private Boolean dataLoaded = false;
		
		@Override
		public void accessMemoryObjects() {
				aMO=this.getOutput("ATTENTION");
			if(dataLoaded == false)
				getData();
		}

		
		@Override
		public void proc() {
		//	synchronized(aMO) {
				aMO.setI(lt);
		//	}
		}

		@Override
		public void calculateActivation() {
		
		}

		
		public void getData() {
			
				BufferedReader br = null;
		        try
		        {
		            //Reading the csv file
		            br = new BufferedReader(new FileReader("C:\\data.csv"));
		            
		                      
		            String line = "";
		            //Read to skip the header
		            br.readLine();
		            //Reading from the second line
		            while ((line = br.readLine()) != null) 
		            {
		                String[] attDetails = line.split(COMMA_DELIMITER);
		                if(attDetails.length > 0 )
		                {
		       	                	AttentionMemoryObject amo = new AttentionMemoryObject(
		                           	attDetails[0],
		                          	attDetails[1],
		                         	attDetails[2],
		                          	attDetails[3], 	
		                          	attDetails[4], 	 
		                          	attDetails[5], 	
		                          	attDetails[6], 	
		                          	attDetails[7],
		                          	attDetails[8], 	
		                          	attDetails[9],
		                          	attDetails[10], 	
		                          	attDetails[11],
		                          	attDetails[12], 	
		                          	attDetails[13],
		                          	attDetails[14] 	
		                        	                          	 
		       	                	);
		       	               
		                            
		                    lt.add(amo);
		                    
		                	System.out.println("Codelet Attention - Memory item added: " + lt.size());
				            
		                }
		            } 
		            
		            dataLoaded=true;	            
		        }catch(Exception ee)
		        {
		        	System.out.println("Error occured while reading");
		        	ee.printStackTrace();
		        }
		        finally
		        {
		            try
		            {
		                br.close();
		            }
		            catch(IOException ie)
		            {
		                System.out.println("Error occured while closing the BufferedReader");
		                ie.printStackTrace();
		            }
		        }
			}
		        
		
		
		
}
