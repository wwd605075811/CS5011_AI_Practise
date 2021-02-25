/**
 * This represents the coordinate data structure (row, column)
 * and prints the required output
 * @author at258 anonymous
 * use this class as the node class and adding helper functions in this class
 */
public class Coord {
	private int r;//row
	private int c;//column
	private Coord father = null;
	private int costToGoal = Integer.MAX_VALUE;
	private int pathCost = -1;
	private int AStartCost = -1;
	//Consider Alphabet Tie-breaking strategy
	private int direction = -1;
	//consider Alphabet Deep First Tie-breaking strategy
	private int deep = -1;
	/*
    How robots moves in the map
    public final int SE = 1;    //(+1,+1)
    public final int E  = 2;    //(+0,+1)
    public final int NE = 3;    //(-1,+0)
    public final int NW = 4;    //(-1,-1)
    public final int W  = 5;    //(+0,-1)
    public final int SW = 6;    //(+1,+0)
    */
	/**
	 * onstruction method
	 * @param row : row
	 * @param column : column
	 */
	public Coord(int row,int column) {
		r=row;
		c=column;
	}

	/**
	 * overrides toString
	 * @return : (r,c)
	 */
	public String toString() {
		return "(" + r + "," + c + ")";
	}

	/**
	 * overrides toString
	 * @param a : the single: '1' use in BestF, '2' use in A*
	 * @return '1':(r,c):costToGoal; '2':(r,c):AStarCost
	 */
	public String toString(int a) {
		if (a == 1) {
			return "(" + r + "," + c + ")" + ":" + (double)this.costToGoal;
		} else if (a == 2) {
			return "(" + r + "," + c + ")" + ":" + (double)this.AStartCost;
		} else {
			System.err.println("You are using the wrong toString in Coord Class");
			return "Error";
		}
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}

	/**
	 * override the equals to check whether two nodes is in the same place
	 * @param o : node(Coord)
	 * @return : boolean
	 */
	@Override
	public boolean equals(Object o) {
		Coord coord=(Coord) o;
		if(coord.r==r && coord.c==c) {
			return true;
		}
		return false;
	}

	/**
	 * For the current node, let the robot move SE. And store the move single in
	 * variable nextNode.direction
	 * @return : next movable node
	 */
	public Coord expandSE(){	//1
		Coord SE = new Coord(this.r + 1, this.c + 1);
		SE.setDirection(1);
		return SE;
	}

	public Coord expandE(){		//2
		Coord E = new Coord(this.r, this.c + 1);
		E.setDirection(2);
		return E;
	}

	public Coord expandNE(){	//3
		Coord NE = new Coord(this.r - 1, this.c);
		NE.setDirection(3);
		return NE;
	}

	public Coord expandNW(){	//4
		Coord NW = new Coord(this.r - 1, this.c - 1);
		NW.setDirection(4);
		return NW;
	}

	public Coord expandW(){		//5
		Coord W = new Coord(this.r, this.c - 1);
		W.setDirection(5);
		return W;
	}

	public Coord expandSW(){	//6
		Coord SW = new Coord(this.r + 1, this.c);
		SW.setDirection(6);
		return SW;
	}

	/**
	 * judge whether the next node is East of the current node.
	 * @param nextNode : next node
	 * @return : boolean
	 */
	public boolean isE(Coord nextNode){
		if((nextNode.getR() - this.getR() == 0) && (nextNode.getC() - this.getC() == 1)){
			return true;
		}
		return false;
	}

	public boolean isW(Coord nextNode){
		if((nextNode.getR() - this.getR() == 0) && (nextNode.getC() - this.getC() == -1)){
			return true;
		}
		return false;
	}

	/**
	 * calculate the cost from current node to goal.
	 * @param goal : goal
	 * @return : cost from current node to goal. use Manhattan distance.
	 * TODO : Consider whether one formula can be used to handle the distance calculation under the rotated coordinates.
	 */
	public int calculateCost(Coord goal){
		int rState = -this.getR();
		int rGoal = -goal.getR();
		int diffR = rState - rGoal;
		int diffC = this.getC() - goal.getC();
		if(diffR * diffC > 0) {     // same sign
			return Math.abs(diffR) + Math.abs(diffC);
		} else {
			return Math.max(Math.abs(diffR), Math.abs(diffC));
		}
	}

	public Coord getFather(){
		return this.father;
	}

	public void setFather(Coord node){
		this.father = node;
	}

	public int getDeep() {
		return deep;
	}

	public void setDeep(int deep) {
		this.deep = deep;
	}

	public int getCostToGoal() {
		return costToGoal;
	}

	public void setCostToGoal(int costToGoal) {
		this.costToGoal = costToGoal;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getPathCost() {
		return pathCost;
	}

	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}

	public int getAStartCost() {
		return AStartCost;
	}

	public void setAStartCost(int aStartCost) {
		AStartCost = aStartCost;
	}
}
