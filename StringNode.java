// StringNode.java

package simonSays;


public class StringNode extends ConstNode {
	private String value;
	
	public StringNode(String value) {
		super(Constants.TYPE_STRING);
		
		this.value = value;
	}
	
	public String getValue() {return value;}
	public void setValue(String newValue) {value = newValue;}
}