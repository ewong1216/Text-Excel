package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	Cell[][] cells;
	public Spreadsheet(){
		cells = new Cell[20][12];
		for(int row = 0; row < 20; row++){
			for(int col = 0; col < 12; col++){
				cells[row][col] = new EmptyCell();
			}
		}
		printGrid();
	}
	
	private void printGrid(){
		System.out.println("   |A         |B         |C         |D         |E         |F         |G         |H         |I         |J         |K         |L         |");
		for(int row = 0; row < 20; row++){
			if(row < 9){
				System.out.print(row + 1 + "  ");
			}
			else{
				System.out.print(row + 1 + " ");
			}
			for(int col = 0; col < 12; col++){
				System.out.print("|" + cells[row][col].abbreviatedCellText());
			}
			System.out.println("|");
		}
	}
	
	public String processCommand(String command){
		
		return command;
	}

	public int getRows(){
		return 20;
	}

	public int getCols(){
		return 12;
	}

	@Override
	public Cell getCell(Location loc){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGridText(){
		// TODO Auto-generated method stub
		return null;
	}
	
	// You are free to use this helper method.  It takes a column letter (starting at "A")
	// and returns the column number corresponding to that letter (0 for "A", 1 for "B", etc.)  
	// WARNING!!  If the parameter is not a single, capital letter in the range of your Spreadsheet,
	// bad things might happen!
	public static int getColumnNumberFromColumnLetter(String columnLetter){
		return Character.toUpperCase(columnLetter.charAt(0)) - 'A';
	}

	// You are free to use this helper method.  It takes a column number (starting at 0)
	// and returns the column letter corresponding to that number ("A" for 0, "B" for 1, etc.)
	// WARNING!!  If the parameter is not a number in the range of your Spreadsheet,
	// bad things might happen!
	public static String getColumnLetterFromColumnNumber(int columnNumber){
		return "" + (char) ('A' + columnNumber);
	}
}
