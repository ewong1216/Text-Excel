package textExcel;

import java.util.Scanner;

public class TestExcel {
	private Cell[][] cells;
	
	public TestExcel(){
		cells = new Cell[20][12];
		clearGrid();
		System.out.println(getGridText());
	}
	
	public String processCommand(String c){
		if(c.equalsIgnoreCase("quit"))
			return c;
		else if(c.isEmpty())
			return "";
		else if(c.equalsIgnoreCase("clear"))
			clearGrid();
		else if(!c.contains(" ") && !c.isEmpty()){
			if(getCell(new SpreadsheetLocation(c)).getClass() == new EmptyCell().getClass())
				return "";
			else
				return "\"" + getCell(new SpreadsheetLocation(c)).fullCellText() + "\"";
		}
		else if(c.substring(0,c.indexOf(" ")).equalsIgnoreCase("clear"))
			clearCell(new SpreadsheetLocation(c.substring(c.indexOf(" ")+1)));
		else if(c.contains("=")){
			if(c.contains("\""))
				setTextCell(new SpreadsheetLocation(c.substring(0,c.indexOf(" "))),c.substring(c.indexOf("\"")+1, c.length()-1));
			//Room for other cell types
		}
		return getGridText();
	}
	
	private void setTextCell(SpreadsheetLocation sl,String s){
		cells[sl.getRow()][sl.getCol()] = new TextCell(s);
	}
	
	private void clearCell(SpreadsheetLocation sl){
		cells[sl.getRow()][sl.getCol()] = new EmptyCell();
	}
	
	public Cell getCell(Location loc){
		return cells[loc.getRow()][loc.getCol()];
	}
	private void clearGrid(){
		for(int row = 0; row < 20; row++){
			for(int col = 0; col < 12; col++){
				cells[row][col] = new EmptyCell();
			}
		}
	}
	
	public String getGridText(){
		String s = "   |A         |B         |C         |D         |E         |F         |G         |H         |I         |J         |K         |L         |";
		for(int row = 0; row < 20; row++){
			s += "\n";
			if(row < 9)
				s += row + 1 + "  ";
			else
				s += row + 1 + " ";
			for(int col = 0; col < 12; col++){
				s += "|" + cells[row][col].abbreviatedCellText();
			}
			s += "|";
		}
		s += "\n";
		return s;
	}
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
	    String input = "";
	    TestExcel t = new TestExcel();
	    while(!input.equalsIgnoreCase("quit")){
	    	System.out.print("Enter your command here -->");
	    	input = scan.nextLine();
	    	System.out.println(t.processCommand(input));
	    }
	    scan.close();
	}
}
