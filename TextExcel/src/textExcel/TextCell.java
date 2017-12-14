package textExcel;

public class TextCell implements Cell{
	
	private String fullText;
	private String abbText;
	
	public TextCell(String cellText){
		fullText = cellText;
		if(fullText.length() >= 10)
			abbText = fullText.substring(0, 10);
		else
			abbText = Spreadsheet.fillSpaces(fullText);
	}
	
	public String abbreviatedCellText(){
		return abbText;
	}

	public String fullCellText(){
		return "\"" + fullText + "\"";
	}

}
