package part4;
import mains.Grid;
import mains.Puzzle;
import org.logicng.formulas.CFalse;
import part2.ProceduralABC;

import java.util.*;

public class HintABC {

    public void Hint() {
    }

    /**
     * This function will give 5 level hints to help people to finish the puzzle, while teaching them
     * technique to use in the next time.
     * Level 0 is the easiest technique, while level 5 is the hardest square to fill
     * @param puzzle puzzle
     * @param grid current grid which user do not know how to do next
     * @return succeed give hints of not
     */
    public static boolean giveHints(Puzzle puzzle, Grid grid) {
        int hintsFlag = -1;
        if (hitsLevel0(puzzle, grid))
            hintsFlag = 0;
        else if (hitsLevel1(puzzle,grid))
            hintsFlag = 1;
        else if (hitsLevel2(puzzle,grid))
            hintsFlag = 2;
        else if (hitsLevel3(puzzle,grid))
            hintsFlag = 3;
        else if (hitsLevel4(puzzle,grid))
            hintsFlag = 4;
        else if (hitsLevel5(puzzle,grid))
            hintsFlag = 5;
        if (hintsFlag != 0) {
            System.out.println("From the hints level" + hintsFlag +".");
            return true;
        }
        return false;
    }

    /**
     * The level 0 is give different corner hints
     * @param puzzle puzzle
     * @param grid grid
     * @return succeed give hints of not
     */
    public static boolean hitsLevel0(Puzzle puzzle, Grid grid) {
        //different Corners hints
        Grid hintGridLevel0 = new Grid(ProceduralABC.differentCorners(puzzle, grid));
        Vector<position> diff = new Vector<position>();
        findDifferent(diff, hintGridLevel0, grid);
        // give the level 0 hints
        List<String> list  =   new ArrayList<String>();
        String element = null;
        if (!diff.isEmpty()) {
            System.out.println("Tips: this is the hints from 'different Corners'");
            System.out.println("You can check those position to filled letters");
            for (int i = 0; i < diff.size(); i++) {
                element = String.valueOf(diff.get(i).row) + " " +  String.valueOf(diff.get(i).col);
                list.add(element);
                element = null;
            }
            // delete the repeat hints
            delRepeat(list);
            return true;
        }
        return false;
    }

    /**
     * The level 1 is give Only Place For Letter Row and Col hints
     * @param puzzle puzzle
     * @param grid grid
     * @return succeed give hints of not
     */
    public static boolean hitsLevel1(Puzzle puzzle, Grid grid) {
        //hits Only Place For Letter Row and Col
        Grid hintGridLevel1Col = new Grid(ProceduralABC.onlyPlaceForLetterCol(puzzle, grid));
        Grid hintGridLevel1Row = new Grid(ProceduralABC.onlyPlaceForLetterRow(puzzle, grid));
        Vector<position> diff = new Vector<position>();
        findDifferent(diff, hintGridLevel1Row, grid);
        findDifferent(diff, hintGridLevel1Col, grid);
        // give the level 1 hints
        List<String> list  =   new ArrayList<String>();
        String element = null;
        if (!diff.isEmpty()) {
            System.out.println("Tips: this is the hints from 'Only Place For Letter Row and Col'");
            System.out.println("You can check those position to filled letters");
            for (int i = 0; i < diff.size(); i++) {
                element = String.valueOf(diff.get(i).row) + " " +  String.valueOf(diff.get(i).col);
                list.add(element);
                element = null;
            }
            // delete the repeat hints
            delRepeat(list);
            return true;
        }
        return false;
    }

    /**
     * The level 2 is give fill In Blanks Col
     * @param puzzle puzzle
     * @param grid grid
     * @return succeed give hints of not
     */
    public static boolean hitsLevel2(Puzzle puzzle, Grid grid) {
        //fillIn Blanks Col
        Grid hintGridLevel2Col = new Grid(ProceduralABC.fillInBlanksCol(puzzle, grid));
        Grid hintGridLevel2Row = new Grid(ProceduralABC.fillInBlanksRow(puzzle, grid));
        Vector<position> diff = new Vector<position>();
        findDifferent(diff, hintGridLevel2Row, grid);
        findDifferent(diff, hintGridLevel2Col, grid);
        // give the level 2 hints
        List<String> list  =   new ArrayList<String>();
        String element = null;
        if (!diff.isEmpty()) {
            System.out.println("Tips: this is the hints from 'fill In Blanks Row and Col'");
            System.out.println("You can check those position to filled letters");
            for (int i = 0; i < diff.size(); i++) {
                element = String.valueOf(diff.get(i).row) + " " +  String.valueOf(diff.get(i).col);
                list.add(element);
                element = null;
            }
            // delete the repeat hints
            delRepeat(list);
            return true;
        }
        return false;
    }

    /**
     * The level 3 is give common Clues
     * @param puzzle puzzle
     * @param grid grid
     * @return succeed give hints of not
     */
    public static boolean hitsLevel3(Puzzle puzzle, Grid grid) {
        //common Clues
        Grid hintGridLevel3 = new Grid(ProceduralABC.commonClues(puzzle, grid));
        Vector<position> diff = new Vector<position>();
        findDifferent(diff, hintGridLevel3, grid);
        // give the level 3 hints
        List<String> list  =   new ArrayList<String>();
        String element = null;
        if (!diff.isEmpty()) {
            System.out.println("Tips: this is the hints from 'common clues'");
            System.out.println("You can check those position to filled letters");
            for (int i = 0; i < diff.size(); i++) {
                element = String.valueOf(diff.get(i).row) + " " +  String.valueOf(diff.get(i).col);
                list.add(element);
                element = null;
            }
            // delete the repeat hints
            delRepeat(list);
            return true;
        }
        return false;
    }

