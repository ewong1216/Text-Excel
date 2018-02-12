package textExcel;

public class SpreadsheetLocation implements Location{
	private int cellCol;
	private int cellRow;
	 
	public SpreadsheetLocation(String cellName){
		cellRow = Integer.parseInt(cellName.substring(1)) - 1;
		cellCol = Spreadsheet.getColumnNumberFromColumnLetter(cellName.substring(0,1));
	}
	
    public int getRow(){
        return cellRow;
    }
    public int getCol(){
        return cellCol;
    }

}
