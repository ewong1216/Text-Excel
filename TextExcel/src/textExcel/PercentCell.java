package textExcel;

public class PercentCell extends RealCell{
	
	private double pValue;
	
	public PercentCell(String input){
		super(input);
		pValue = Double.parseDouble(input.substring(0,input.length()-1));
	}
	
	public double getDoubleValue(){
		return pValue;
	}
}
