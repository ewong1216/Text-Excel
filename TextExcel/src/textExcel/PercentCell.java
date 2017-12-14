package textExcel;

public class PercentCell extends RealCell{
	
	private double pValue;
	private String inputText;
	
	public PercentCell(String input){
		super(input);
		inputText = input;
		pValue = Double.parseDouble(input.substring(0,input.length()-1));
	}
	
	public double getDoubleValue(){
		return pValue;
	}
	
	public String abbreviatedCellText(){
		return Spreadsheet.fillSpaces(inputText.substring(0, inputText.indexOf(".")) + "%");
	}
	
	public String fullCellText(){
		if(inputText.indexOf(".") <= 2){
			return "." +inputText.substring(0,inputText.indexOf(".")) + inputText.substring(inputText.indexOf(".")+1,inputText.length()-1);
		}
		return "lol";
	}
}
