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
		if(pValue >= 1)
			return Spreadsheet.fillSpaces(inputText.substring(0, inputText.indexOf(".")) + "%");
		else
			return Spreadsheet.fillSpaces("0%");
	}
	
	public String fullCellText(){
		if(inputText.indexOf(".") >= 2)
			return "0." + inputText.substring(0,inputText.indexOf(".")) + inputText.substring(inputText.indexOf(".")+1,inputText.length()-1);
		else if(inputText.indexOf(".") == 1)
			return "0.0" + inputText.substring(0,1) + inputText.substring(2,inputText.length()-1);
		else
			return "0.00" + inputText.substring(1);
	}
}
