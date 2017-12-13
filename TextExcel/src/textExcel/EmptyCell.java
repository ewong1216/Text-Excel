package textExcel;

public class EmptyCell implements Cell
{

	public String abbreviatedCellText(){
		return "          ";
	}

	@Override
	public String fullCellText(){
		// TODO Auto-generated method stub
		return null;
	}	
}
