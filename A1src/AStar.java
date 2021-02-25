import java.awt.desktop.SystemEventListener;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This AStar algorithm
 * @author anonymous
 */
public class AStar extends Algorithms {
    public PriorityQueue<Coord> frontier;      //priority Queue
    public Queue<Coord> explored;

    /**
     * Construction method
     * @param map : map
     * @param start : the location of person
     * @param goal : the location of safety place
     */
    public AStar(Map map, Coord start, Coord goal) {
        this.map = map.getMap();
        this.start = start;
        this.start.setDeep(0);
        this.goal = goal;
        this.boundaryRow = map.getMap().length;
        this.boundaryColumn = map.getMap()[0].length;
        explored = new LinkedList<Coord>();
        frontier = new PriorityQueue<Coord>(1, new Comparator<Coord>() {
            @Override
            public int compare(Coord c1, Coord c2) {
                if (c1.getAStartCost() == c2.getAStartCost()) {
                    //Because we didn't consider Tie-breaking strategy in A*, we just return 0. Let the Priority Queue
                    //use random way when facing the same cost node. Why return 0 is random? Because Priority Queue use
                    //the heap to sort. (just like the random)
                    return 0;
                } else {
                    return c1.getAStartCost() - c2.getAStartCost();
                }
            }
        });
    }

    /**
     * A* Algorithm
     */
    @Override
    public void SearchAlgorithm() {
        AStarSearch();
    }

    /**
     * A* search Algorithm
     * @return 0 is succeed and -1(255) is failure
     */
    public void AStarSearch(){
        this.start.setCostToGoal(this.start.calculateCost(this.goal));
        this.start.setPathCost(0);
        this.start.setAStartCost(this.start.getPathCost() + this.start.getCostToGoal());
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
            //output the Frontier in right order
            //Because I want to output the right order, I need another Priority Queue to poll the nodes.
            PriorityQueue<Coord> printFrontier;      //priority Queue why initial Capacity is 1
            printFrontier = new PriorityQueue<Coord>(1, new Comparator<Coord>() {
                @Override
                public int compare(Coord c1, Coord c2) {
                    if (c1.getAStartCost() == c2.getAStartCost()) {
                        return 0;
                    } else {
                        return c1.getAStartCost() - c2.getAStartCost();
                    }
                }
            });
            for(Coord x: frontier){
                printFrontier.add(x);
            }
            System.out.print("[");
            while (!printFrontier.isEmpty()) {
                System.out.print(printFrontier.poll().toString(2) + ", ");
            }
            // delete one more ", "
            System.out.println("\b" + "\b" + "]");
            //pop the node and add it in explored
            stateNode = frontier.poll();
            explored.add(stateNode);
            if(goal.equals(stateNode)){
                printResult(stateNode, explored);
                System.exit(0);
            } else {
                expandAStar(stateNode, map, frontier, explored, this.goal);
            }
        }//end while
    }//end A*Search()
}
