package textExcel;

public class ValueCell extends RealCell{
	
	private double value;
	
	public ValueCell(String input){
		super(input);
		value = Double.parseDouble(input);
	}
	
	public double getDoubleValue(){
		return value;
	}
}