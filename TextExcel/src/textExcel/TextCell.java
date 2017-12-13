package textExcel;

public class TextCell implements Cell{
	
	private String fullText;
	private String abbText;
	
	public TextCell(String cellText){
		fullText = cellText;
		if(fullText.length() >= 10){
			abbText = fullText.substring(0, 10);
		}
		else{
			abbText = fillSpaces(fullText);
		}
	}
	
	public static String fillSpaces(String input){
		if(input.length() < 10){
			for(int i = input.length(); i < 10; i++){
				input += " ";
			}
		}
		return input;
	}
	public String abbreviatedCellText(){
		return abbText;
	}

	public String fullCellText(){
		return fullText;
	}

}
