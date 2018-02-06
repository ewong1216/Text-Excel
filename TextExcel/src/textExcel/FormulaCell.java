package textExcel;

public class FormulaCell extends RealCell{
	
	public FormulaCell(String input){
		super(input);
	}
	
	public double getDoubleValue(){
		String input = super.getInput();
		String[] arr = input.substring(2,input.length()-2).split(" ");
		Double dValue = Double.parseDouble(arr[0]);
		for(int i = 1; i < arr.length; i+=2){
			if(arr[i].equals("+"))
				dValue += Double.parseDouble(arr[i+1]);
			else if(arr[i].equals("-"))
				dValue -= Double.parseDouble(arr[i+1]);
			else if(arr[i].equals("*"))
				dValue *= Double.parseDouble(arr[i+1]);
			else
				dValue /= Double.parseDouble(arr[i+1]);
		}
		return dValue;
	}
	
	public String abbreviatedCellText(){
		return Spreadsheet.fillSpaces(getDoubleValue()+"");
	}
	
	/*
	public static void main(String[] args){
		FormulaCell f = new FormulaCell("( 1 + 1 + 1 )");
	}
	*/
}
