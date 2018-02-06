package textExcel;

public class PercentCell extends RealCell{
	
	private double percentValue;
	
	public PercentCell(String input){
		super(input);
		percentValue = Double.parseDouble(input.substring(0,input.length()-1));
		super.setDoubleValue(percentValue / 100);
	}
	public PercentCell(Double dValue){
		super(dValue);
		super.setDoubleValue(dValue);
		percentValue = dValue * 100;
	}
	
	public String abbreviatedCellText(){
		if(percentValue > 0)
			return Spreadsheet.fillSpaces((int) (Math.floor(percentValue)) + "%");
		else
			return Spreadsheet.fillSpaces((int) (Math.ceil(percentValue)) + "%");
	}
	
	public String fullCellText(){
		return super.getDoubleValue() + "";
	}
	
}