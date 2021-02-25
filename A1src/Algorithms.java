import java.util.*;

/**
 * Abstract class of tool functions, which are used in Search Algorithms
 * @author anonymous
 */
public abstract class Algorithms {
    public int [][] map;
    public Coord start;
    public Coord goal;
    public int boundaryRow;     //the boundary of row
    public int boundaryColumn;  //the boundary of column

    /**
     * abstract function of each search algorithm
     */
    public abstract void SearchAlgorithm();

    /**
     * BFS algorithm: This function is the step of expanding nodes
     * @param stateNode : the node that we ate visiting
     * @param map : map
     * @param frontier : Frontier (Queue)
     * @param explored : Explored (nodes has been visited)
     */
    public void expandBFS(Coord stateNode, int[][] map, Queue<Coord> frontier, Queue<Coord> explored){
        Queue<Coord> exploreNodes = new LinkedList<Coord>();
        //There are three situations.If robot are currently on node '2', it can only expand to E and W.
        //If robot are currently on node '1', it must be error.
        //If robot are currently on node '0', it can only expand to six directions.
        if(map[stateNode.getR()][stateNode.getC()] == 2){
            if(isNodeILegal(stateNode, stateNode.expandE())){      //2
                exploreNodes.add(stateNode.expandE());
            }
            if(isNodeILegal(stateNode, stateNode.expandW())){      //5
                exploreNodes.add(stateNode.expandW());
            }
        } else if(map[stateNode.getR()][stateNode.getC()] == 1){
            System.err.println("Error! robot is in the wrong place!");
        } else {
            // Use the Tie-breaking strategy
            if(isNodeILegal(stateNode, stateNode.expandSE())){     //1
                exploreNodes.add(stateNode.expandSE());
            }
            if(isNodeILegal(stateNode, stateNode.expandE())){      //2
                exploreNodes.add(stateNode.expandE());
            }
            if(isNodeILegal(stateNode, stateNode.expandNE())){     //3
                exploreNodes.add(stateNode.expandNE());
            }
            if(isNodeILegal(stateNode, stateNode.expandNW())){     //4
                exploreNodes.add(stateNode.expandNW());
            }
            if(isNodeILegal(stateNode, stateNode.expandW())){      //5
                exploreNodes.add(stateNode.expandW());
            }
            if(isNodeILegal(stateNode, stateNode.expandSW())){     //6
                exploreNodes.add(stateNode.expandSW());
            }
        }
        //add Movable nodes into frontier
        for(Coord node: exploreNodes){
            if(!isExistQueue(node, frontier) && !isExistQueue(node, explored)){
                node.setFather(stateNode);
                frontier.add(node);
            }
        }
    }

    /**
     * DFS algorithm: This function is the step of expanding nodes.
     * I didn't combine BFS and DFS into one function,because they use different data structure
     * @param stateNode : the node that we ate visiting
     * @param map : map
     * @param frontier : Frontier (Stack)
     * @param explored : Explored (nodes has been visited)
     */
    public void expandDFS(Coord stateNode, int[][] map, Stack<Coord> frontier, Queue<Coord> explored){
        Queue<Coord> exploreNodes = new LinkedList<Coord>();
        // almost the same as expandBFS
        if(map[stateNode.getR()][stateNode.getC()] == 2){
            if(isNodeILegal(stateNode, stateNode.expandW())){      //5
                exploreNodes.add(stateNode.expandW());
            }
            if(isNodeILegal(stateNode, stateNode.expandE())){      //2
                exploreNodes.add(stateNode.expandE());
            }
        } else if(map[stateNode.getR()][stateNode.getC()] == 1){
            System.err.println("Error! robot is in the wrong place!");
        } else {
            //Because the traversal order of the stack and the queue is reversed,
            //the node will be expanded retrogradely.
            if(isNodeILegal(stateNode, stateNode.expandSW())){     //6
                exploreNodes.add(stateNode.expandSW());
            }
            if(isNodeILegal(stateNode, stateNode.expandW())){      //5
                exploreNodes.add(stateNode.expandW());
            }
            if(isNodeILegal(stateNode, stateNode.expandNW())){     //4
                exploreNodes.add(stateNode.expandNW());
            }
            if(isNodeILegal(stateNode, stateNode.expandNE())){     //3
                exploreNodes.add(stateNode.expandNE());
            }
            if(isNodeILegal(stateNode, stateNode.expandE())){      //2
                exploreNodes.add(stateNode.expandE());
            }
            if(isNodeILegal(stateNode, stateNode.expandSE())){     //1
                exploreNodes.add(stateNode.expandSE());
            }
        }
        //add Movable nodes into frontier
        for(Coord node: exploreNodes){
            if(!isExistStack(node, frontier) && !isExistQueue(node, explored)){
                node.setFather(stateNode);
                frontier.push(node);
            }
        }
    }

