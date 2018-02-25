package textExcel;

public class FormulaCell extends RealCell{
	private Spreadsheet s;
	private String[] cellReferences;
	
	public FormulaCell(String input,Spreadsheet s){
		super(input);
		this.s = s;
		String[] operands = input.substring(2,input.length()-2).split(" ");
		if(operands[0].equalsIgnoreCase("sum") || operands[0].equalsIgnoreCase("avg"))
			operands[0] = "";
		int count = 0;
		int[] indexes = new int[20];
		for(int i = 0; i < operands.length; i+=2){
			if(Spreadsheet.containsLetter(operands[i])){
				indexes[count] = i;
				count++;
			}	
		}
		cellReferences = new String[count];
		for(int i = 0; i < cellReferences.length; i++){
			cellReferences[i] = operands[indexes[i]];
		}
	}
	public double getDoubleValue(){
		String input = super.getInput().substring(2,super.getInput().length()-2);
		String[] arr = input.split(" ");
		if(arr[0].equalsIgnoreCase("sum"))
			return calculate(arr[1],true);
		if(arr[0].equalsIgnoreCase("avg"))
			return calculate(arr[1],false);
		double dValue = 0.0;
		boolean containsBoth = false;
		if((input.contains("+") || input.contains(" - ")) && (input.contains("*") || input.contains("/")))
			containsBoth = true;
		if(containsBoth){
			for(int i = 1; i < arr.length; i+=2){
				if(arr[i].equals("*") || arr[i].equals("/")){
					String s = arr[i-1] + " " + arr[i] + " " + arr[i+1];
					String res = fix999(calculateOp(arr[i],setValue(arr[i-1]),setValue(arr[i+1]))) + "";
					if(res.contains("000"))
						res = res.substring(0, res.indexOf("000"));
					input = input.substring(0,input.indexOf(s)) + res + input.substring(input.indexOf(s)+s.length());
					arr = input.split(" ");
					if(i > 2)
						i -= 2;
				}
			}
		}
		arr = input.split(" ");
		dValue = setValue(arr[0]);
		for(int i = 1; i < arr.length; i+=2){
			double nextValue = setValue(arr[i+1]);
			dValue = calculateOp(arr[i],dValue,nextValue);
		}
		return fix999(dValue);
	}
	
	private double calculate(String cellRange,boolean isSum){
		SpreadsheetLocation topLeft = new SpreadsheetLocation(cellRange.substring(0,cellRange.indexOf("-")));
		SpreadsheetLocation bottomRight = new SpreadsheetLocation(cellRange.substring(cellRange.indexOf("-")+1));
		Cell[][] cells = s.getCells();
		double sum = 0.0;
		int numCells = 0;
		for(int row = topLeft.getRow(); row <= bottomRight.getRow(); row++){
			for(int col = topLeft.getCol(); col <= bottomRight.getCol(); col++){
				sum += cells[row][col].getDoubleValue();
				numCells ++;
			}
		}
		if(isSum)
			return sum;
		return sum/numCells;
	}
	
	public String abbreviatedCellText(){
		if(hasError())
			return "#ERROR    ";
		return Spreadsheet.fillSpaces(getDoubleValue()+"");
	}
	
	private double setValue(String num){
		if(Spreadsheet.containsLetter(num))
			return s.getCell(new SpreadsheetLocation(num)).getDoubleValue();
		return Double.parseDouble(num);
	}
	private double calculateOp(String op,double d1, double d2){
		if(op.equals("+"))
			return d1 + d2;
		if(op.equals("-"))
			return d1 - d2;
		if(op.equals("*"))
			return d1 * d2;
		return d1/d2;
	}
	private double fix999(double d){
		if((d+"").contains("999")){
			String s = d+"";
			s = s.substring(0, s.indexOf("999"));
			int decimalLength = s.substring(s.indexOf(".")+1).length();
			String toAdd = "0.";
			for(int t =0; t < decimalLength-1; t++)
				toAdd += "0";
			toAdd += "1";
			return Double.parseDouble(s) + Double.parseDouble(toAdd);
		}
		return d;
	}

	private boolean hasError(){
		for(int i = 0; i < cellReferences.length; i++){
			Cell c = s.getCell(new SpreadsheetLocation(cellReferences[i]));
			if(c.getClass() == EmptyCell.class || c.getClass() == TextCell.class)
				return true;
			if(c.abbreviatedCellText().contains("#ERROR"))
				return true;
			//TODO: Circular Reference Errors
		}
		if(super.getInput().contains(" / 0"))
			return true;
		String[] formula = super.getInput().substring(2,super.getInput().length()-2).split(" ");
		if(formula[0].equalsIgnoreCase("sum") || formula[0].equalsIgnoreCase("avg")){
			String cellRange = formula[1];
			SpreadsheetLocation topLeft = new SpreadsheetLocation(cellRange.substring(0,cellRange.indexOf("-")));
			SpreadsheetLocation bottomRight = new SpreadsheetLocation(cellRange.substring(cellRange.indexOf("-")+1));
			Cell[][] cells = s.getCells();
			for(int row = topLeft.getRow(); row <= bottomRight.getRow(); row++){
				for(int col = topLeft.getCol(); col <= bottomRight.getCol(); col++){
					Cell c = cells[row][col];
					if(c.getClass() == EmptyCell.class || c.getClass() == TextCell.class)
						return true;
					if(c.abbreviatedCellText().contains("#ERROR"))
						return true;
				}
			}
		}	
		for(int i = 1; i < formula.length; i++){
			double d = setValue(formula[i+1]);
			if(formula[i].equals("/") && d == 0.0)
				return true;
		}
		return false;
	}

}