package com.simple.server.lifecycle;

public enum HqlStepsType {
	   PREPARE,
	   REGISTER,
	   START,
	   CREATE_HQL,
	   EXEC_HQL,
	   FINISH;

	    public int getLastStep() {
	        return FINISH.ordinal();
	    }
}
