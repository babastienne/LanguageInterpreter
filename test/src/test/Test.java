package test;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import calc.Calc;

public class Test {
	static int count = 0;
	static int success = 0;
	static boolean verbose = false;
	
	public static void report(){
		System.out.println(success + " successful tests out of " + count);
	}

	public static void test(boolean verbose, String filename, String mess1, String mess2){
		test(verbose, filename, mess1, mess2, "");
	}
			
	public static void test(boolean verbose, String filename, String mess1, String mess2, String comment) {
		count++;
		String[] args0 = new String[1];
		args0[0] = filename;
		if (verbose) {
			System.out.println("====: " + filename);
			System.out.println(mess1);
			System.out.println("expected: " + mess2 + " = " + comment);
		}
		PrintStream out = System.out;
		try {		
			System.setOut(new PrintStream("result.txt"));
			Calc.main(args0);
			System.setOut(out);
			Scanner sc = new Scanner(new File("result.txt"));
			boolean integerFound = false;
			int found = 0;
			try {
				found = sc.nextInt();
				integerFound = true;
			} catch(Exception e){
				System.err.println("Result is not an integer");
			}
			sc.close();
			
			if (mess2.equals("error")) { // error expected
				System.err.println("FAILURE on " + filename);
				if (integerFound) 
					System.err.println("error expected, found " + found);
			} else { // integer expected
				int expected = Integer.parseInt(mess2);
				if (verbose) System.out.println("result: " + found);
				if (found != expected) {
					System.err.println("FAILURE on " + filename);
					System.err.println("expected " + expected + " found " + found);
				} else success++;
			}
		} catch(Exception e){
			System.setOut(out);
			if (mess2.equals("error")) { // expected error
				success++;
				System.err.println(e.getMessage()); // is it indeed a meaningful error?
			}
			else { // unexpected error
				System.err.println("FAILURE on " + filename); // for debugging purposes
				e.printStackTrace(); 
			}	
		}		
	}
	public static void main(String[] args){		
		if (args.length > 0 && args[0].equals("-v")) 
			verbose = true;
		else verbose = false;
	}
}

