// IntNode.java

package simonSays;


public class IntNode extends ConstNode {
	private int value;
	
	public IntNode(int value) {
		super(Constants.TYPE_INT);
		
		this.value = value;
	}
	
	public int getValue() {return value;}
	public void setValue(int newValue) {value = newValue;}
}