// SyntaxTree.java

package simonSays;


public class SyntaxTree {
	int type;  // As in int, string etc.
	
	public SyntaxTree(int type) {
		this.type = type;
	}
	
	public int getType() {return type;}
	public void setType(int newType) {type = newType;}
}