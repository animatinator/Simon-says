// TokenDescriptor.java

package simonSays;


public class TokenDescriptor {
	private int type;
	private String regExp;
	
	public TokenDescriptor(int type, String regExp) {
		this.type = type;
		this.regExp = regExp;
	}
	
	public int getType() {
		return type;
	}
	
	public String getRegExp() {
		return regExp;
	}
}