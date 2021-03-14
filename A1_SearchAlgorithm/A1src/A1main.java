/**
 * This Class is the main Class for running the Search Algorithms
 * run with:
 * java A1main <BFS> <ConfID>
 * java A1main <DFS> <ConfID>
 * java A1main <BestF> <ConfID> <null/Alphabet/AlphabetDeepFirst>
 * java A1main <AStar> <ConfID>
 * Or run Test Class in Package test
 * @author at258 anonymous
 */
public class A1main {

	public static void main(String[] args) {
		//run search algorithm
		//when running BestF algorithms, you can choose which Tie-breaking strategies, which use the arg[2].
		//null == using Random Tie-breaking strategy
		//Alphabet == using alphabetical Tie-breaking strategy
		//AlphabetDeepFirst == using alphabetical and deep first Tie-breaking strategy
		if(args.length > 1) {
			Conf conf = Conf.valueOf(args[1]);
			if (args.length == 3) {
				runSearch(args[0],conf.getMap(),conf.getP(),conf.getS(), args[2]);
			} else {
				runSearch(args[0],conf.getMap(),conf.getP(),conf.getS(), null);
			}
		}
	}

	/**
	 *
	 * @param algo : The name of Algorithm
	 * @param map : The map
	 * @param start : The person's location
	 * @param goal : The safety place(goal)
	 * @param conf : Tie-breaking strategy
	 */
	private static void runSearch(String algo, Map map, Coord start, Coord goal, String conf) {
		switch (algo) {
			case "BFS": {    //run BFS
				BFS bfs = new BFS(map, start, goal);
				bfs.SearchAlgorithm();
				break;
			}
			case "DFS": {    //run DFS
				DFS dfs = new DFS(map, start, goal);
				dfs.SearchAlgorithm();
				break;
			}
			case "BestF": {  //run BestF
				BestF bestF;
				if(conf != null) {	//with Tie-breaking strategy
					bestF = new BestF(map, start, goal, conf);
				} else {	//without Tie-breaking strategy
					bestF = new BestF(map, start, goal);
				}
				bestF.SearchAlgorithm();
				break;
			}
			case "AStar": {    //run AStar
				AStar aStar = new AStar(map, start, goal);
				aStar.SearchAlgorithm();
				break;
			}
		} //end switch
	}//end function
}