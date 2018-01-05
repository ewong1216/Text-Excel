package textExcel;

import java.util.Arrays;

public class Spreadsheet implements Grid{
	private Cell[][] cells;
	private String[] history;
	private boolean recordHistory;
	private int numComs;
	private int numClear;
	
	public Spreadsheet(){
		cells = new Cell[20][12];
		clearGrid();
		System.out.println(getGridText());
		history = null;
		recordHistory = false;
	}
	
	public String processCommand(String c){
		if(c.equalsIgnoreCase("quit") || c.equals(""))
			return c;
		if(c.equalsIgnoreCase("clear")){
			clearGrid();
			return getGridText();
		}
		String error = checkForErrors(c);
		if(!error.isEmpty())
			return error;
		String[] com = c.split(" ",3);
		if(com[0].equalsIgnoreCase("clear"))
			clearCell(new SpreadsheetLocation(com[1]));
		else if(com.length == 1){
			return getCell(new SpreadsheetLocation(c)).fullCellText();
		}
		else if(com[1].equals("=")){
			if(com[2].contains("\""))
				setTextCell(new SpreadsheetLocation(com[0]),com[2].substring(1, com[2].length()-1));
			else if(com[2].contains("%"))
				setPercentCell(new SpreadsheetLocation(com[0]),com[2]);
			else if(com[2].contains("("))
				setFormulaCell(new SpreadsheetLocation(com[0]),com[2]);
			else
				setValueCell(new SpreadsheetLocation(com[0]),com[2]);
		}
		if(com[1].equals("start")){
			startHistory(com[2]);
			return "";
		}
		if(recordHistory){
			if(!com[0].equals("history"))
				record(c);
			else if(com[1].equals("display")){
				return displayHistory();
			}
			else if(com[1].equals("stop")){
				stopHistory();
				return "";
			}
			else{
				clearHistory(com[2]);
				return "";
			}
		}
		return getGridText();
	}
	
	private String checkForErrors(String c){
		if(!c.contains(" ")){
			if(c.length() > 3 && !c.equalsIgnoreCase("clear")){
				return "ERROR: Invalid command.\n";
			}
			else{
				for(int row = 0; row < 20; row++){
					for(int col = 0; col < 12; col++){
						String cellName = getColumnLetterFromColumnNumber(col) + (row+1);
						if(cellName.equalsIgnoreCase(c))
							return"";
					}
				}
				return "ERROR: Invalid command.\n";
			}
		}
		if(c.contains("  ") || c.startsWith(" ") || c.endsWith(" "))
			return "ERROR: Invalid command.\n";
		String[] coms = c.split(" ", 3);
		if(coms[0].equals("history"))
			return"";
		if(coms.length == 2 && !coms[0].equalsIgnoreCase("clear"))
			return "ERROR: Invalid command.\n";
		int cellRow;
		if(coms[0].equalsIgnoreCase("clear")){
			cellRow = Integer.parseInt(coms[1].substring(1));
		}
		else{
			cellRow = Integer.parseInt(coms[0].substring(1));
		}
		if(cellRow > 20 || cellRow < 1)
			return "ERROR: Invalid command.\n";
		else if(coms[0].equalsIgnoreCase("clear"))
			return "";
		if(getColumnNumberFromColumnLetter(coms[0].substring(0, 1)) > 11)
			return "ERROR: Invalid command.\n";
		if(coms[2].contains("\"")){
			if(!coms[2].startsWith("\"") || !coms[2].endsWith("\""))
				return "ERROR: Invalid command.\n";
			else
				return "";
		}
		if(coms.length == 3){
			if(c.contains(" -")){
				return "";
			}
			if(coms[2].contains("+") || coms[2].contains("-") ||  coms[2].contains("*") || coms[2].contains("/")){
				if(!coms[2].contains("(") || !coms[2].contains(")") || !coms[2].contains(" ")){
					return "ERROR: Invalid command.\n";
				}
			}
			if(coms[2].endsWith("%"))
				return "";
			for(int i = 0; i < 26; i++){
				if(getColumnLetterFromColumnNumber(i+1).equals(coms[2].substring(0, 1)))
					return "ERROR: Invalid command.\n";
			}
		}
		return "";
	}
	
	private void clearGrid(){
		for(int row = 0; row < 20; row++){
			for(int col = 0; col < 12; col++){
				cells[row][col] = new EmptyCell();
			}
		}
	}
	
	private void clearCell(SpreadsheetLocation sl){
		cells[sl.getRow()][sl.getCol()] = new EmptyCell();
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
	public Cell getCell(Location loc){
		return cells[loc.getRow()][loc.getCol()];
	}
	
	private void startHistory(String num){
		history = new String[Integer.parseInt(num)];
		recordHistory = true;
	}
	private void record(String command){
		for(int i = history.length - 1; i > 0; i--){
			history[i] = history[i-1];
		}
		history[0] = command;
		numComs++;
	}
	private String displayHistory(){
		String display = "";
		if(numComs > history.length)
			numComs = history.length;
		for(int i = 0; i < numComs; i++){
			display += history[i] + "\n";
		}
		return display;
	}
	private void clearHistory(String nClear){
		numClear = Integer.parseInt(nClear);
		if(numClear >= history.length || numClear > numComs){
			Arrays.fill(history, "");
			numComs = 0;
			return;
		}
		boolean clearFromEnd = false;
		if(numComs >= history.length){
			numComs = history.length;
			clearFromEnd = true;
		}
		for(int i = 0; i < numClear; i++){
			if(clearFromEnd){
				history[history.length-1-i] = "";
			}
			else{
				history[numComs-i] = "";
			}
			numComs--;
		}
	}
	private void stopHistory(){
		history = null;
		recordHistory = false;
		numComs = 0;
	}
	
	public int getRows(){
		return 20;
	}

	public int getCols(){
		return 12;
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
	
	public static String fillSpaces(String input){
		if(input.length() < 10){
			for(int i = input.length(); i < 10; i++){
				input += " ";
			}
		}
		return input;
	}
	public static int getColumnNumberFromColumnLetter(String columnLetter){
		return Character.toUpperCase(columnLetter.charAt(0)) - 'A';
	}
	public static String getColumnLetterFromColumnNumber(int columnNumber){
		return "" + (char) ('A' + columnNumber);
	}
}