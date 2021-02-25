import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * BestF algorithm. Considering three Tie-breaking strategies:(Random, Alphabet and AlphabetDeepFirst)
 * @author anonymous
 */
public class BestF extends Algorithms {
    public PriorityQueue<Coord> frontier;   //priority Queue
    public Queue<Coord> explored;
    public String conf; //Tie-breaking strategy

    /**
     * Construction method
     * @param map : map
     * @param start : the location of person
     * @param goal : the location of safety place
     */
    public BestF(Map map, Coord start, Coord goal) {
        this.map = map.getMap();
        this.start = start;
        this.start.setDeep(0);
        this.goal = goal;
        this.boundaryRow = map.getMap().length;
        this.boundaryColumn = map.getMap()[0].length;
        this.conf = null;
        explored = new LinkedList<Coord>();
        frontier = new PriorityQueue<Coord>(1, new Comparator<Coord>() {
            @Override
            public int compare(Coord c1, Coord c2) {
                if (c1.getCostToGoal() == c2.getCostToGoal()) {
                    //If the two node's cost is same, I will ignore the order. (Random Tie-breaking strategy)
                    return 0;
                } else {
                    return c1.getCostToGoal() - c2.getCostToGoal();
                }
            }
        });
    }

    /**
     * Construction method, consider different tie-breaking strategies
     * @param map : map
     * @param start : the location of person
     * @param goal : the location of safety place
     * @param conf : Tie-breaking strategy
     */
    public BestF(Map map, Coord start, Coord goal, String conf) {
        if (conf.equals("Alphabet")) {
            this.map = map.getMap();
            this.start = start;
            this.start.setDeep(0);
            this.goal = goal;
            this.boundaryRow = map.getMap().length;
            this.boundaryColumn = map.getMap()[0].length;
            this.conf = conf;
            explored = new LinkedList<Coord>();
            frontier = new PriorityQueue<Coord>(1, new Comparator<Coord>() {
                @Override
                public int compare(Coord c1, Coord c2) {
                    if (c1.getCostToGoal() == c2.getCostToGoal()) {
                        //If the two node's cost is same, I will use Alphabet order. (Alphabet Tie-breaking strategy)
                        return c1.getDirection() - c2.getDirection();
                    } else {
                        return c1.getCostToGoal() - c2.getCostToGoal();
                    }
                }
            });
        } else if (conf.equals("AlphabetDeepFirst")) {
            this.map = map.getMap();
            this.start = start;
            this.start.setDeep(0);
            this.goal = goal;
            this.boundaryRow = map.getMap().length;
            this.boundaryColumn = map.getMap()[0].length;
            this.conf = conf;
            explored = new LinkedList<Coord>();
            frontier = new PriorityQueue<Coord>(1, new Comparator<Coord>() {
                @Override
                public int compare(Coord c1, Coord c2) {    //(Alphabet Deep First Tie-breaking strategy)
                    if (c1.getCostToGoal() == c2.getCostToGoal() && c1.getDeep() == c2.getDeep()) {
                        //If the two node's cost and deep is same, use Alphabet order
                        return c1.getDirection() - c2.getDirection();
                    } else if (c1.getCostToGoal() == c2.getCostToGoal() && c1.getDeep() != c2.getDeep()) {
                        //If the two node's cost is same, but deep is different, use Alphabet Deep first order
                        return -(c1.getDeep() - c2.getDeep());
                    } else {
                        return c1.getCostToGoal() - c2.getCostToGoal();
                    }
                }
            });
        } else {
            System.err.println("Sorry, you are using wrong Tie-breaking strategy.");
        }
    }

    /**
     * BestF Algorithm
     */
    @Override
    public void SearchAlgorithm() {
        bestFSearch();
    }

