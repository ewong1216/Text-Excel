package textExcel;

public class Spreadsheet implements Grid{
	private Cell[][] cells;
	public Spreadsheet(){
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
		else if(com.length == 1){ //Inspection
			if(getCell(new SpreadsheetLocation(c)).getClass() == new EmptyCell().getClass())
				return "";
			else if(getCell(new SpreadsheetLocation(c)).getClass() == new TextCell("").getClass())
				return "\"" + getCell(new SpreadsheetLocation(c)).fullCellText() + "\"";
			//Room for other cell types.
		}
		else{ //For setting cells
			if(com[2].contains("\""))
				setTextCell(new SpreadsheetLocation(com[0]),com[2].substring(1, com[2].length()-1));
			//Room for other cell types.
		}
		return getGridText();
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
	
	public int getRows(){
		return 20;
	}

	public int getCols(){
		return 12;
	}

	public Cell getCell(Location loc){
		return cells[loc.getRow()][loc.getCol()];
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
