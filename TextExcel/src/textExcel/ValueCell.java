package textExcel;

public class ValueCell extends RealCell{
	
	private double value;
	private String strValue;
	
	public ValueCell(String input){
		super(input);
		if(!input.contains(".")){
			strValue = input + ".0";
		}
		value = Double.parseDouble(strValue);
	}
	
	public double getDoubleValue(){
		return value;
	}
	
	public String abbreviatedCellText(){
		return Spreadsheet.fillSpaces(strValue);
	}
}
