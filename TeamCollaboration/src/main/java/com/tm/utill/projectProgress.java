package com.tm.utill;

public enum projectProgress {

	RequirementGathering("10"),
	FeasibiltyStudy("20"),
	Design("30"),
	Implementation("40"),
	Tesing("60"),
	BugFixing("80"),
	Completed("100"),
	Closed("0"),
	OnHold("0");
   
	
	private String progress;
	
	projectProgress(String progress) {
		this.progress=progress;
	}
	
	public String getProgress() {
		return progress;
	}
	
}
