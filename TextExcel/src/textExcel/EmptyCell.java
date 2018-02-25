package textExcel;

public class EmptyCell implements Cell{
	public double getDoubleValue(){
		return 0.0;
	}
	public String abbreviatedCellText(){
		return "          ";
	}

	public String fullCellText(){
		return "";
	}	
}
