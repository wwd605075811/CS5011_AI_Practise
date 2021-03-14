package part2;

import mains.Grid;
import mains.Puzzle;

import java.util.Vector;

public class ProceduralABC {

    public ProceduralABC() {
    }

    /**
     * If there is only one unfilled, which is the letter. Then fill it.
     *
     * @param puzzle puzzle
     * @param grid   origin grid
     * @return new_grid, can not return grid, which will change the data.
     */
    public static Grid onlyPlaceForLetterCol(Puzzle puzzle, Grid grid) {
        // alloc memory to start
        Grid new_grid = new Grid(grid);
        int unfilledChar_row = 0;
        int unfilledChar_col = 0;
        char[] allSymbols = new char[puzzle.symbols.length + 1];
        for (int i = 0; i < allSymbols.length - 1; i++) {
            allSymbols[i] = puzzle.symbols[i];
        }
        allSymbols[allSymbols.length - 1] = puzzle.unfilledChar;
        int[] checkSymbols = new int[puzzle.symbols.length + 1];
        for (int i = 0; i < checkSymbols.length; i++) {
            checkSymbols[i] = 0;
        }

        // find and change
        for (int row = 0; row < new_grid.chars.length; row++) {
            for (int i = 0; i < new_grid.chars[row].length; i++) {
                for (int j = 0; j < allSymbols.length; j++) { // count every letter's number
                    if (new_grid.chars[i][row] == allSymbols[j]) {
                        checkSymbols[j]++;
                        if (j == checkSymbols.length - 1) { // when get the blank, remember the row and col
                            unfilledChar_row = i;
                            unfilledChar_col = row;
                        }
                    }
                }
            }// end for col
            // firstly, check whether there is only one blank
            if (checkSymbols[checkSymbols.length - 1] == 1) {
                // Then check whether there are only two x
                if (checkSymbols[checkSymbols.length - 2] == puzzle.size() - puzzle.letters.length) {
                    // if so, change that blank into the missing letter
                    for (int indexCheck = 0; indexCheck < checkSymbols.length; indexCheck++) {
                        if (checkSymbols[indexCheck] == 0) {
                            new_grid.chars[unfilledChar_row][unfilledChar_col] = puzzle.symbols[indexCheck];
                        }
                    }
                }
            }
            // reset all the count array and number
            unfilledChar_row = 0;
            unfilledChar_col = 0;
            for (int indexCheck = 0; indexCheck < checkSymbols.length; indexCheck++) {
                checkSymbols[indexCheck] = 0;
            }
        }// end for row
        return new_grid; // only there so it returns some kind of result
    }

    public static Grid onlyPlaceForLetterRow(Puzzle puzzle, Grid grid) {
        // alloc memory to start
        Grid new_grid = new Grid(grid);
        int unfilledChar_row = 0;
        int unfilledChar_col = 0;
        char[] allSymbols = new char[puzzle.symbols.length + 1];
        for (int i = 0; i < allSymbols.length - 1; i++) {
            allSymbols[i] = puzzle.symbols[i];
        }
        allSymbols[allSymbols.length - 1] = puzzle.unfilledChar;
        int[] checkSymbols = new int[puzzle.symbols.length + 1];
        for (int i = 0; i < checkSymbols.length; i++) {
            checkSymbols[i] = 0;
        }

        // find and change
        for (int row = 0; row < new_grid.chars.length; row++) {
            for (int i = 0; i < new_grid.chars[row].length; i++) {
                for (int j = 0; j < allSymbols.length; j++) { // count every letter's number
                    if (new_grid.chars[row][i] == allSymbols[j]) {
                        checkSymbols[j]++;
                        if (j == checkSymbols.length - 1) { // when get the blank, remember the row and col
                            unfilledChar_row = row;
                            unfilledChar_col = i;
                        }
                    }
                }
            }// end for col
            // firstly, check whether there is only one blank
            if (checkSymbols[checkSymbols.length - 1] == 1) {
                // Then check whether there are only two x
                if (checkSymbols[checkSymbols.length - 2] == puzzle.size() - puzzle.letters.length) {
                    // if so, change that blank into the missing letter
                    for (int indexCheck = 0; indexCheck < checkSymbols.length; indexCheck++) {
                        if (checkSymbols[indexCheck] == 0) {
                            new_grid.chars[unfilledChar_row][unfilledChar_col] = puzzle.symbols[indexCheck];
                        }
                    }
                }
            }
            // reset all the count array and number
            unfilledChar_row = 0;
            unfilledChar_col = 0;
            for (int indexCheck = 0; indexCheck < checkSymbols.length; indexCheck++) {
                checkSymbols[indexCheck] = 0;
            }
        }// end for row
        return new_grid; // only there so it returns some kind of result
    }