    /**
     * BestF algorithm: This function is the step of expanding nodes.
     * @param stateNode : the node that we ate visiting
     * @param map : map
     * @param frontier : Frontier (Priority Queue)
     * @param explored : Explored (nodes has been visited)
     * @param goal : the node that we need to arrive
     */
    public void expandBestF(Coord stateNode, int[][] map, PriorityQueue<Coord> frontier, Queue<Coord> explored, Coord goal){
        Queue<Coord> exploreNodes = new LinkedList<Coord>();
        if(map[stateNode.getR()][stateNode.getC()] == 2){
            if(isNodeILegal(stateNode, stateNode.expandE())){      //2
                exploreNodes.add(stateNode.expandE());
            }
            if(isNodeILegal(stateNode, stateNode.expandW())){      //5
                exploreNodes.add(stateNode.expandW());
            }
        } else if(map[stateNode.getR()][stateNode.getC()] == 1){
            System.err.println("Error! robot is in the wrong place!");
        } else {
            if(isNodeILegal(stateNode, stateNode.expandSE())){     //1
                exploreNodes.add(stateNode.expandSE());
            }
            if(isNodeILegal(stateNode, stateNode.expandE())){      //2
                exploreNodes.add(stateNode.expandE());
            }
            if(isNodeILegal(stateNode, stateNode.expandNE())){     //3
                exploreNodes.add(stateNode.expandNE());
            }
            if(isNodeILegal(stateNode, stateNode.expandNW())){     //4
                exploreNodes.add(stateNode.expandNW());
            }
            if(isNodeILegal(stateNode, stateNode.expandW())){      //5
                exploreNodes.add(stateNode.expandW());
            }
            if(isNodeILegal(stateNode, stateNode.expandSW())){     //6
                exploreNodes.add(stateNode.expandSW());
            }
        }
        //add Movable nodes into frontier. Before adding nodes, we need to set Attributes for nodes.
        for(Coord node: exploreNodes){
            if(!isExistQueue(node, frontier) && !isExistQueue(node, explored)){
                node.setFather(stateNode);
                node.setCostToGoal(node.calculateCost(goal));
                node.setDeep(stateNode.getDeep() + 1);
                frontier.add(node);
            }
        }
    }

    /**
     * AStar algorithm: This function is the step of expanding nodes.
     * @param stateNode : the node that we ate visiting
     * @param map : map
     * @param frontier : Frontier (Priority Queue)
     * @param explored : Explored (nodes has been visited)
     * @param goal : the node that we need to arrive
     */
    public void expandAStar(Coord stateNode, int[][] map, PriorityQueue<Coord> frontier, Queue<Coord> explored, Coord goal){
        Queue<Coord> exploreNodes = new LinkedList<Coord>();
        if(map[stateNode.getR()][stateNode.getC()] == 2){
            if(isNodeILegal(stateNode, stateNode.expandE())){      //2
                exploreNodes.add(stateNode.expandE());
            }
            if(isNodeILegal(stateNode, stateNode.expandW())){      //5
                exploreNodes.add(stateNode.expandW());
            }
        } else if(map[stateNode.getR()][stateNode.getC()] == 1){
            System.err.println("Error! robot is in the wrong place!");
        } else {
            if(isNodeILegal(stateNode, stateNode.expandSE())){     //1
                exploreNodes.add(stateNode.expandSE());
            }
            if(isNodeILegal(stateNode, stateNode.expandE())){      //2
                exploreNodes.add(stateNode.expandE());
            }
            if(isNodeILegal(stateNode, stateNode.expandNE())){     //3
                exploreNodes.add(stateNode.expandNE());
            }
            if(isNodeILegal(stateNode, stateNode.expandNW())){     //4
                exploreNodes.add(stateNode.expandNW());
            }
            if(isNodeILegal(stateNode, stateNode.expandW())){      //5
                exploreNodes.add(stateNode.expandW());
            }
            if(isNodeILegal(stateNode, stateNode.expandSW())){     //6
                exploreNodes.add(stateNode.expandSW());
            }
        }
        //add movable nodes into frontier. If the node already exist in frontier with higher cost,
        //replace that node.
        for(Coord node: exploreNodes){
            if(!isExistQueue(node, frontier) && !isExistQueue(node, explored)){
                node.setFather(stateNode);
                node.setCostToGoal(node.calculateCost(goal));
                node.setPathCost(stateNode.getPathCost() + 1);
                node.setAStartCost(node.getPathCost() + node.getCostToGoal());
                frontier.add(node);
            } else if (isExistQueue(node, frontier)) {    //If node in the frontier, replace low cost
                node.setFather(stateNode);
                node.setCostToGoal(node.calculateCost(goal));
                node.setPathCost(stateNode.getPathCost() + 1);
                node.setAStartCost(node.getPathCost() + node.getCostToGoal());
                if (replaceExistQueue(node, frontier)) {
                   frontier.add(node);
                }
            } else if (isExistQueue(node, explored)) {
                //If we do not consider the explored, we may poll up a certain key node of the optimal
                // path in advance, so that the end goal will not be found through this node.
                // On the other hand, we should not give up the judgment of nodes in explored of AStar Algorithm.
                node.setFather(stateNode);
                node.setCostToGoal(node.calculateCost(goal));
                node.setPathCost(stateNode.getPathCost() + 1);
                node.setAStartCost(node.getPathCost() + node.getCostToGoal());
                replaceExistExplored(node, explored, frontier);
            }
        }
    }


