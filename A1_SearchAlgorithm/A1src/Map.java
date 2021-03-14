/**
 * This class contains the maps to be used for evaluation
 * @author at258
 *
 * Map.java provides the coastal maps (MAP) to be used for evaluation,
 * 5 in total, defined as Enum Type6. This class also includes a few test
 * maps that are used for discussion in class (TMAP). TMAP00 is the map
 * presented in Figure 1. Please do not modify the maps in this class
 * but you can add more if you wish.
 */

public enum Map{
	//************************TEST MAPS as discussed in lectures ********************   
	TMAP00(new int [][] { //TMAP00 is the map in the spec
		     {0,0,0,0,0,0},
		    {0,0,2,0,0,0},
		   {0,0,2,0,0,0},
		  {0,1,1,1,1,0},
		 {0,0,0,0,0,0},
		{0,0,0,1,0,0}
	}),
	TMAP01(new int [][] {
		   {0,0,0,0},
		  {0,0,0,0},
		 {0,0,0,0},
		{0,0,0,0},
	}),

	//************************MAPS for evaluation ********************
	MAP0(new int [][] {
		     {0,0,0,0,0,0},
		    {0,0,0,0,0,0},
		   {0,0,0,0,0,0},
		  {0,0,0,0,0,0},
		 {0,0,0,0,0,0},
		{0,0,0,0,0,0}
	}),
	MAP1(new int [][] {
		    {0,0,0,0,0},
		   {0,0,1,0,0},
		  {0,0,2,0,0},
		 {0,0,1,0,0},
		{0,0,0,0,0},
	}),
	MAP2(new int [][] {
		     {0,0,0,0,0,0},
		    {0,1,1,1,0,0},
		   {0,1,0,1,0,0},
		  {0,1,1,1,0,0},
		 {0,0,0,0,0,0},
		{0,0,2,2,2,0}
	}),
	MAP3(new int [][] {
		     {0,0,0,0,0,0},
		    {0,2,0,0,1,0},
		   {0,0,1,1,1,0},
		  {0,0,0,0,1,0},
		 {0,0,0,0,1,0},
		{0,0,0,0,1,0}
	}),
	MAP4(new int [][] {
		     {0,0,0,0,0,0,0},
		    {0,1,1,1,1,1,1},
		   {0,0,0,0,0,0,0},
		  {0,0,0,0,1,1,0},
		 {0,0,0,0,0,1,0},
		{0,2,2,2,2,2,0},
       {0,0,0,0,0,0,0}
	})
	;

	private final int[][] map; 

	Map(int[][] map){
		this.map=map;
	}

	public int[][] getMap() {
		return map;
	}
}
