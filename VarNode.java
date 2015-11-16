// VarNode.java

package simonSays;


public class VarNode extends ConstNode {
	private String identifier;
	
	public VarNode(String identifier) {
		super(Constants.TYPE_UNDEFINED);
		
		this.identifier = identifier;
	}
	
	public String getIdentifier() {return identifier;}
	public void setIdentifier(String newIdentifier) {identifier = newIdentifier;}
}