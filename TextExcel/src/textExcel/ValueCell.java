package textExcel;

public class ValueCell extends RealCell{
	
	private double value;
	private String input;
	
	public ValueCell(String input){
		this.input = input;
		if(!input.contains(".")){
			input += ".0";
		}
		value = Double.parseDouble(input);
	}
	
	public double getDoubleValue(){
		return value;
	}
	
	public String abbreviatedCellText(){
		if(input.length() > 10){
			return input.substring(0, 10);
		}
		return Spreadsheet.fillSpaces(value+"");
	}
	public String fullCellText(){
		return input;
	}
}