package codelets.perception;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryObject;
import com.labexperiment.lab.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.util.ArrayList;
import java.lang.Exception;

import java.util.Arrays;

public class CorrelationDetector extends Codelet {

    private Memory newAttentionMO;
    private Memory knownPatternsMO;

    private boolean correlationDone = false;
    private Object dt[][] = null;

    public CorrelationDetector() {
    	
    } 
    
	@Override
	public void accessMemoryObjects() {
		synchronized(this) {
		   this.newAttentionMO=this.getInput("ATTENTION");
		   this.knownPatternsMO=this.getOutput("KNOWN_PATTERS");
        }   
	}

	@SuppressWarnings("unchecked")
	@Override
	public void proc() {
	  		
		if(newAttentionMO.getI() instanceof java.lang.Integer) {
			// is empty
		}
		else {
			    if(correlationDone == false) {
				    CopyOnWriteArrayList<AttentionMemoryObject> a_lt;
					a_lt = new CopyOnWriteArrayList<AttentionMemoryObject>((List<AttentionMemoryObject>)newAttentionMO.getI());
					CorrelationDetector2(a_lt);
					 correlationDone=true;
				  }
			  }
		 }
		
		// end proc
        
        @Override
        public void calculateActivation() {
      
        	
        }
        
        
        public void CorrelationDetector2(CopyOnWriteArrayList<AttentionMemoryObject> a_lt){
		
		int temp = 0;
		double gp[] = new double[a_lt.size()];
		double vs[] = new double[a_lt.size()];
		double mrd[] = new double[a_lt.size()];
		double gdr[] = new double[a_lt.size()];
		double hnd[] = new double[a_lt.size()];
		double age[] = new double[a_lt.size()];
		double edu[] = new double[a_lt.size()];
		double ses[] = new double[a_lt.size()];
		double mmse[] = new double[a_lt.size()];
		double cdr[] = new double[a_lt.size()];
		double etiv[] = new double[a_lt.size()];
		double nwbv[] = new double[a_lt.size()];
		double sid[] = new double[a_lt.size()];
				
	
		for(AttentionMemoryObject i : a_lt) {
			
			try {
				gp[temp] = Double.parseDouble(i.group);
				}
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    } 
			try {
				vs[temp] = Double.parseDouble(i.visit);
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    } 
			try {
				mrd[temp] = Double.parseDouble(i.mr_delay);
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    }
			try {
				gdr[temp] = Double.parseDouble(i.gender);
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    }
			try {
				hnd[temp] = Double.parseDouble(i.hand);
				
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    } 
			try {
				age[temp] = Double.parseDouble(i.age);
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    } 
			try {
				edu[temp] = Double.parseDouble(i.educ);
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    }
			try {
				ses[temp] = Double.parseDouble(i.ses);
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    } 
			try {
				mmse[temp] = Double.parseDouble(i.mmse);
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    }
			try {
				cdr[temp] = Double.parseDouble(i.cdr);
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    } 
			try {
				etiv[temp] = Double.parseDouble(i.etiv);
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    }
			try {
				nwbv[temp] = Double.parseDouble(i.n_wbv);
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    } 
			try {
				sid[temp] = Double.parseDouble(i.sid);
			    } 
			catch (Exception e) {
			      System.out.println("Imputantion needed or something wrong.");
			    } 
							
			
				temp++;
			}
		 	
		   System.out.println("======================================================");
		   System.out.println("====================Pearson's model===================");
		   System.out.println("Correlation with Group: " + findCorrelation2(gp,gp,temp));
		   System.out.println("Correlation with CDR: " + findCorrelation2(gp,cdr,temp));
		   System.out.println("Correlation with M/F : " + findCorrelation2(gp,gdr,temp));
		   System.out.println("Correlation with SES: " + findCorrelation2(gp,ses,temp));
		   System.out.println("Correlation with Age: " + findCorrelation2(gp,age,temp));
		   System.out.println("Correlation with eTIV: " + findCorrelation2(gp,etiv,temp));
		   System.out.println("Correlation with Visit: " + findCorrelation2(gp,vs,temp));
		   System.out.println("Correlation with MR Delay: " + findCorrelation2(gp,mrd,temp));
		   System.out.println("Correlation with EDUC: " + findCorrelation2(gp,edu,temp));
		   System.out.println("Correlation with nWBV: " + findCorrelation2(gp,nwbv,temp));
		   System.out.println("Correlation with MMSE: " + findCorrelation2(gp,mmse,temp));
		   System.out.println("Correlation with Hand: " + findCorrelation2(gp,hnd,temp));
		   
		   System.out.println("======================================================");
		   System.out.println("======================================================");		   
		 
			  
		
        }
        
               
        
        public double findCorrelation2(double X[],double Y[], int size) {
        	double r,nr=0,dr_1=0,dr_2=0,dr_3=0,dr=0;
     	    double xx[],xy[],yy[];
     	    xx =new double[size];
     	    xy =new double[size];
     	    yy =new double[size];
     	    double x[]=X,y[]=Y;
     	    double sum_y=0,sum_yy=0,sum_xy=0,sum_x=0,sum_xx=0;
     	    int i,n=size;
     	    for(i=0;i<n;i++)
     	    {
	     	    xx[i]=x[i]*x[i];
	     	    yy[i]=y[i]*y[i];
     	    }
     	    for(i=0;i<n;i++)
     	    {
	     	    sum_x+=x[i];
	     	    sum_y+=y[i];
	     	    sum_xx+= xx[i];
	     	    sum_yy+=yy[i];
	     	    sum_xy+= x[i]*y[i];
     	    }
     	    nr=(n*sum_xy)-(sum_x*sum_y);
     	    double sum_x2=sum_x*sum_x;
     	    double sum_y2=sum_y*sum_y;
     	    dr_1=(n*sum_xx)-sum_x2;
     	    dr_2=(n*sum_yy)-sum_y2;
     	    dr_3=dr_1*dr_2;
     	    dr=Math.sqrt(dr_3);
     	    r=(nr/dr);
     	    String s = String.format("%.5f",r);
     	    r = Double.parseDouble(s);
     	   	return r;
        }
        
 
}//end class

