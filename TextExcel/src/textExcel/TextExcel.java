package textExcel;
import java.util.Scanner;

public class TextExcel{
	
	public static void main(String[] args){
	    Scanner scan = new Scanner(System.in);
	    Spreadsheet s = new Spreadsheet();
	    System.out.print("Enter your command here -->");
	    String input = scan.nextLine();
	    while(!input.equalsIgnoreCase("quit")){
	    	System.out.println(s.processCommand(input));
	    	System.out.print("Enter your command here -->");
	    	input = scan.nextLine();
	    }
	    scan.close();
	}
}