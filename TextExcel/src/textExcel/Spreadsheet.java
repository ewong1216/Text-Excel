package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private Cell[][] cells;
	public Spreadsheet(){
		cells = new Cell[20][12];
		clearGrid();
		System.out.println(getGridText());
	}

	public String processCommand(String c){
		if(c.equalsIgnoreCase("quit"))
			return c;
		if(c.contains("clear"))
			if(!c.contains(" "))
				clearGrid();
			else
				clearCell(new SpreadsheetLocation(c.substring(c.indexOf(" ")+1)));
		if(c.contains("="))
			if(c.contains("\""))
				setTextCell(new SpreadsheetLocation(c.substring(0,c.indexOf(" "))),c.substring(c.indexOf("\"")+1, c.length()-1));
			//Room for Setting Other Types
		if(!c.contains(" ") && !c.isEmpty())
			return "\"" + getCell(new SpreadsheetLocation(c)).fullCellText() + "\"";
		if(c.isEmpty())
			return "";
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
	
	public static int getColumnNumberFromColumnLetter(String columnLetter){
		return Character.toUpperCase(columnLetter.charAt(0)) - 'A';
	}

	public static String getColumnLetterFromColumnNumber(int columnNumber){
		return "" + (char) ('A' + columnNumber);
	}
}
