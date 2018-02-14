package textExcel;

public class FormulaCell extends RealCell{
	private Spreadsheet s;
	
	public FormulaCell(String input,Spreadsheet s){
		super(input);
		this.s = s;
	}
	
	public double getDoubleValue(){
		String input = super.getInput();
		String[] arr = input.substring(2,input.length()-2).split(" ");
		if(arr[0].equalsIgnoreCase("sum"))
			return calculate(arr[1],true);
		if(arr[0].equalsIgnoreCase("avg"))
			return calculate(arr[1],false);
		double dValue;
		double nextValue;
		String next = "";
		if(Spreadsheet.containsLetter(arr[0]))
			dValue = s.getCell(new SpreadsheetLocation(arr[0])).getDoubleValue();
		else
			dValue = Double.parseDouble(arr[0]);
		int index = findMulOrDiv(arr);
		double tDouble = 0.0;
		if(index != -1){
			String before = arr[index-1];
			if(Spreadsheet.containsLetter(before))
				tDouble = s.getCell(new SpreadsheetLocation(before)).getDoubleValue();
			else
				tDouble = Double.parseDouble(before);
		}
		while(index != -1){
			next = arr[index+1];
			if(Spreadsheet.containsLetter(next))
				nextValue = s.getCell(new SpreadsheetLocation(next)).getDoubleValue();
			else
				nextValue = Double.parseDouble(next);
			if(arr[index].equals("*"))
				tDouble *= nextValue;
			else
				tDouble /= nextValue;
			arr[index] = "";
			arr[index-1] = tDouble + "";
			index = findMulOrDiv(arr);
		}
		for(int i = 1; i < arr.length; i+=2){
			next = arr[i+1];
			if(Spreadsheet.containsLetter(next))
				nextValue = s.getCell(new SpreadsheetLocation(next)).getDoubleValue();
			else
				nextValue = Double.parseDouble(next);
			if(arr[i].equals("+"))
				dValue += nextValue;
			else if(arr[i].equals("-"))
				dValue -= nextValue;
			else if(arr[i].equals("*"))
				dValue *= nextValue;
			else if(arr[i].equals("/"))
				dValue /= nextValue;
		}
		if((dValue+"").contains("999")){
			String s = dValue+"";
			s = s.substring(0, s.indexOf("999"));
			int decimalLength = s.substring(s.indexOf(".")+1).length();
			String toAdd = "0.";
			for(int t =0; t < decimalLength-1; t++)
				toAdd += "0";
			toAdd += "1";
			dValue = Double.parseDouble(s) + Double.parseDouble(toAdd);
		}
		return dValue;
	}
	
	private double calculate(String cellRange,boolean isSum){
		SpreadsheetLocation topLeft = new SpreadsheetLocation(cellRange.substring(0,cellRange.indexOf("-")));
		SpreadsheetLocation bottomRight = new SpreadsheetLocation(cellRange.substring(cellRange.indexOf("-")+1));
		Cell[][] cells = s.getCells();
		double sum = 0.0;
		int numCells = 0;
		for(int row = topLeft.getRow(); row <= bottomRight.getRow(); row++){
			for(int col = topLeft.getCol(); col <= bottomRight.getCol(); col++){
				sum += (cells[row][col]).getDoubleValue();
				numCells ++;
			}
		}
		if(isSum)
			return sum;
		return sum/numCells;
	}
	public String abbreviatedCellText(){	
		return Spreadsheet.fillSpaces(getDoubleValue()+"");
	}
	private int findMulOrDiv(String[] arr){
		boolean containsAddOrSub = false;
		for(int i = 0; i < arr.length; i++){
			if(arr[i].equals("+") || arr[i].equals("-"))
				containsAddOrSub = true;
			if(arr[i].equals("*") || arr[i].equals("/")){
				if(containsAddOrSub)
					return i;
				else
					return -1;
			}
		}
		return -1;
	}
}
