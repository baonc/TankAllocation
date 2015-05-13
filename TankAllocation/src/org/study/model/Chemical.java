package org.study.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import localsearch.constraints.basic.Implicate;
import localsearch.constraints.basic.IsEqual;
import localsearch.constraints.basic.LessOrEqual;
import localsearch.constraints.basic.NotEqual;
import localsearch.functions.conditionalsum.ConditionalSum;
import localsearch.model.ConstraintSystem;
import localsearch.model.IFunction;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import localsearch.search.TabuSearch;
import localsearch.selectors.MinMaxSelector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class contain data of problem and: </br>
 * 
 * <li> ReadFile to get data and convert to data struct 
 * <li> Create model for problem
 * <li> Find Search algorithms to find best solution of problem
 * 
 * @created 17 / 4 / 2015
 * @author baonc
 *
 */
public class Chemical {
	// variable contains information of problem
	private Cargos cargos[];									// Array contain array of cargos of problem
	private ArrayList<Incompatible> incompatible;				// ArrayList containt List of incompatible of problem
	private Tanks tanks[];										// Array contain array of tank of vessel of problem
	private int numberOfCargos;									// Number of cargos of problem
	private int numberOfTanks;									// Number of tank of problem
	
	// variable contains solution and model of problem
	private LocalSearchManager lsManager;						// Local Search manager of cbo library
	private ConstraintSystem constraints;						// Constraint system contains constrain of problem
	private VarIntLS x[];										// Variable containt solution of problem, x[t] for each tank t representing the product
																// type (cargos) to be place in side that tank.
	
	/**
	 * Constructor to create a chemical instance
	 */
	public Chemical(String fileName) {
		readFile(fileName);		// call function readFile to set parameter
	}
	
	/**
	 * Getter for x
	 * @return	: x variable
	 */
	public VarIntLS[] getX() {
		return this.x;
	}
	
