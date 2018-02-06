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
		if((dValue+"").contains("999")){
			String s = dValue+"";
			s = s.substring(0, s.indexOf("999"));
			int decimalLength = s.substring(s.indexOf(".")+1).length();
			String toAdd = "0.";
			for(int i =0; i < decimalLength-1; i++)
				toAdd += "0";
			toAdd += "1";
			dValue = Double.parseDouble(s) + Double.parseDouble(toAdd);
		}
		return dValue;
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
