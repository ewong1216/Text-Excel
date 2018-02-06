package textExcel;

public class FormulaCell extends RealCell{
	
	private String formula;
	private double dValue;
	
	public FormulaCell(String input){
		formula = input;
		String[] arr = formula.split(" ");
		for(int i = 0; i < (arr.length-2); i++){
			
		}
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
