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
		if(arr[0].equalsIgnoreCase("sum")){
			//TODO
		}
		if(arr[0].equalsIgnoreCase("avg")){
			//TODO
		}
		//TODO
		Double dValue;
		if(Spreadsheet.containsLetter(arr[0]))
			dValue = ((RealCell) s.getCell(new SpreadsheetLocation(arr[0]))).getDoubleValue();
		else
			dValue = Double.parseDouble(arr[0]);
		for(int i = 1; i < arr.length; i+=2){
			String next = arr[i+1];
			Double nextValue;
			if(Spreadsheet.containsLetter(next))
				nextValue = ((RealCell) s.getCell(new SpreadsheetLocation(next))).getDoubleValue();
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
	
	public double sumFormula(String cellRange){
		//TODO
		return 0.0;
	}
	
	public String abbreviatedCellText(){	
		return Spreadsheet.fillSpaces(getDoubleValue()+"");
	}
	
	/*
	public static void main(String[] args){
		FormulaCell f = new FormulaCell("( 5.4 * 3.5 / -1.4 + 27.4 - 11.182 )");
	}
	*/
}
