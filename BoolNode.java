// BoolNode.java

package simonSays;


public class BoolNode extends ConstNode {
	private boolean value;
	
	public BoolNode(boolean value) {
		super(Constants.TYPE_BOOL);
		
		this.value = value;
	}
	
	public boolean getValue() {return value;}
	public void setValue(boolean newValue) {value = newValue;}
}