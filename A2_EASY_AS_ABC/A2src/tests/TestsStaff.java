package tests;
/*
 * author Ian Gent
 * 
 * Please do not add tests to this file. 
 * Feel free to add tests to the TestsStudent file.
 */

import mains.Puzzle;
import mains.Grid;
import tests.SamplePuzzles;
import tests.SampleGrids;
import part1.CheckerABC;
import part2.ProceduralABC;

import tests.TestsBase;


public class TestsStaff {


        public static void part1() {

            Puzzle puzzle;
            Grid grid;
            Grid result;

            TestsBase.testIsConsistent("Part 1-1",true,"ABC1","ABC1Sol");
            TestsBase.testIsFullGrid("Part 1-2",true,"ABC1","ABC1Sol");
            TestsBase.testIsSolution("Part 1-3",true,"ABC1","ABC1Sol");

            TestsBase.testIsConsistent("Part 1-4",true,"ABC1","ABC1incomplete");
            TestsBase.testIsFullGrid("Part 1-5",false,"ABC1","ABC1incomplete");
            TestsBase.testIsSolution("Part 1-6",false,"ABC1","ABC1incomplete");

            TestsBase.testIsConsistent("Part 1-7",false,"ABC1","ABC1inconsistent1");
            TestsBase.testIsConsistent("Part 1-8",false,"ABC1","ABC1inconsistent2");
            TestsBase.testIsConsistent("Part 1-9",false,"ABC1","ABC1inconsistent3");
            TestsBase.testIsConsistent("Part 1-10",false,"ABC1","ABC1inconsistent4");
            TestsBase.testIsConsistent("Part 1-11",false,"ABC1","ABC1inconsistent5");
            TestsBase.testIsConsistent("Part 1-12",false,"ABC1","ABC1inconsistent6");
            TestsBase.testIsConsistent("Part 1-13",false,"ABC1","ABC1inconsistent7");
            TestsBase.testIsConsistent("Part 1-14",false,"ABC1","ABC1inconsistent8");
            TestsBase.testIsConsistent("Part 1-15",false,"ABC1","ABC1inconsistent9");
            TestsBase.testIsConsistent("Part 1-16",false,"ABC1","ABC1inconsistent10");
            TestsBase.testIsConsistent("Part 1-17",false,"ABC1","ABC1inconsistent11");
            TestsBase.testIsConsistent("Part 1-18",false,"ABC1","ABC1inconsistent12");
            
        }
        
        public static void part2() {

		    TestsBase.testDifferentCorners("Part 2-1","ABC1Empty","ABC1","ABC1Empty");
		    TestsBase.testDifferentCorners("Part 2-2","ABCdifferentcorners6","ABC6","ABC6Empty");
            TestsBase.testCommonClues("Part 2-3","ABCcommonclues1","ABC1","ABC6Empty");
            TestsBase.testCommonClues("Part 2-4","ABCcommonclues6","ABC6","ABC6Empty");
            TestsBase.testFillInBlanksCol("Part 2-5","ABC1testblankscolscols","ABC1","ABC1testcols");
            TestsBase.testFillInBlanksCol("Part 2-6","ABC1testrowscols","ABC1","ABC1testrows");
            TestsBase.testFillInBlanksCol("Part 2-7","ABC1Sol","ABC1","ABC1testblanks");
            TestsBase.testFillInBlanksRow("Part 2-8","ABC1testcols","ABC1","ABC1testcols");
            TestsBase.testFillInBlanksRow("Part 2-9","ABC1testblanksrowsrows","ABC1","ABC1testrows");
            TestsBase.testFillInBlanksRow("Part 2-10","ABC1Sol","ABC1","ABC1testblanks");

            TestsBase.testOnlyPlaceRow("Part 2-11","ABC1Sol","ABC1","ABC1testboth");
            TestsBase.testOnlyPlaceRow("Part 2-12","ABC1testonlyplacerowrows","ABC1","ABC1testrows");
            TestsBase.testOnlyPlaceRow("Part 2-13","ABC1Sol","ABC1","ABC1incomplete");
            TestsBase.testOnlyPlaceRow("Part 2-14","ABC1testonlyplacerowcols","ABC1","ABC1testcols");
            TestsBase.testOnlyPlaceCol("Part 2-15","ABC1Sol","ABC1","ABC1testboth");
            TestsBase.testOnlyPlaceCol("Part 2-16","ABC1testonlyplacecolrows","ABC1","ABC1testrows");
            TestsBase.testOnlyPlaceCol("Part 2-17","ABC1Sol","ABC1","ABC1incomplete");
            TestsBase.testOnlyPlaceCol("Part 2-18","ABC1testonlyplacecolcols","ABC1","ABC1testcols");
        }

        TestsStaff() {} 

}


