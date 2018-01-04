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
		if(c.equalsIgnoreCase("quit") || c.equals(""))
			return c;
		if(c.equalsIgnoreCase("clear")){
			clearGrid();
			return getGridText();
		}
		String[] com = c.split(" ",3);
		if(com[0].equalsIgnoreCase("clear"))
			clearCell(new SpreadsheetLocation(com[1]));
		else if(com.length == 1){
			return getCell(new SpreadsheetLocation(c)).fullCellText();
		}
		else{
			if(com[2].contains("\""))
				setTextCell(new SpreadsheetLocation(com[0]),com[2].substring(1, com[2].length()-1));
			else if(com[2].contains("%"))
				setPercentCell(new SpreadsheetLocation(com[0]),com[2]);
			else if(com[2].contains("("))
				setFormulaCell(new SpreadsheetLocation(com[0]),com[2]);
			else
				setValueCell(new SpreadsheetLocation(com[0]),com[2]);
		}
		return getGridText();
	}

	private void setTextCell(SpreadsheetLocation sl,String s){
		cells[sl.getRow()][sl.getCol()] = new TextCell(s);
	}
	private void setPercentCell(SpreadsheetLocation sl, String s){
		cells[sl.getRow()][sl.getCol()] = new PercentCell(s);
	}
	private void setValueCell(SpreadsheetLocation sl, String s){
		cells[sl.getRow()][sl.getCol()] = new ValueCell(s);
	}
	private void setFormulaCell(SpreadsheetLocation sl, String s){
		cells[sl.getRow()][sl.getCol()] = new FormulaCell(s);
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