    /**
     * In the step of expanding, check the node is legal or not.
     * @param stateNode : the node that we ate visiting
     * @param node : the node that we ate expanding
     * @return boolean of legal or not
     */
    public boolean isNodeILegal(Coord stateNode, Coord node){
        //firstly, check the robot is in the map
        if(node.getR() >= 0 && node.getR() <= boundaryRow - 1 && node.getC() >= 0 && node.getC() <= boundaryColumn - 1) {
            switch (map[node.getR()][node.getC()]) { // check the robot is in the legal map places.
                case 0 : {
                    return true;
                }
                case 1 : {
                    return false;
                }
                case 2 : { //If the robot is the '2' node. Check whether the robot has moved from the E or W.
                    if(stateNode.isE(node) || stateNode.isW(node)){
                        return true;
                    }
                    return false;
                }
                default: return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Check the Queue whether contain this node.
     * @param node : node
     * @param queue : queue
     * @return : contain or not contain
     */
    public boolean isExistQueue(Coord node, Queue<Coord> queue){
        for (Coord x : queue) {
            if(x.getR() == node.getR() && x.getC() == node.getC()){
                return true;
            }
        }
        return false;
    }

    /**
     * Check the Stack whether contain this node.
     * @param node : node
     * @param stack : queue
     * @return : contain or not contain
     */
    public boolean isExistStack(Coord node, Stack<Coord> stack){
        for (Coord x : stack) {
            if(x.getR() == node.getR() && x.getC() == node.getC()){
                return true;
            }
        }
        return false;
    }

    /**
     * Print the result(route, cost and explored nodes)
     * @param stateNode : the node we are visiting
     * @param explored : the nodes has been visited
     */
    public void printResult(Coord stateNode, Queue<Coord> explored){
        double cost = 0.0;
        //because we use backwards way to find the father node, I use a stack to make it in right order.
        Stack<Coord> route = new Stack<Coord>();
        while (stateNode.getFather() != null){
            route.push(stateNode);
            stateNode = stateNode.getFather();
            cost ++;
        }
        route.push(stateNode);
        while (!route.isEmpty()){
            System.out.print(route.pop());
        }
        System.out.println();
        System.out.println(cost);
        System.out.println(explored.size());
    }

    /**
     * This function is to replace node in the frontier
     * @param node state node
     * @param queue the frontier
     */
    public boolean replaceExistQueue(Coord node, Queue<Coord> queue){
        // IF we just remove a element in the while loop,while adding element into the same queue. it will be wrong.
        // Exception in thread "Thread-1" java.util.ConcurrentModificationException
        // So I exchange the value of those nodes. Also, we can use Iterator.remove().
        // Solution 1: https://stackoverflow.com/questions/6293813/yet-another-concurrentmodificationexception-question?rq=1
        // Solution 2: https://blog.csdn.net/jdk_wangtaida/article/details/87450334
        for(Iterator<Coord> it = queue.iterator(); it.hasNext();) {
            Coord s = it.next();
            if(s.getR() == node.getR() && s.getC() == node.getC()){
                if(node.getAStartCost() < s.getAStartCost()) {
                    //queue.add(node);
                    it.remove();
                    return true;
                } else {
                    return false;
                }
            }
        }//end for
        return false;
    }//end function

    public void replaceExistExplored(Coord node, Queue<Coord> explored, Queue<Coord> frontier){
        // remember to use Iterator in Java set
        for(Iterator<Coord> it = explored.iterator(); it.hasNext();) {
            Coord s = it.next();
            if(s.getR() == node.getR() && s.getC() == node.getC()){
                if(node.getAStartCost() < s.getAStartCost()) {
                    frontier.add(node);
                    it.remove();
                }
            }
        }//end for
    }//end function
}
