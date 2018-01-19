package textExcel;

public class PercentCell extends RealCell{
	
	private double percentValue;
	private double doubleValue;
	
	public PercentCell(String input){
		percentValue = Double.parseDouble(input.substring(0,input.length()-1));
		doubleValue = percentValue / 100;
	}
	public PercentCell(Double dValue){
		doubleValue = dValue;
		percentValue = dValue * 100;
	}
	
	public double getDoubleValue(){
		return doubleValue;
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
}