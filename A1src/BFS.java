import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS Algorithm
 * @author anonymous
 */
public class BFS extends Algorithms {
    public Queue<Coord> frontier;
    public Queue<Coord> explored;

    /**
     * Construction method
     * @param map : map
     * @param start : the location of person
     * @param goal : the location of safety place
     */
    public BFS(Map map, Coord start, Coord goal) {
        this.map = map.getMap();
        this.start = start;
        this.goal = goal;
        this.boundaryRow = map.getMap().length;
        this.boundaryColumn = map.getMap()[0].length;
        frontier = new LinkedList<Coord>();
        explored = new LinkedList<Coord>();
    }

    /**
     * BFS Algorithm
     */
    @Override
    public void SearchAlgorithm() {
        bfsSearch();
    }

    /**
     * BFS search Algorithm
     * @return 0 is succeed and -1(255) is failure
     */
    public void bfsSearch(){
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
            // output the Frontier
            System.out.print("[");
            for(Coord x: frontier){
                System.out.print(x + ", ");
            }
            //delete one more ", "
            System.out.println("\b" + "\b" + "]");
            //pop the node and add it in explored
            stateNode = frontier.poll();
            explored.add(stateNode);
            if(goal.equals(stateNode)){
                printResult(stateNode, explored);
                System.exit(0);
            } else {
                expandBFS(stateNode, map, frontier, explored);
            }
        }//end while
    }//end bfsSearch()
}
