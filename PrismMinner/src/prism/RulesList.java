package prism;

import java.util.ArrayList;

public class RulesList {

	private ArrayList<Rules> rulesList;
	
	RulesList(){
		rulesList = new ArrayList<>();
	}
	
	/*
	 * public ArrayList<Rules> getRules() { return rulesList; }
	 */

	public void addRules(Rules rule) {
		rulesList.add(rule);
	}


}