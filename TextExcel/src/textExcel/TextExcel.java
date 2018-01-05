package textExcel;

import java.util.Scanner;

public class TextExcel
{
	
	public static void main(String[] args){
	    Scanner scan = new Scanner(System.in);
	    String input = "";
	    Spreadsheet s = new Spreadsheet();
	    while(!input.equalsIgnoreCase("quit")){
	    	System.out.print("Enter your command here -->");
	    	input = scan.nextLine();
	    	System.out.println(s.processCommand(input));
	    }
	    scan.close();
	}
}