	/**
	 * Function readfile to get data and convert to data struct to construct model and search algorithms
	 * for problem
	 */
	public void readFile(String fileName) {
		try {
			//File xmlFile = new File("data/chemical.xml");
			File xmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			// normalize document
			doc.normalize();
			// get root node
			System.out.println("Root: " + doc.getDocumentElement().getNodeName());
			// get node list cargos
			NodeList cargosNodeList = doc.getElementsByTagName("cargos");
			Node cargosNode = cargosNodeList.item(0);
			Element cargosElement = (Element) cargosNode;
			// get numberOfCargos from attribute of cargosElement "nb"
			this.numberOfCargos = Integer.parseInt(cargosElement.getAttribute("nb"));
			this.cargos = new Cargos[this.numberOfCargos];
			NodeList cargoNodeList = cargosElement.getElementsByTagName("cargo");
			for(int i = 0; i < cargoNodeList.getLength(); i++) {
				Node cargoNode = cargoNodeList.item(i);
				System.out.println("Curren element: " + cargoNode.getNodeName());
				if(cargoNode.getNodeType()  == Node.ELEMENT_NODE) {
					Element cargoElement = (Element) cargoNode;
					int id = Integer.parseInt(cargoElement.getAttribute("id"));
					String name = cargoElement.getAttribute("name");
					int volume = Integer.parseInt(cargoElement.getAttribute("volume"));
					Cargos cargos = new Cargos(id, name, volume);
					this.cargos[i] = cargos;
				}
			}
			// get node list of incompatibles
			NodeList incompatiblesNodeList = doc.getElementsByTagName("incompatibles");
			Node incompatiblesNode = incompatiblesNodeList.item(0);
			Element incompatiblesElement = (Element) incompatiblesNode;
			NodeList incompatibleNodeList = incompatiblesElement.getElementsByTagName("incompatible");
			this.incompatible = new ArrayList<Incompatible>();
			for(int i = 0; i < incompatibleNodeList.getLength(); i++) {
				Node incompatibleNode = incompatibleNodeList.item(i);
				System.out.println("Current element: " + incompatibleNode.getNodeName());
				if(incompatibleNode.getNodeType() == Node.ELEMENT_NODE) {
					Element incompatibleElement = (Element) incompatibleNode;
					int cargo1 = Integer.parseInt(incompatibleElement.getAttribute("cargo1"));
					int	cargo2 = Integer.parseInt(incompatibleElement.getAttribute("cargo2")); 
					Incompatible incompatible = new Incompatible(cargo1, cargo2);
					this.incompatible.add(incompatible);
				}
			}
			// get node list of tanks
			NodeList tanksNodeList = doc.getElementsByTagName("tanks");
			Node tanksNode = tanksNodeList.item(0);
			Element tanksElement = (Element) tanksNode;
			this.numberOfTanks = Integer.parseInt(tanksElement.getAttribute("nb"));
			this.tanks = new Tanks[this.numberOfTanks];
			NodeList tankNodeList = tanksElement.getElementsByTagName("tank1");
			System.out.println("Length: " + tankNodeList.getLength());
			for(int i = 0; i < tankNodeList.getLength(); i++) {
				Node tankNode = tankNodeList.item(i);
				System.out.println("Current element: " + tankNode.getNodeName());
				if(tankNode.getNodeType() == Node.ELEMENT_NODE) {
					Element tankElemen = (Element) tankNode;
					int id = Integer.parseInt(tankElemen.getAttribute("id"));
					System.out.println("capa " + i + tankElemen.getAttribute("capa"));
					int capa = Integer.parseInt(tankElemen.getAttribute("capa"));
					System.out.println("x: " + i + " " + tankElemen.getAttribute("x"));
					int x = Integer.parseInt(tankElemen.getAttribute("x"));
					System.out.println("y: " + i + " " + tankElemen.getAttribute("y"));
					int y = Integer.parseInt(tankElemen.getAttribute("y"));
					int w = Integer.parseInt(tankElemen.getAttribute("w"));
					int h = Integer.parseInt(tankElemen.getAttribute("h"));
					NodeList imposiblecargosNodeList = tankElemen.getElementsByTagName("impossiblecargos");
					Node imposiblecargosNode = imposiblecargosNodeList.item(0);
					Element imposiblecargosElement = (Element) imposiblecargosNode;
					NodeList cargo1NodeList = imposiblecargosElement.getElementsByTagName("cargo");
					ArrayList<Integer> imposibleCargos = new ArrayList<Integer>();
					for(int j = 0; j < cargo1NodeList.getLength(); j++) {
						Node cargo1Node = cargo1NodeList.item(j);
						System.out.println("Curren element: " + cargo1Node.getNodeName());
						if(cargo1Node.getNodeType() == Node.ELEMENT_NODE) {
							Element idCargo1Element = (Element) cargo1Node;
							int idCargo1 = Integer.parseInt(idCargo1Element.getAttribute("id"));
							imposibleCargos.add(idCargo1);
						}
					}
					ArrayList<Integer> neighbour = new ArrayList<Integer>();
					NodeList neighbourNodeList = tankElemen.getElementsByTagName("neighbours");
					Node neighbourNode = neighbourNodeList.item(0);
					Element neighbourElement = (Element) neighbourNode;
					NodeList tank = neighbourElement.getElementsByTagName("tank");
					for(int j = 0; j < tank.getLength(); j++) {
						Node tank1Node = tank.item(j);
						System.out.println("Curren element: " + tank1Node.getNodeName());
						if(tank1Node.getNodeType() == Node.ELEMENT_NODE) {
							Element tank1Element = (Element) tank1Node;
							int idTank = Integer.parseInt(tank1Element.getAttribute("id"));
							neighbour.add(idTank);
						}
					}
					Tanks tanks = new Tanks(id, capa, x, y, w, h, imposibleCargos, neighbour);
					this.tanks[i] = tanks;
				}
			}
		} catch(Exception ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Function initialize library's variable and set domain for variable x.
	 */
	public void initLibraryVariable() {
		this.lsManager = new LocalSearchManager();
		this.constraints = new ConstraintSystem(this.lsManager);
		this.x = new VarIntLS[this.numberOfTanks];
		
		// domain of variable x
		for(int i = 0; i < this.x.length; i++) {
			this.x[i] = new VarIntLS(this.lsManager, 0, this.numberOfCargos);
		}
	}
	
	/**
	 * Function create First constrains in stateModel</br>
	 * <li> <b>First:</b> Volume to ship of cargo X <= amount of cargo X included in allocation
	 */
	public void createFirstConstraint() {
		int capacityOfTank[] = new int[this.numberOfTanks];
		for(int i = 0; i < this.numberOfTanks; i++) {
			capacityOfTank[i] = this.tanks[i].getCapa();
		}
		
		for(int i = 0; i < this.numberOfCargos + 1; i++) {
			IFunction fSum = new ConditionalSum(this.x, capacityOfTank, i);
			for(int j = 0; j < this.numberOfCargos; j++) {
				if(this.cargos[j].getID() == i) {
					this.constraints.post(new LessOrEqual(this.cargos[j].getVolume(), fSum));
				}
			}
		}
	}
	
	/**
	 * Function create Second constraints in statemodel </br>
	 * <li> <b>Second:</b> No tank can be allocated a cargo in its impossibleCargos
	 */
	public void createSecondConstraint() {
		for(int i = 0; i < this.numberOfTanks; i++) {
			ArrayList<Integer> impossibleCargos = this.tanks[i].getImposibleCargos();
			for(int j = 0; j < impossibleCargos.size(); j++) {
				this.constraints.post(new NotEqual(this.x[i], impossibleCargos.get(j)));
			}
		}
	}
	
	/**
	 * Function create third constraint in statemodel </br>
	 * <li> <b>Third:</b> No tank can be neighbors with a tank containing incompatible cargo
	 */
	public void createThirdConstraint() {
		for(int i = 0; i < this.numberOfTanks; i++) {
			ArrayList<Integer> neighborsOfTank = this.tanks[i].getNeighbour();
			
			for(int j = 0; j < neighborsOfTank.size(); j++) {
				for(int k = 0; k < this.numberOfTanks; k++) {
					if(this.tanks[k].getID() == neighborsOfTank.get(j)) {
						for(int h = 0; h < this.incompatible.size(); h++) {
							this.constraints.post(new Implicate(new IsEqual(this.x[i], this.incompatible.get(h).getCargoId1()), 
									new NotEqual(this.x[k], this.incompatible.get(h).getCargoId2())));
							this.constraints.post(new Implicate(new IsEqual(this.x[i], this.incompatible.get(h).getCargoId2()), 
									new NotEqual(this.x[k], this.incompatible.get(h).getCargoId1())));
						}
					}
				}
			}
		}
	}
	
	/**
	 * Function create model for problem. </br>
	 * <li> <b>First:</b> Volume to ship of cargo X <= amount of cargo X included in allocation
	 * <li> <b>Second:</b> No tank can be allocated a cargo in its impossibleCargos
	 * <li> <b>Third:</b> No tank can be neighbors with a tank containing incompatible cargo  
	 * <li> <b>Fourth:</b> Amount of cargos = amount of cargos in tank
	 */
	public void stateModel() {
		this.initLibraryVariable();
		this.createFirstConstraint();
		this.createSecondConstraint();
		this.createThirdConstraint();
		
		// close model
		this.lsManager.close();
	}
	
	/**
	 * Search function using tabu search in opencbo library
	 * 
	 * @param tabuLength	: Length of tabu list
	 * @param maxTime		: Max time tu solution problem
	 * @param maxIte		: Max iteration
	 * @param maxStable		: Max stable of algorithm
	 * 
	 */
	public VarIntLS[] tabuSearchLib(int tabuLength, int maxTime, int maxIte, int maxStable) {
		TabuSearch tb = new TabuSearch();
		tb.search(this.constraints, tabuLength, maxTime, maxIte, maxStable);
		return this.x;
	}

	/**
	 * Function print parameter of problem and solution of problem
	 * 
	 */
	public void print() {
		System.out.println("number of tanks: " + this.numberOfTanks);
		System.out.println("number of cargos: " + this.numberOfCargos);
		
		for(int i = 0; i < this.cargos.length; i++) {
			Cargos cargos = this.cargos[i];
			System.out.println("Cargos ID: " + cargos.getID() + ", Cargos Name: " + cargos.getName() + ", Volume: " + cargos.getVolume());
		}
		
		for(int i = 0; i < this.incompatible.size(); i++) {
			Incompatible incom = this.incompatible.get(i);
			System.out.println("CargosID1: " + incom.getCargoId1() + ", CargosID2: " + incom.getCargoId2());
		}
		
		for(int i = 0; i < tanks.length; i++) {
			Tanks tank = this.tanks[i];
			System.out.println("Tank ID: " + tank.getID() + ", Capa: " + tank.getCapa());
		}
	}
	
	/**
	 * Function print result of problem
	 */
	public void printResult() {
		System.out.println("Cargo: " + this.cargos[0].getID());
		for(int i = 0; i < this.numberOfTanks; i++) {
			System.out.println("Tank: " + (i + 1) + ", cargo: " + this.x[i].getValue());
		}
	}
	
	/**
	 * Function initialize first solution for problem<br>
	 * All variable will be assignment to zero
	 */
	public void initSolution() {
		for(int i = 0; i < this.x.length; i++) {
			this.x[i].setValuePropagate(0);
		}
	}
		
	/**
	 * My search using heuristic
	 * <li> Find variable most violation
	 * <li> Find most promissing value
	 * <li> Set value for variable most violation
	 * 
	 * @param iteration	: Max iteration
	 */
	public VarIntLS[] greedySearch(int iteration) {
		this.initSolution();
		System.out.println("Init state, Violation = " + this.constraints.violations());
		MinMaxSelector mms = new MinMaxSelector(this.constraints);
		ArrayList<Integer> list = new ArrayList<Integer>();
		int minViolation = Integer.MAX_VALUE;
		Random r = new Random();
		
		int it = 0;
		while(it < iteration && this.constraints.violations() > 0) {
			VarIntLS sel_x = mms.selectMostViolatedVariable();
			int sel_v = mms.selectMostPromissingValue(sel_x);
			sel_x.setValuePropagate(sel_v);
			
			int violation = this.constraints.violations(sel_x);
			if(violation < minViolation) {
				list.clear();
				list.add(sel_v);
			} 
			if(violation == minViolation) {
				list.add(sel_v);
			}
			
			sel_x.setValuePropagate(list.get(r.nextInt(list.size())));
			System.out.println("Step: " + it + ", Violation: " + this.constraints.violations());
			it++;
		}
		
		return this.x;
	}
	
	/**
	 * Search using simulated annealing</br> 
	 * for more information: http://katrinaeg.com/simulated-annealing.html
	 * 
	 * @param T		: T parameter in algorithm
	 * @param alpha	: alpha parameter using when update T paprameter
	 * @param T_min	: T_min parameter in algorithm
	 */
	public VarIntLS[] simulatedAnnealingSearch(double T, double alpha, double T_min, int maxStep) {
		initSolution();
		Random r = new Random();

		int it = 0;
		while(this.constraints.violations() > 0 && T > T_min) {
		//while(T > T_min) {
			System.out.println("Step: " + it + ", T = " + T + ", epsilon = " + (T - T_min) + ", Violation: " + this.constraints.violations()
					+ ", optimation value: " + optimationFunction());
			it++;
			for(int i = 0; i < 200; i++) {
				int index = r.nextInt(this.x.length);
				int oldValueOfIndex = this.x[index].getValue();
				int newValue = r.nextInt(this.numberOfCargos + 1);
				int oldViolation = this.constraints.violations();
				this.x[index].setValuePropagate(newValue);
				int newViolation = this.constraints.violations(); 
				if(newViolation < oldViolation) {
					// Ver 1: T = T * alpha;		
				} else if(isMoving(oldViolation, newViolation, T)) {
					// Ver 1: T = T * alpha;
				} else {
					// Ver 1: T = T * alpha;
					this.x[index].setValuePropagate(oldValueOfIndex);
				}
			}
			T = T * alpha;
		}
		
		return this.x;
	}
	
	/**
	 * Function sort tank not increment of capacity of the tank
	 */
	public void sortTank() {
		for(int i = 0; i < this.tanks.length - 1; i++) {
			for(int j = i + 1; j < this.tanks.length; j++) {
				if(this.tanks[i].getCapa() < this.tanks[j].getCapa()) {
					Tanks temp = this.tanks[i];
					this.tanks[i] = this.tanks[j];
					this.tanks[j] = temp;
				}
			}
		}
	}
	
	/**
	 * Function using sumulated anealing to optimation number of tank no carrie cargos<br>
	 * foreach tank = 0. Try to find solution with violation = 0 to other tank.
	 * 
	 * @param T			: Temperature parameter in simulated anealing algorithm
	 * @param alpha		: alpha parameter in simulated anealing algorithm
	 * @param T_min		: T_min parameter in simulated anealing algorithm
	 * @return			: Bester solution
	 */
	public VarIntLS[] simulatedAnealingSearchOptimation(double T, double alpha, double T_min, int maxIte) {
		Random r = new Random();
		double initT = T;
		VarIntLS bestSolution[] = new VarIntLS[this.x.length];
		double saveVolume = 0;

		boolean flag[] = new boolean[this.x.length];
		for(int i = 0; i < flag.length; i++) {
			flag[i] = true;
		}
		
		// sort tank
		sortTank();
		
		for(int i = 0; i < this.x.length; i++) {
			flag[i] = false;
			initSolution();
			System.out.println("Optimation Step: " + (i + 1));
			int loop = 0;
			while(this.constraints.violations() > 0 && loop < maxIte) {
				System.out.println("Step: " + loop + ", Violation: " + this.constraints.violations());
				for(int j = 0; j < 200; j++) {
					int index = r.nextInt(this.x.length);
					while(flag[index] == false) {
						index = r.nextInt(this.x.length);
					}
					int oldValueOfIndex = this.x[index].getValue();
					int newValueOfIndex = r.nextInt(this.numberOfCargos + 1);
					int oldViolation = this.constraints.violations();
					this.x[index].setValuePropagate(newValueOfIndex);
					int newViolation = this.constraints.violations();
					if(newViolation < oldViolation) {
						
					} else if(isMoving(oldViolation, newViolation, T)) {
						
					} else {
						this.x[index].setValuePropagate(oldValueOfIndex);
					}
				}
				T =  T * alpha;
				loop++;
			}
			T = initT;
			
			if(this.constraints.violations() > 0) {
				flag[i] = true;
			}
			if(this.constraints.violations() == 0) {
				//bestSolution = this.x;
				for(int j = 0; j < bestSolution.length; j++) {
					bestSolution[j] = new VarIntLS(this.lsManager, 0, this.numberOfCargos);
					bestSolution[j].setValue(this.x[j].getValue());
				}
			}
		}
		
		// Get save capacity
		for(int i = 0; i < bestSolution.length; i++) {
			if(bestSolution[i].getValue() == 0) {
				saveVolume += this.tanks[i].getCapa();
			}
		}
		
		System.out.println();
		System.out.println("Save capacity: " + saveVolume);

		for(int i = 0; i < bestSolution.length; i++) {
			this.x[i].setValuePropagate(bestSolution[i].getValue());
		}
		System.out.println("Violation: " + this.constraints.violations());

		return bestSolution;
	}
	
	/**
	 * function check is result a solution of problem
	 * 
	 * @param solution	: solution will be checked
	 * @return			: true if result is a solution, false otherwise
	 */
	public boolean testSolution(VarIntLS solution[]) {
		for(int i = 0; i < solution.length; i++) {
			this.x[i].setValuePropagate(solution[i].getValue());
		}
		
		if(this.constraints.violations() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function calculating optimation value of x variable<br>
	 * <li>	calculating number of tank not equal zero
	 *  
	 * @return	: optimation value
	 */
	public int optimationFunction() {
		int optimationValue = 0;
		for(int i = 0; i < this.x.length; i++) {
			if(this.x[i].getValue() != 0) {
				optimationValue++;
			}
		}

		return optimationValue;
	}
	
	/**
	 * Function calculating number of tank not zero in solution
	 * 
	 * @return	: Number of tank not containt any cargo
	 */
	public int numberOfTankNotEqualZero() {
		int numberOfTank = 0;
		for(int i = 0; i < this.x.length; i++) {
			if(this.x[i].getValue() != 0) {
				numberOfTank++;
			}
		}
		
		return numberOfTank;
	}
	
	/**
	 * Function random generation new solution
	 * 
	 * @return	: new solution
	 */
	public VarIntLS[] randomGenerationNeighbour() {
		Random r = new Random();
		VarIntLS randomSolution[] = new VarIntLS[this.numberOfTanks];
		int index = r.nextInt(this.numberOfTanks);
		for(int i = 0; i < this.numberOfTanks; i++) {
			randomSolution[i] = new VarIntLS(this.lsManager, 0, this.numberOfCargos);
			if(i == index) {
				randomSolution[index].setValue(r.nextInt(this.numberOfCargos + 1));
			} else {
				randomSolution[i] = this.x[i];
			}
		}
		
		return randomSolution;
	}
	
	/**
	 * Assignment to new solution from neighbour
	 * 
	 * @param neighbour	: Neighbour will be assiged
	 * @return			: Old solution
	 */
	public VarIntLS[] assignmentToNeighbour(VarIntLS neighbour[]) {
		VarIntLS oldSolution[] = new VarIntLS[this.numberOfTanks];
		
		for(int i = 0; i < this.numberOfTanks; i++) {
			oldSolution[i] = new VarIntLS(this.lsManager, 0, this.numberOfCargos);
			oldSolution[i].setValue(this.x[i].getValue());
			this.x[i].setValuePropagate(neighbour[i].getValue());
		}
		
		return oldSolution;
	}
	
	/**
	 * Function calculating moving to new solution from currence solution
	 * 
	 * @param oldViolation	: Old violation of currence solution
	 * @param newViolation	: New violation of new solution
	 * @param T				: Temprature parameter of silulated annealing
	 * @return				: true if moving and false if otherwise
	 */
	public boolean isMoving(int oldViolation, int newViolation, double T) {
		boolean moving;
		double probability = Math.exp(-(newViolation - oldViolation) / T); 
		double random = Math.random();
		
		if(random > probability) {
			moving = false;
		} else {
			moving = true;
		}
		
		return moving;
	}
	
	/**
	 * Function using generic algorithm to solve problem
	 * 
	 * @return	: solution of problem
	 */
	public VarIntLS[] ga(int numberOfChromose, int crossOverProbability, int mutationProbability) {
		//boolean flag[] = new boolean[100];
		boolean flag[] = new boolean[numberOfChromose];
		Random r = new Random();
		// Generating population with 100 chromose
		Population population[] = new Population[numberOfChromose];
		for(int i = 0; i < population.length; i++) {
			population[i] = new Population(this.numberOfTanks, this.numberOfCargos);
		}
		
		int numberOfCrossOver = (crossOverProbability / 100) * numberOfChromose / 2;
		int numberOfMutation = (mutationProbability / 100) * numberOfChromose;
		int sizeOfPopulationAfterCrossOverAndMutation = numberOfChromose + numberOfCrossOver + numberOfMutation;
		
		// sort population
		sortPopulation(population);

		assignmentToX(population[0].getX());
		int numberOfGeneration = 0;
		while(this.constraints.violations() > 0) {
			for(int i = 0; i < flag.length; i++) {
				flag[i] = false;
			}
			Population populationOfCrossOverAndMutation[] = new Population[numberOfCrossOver + numberOfMutation];
			// percent parent cross over
			for(int i = 0; i < numberOfCrossOver; i++) {
				int indexOfParent1 = r.nextInt(numberOfChromose);
				int indexOfParent2 = r.nextInt(numberOfChromose);
				while(flag[indexOfParent1] == true) {
					indexOfParent1 = r.nextInt(100);
				}
				while(flag[indexOfParent2] == true) {
					indexOfParent2 = r.nextInt(numberOfChromose);
				}
				populationOfCrossOverAndMutation[i] = crossOver(population[indexOfParent1], population[indexOfParent2]);
				flag[indexOfParent1] = true;
				flag[indexOfParent2] = true;
			}
			
			// percent parent mutation
			for(int i = numberOfCrossOver; i < numberOfCrossOver + numberOfMutation; i++) {
				int indexOfParent = r.nextInt(numberOfChromose);
				populationOfCrossOverAndMutation[i] = mutation(population[indexOfParent]);
			}
			for(int i = 0; i < populationOfCrossOverAndMutation.length; i++) {
				if(populationOfCrossOverAndMutation[i] == null) {
					System.out.println("NULL " + i);
				}
			}
			Population preNextPopulation[] = new Population[sizeOfPopulationAfterCrossOverAndMutation];
			for(int i = 0; i < numberOfChromose; i++) {
				preNextPopulation[i] = population[i];
			}
			for(int i = numberOfChromose; i < sizeOfPopulationAfterCrossOverAndMutation; i++) {
				preNextPopulation[i] = populationOfCrossOverAndMutation[i - numberOfChromose];
			}
			// select next population
			population = nextPopulation(preNextPopulation);
			sortPopulation(population);
			assignmentToX(population[0].getX());

			numberOfGeneration++;
			System.out.println("Generation: " + numberOfGeneration + ", Violation: " + this.constraints.violations());
		}
		
		return this.x;
	}
	
	/**
	 * Function assignment the int variable to VarIntLS variable
	 * 
	 * @param x	: variable will be assignment
	 */
	public void assignmentToX(int x[]) {
		for(int i = 0; i < this.numberOfTanks; i++) {
			this.x[i].setValuePropagate(x[i]);
		}
	}
	
	/**
	 * Function sort population to decreasing of violation
	 * 
	 * @param population	: population
	 */
	public void sortPopulation(Population population[]) {
		int firstViolation;
		int secondViolation;
		
		for(int i = 0; i < population.length - 1; i++) {
			assignmentToX(population[i].getX());
			firstViolation = this.constraints.violations();
			for(int j = i + 1; j < population.length; j++) {
				assignmentToX(population[j].getX());
				secondViolation = this.constraints.violations();
				if(firstViolation > secondViolation) {
					Population temp = population[i];
					population[i] = population[j];
					population[j] = temp;
				}
			}
		}
	}
	
	public Population[] nextPopulation(Population population[]) {
		Population next[] = new Population[100];
		
		sortPopulation(population);
		for(int i = 0; i < 100; i++) {
			next[i] = population[i];
		}
		
		return next;
	}
	/**
	 * Function cross over of two parent
	 * 
	 * @param parent1	: parent 1
	 * @param parent2	: parent 2
	 * @return			: Chirdren after cross over two parent
	 */
	public Population crossOver(Population parent1, Population parent2) {
		Population children= null;
		int tankOfParent1[] = parent1.getX();
		int tankOfParent2[] = parent2.getX();
		int tankOfChildern[] = new int[this.numberOfTanks];
		Random r = new Random();
		int index1 = r.nextInt(this.numberOfTanks);
		int index2 = r.nextInt(this.numberOfTanks);
		
		if(index2 < index1) {
			int temp = index1;
			index1 = index2;
			index2 = temp;
		}
		
		for(int i = 0; i < this.numberOfTanks; i++) {
			if(i < index1) {
				tankOfChildern[i] = tankOfParent1[i];
			} else if(i >= index1 && i <= index2) {
				tankOfChildern[i] = tankOfParent2[i];
			} else {
				tankOfChildern[i] = tankOfParent1[i];
			}
		}
		
		children = new Population(tankOfChildern);
		return children;
	}
	
	/**
	 * Function mutation population
	 * 
	 * @param parent	: parent
	 * @return			: children after mutation
	 */
	public Population mutation(Population parent) {
		Population children = null;
		Random r = new Random();
		int iteration = 1 + r.nextInt(this.numberOfTanks);
		
		assignmentToX(parent.getX());
		MinMaxSelector mms = new MinMaxSelector(this.constraints);
		int it = 0;
		
		while(it < iteration) {
			VarIntLS sel_x = mms.selectMostViolatedVariable();
			int sel_v = mms.selectMostPromissingValue(sel_x);
			sel_x.setValuePropagate(sel_v);
			it++;
		}
		
		int x[] = new int[this.numberOfTanks];
		for(int i = 0; i < this.numberOfTanks; i++) {
			x[i] = this.x[i].getValue();
		}
		children = new Population(x);
		return children;
	}
	
	/**
	 * Function find optimation solution for problem, using simulated anealing
	 * 
	 * @param T			: T parameter in simulated anealing
	 * @param T_min		: T_min parameter in simulated anealing
	 * @param alpha		: alpha parameter in simulated anealing
	 * @return			: optimation solution
	 */
	public VarIntLS[] optimationSolutionSimulatedAnealingSearch(double T, double T_min, double alpha) {
		VarIntLS bestSolution[] = null;
		VarIntLS solution[];
		VarIntLS neighBourOfSolution[];
		int optimationValueOfSolution;
		int optimationValueOfNeighbours;
		
		int it = 0;
		solution = tabuSearchLib(10, 300, 100000, 100);
		while(T > T_min) {
			neighBourOfSolution = randomGenerationNeighbour();
			VarIntLS oldSolution[] = assignmentToNeighbour(neighBourOfSolution);
			
			while(this.constraints.violations() > 0) {
				oldSolution = assignmentToNeighbour(oldSolution);
				neighBourOfSolution = randomGenerationNeighbour();
				oldSolution = assignmentToNeighbour(neighBourOfSolution);
			}
			
			optimationValueOfNeighbours = optimationFunction(neighBourOfSolution);
			optimationValueOfSolution = optimationFunction(solution);
			if(optimationValueOfNeighbours < optimationValueOfSolution) {
				bestSolution = neighBourOfSolution;
			} else if(isMoving(optimationValueOfSolution, optimationValueOfNeighbours, T)) {
				bestSolution = neighBourOfSolution;
			} else {
				bestSolution = solution;
			}
			solution = bestSolution;
			T = T * alpha;
			it++;
			System.out.println("Step " + it + ", Best Optimation: " + optimationFunction(bestSolution));
		}

		return bestSolution;
	}
	
	/**
	 * Function calculating the optimation value of one solution
	 * 
	 * @param solution	: solution will be calculated optimation value
	 * @return	: optimation value
	 */
	public int optimationFunction(VarIntLS solution[]) {
		int optimationValue = 0;
		for(int i = 0; i < solution.length; i++) {
			if(this.x[i].getValue() != 0) {
				optimationValue++;
			}
		}
		
		return optimationValue;
	}
	
	/**
	 * print current solution
	 * 
	 * @param solution	: solution will be printed
	 */
	public void printSolution(VarIntLS solution[]) {
		for(int i = 0; i < solution.length; i++) {
			System.out.println("Tank " + this.tanks[i].getID() + ", Cargo: " + solution[i].getValue());
		}
	}
	
	public VarIntLS[] backTrackingSearch(int index, int optimationValue) {
		initSolution();
		int currentOptimationValue = 0;
		
		for(int value = 0; value < this.numberOfCargos + 1; value++) {
			System.out.println("Searching... Best optimation value: " + this.constraints.violations());
			this.x[index].setValuePropagate(value);
			if(index == this.x.length - 1) {
				if(this.constraints.violations() == 0) {
					currentOptimationValue = optimationFunction();
					if(currentOptimationValue < optimationValue) {
						optimationValue = currentOptimationValue;
					}
				}
			} else {
				backTrackingSearch(index + 1, optimationValue);
			}
		}
		return this.x;
	}
}