    /**
     * If all the letter was filled in one line, fill other space with x
     *
     * @param puzzle puzzle
     * @param grid   origin grid
     * @return new_grid
     */
    public static Grid fillInBlanksCol(Puzzle puzzle, Grid grid) {
        // alloc memory to start
        Grid new_grid = new Grid(grid);
        int[] unfilledBlank_row = new int[2];
        int[] unfilledBlank_col = new int[2];
        int unfilledIndex = 0;
        char[] allSymbols = new char[puzzle.symbols.length + 1];
        for (int i = 0; i < allSymbols.length - 1; i++) {
            allSymbols[i] = puzzle.symbols[i];
        }
        allSymbols[allSymbols.length - 1] = puzzle.unfilledChar;
        int[] checkSymbols = new int[puzzle.symbols.length + 1];
        for (int i = 0; i < checkSymbols.length; i++) {
            checkSymbols[i] = 0;
        }

        // find and fill with blank
        for (int row = 0; row < new_grid.chars.length; row++) {
            for (int i = 0; i < new_grid.chars[row].length; i++) {
                for (int j = 0; j < allSymbols.length; j++) { // count every letter's number
                    if (new_grid.chars[i][row] == allSymbols[j]) {
                        checkSymbols[j]++;
                        if (j == checkSymbols.length - 1) { // when get the blank, remember the row and col
                            unfilledBlank_row[unfilledIndex] = i;
                            unfilledBlank_col[unfilledIndex] = row;
                            if (unfilledIndex < 1) {
                                unfilledIndex++;
                            }
                        }
                    }
                }
            }//end for allSymbols
            // firstly, check whether there is only tow unfilled
            if (checkSymbols[checkSymbols.length - 1] == 2) {
                // Then check whether the number of blank is zero
                if (checkSymbols[checkSymbols.length - 2] == 0) {
                    // if so, change that unfilled into the blank
                    for (int blankIndex = 0; blankIndex < unfilledBlank_row.length; blankIndex++) {
                        new_grid.chars[unfilledBlank_row[blankIndex]][unfilledBlank_col[blankIndex]] = puzzle.blankSymbol;
                    }
                }
            }
            // check whether there is only one unfilled
            if (checkSymbols[checkSymbols.length - 1] == 1) {
                // Then check whether the number of blank is zero
                if (checkSymbols[checkSymbols.length - 2] == 1) {
                    // if so, change that unfilled into the blank
                    new_grid.chars[unfilledBlank_row[0]][unfilledBlank_col[0]] = puzzle.blankSymbol;
                }
            }
            // reset all the count array and number
            for (int blankIndex = 0; blankIndex < unfilledBlank_row.length; blankIndex++) {
                unfilledBlank_row[blankIndex] = 0;
                unfilledBlank_col[blankIndex] = 0;
            }
            for (int indexCheck = 0; indexCheck < checkSymbols.length; indexCheck++) {
                checkSymbols[indexCheck] = 0;
            }
            unfilledIndex = 0;
        }//end for row
        return new_grid; // only there so it returns some kind of result
    }

