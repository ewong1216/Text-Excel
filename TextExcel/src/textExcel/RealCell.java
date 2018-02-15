package textExcel;

public class RealCell implements Cell{
	
	private double dValue;
	private String input;
	
	public RealCell(String i){
		input = i;
		if(i.contains("%") || i.contains("("))
			return;
		if(!input.contains("."))
			input += ".0";
		dValue = Double.parseDouble(input);
	}
	public RealCell(double d){
		dValue = d;
		input = d+"";
	}
	public String getInput(){
		return input;
	}
	public double getDoubleValue(){
		return dValue;
	}
	public void setDoubleValue(Double d){
		dValue = d;
	}
	public String abbreviatedCellText(){
		return Spreadsheet.fillSpaces(input);
	}
	public String fullCellText(){
		return input;
	}
}