    /**
     * BestF search Algorithm
     * @return 0 is succeed and -1(255) is failure
     */
    public void bestFSearch(){
        this.start.setCostToGoal(this.start.calculateCost(this.goal));
        this.start.setDeep(0);
        Coord intialNode = this.start;
        Coord stateNode = intialNode;
        frontier.add(intialNode);
        //explored<Queue> is empty
        while (true){
            if(frontier.isEmpty()){
                System.out.println("fail");
                System.out.println(explored.size());
                System.exit(-1);
            }
            // output Frontier in right order
            // because different Tie-breaking strategies use different Priority Queue, we creat two different print function
            if(conf == null) {
                printBestFFrontier(frontier);
            } else if (conf.equals("Alphabet")) {
                printBestFAFrontier(frontier);
            } else if (conf.equals("AlphabetDeepFirst")) {
                printBestFADFFrontier(frontier);
            }
            //pop the node and add it in explored
            stateNode = frontier.poll();
            explored.add(stateNode);
            if(goal.equals(stateNode)){
                printResult(stateNode, explored);
                System.exit(0);
            } else {
                expandBestF(stateNode, map, frontier, explored, this.goal);
            }
        }//end while
    }//end bestFSearch()

    /**
     * Random Tie-breaking strategy print Frontier
     * @param frontier : frontier
     */
    public void printBestFFrontier(PriorityQueue<Coord> frontier) {
        PriorityQueue<Coord> printFrontier;      //priority Queue
        printFrontier = new PriorityQueue<Coord>(1, new Comparator<Coord>() {
            @Override
            public int compare(Coord c1, Coord c2) {
                if (c1.getCostToGoal() == c2.getCostToGoal()) {
                    return 0;
                } else {
                    return c1.getCostToGoal() - c2.getCostToGoal();
                }
            }
        });
        for(Coord x: frontier){
            printFrontier.add(x);
        }
        System.out.print("[");
        while (!printFrontier.isEmpty()) {
            System.out.print(printFrontier.poll().toString(1) + ", ");
        }
        //delete one more ", "
        System.out.println("\b" + "\b" + "]");
    }

    /**
     * Alphabet Tie-breaking strategy print Frontier
     * @param frontier : frontier
     */
    public void printBestFAFrontier(PriorityQueue<Coord> frontier) {
        PriorityQueue<Coord> printFrontier;      //priority Queue
        printFrontier = new PriorityQueue<Coord>(1, new Comparator<Coord>() {
            @Override
            public int compare(Coord c1, Coord c2) {
                if (c1.getCostToGoal() == c2.getCostToGoal()) {
                    return c1.getDirection() - c2.getDirection();
                } else {
                    return c1.getCostToGoal() - c2.getCostToGoal();
                }
            }
        });
        for(Coord x: frontier){
            printFrontier.add(x);
        }
        System.out.print("[");
        while (!printFrontier.isEmpty()) {
            System.out.print(printFrontier.poll().toString(1) + ", ");
        }
        //delete one more ", "
        System.out.println("\b" + "\b" + "]");
    }

    /**
     * Alphabet Deep First Tie-breaking strategy print Frontier
     * @param frontier : frontier
     */
    public void printBestFADFFrontier(PriorityQueue<Coord> frontier) {
        PriorityQueue<Coord> printFrontier;      //priority Queue
        printFrontier = new PriorityQueue<Coord>(1, new Comparator<Coord>() {
            @Override
            public int compare(Coord c1, Coord c2) {
                if (c1.getCostToGoal() == c2.getCostToGoal() && c1.getDeep() == c2.getDeep()) {
                    return c1.getDirection() - c2.getDirection();
                } else if (c1.getCostToGoal() == c2.getCostToGoal() && c1.getDeep() != c2.getDeep()) {
                    return -(c1.getDeep() - c2.getDeep());
                } else {
                    return c1.getCostToGoal() - c2.getCostToGoal();
                }
            }
        });
        for(Coord x: frontier){
            printFrontier.add(x);
        }
        System.out.print("[");
        while (!printFrontier.isEmpty()) {
            System.out.print(printFrontier.poll().toString(1) + ", ");
        }
        //delete one more ", "
        System.out.println("\b" + "\b" + "]");
    }
}
