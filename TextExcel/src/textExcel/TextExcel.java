package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{
	
	public static void main(String[] args){
	    Scanner scan = new Scanner(System.in);
	    String input = "";
	    Spreadsheet s = new Spreadsheet();
	    String[] comHis;
	    int index;
	    boolean trackHistory;
	    while(!input.equalsIgnoreCase("quit")){
	    	System.out.print("Enter your command here -->");
	    	input = scan.nextLine();
	    	String[] command = input.split(" ");
	    	if(command[0].equals("history") && command[1].equals("start")){
	    		trackHistory = true;
	    		comHis = new String[Integer.parseInt(command[2])];
	    		index = Integer.parseInt(command[2]);
	    		input = scan.nextLine();
	    		//comHis[]
	    	}
	    	System.out.println(s.processCommand(input));
	    }
	    scan.close();
	}
}
