package part1;

import mains.Grid;
import mains.Puzzle;

public class CheckerABC {

    public CheckerABC() {
    }

    public static boolean isConsistent(Puzzle puzzle, Grid grid) {
        return isConsistent(puzzle, grid.chars);
    }

	/**
	 * test if all the constraints of the puzzle are obeyed for every square
	 * @param puzzle puzzle
	 * @param chars origin grid.chars
	 * @return the grid obeyed the rule or not
	 */
	public static boolean isConsistent(Puzzle puzzle, char[][] chars) {
		// alloc memory to start
        char[] symbols = puzzle.symbols;
        int[] checkSymbols = new int[puzzle.symbols.length];
        int blankSize = puzzle.size() - puzzle.letters.length;
        for (int i = 0; i < checkSymbols.length; i++) {
            checkSymbols[i] = 0;
        }

        // check Illegal character (not exist)
        boolean flag = false;
        char[] allSymbols = new char[puzzle.symbols.length + 1];
        for (int i = 0; i < allSymbols.length - 1; i++) {
            allSymbols[i] = puzzle.symbols[i];
        }
        allSymbols[allSymbols.length - 1] = '_';
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                for (int k = 0; k < allSymbols.length; k++) {
                    if (chars[i][j] == allSymbols[k]) {
                        flag = true;
                    }
                }
                if (!flag) {
                    return false;
                }
                flag = false;
            }
        }

        // check each Rows whether repeating
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                for (int k = 0; k < symbols.length; k++) {
                    if (symbols[k] == chars[i][j]) {
                        checkSymbols[k]++;
                    }
                }
            }
            if (!isFullLineNoRepeat(checkSymbols, blankSize)) {
                return false;
            }
            //reset the cheak array
            for (int lineIndex = 0; lineIndex < checkSymbols.length; lineIndex++) {
                checkSymbols[lineIndex] = 0;
            }
        }

        // check each Columns whether repeating
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                for (int k = 0; k < symbols.length; k++) {
                    if (symbols[k] == chars[j][i]) {
                        checkSymbols[k]++;
                    }
                }
            }
            if (!isFullLineNoRepeat(checkSymbols, blankSize)) {
                return false;
            }
            //reset the cheak array
            for (int lineIndex = 0; lineIndex < checkSymbols.length; lineIndex++) {
                checkSymbols[lineIndex] = 0;
            }
        }

        // checking whether First char is same to top
        for (int i = 0; i < puzzle.top.length; i++) {
            if (chars[0][i] == puzzle.unfilledChar || chars[0][i] == puzzle.top[i])
                continue;
            else if (chars[0][i] != puzzle.top[i] && chars[0][i] != puzzle.blankSymbol) {
                //System.out.println(i + "222 top");
                return false;
            } else if (chars[1][i] == puzzle.unfilledChar || chars[1][i] == puzzle.top[i])
                continue;
            else if (chars[1][i] != puzzle.top[i] && chars[1][i] != puzzle.blankSymbol) {
                //System.out.println(i + "333 top");
                return false;
            } else if (chars[2][i] == puzzle.unfilledChar || chars[2][i] == puzzle.top[i])
                continue;
            else if (chars[2][i] != puzzle.top[i]) {
                //System.out.println(i + "444 top");
                return false;
            }
        }

        // checking whether First char is same to left
        for (int i = 0; i < puzzle.left.length; i++) {
            if (chars[i][0] == puzzle.unfilledChar || chars[i][0] == puzzle.left[i])
                continue;
            else if (chars[i][0] != puzzle.left[i] && chars[i][0] != puzzle.blankSymbol) {
                //System.out.println(i + "222 left");
                return false;
            } else if (chars[i][1] == puzzle.unfilledChar || chars[i][1] == puzzle.left[i])
                continue;
            else if (chars[i][1] != puzzle.left[i] && chars[i][1] != puzzle.blankSymbol) {
                //System.out.println(i + "333 left");
                return false;
            } else if (chars[i][2] == puzzle.unfilledChar || chars[i][2] == puzzle.left[i])
                continue;
            else if (chars[i][2] != puzzle.left[i]) {
                //System.out.println(i + "444 left");
                return false;
            }
        }

        // checking whether First char is same to right
        for (int i = 0; i < puzzle.right.length; i++) {
            if (chars[i][puzzle.right.length - 1] == puzzle.unfilledChar || chars[i][puzzle.right.length - 1] == puzzle.right[i])
                continue;
            else if (chars[i][puzzle.right.length - 1] != puzzle.right[i] && chars[i][puzzle.right.length - 1] != puzzle.blankSymbol) {
                //System.out.println(i + "222 right");
                return false;
            } else if (chars[i][puzzle.right.length - 2] == puzzle.unfilledChar || chars[i][puzzle.right.length - 2] == puzzle.right[i])
                continue;
            else if (chars[i][puzzle.right.length - 2] != puzzle.right[i] && chars[i][puzzle.right.length - 2] != puzzle.blankSymbol) {
                //System.out.println(i + "333 right");
                return false;
            } else if (chars[i][puzzle.right.length - 3] == puzzle.unfilledChar || chars[i][puzzle.right.length - 3] == puzzle.right[i])
                continue;
            else if (chars[i][puzzle.right.length - 3] != puzzle.right[i]) {
                //System.out.println(i + "444 right");
                return false;
            }
        }

        // checking whether First char is same to bottom
        for (int i = 0; i < puzzle.bottom.length; i++) {
            if (chars[puzzle.bottom.length - 1][i] == puzzle.unfilledChar || chars[puzzle.bottom.length - 1][i] == puzzle.bottom[i])
                continue;
            else if (chars[puzzle.bottom.length - 1][i] != puzzle.bottom[i] && chars[puzzle.bottom.length - 1][i] != puzzle.blankSymbol) {
                //System.out.println(i + "222 bottom");
                return false;
            } else if (chars[puzzle.bottom.length - 2][i] == puzzle.unfilledChar || chars[puzzle.bottom.length - 2][i] == puzzle.bottom[i])
                continue;
            else if (chars[puzzle.bottom.length - 2][i] != puzzle.bottom[i] && chars[puzzle.bottom.length - 2][i] != puzzle.blankSymbol) {
                //System.out.println(i + "333 bottom");
                return false;
            } else if (chars[puzzle.bottom.length - 3][i] == puzzle.unfilledChar || chars[puzzle.bottom.length - 3][i] == puzzle.bottom[i])
                continue;
            else if (chars[puzzle.bottom.length - 3][i] != puzzle.bottom[i]) {
                //System.out.println(i + "444 bottom");
                return false;
            }
        }
        return true;
    }

    public static boolean isFullGrid(Puzzle puzzle, Grid grid) {
        return isFullGrid(puzzle, grid.chars);
    }

	/**
	 * check the grid if full
	 * @param puzzle puzzle
	 * @param chars origin grid.chars
	 * @return the grid is full or not
	 */
    public static boolean isFullGrid(Puzzle puzzle, char[][] chars) {
        int size = puzzle.size();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i].length != size) {
                return false;
            }
            for (int j = 0; j < chars[i].length; j++) {
                if (chars[i][j] == puzzle.unfilledChar) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSolution(Puzzle puzzle, Grid grid) {
        return isFullGrid(puzzle, grid) && isConsistent(puzzle, grid);
    }

	/**
	 * this function will use in the isConsistent(), to check each line has repeat letters or not
	 * @param checkSymbols the count array to count the number of each letter
	 * @param blankSize use this arg(without 2), in case puzzle.top.size() is change
	 * @return repeat ot not
	 */
    public static boolean isFullLineNoRepeat(int[] checkSymbols, int blankSize) {
        for (int i = 0; i < checkSymbols.length - 1; i++) {
            if (checkSymbols[i] > 1) {
                return false;
            }
        }
        if (checkSymbols[checkSymbols.length - 1] > blankSize) {
            return false;
        }
        return true;
    }
}