    public static Grid fillInBlanksRow(Puzzle puzzle, Grid grid) {
        // alloc memory to start
        Grid new_grid = new Grid(grid);
        int[] unfilledBlank_row = new int[2];
        int[] unfilledBlank_col = new int[2];
        int unfilledIndex = 0;
        char[] allSymbols = new char[puzzle.symbols.length + 1];
        for (int i = 0; i < allSymbols.length - 1; i++) {
            allSymbols[i] = puzzle.symbols[i];
        }
        allSymbols[allSymbols.length - 1] = puzzle.unfilledChar;
        int[] checkSymbols = new int[puzzle.symbols.length + 1];
        for (int i = 0; i < checkSymbols.length; i++) {
            checkSymbols[i] = 0;
        }

        // find the fill with blank
        for (int row = 0; row < new_grid.chars.length; row++) {
            for (int i = 0; i < new_grid.chars[row].length; i++) {
                for (int j = 0; j < allSymbols.length; j++) { // count every letter's number
                    if (new_grid.chars[row][i] == allSymbols[j]) {
                        checkSymbols[j]++;
                        if (j == checkSymbols.length - 1) { // when get the blank, remember the row and col
                            unfilledBlank_row[unfilledIndex] = row;
                            unfilledBlank_col[unfilledIndex] = i;
                            if (unfilledIndex < 1) {
                                unfilledIndex++;
                            }
                        }
                    }
                }
            }// end for col
            // firstly, check whether there is only tow unfilled
            if (checkSymbols[checkSymbols.length - 1] == 2) {
                // Then check whether the number of blank is zero
                if (checkSymbols[checkSymbols.length - 2] == 0) {
                    // if so, change that unfilled into the blank
                    for (int blankIndex = 0; blankIndex < unfilledBlank_row.length; blankIndex++) {
                        new_grid.chars[unfilledBlank_row[blankIndex]][unfilledBlank_col[blankIndex]] = puzzle.blankSymbol;
                    }
                }
            }
            // check whether there is only one unfilled
            if (checkSymbols[checkSymbols.length - 1] == 1) {
                // Then check whether the number of blank is zero
                if (checkSymbols[checkSymbols.length - 2] == 1) {
                    // if so, change that unfilled into the blank
                    new_grid.chars[unfilledBlank_row[0]][unfilledBlank_col[0]] = puzzle.blankSymbol;
                }
            }
            // reset all the count array and number
            for (int blankIndex = 0; blankIndex < unfilledBlank_row.length; blankIndex++) {
                unfilledBlank_row[blankIndex] = 0;
                unfilledBlank_col[blankIndex] = 0;
            }
            for (int indexCheck = 0; indexCheck < checkSymbols.length; indexCheck++) {
                checkSymbols[indexCheck] = 0;
            }
            unfilledIndex = 0;
        }// end for row
        return new_grid; // only there so it returns some kind of result
    }

    /**
     * when the corner are the different letter, fill that corner with x
     *
     * @param puzzle puzzle
     * @param grid   origin grid
     * @return new_grid
     */
    public static Grid differentCorners(Puzzle puzzle, Grid grid) {
        //alloc memory to start
        Grid new_grid = new Grid(grid);
        // apply for the corner array
        char left_top_corner[] = new char[2];
        char top_right_corner[] = new char[2];
        char right_bottom_corner[] = new char[2];
        char left_bottom_corner[] = new char[2];

        // set number into corner array
        int topSize = puzzle.size();
        left_top_corner[0] = puzzle.left[0];
        left_top_corner[1] = puzzle.top[0];
        top_right_corner[0] = puzzle.top[topSize - 1];
        top_right_corner[1] = puzzle.right[0];
        right_bottom_corner[0] = puzzle.right[topSize - 1];
        right_bottom_corner[1] = puzzle.bottom[topSize - 1];
        left_bottom_corner[0] = puzzle.left[topSize - 1];
        left_bottom_corner[1] = puzzle.bottom[0];

        //judge whether the corner is different. Set 'x' into that corner when the letter is different
        if (left_top_corner[0] != left_top_corner[1]) {
            new_grid.chars[0][0] = puzzle.blankSymbol;
        }
        if (top_right_corner[0] != top_right_corner[1]) {
            new_grid.chars[0][topSize - 1] = puzzle.blankSymbol;
        }
        if (right_bottom_corner[0] != right_bottom_corner[1]) {
            new_grid.chars[topSize - 1][topSize - 1] = puzzle.blankSymbol;
        }
        if (left_bottom_corner[0] != left_bottom_corner[1]) {
            new_grid.chars[topSize - 1][0] = puzzle.blankSymbol;
        }
        return new_grid; // only there so it returns some kind of result
    }

    /**
     * In each clue, if there are letters which number is 1, then fill corresponding space with this letter
     *
     * @param puzzle puzzle
     * @param grid   origin grid
     * @return new_gird
     */
    public static Grid commonClues(Puzzle puzzle, Grid grid) {
        // alloc memory to start
        Grid new_grid = new Grid(grid);

        // check every direction of clues and change the space
        new_grid = topCommonClues(puzzle, new_grid);
        new_grid = leftCommonClues(puzzle, new_grid);
        new_grid = rightCommonClues(puzzle, new_grid);
        new_grid = bottomCommonClues(puzzle, new_grid);
        return new_grid; // only there so it returns some kind of result
    }

