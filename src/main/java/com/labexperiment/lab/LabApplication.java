package com.labexperiment.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.labexperiment.lab.*;


@SpringBootApplication
public class LabApplication {

	public static void main(String[] args) {
		
		
        AgentMind a = new AgentMind();  // Creates the Agent Mind and start it   
		SpringApplication.run(LabApplication.class, args);
		
	}

}
