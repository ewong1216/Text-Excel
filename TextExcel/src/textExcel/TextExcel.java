package textExcel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//Simply could not get file saving to work on BuildCi, but you can try it out.
//It works when I do it on my laptop so this kind of problem is beyond my knowledge.

public class TextExcel{
	private static File f;
	
	private static int save(String fileName,Cell[][] cells)throws IOException{
		f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		int numFileLines = 0;
		for(int row = 0; row < 20; row++){
			for(int col = 0; col < 12; col++){
				if(cells[row][col].getClass() != EmptyCell.class){
					fw.write(Spreadsheet.getColumnLetterFromColumnNumber(col) + (row + 1) + ",");
					if(cells[row][col].getClass() == TextCell.class)
						fw.write("TextCell," + cells[row][col].fullCellText() + "\n");
					if(cells[row][col].getClass() == ValueCell.class)
						fw.write("ValueCell," + cells[row][col].fullCellText() + "\n");
					if(cells[row][col].getClass() == PercentCell.class)
						fw.write("PercentCell," + cells[row][col].fullCellText() + "\n");
					if(cells[row][col].getClass() == FormulaCell.class)
						fw.write("FormulaCell," + cells[row][col].fullCellText() + "\n");
					numFileLines++;
				}		
			}
		}
		fw.close();
		return numFileLines;
	}
	
	private static void open(String fileName,Cell[][] cells,int numLines)throws IOException{
		Scanner scan = new Scanner(f);
		String line = "";
		for(int i = 0; i < numLines; i++){
			line = scan.nextLine();
			String after1stComma = line.substring(line.indexOf(",")+1);
			String cellType = after1stComma.substring(0,after1stComma.indexOf(","));
			String fullText = after1stComma.substring(after1stComma.indexOf(",")+1);
			SpreadsheetLocation sl = new SpreadsheetLocation(line.substring(0,line.indexOf(",")));
			if(cellType.equals("TextCell"))
				cells[sl.getRow()][sl.getCol()] = new TextCell(fullText.substring(1,fullText.length()-1));
			else if(cellType.equals("ValueCell"))
				cells[sl.getRow()][sl.getCol()] = new ValueCell(fullText);
			else if(cellType.equals("PercentCell"))
				cells[sl.getRow()][sl.getCol()] = new PercentCell(Double.parseDouble(fullText));
			else if(cellType.equals("FormulaCell"))
				cells[sl.getRow()][sl.getCol()] = new FormulaCell(fullText);
		}
		scan.close();
	}

	public static void main(String[] args) throws IOException{
	    Scanner scan = new Scanner(System.in);
	    Spreadsheet s = new Spreadsheet();
	    System.out.print("Enter your command here -->");
	    String input = scan.nextLine();
	    int numFileLines = 0;
	    while(!input.equalsIgnoreCase("quit")){
	    	if(input.startsWith("save")){
	    		numFileLines = save(input.substring(input.indexOf(" ")+1),s.getCells());
	    	}
	    	else if(input.startsWith("open")){
	    		open(input.substring(input.indexOf(" ")+1),s.getCells(),numFileLines);
	    	}
	    	System.out.println(s.processCommand(input));
	    	System.out.print("Enter your command here -->");
	    	input = scan.nextLine();
	    }
	    scan.close();
	}
}