// Token.java

package simonSays;


public class Token {
	private int type = 0;
	private String stringRepr = "";
	
	public Token(int type, String stringRepr) {
		this.type = type;
		this.stringRepr = stringRepr;
	}
	
	public int getType() {
		return type;
	}
	
	public String getStringRepr() {
		return stringRepr;
	}
	
	public static String getString(Token token) {
		switch(token.getType()) {
			case Constants.TOKEN_EOF:
				return "EOF";
			case Constants.TOKEN_LINESTART:
				return "linestart";
			case Constants.TOKEN_NEWLINE:
				return "newline";
			case Constants.TOKEN_PRINT:
				return "print";
			case Constants.TOKEN_RETURN:
				return "return";
			case Constants.TOKEN_ASSN_PUT:
				return "assign";
			case Constants.TOKEN_ASSN_IN:
				return "to";
			case Constants.TOKEN_FUNDEF_LEARN:
				return "define function";
			case Constants.TOKEN_FUNDEF_USING:
				return "taking vars";
			case Constants.TOKEN_FUNDEF_LEARNED:
				return "end def";
			case Constants.TOKEN_FUNCALL_DO:
				return "funcall";
			case Constants.TOKEN_FUNCALL_WITH:
				return "with";
			case Constants.TOKEN_VARNAME:
				return token.getStringRepr().split(" ")[1];
			default:
				return token.getStringRepr();
		}
	}
}