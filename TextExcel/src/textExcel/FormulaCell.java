package textExcel;

public class FormulaCell extends RealCell{
	
	private String formula;
	
	public FormulaCell(String input){
		formula = input;
	}
	
	public String abbreviatedCellText(){
		if(formula.length() > 10){
			return formula.substring(0, 10);
		}
		return Spreadsheet.fillSpaces(formula);
	}
	
	public String fullCellText(){
		return formula;
	}
}