    /**
     * The level 4 is to give the Possibility of remaining squares
     * @param puzzle puzzle
     * @param grid grid
     * @return succeed give hints of not
     */
    public static boolean hitsLevel4(Puzzle puzzle, Grid grid) {
        //give possibility
        Grid hintGridLevel4 = new Grid(grid);
        Vector<position> empty = new Vector<position>();
        findEmpty(empty,grid);
        setPossibility(empty,puzzle,hintGridLevel4);
        if (!empty.isEmpty()) {
            System.out.println("Tips: this is the hints from 'possibility'");
            System.out.println("You can check those position with possibility to filled the square");
            for (int i = 0; i < empty.size(); i++) {
                System.out.print("row:" + empty.get(i).row + " col:" + empty.get(i).col + " possibility:");
                for (int j = 0; j < empty.get(i).possibility.length; j++) {
                    System.out.print(empty.get(i).possibility[j]);
                }
                System.out.println();
            }
            return true;
        }
        return false;
    }

    /**
     * If none of the first 4 levels work, use this function to compare the puzzle,
     * then give next step
     * @param puzzle puzzle
     * @param grid current grid
     * @return succeed give hint or not
     */
    public static boolean hitsLevel5(Puzzle puzzle, Grid grid) {
        // TODO give the next step solution
        System.out.println("Tips: this is the level5 hints");
        return true;
    }

    /**
     * set possibility tot each empty spare
     * @param empty the list of each empty square
     * @param puzzle puzzle
     * @param grid current grid
     */
    public static void setPossibility(Vector<position> empty, Puzzle puzzle, Grid grid) {
        position term = new position();
        for (int i = 0; i < empty.size(); i++) {
            empty.get(i).possibility = getPossibility(empty.get(i), puzzle, grid);
        }
    }

    /**
     * calculate the possibility of one square
     * @param pos the position
     * @param puzzle puzzle
     * @param grid current grid
     * @return an array of possibility. eg. possibility = {'c', 'x'}
     */
    public static char[] getPossibility(position pos, Puzzle puzzle, Grid grid) {
        // check the row
        // when the letter will never appear again, change this letter into '#'.
        char [] checkPossibility = puzzle.letters;
        int xCount = 0;
        for (int i = 0; i < grid.chars.length; i++) {
            for (int j = 0; j < puzzle.letters.length; j++) {
                if (grid.chars[i][pos.col] == '_') continue;
                if (grid.chars[i][pos.col] == 'x') {
                    xCount ++;
                    if (xCount > 1) {
                        checkPossibility[puzzle.letters.length - 1] = '#';
                    }
                }

                if (grid.chars[i][pos.col] == puzzle.letters[j]) {
                    checkPossibility[j] = '#';
                }
            }
        }
        // check the col
        for (int i = 0; i < grid.chars.length; i++) {
            for (int j = 0; j < puzzle.letters.length; j++) {
                if (grid.chars[pos.row][i] == '_') continue;
                if (grid.chars[pos.row][i] == 'x') {
                    xCount ++;
                    if (xCount > 1) {
                        checkPossibility[puzzle.letters.length - 1] = '#';
                    }
                }

                if (grid.chars[pos.row][i] == puzzle.letters[j]) {
                    checkPossibility[j] = '#';
                }
            }
        }
        int countPossibility = 0;
        for (int i = 0; i < checkPossibility.length; i++) {
            if (checkPossibility[i] != '#') {
                countPossibility ++;
            }
        }
        char [] possibility = new char[countPossibility];
        int index = 0;
        for (int i = 0; i < checkPossibility.length; i++) {
            if (checkPossibility[i] != '#') {
                possibility[index] = checkPossibility[i];
                index ++;
            }
        }
        return possibility;
    }

    /**
     * find the different between two Grids
     * @param diff store the position of different
     * @param hint the grid already filled some next step
     * @param grid current grid
     */
    public static void findDifferent(Vector<position> diff, Grid hint, Grid grid) {
        position term = new position();
        for (int i = 0; i < grid.chars.length; i++) {
            for (int j = 0; j < grid.chars[0].length; j++) {
                if (grid.chars[i][j] != hint.chars[i][j]) {
                    term.row = i;
                    term.col = j;
                    diff.add(term);
                }
            }
        }
    }

    /**
     * find the empty between two Grids
     * @param diff store the position of empty
     * @param grid current grid
     */
    public static void findEmpty(Vector<position> diff, Grid grid) {
        position term = new position();
        for (int i = 0; i < grid.chars.length; i++) {
            for (int j = 0; j < grid.chars[0].length; j++) {
                if (grid.chars[i][j] == '_') {
                    term.row = i;
                    term.col = j;
                    diff.add(term);
                }
            }
        }
    }

    /**
     * use HashSet to delete the repeat element in the List
     * print the hints to user
     * @param list store all the hints(which may repeat)
     */
    public static void delRepeat(List<String> list){
        Set set = new HashSet();
        List newList = new  ArrayList();
        for (String cd:list) {
            if(set.add(cd)){
                newList.add(cd);
            }
        }
        System.out.println( "position：" + newList);
    }
}