    /**
     * In top clue, if there are letters which number is 1, then fill corresponding space with this letter
     *
     * @param puzzle   puzzle
     * @param new_grid the copy grid of origin grid
     * @return the grid which has been modified
     */
    public static Grid topCommonClues(Puzzle puzzle, Grid new_grid) {
        // alloc memory to start
        int[] count_letter = new int[puzzle.letters.length];
        Vector<Character> onlyChar = new Vector<Character>();
        for (int i = 0; i < count_letter.length; i++) {
            count_letter[i] = 0;
        }

        // count the number of letter in the clue
        for (int i = 0; i < puzzle.top.length; i++) {
            for (int j = 0; j < puzzle.letters.length; j++) {
                if (puzzle.top[i] == puzzle.letters[j]) {
                    count_letter[j]++;
                    continue;
                }
            }
        }

        // find the letter. And fill the square below it.
        for (int i = 0; i < count_letter.length; i++) {
            if (count_letter[i] == 1) {
                onlyChar.add(puzzle.letters[i]);
            }
        }
        for (int i = 0; i < onlyChar.size(); i++) {
            for (int j = 0; j < puzzle.top.length; j++) {
                if (onlyChar.get(i) == puzzle.top[j]) {
                    new_grid.chars[0][j] = onlyChar.get(i);
                }
            }
        }
        return new_grid;
    }

    public static Grid leftCommonClues(Puzzle puzzle, Grid new_grid) {
        int[] count_letter = new int[puzzle.letters.length];
        Vector<Character> onlyChar = new Vector<Character>();
        for (int i = 0; i < count_letter.length; i++) {
            count_letter[i] = 0;
        }

        // count the number of letter in the clue
        for (int i = 0; i < puzzle.left.length; i++) {
            for (int j = 0; j < puzzle.letters.length; j++) {
                if (puzzle.left[i] == puzzle.letters[j]) {
                    count_letter[j]++;
                    continue;
                }
            }
        }

        // find the letter. And fill the square right to it.
        for (int i = 0; i < count_letter.length; i++) {
            if (count_letter[i] == 1) {
                onlyChar.add(puzzle.letters[i]);
            }
        }
        for (int i = 0; i < onlyChar.size(); i++) {
            for (int j = 0; j < puzzle.left.length; j++) {
                if (onlyChar.get(i) == puzzle.left[j]) {
                    new_grid.chars[j][0] = onlyChar.get(i);
                }
            }
        }
        return new_grid;
    }

    public static Grid rightCommonClues(Puzzle puzzle, Grid new_grid) {
        // alloc memory to start
        int[] count_letter = new int[puzzle.letters.length];
        Vector<Character> onlyChar = new Vector<Character>();
        for (int i = 0; i < count_letter.length; i++) {
            count_letter[i] = 0;
        }

        // count the number of letter in the clue
        for (int i = 0; i < puzzle.right.length; i++) {
            for (int j = 0; j < puzzle.letters.length; j++) {
                if (puzzle.right[i] == puzzle.letters[j]) {
                    count_letter[j]++;
                    continue;
                }
            }
        }

        // find the letter. And fill the square left to it.
        for (int i = 0; i < count_letter.length; i++) {
            if (count_letter[i] == 1) {
                onlyChar.add(puzzle.letters[i]);
            }
        }
        for (int i = 0; i < onlyChar.size(); i++) {
            for (int j = 0; j < puzzle.right.length; j++) {
                if (onlyChar.get(i) == puzzle.right[j]) {
                    new_grid.chars[j][puzzle.top.length - 1] = onlyChar.get(i);
                }
            }
        }
        return new_grid;
    }

    public static Grid bottomCommonClues(Puzzle puzzle, Grid new_grid) {
        // alloc memory to start
        int[] count_letter = new int[puzzle.letters.length];
        Vector<Character> onlyChar = new Vector<Character>();
        for (int i = 0; i < count_letter.length; i++) {
            count_letter[i] = 0;
        }

        // count the number of letter in the clue
        for (int i = 0; i < puzzle.bottom.length; i++) {
            for (int j = 0; j < puzzle.letters.length; j++) {
                if (puzzle.bottom[i] == puzzle.letters[j]) {
                    count_letter[j]++;
                    continue;
                }
            }
        }

        // find the letter. And fill the square above it.
        for (int i = 0; i < count_letter.length; i++) {
            if (count_letter[i] == 1) {
                onlyChar.add(puzzle.letters[i]);
            }
        }
        for (int i = 0; i < onlyChar.size(); i++) {
            for (int j = 0; j < puzzle.bottom.length; j++) {
                if (onlyChar.get(i) == puzzle.bottom[j]) {
                    new_grid.chars[puzzle.left.length - 1][j] = onlyChar.get(i);
                }
            }
        }
        return new_grid;
    }
}
