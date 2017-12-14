package textExcel;

public class RealCell implements Cell{

	private String fullText;
	private String abbText;
	
	public RealCell(String input){
		fullText = input;
		if(fullText.length() >= 10)
			abbText = fullText.substring(0, 10);
		else
			abbText = Spreadsheet.fillSpaces(fullText);
	}
	public double getDoubleValue(){
		return 0.0;
	}
	public String abbreviatedCellText(){
		return abbText;
	}

	public String fullCellText(){
		return fullText;
	}
}
