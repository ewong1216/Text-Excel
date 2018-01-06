package textExcel;

public class PercentCell extends RealCell{
	
	private double percentValue;
	private double doubleValue;
	
	public PercentCell(String input){
		input = input.substring(0, input.length()-1);
		int dot = input.indexOf(".");
		percentValue = Double.parseDouble(input.substring(0,input.length()));
		doubleValue = percentValue / 100;
		/*
		 * 		if(!input.contains("."))
			doubleValue = Double.parseDouble(input.substring(0, input.length()-2) + "." + input.substring(input.length()-2,input.length()));
		else if(percentValue < 1 && percentValue >= 0)
			doubleValue = Double.parseDouble("0.00" + input.substring(dot+1));
		else if(percentValue >= 100 || percentValue <= -100)
			doubleValue = Double.parseDouble(input.substring(0,dot-2) + "." + input.substring(dot+1,input.length()));
		else if(percentValue < 100 & percentValue >= 10)
			doubleValue = Double.parseDouble("0." + input.substring(0,dot) + input.substring(dot+1,input.length()));
		else if(percentValue > -100 && percentValue <= -10)
			doubleValue = Double.parseDouble("-0." + input.substring(1,dot) + input.substring(dot+1,input.length()));
		else if(percentValue < 10 && percentValue >= 1)
			doubleValue = Double.parseDouble("0.00" + input.charAt(0) + input.substring(dot+1));
		else 
			doubleValue = Double.parseDouble("-0.00" + input.charAt(1) + input.substring(dot+1));
		 */

	}
	public PercentCell(Double dValue){
		doubleValue = dValue;
		percentValue = dValue * 100;
	}
	
	public double getDoubleValue(){
		System.out.println(abbreviatedCellText());
		System.out.println(fullCellText());
		return percentValue;
	}
	public String abbreviatedCellText(){
		if(percentValue > 0)
			return Spreadsheet.fillSpaces((int) (Math.floor(percentValue)) + "%");
		else
			return Spreadsheet.fillSpaces((int) (Math.ceil(percentValue)) + "%");
	}
	
	public String fullCellText(){
		return doubleValue + "";
	}
	
	public static void main(String[] args){
		PercentCell pc = new PercentCell("1234%");
		pc.getDoubleValue();
		System.out.println(1.1 / 100);
	} 
}