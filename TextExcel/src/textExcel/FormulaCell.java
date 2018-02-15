package textExcel;

public class FormulaCell extends RealCell{
	private Spreadsheet s;
	
	public FormulaCell(String input,Spreadsheet s){
		super(input);
		this.s = s;
	}
	
	public double getDoubleValue(){
		String input = super.getInput();
		String[] arr = input.substring(2,input.length()-2).split(" ");
		if(arr[0].equalsIgnoreCase("sum"))
			return calculate(arr[1],true);
		if(arr[0].equalsIgnoreCase("avg"))
			return calculate(arr[1],false);
		double dValue = setValue(arr[0]);
		boolean containsBoth = false;
		if((input.contains("+") || input.contains("-")) && (input.contains("*") || input.contains("/")))
			containsBoth = true;
		if(containsBoth){
			for(int i = 1; i < arr.length; i +=2){
				double nextValue = setValue(arr[i+1]);
				double temp = setValue(arr[i-1]);
				if(arr[i].equals("*") || arr[i].equals("/")){
					if(i == 1)
						arr[i+1] = calculateOp(arr[i],temp,nextValue) + "";
					else
						arr[i-1] = calculateOp(arr[i],temp,nextValue) + "";
					arr[i] = "";
				}
			}
			for(int i = 1; i < arr.length; i +=2){
				double nextValue = setValue(arr[i+1]);
				if(!arr[i].isEmpty()){
					dValue = calculateOp(arr[i],dValue,nextValue);
				}
			}
		}
		else{
			for(int i = 1; i < arr.length; i+=2){
				double nextValue = setValue(arr[i+1]);
				dValue = calculateOp(arr[i],dValue,nextValue);
			}
		}
		if((dValue+"").contains("999")){
			String s = dValue+"";
			s = s.substring(0, s.indexOf("999"));
			int decimalLength = s.substring(s.indexOf(".")+1).length();
			String toAdd = "0.";
			for(int t =0; t < decimalLength-1; t++)
				toAdd += "0";
			toAdd += "1";
			dValue = Double.parseDouble(s) + Double.parseDouble(toAdd);
		}
		return dValue;
	}
	
	private double calculate(String cellRange,boolean isSum){
		SpreadsheetLocation topLeft = new SpreadsheetLocation(cellRange.substring(0,cellRange.indexOf("-")));
		SpreadsheetLocation bottomRight = new SpreadsheetLocation(cellRange.substring(cellRange.indexOf("-")+1));
		Cell[][] cells = s.getCells();
		double sum = 0.0;
		int numCells = 0;
		for(int row = topLeft.getRow(); row <= bottomRight.getRow(); row++){
			for(int col = topLeft.getCol(); col <= bottomRight.getCol(); col++){
				sum += (cells[row][col]).getDoubleValue();
				numCells ++;
			}
		}
		if(isSum)
			return sum;
		return sum/numCells;
	}
	public String abbreviatedCellText(){	
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
}
