package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
	private int cellCol;
	private int cellRow;
	public SpreadsheetLocation(String cellName){
		cellRow = Integer.parseInt(cellName.substring(1)) - 1;
		cellCol = Spreadsheet.getColumnNumberFromColumnLetter(cellName.substring(0,1));
		System.out.print(cellCol + "\n" + cellRow);
	}
	
    public int getRow(){
        return cellRow;
    }

    public int getCol(){
        return cellCol;
    }

}
