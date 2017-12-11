package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args)
	{
	    Scanner scan = new Scanner(System.in);
	    String input = "";
	    Spreadsheet s = new Spreadsheet();
	    while(!input.equalsIgnoreCase("quit")){
	    	System.out.print("Enter your command here -->");
	    	input = scan.nextLine();
	    	System.out.println(s.processCommand(input));
	    }
	}
}
