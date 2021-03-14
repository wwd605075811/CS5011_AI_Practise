import java.util.ArrayList;

import mains.Puzzle;
import mains.Grid;
import tests.SamplePuzzles;
import tests.SampleGrids;
import tests.TestsStaff;
import part1.CheckerABC;
import part2.ProceduralABC;
import part4.HintABC;

public class A2main {

    public static void printUsage() { 
        System.out.println("Input not recognised.  Usage is:");
        System.out.println("java A2main TEST|SOLVE|HINT <TestSet or Puzzle>"  ); 
    }

    public static boolean checkPuzzleName(String name) { 
        for(SamplePuzzles p : SamplePuzzles.values() ) { 
            if(p.name().equals(name)) { return true; } ;
        } 
        return false; 
    }

	public static void main(String[] args) {
       
        if(args.length < 2) { printUsage(); };

		switch (args[0]) {
            //
            // Add additional commands if you wish 
            //
		case "TEST":
			switch (args[1]) {

                // Please add other test sets you construct yourself

                case "Staff1" : 
                    TestsStaff.part1();
                    return;

                case "Staff2" : 
                    TestsStaff.part2();
                    return;


                case "StaffAll" : 
                    TestsStaff.part1();
                    TestsStaff.part2();
                    return;

                default:
                    System.out.println("Test set not recognised");
			        return;
            }

        case "HINT": 
            if(checkPuzzleName(args[1])) {
                Puzzle p = SamplePuzzles.valueOf(args[1]).puzzle;
                Grid currentGrid = SampleGrids.valueOf("ABC1testHints").grid;
                System.out.println("Current Grid:");
                p.printPuzzleGrid(currentGrid);
                HintABC.giveHints(p, currentGrid);
            }
            else { System.out.println("Unknown puzzle " + args[1]); }
            return;

        default : 
            printUsage(); 
            return;

		}

	}
}
