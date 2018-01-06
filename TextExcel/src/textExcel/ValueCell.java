package textExcel;

public class ValueCell extends RealCell{
	
	private double value;
	private String input;
	
	public ValueCell(String input){
		if(!input.contains(".")){
			System.out.println("inside");
			input += ".0";
		}
		this.input = input;
		value = Double.parseDouble(input);
	}
	
	public double getDoubleValue(){
		return value;
	}
	
	public String abbreviatedCellText(){
		if(input.length() > 10){
			return input.substring(0, 10);
		}
		return Spreadsheet.fillSpaces(input);
	}
	public String fullCellText(){
		return input;
	}
}