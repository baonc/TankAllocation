package org.study.model;

import localsearch.model.VarIntLS;




public class Main {
	public static void main(String args[]) {
		Chemical chemical = new Chemical("data/chemical.xml");
		chemical.stateModel();
		//chemical.ga(100, 100, 50);
		//chemical.simulatedAnnealingSearch(1, 0.9, 0, 500);
		VarIntLS best[] = chemical.simulatedAnealingSearchOptimation(1, 0.9, 0.000000001, 10000);
		//boolean checkBest = chemical.testSolution(best);
		//System.out.println("Solution is: " + checkBest);
		//chemical.backTrackingSearch(0, Integer.MAX_VALUE);
		//chemical.simulatedAnealingSearchVer2(1, 0.9, 0);
		//chemical.greedySearch(100000);
		//VarIntLS best[] = chemical.optimationSolutionSimulatedAnealingSearch(1, 0.0000000001, 0.9);
		//chemical.tabuSearchLib(10, 300, 100000, 100);
		//chemical.printResult();
		chemical.printSolution(best);
	}
}