package textExcel;

public class RealCell implements Cell{

	private String fullText;
	private String abbText;
	
	public RealCell(String input){
		fullText = input;
		if(fullText.length() <= 10)
			abbText = fullText;
		else
			abbText = Spreadsheet.fillSpaces(fullText);
	}
	
	public String abbreviatedCellText(){
		return abbText;
	}

	public String fullCellText(){
		return fullText;
	}
}
