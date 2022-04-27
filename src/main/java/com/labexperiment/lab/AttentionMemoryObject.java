package com.labexperiment.lab;

public class AttentionMemoryObject {
	public String subject_id;
	public String mir_id;
	public String group;
	public String visit;
	public String mr_delay;
	public String gender;
	public String hand;
	public String age;
	public String educ;
	public String ses;
	public String mmse;
	public String cdr;
	public String etiv;
	public String n_wbv;
	public String sid;
	 
	public AttentionMemoryObject(
			String subject_id, 
			String mir_id,
			String group, 
			String visit, 
			String mr_delay, 
			String gender, 
			String hand,
			String age,
			String educ,
			String ses, 
			String mmse,
			String cdr,
			String etiv,
			String n_wbv,
			String sid 
			)
	{
		
		this.subject_id =  subject_id;
		this.mir_id = mir_id;
		this.group = group;
		this.visit = visit;
		this.mr_delay = mr_delay;
		this.gender = gender; 
		this.hand = hand;
		this.age = age;
		this.educ= educ;
		this.ses = ses;
		this.mmse = mmse;
		this.cdr = cdr;
		this.etiv = etiv;
		this.n_wbv = n_wbv;
		this.sid = sid;		
		
	}

	
}
