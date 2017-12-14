package textExcel;

public class FormulaCell extends RealCell{
	
	private String formula;
	
	public FormulaCell(String input){
		super(input);
		formula = input;
	}
	
}
