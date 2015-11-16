// Constants.java

package simonSays;
import java.util.ArrayList;


public class Constants {
	public static final boolean DEBUG_LEXER = true;
	public static final boolean DEBUG_PARSER = true;
	public static final boolean DEBUG_TOKENSTREAM = false;
	
	public static ArrayList<TokenDescriptor> allTokens = new ArrayList<TokenDescriptor>();
	
	public static final int TOKEN_EOF = 0;
	public static final TokenDescriptor DTOKEN_EOF = new TokenDescriptor(TOKEN_EOF, "");
	public static final int TOKEN_NEWLINE = 1;
	public static final String REGEXP_NEWLINE = "\\n";
	public static final TokenDescriptor DTOKEN_NEWLINE = new TokenDescriptor(TOKEN_NEWLINE, REGEXP_NEWLINE);
	public static final int TOKEN_LINESTART = 2;
	public static final String REGEXP_LINESTART = "simon says";
	public static final TokenDescriptor DTOKEN_LINESTART = new TokenDescriptor(TOKEN_LINESTART, REGEXP_LINESTART);
	public static final int TOKEN_OP_ADD = 3;
	public static final String REGEXP_OP_ADD = "add";
	public static final TokenDescriptor DTOKEN_OP_ADD = new TokenDescriptor(TOKEN_OP_ADD, REGEXP_OP_ADD);
	public static final int TOKEN_OP_ADD_TO = 4;
	public static final String REGEXP_OP_ADD_TO = "to";
	public static final TokenDescriptor DTOKEN_OP_ADD_TO = new TokenDescriptor(TOKEN_OP_ADD_TO, REGEXP_OP_ADD_TO);
	public static final int TOKEN_OP_SUB = 5;
	public static final String REGEXP_OP_SUB = "take";
	public static final TokenDescriptor DTOKEN_OP_SUB = new TokenDescriptor(TOKEN_OP_SUB, REGEXP_OP_SUB);
	public static final int TOKEN_OP_SUB_FROM = 6;
	public static final String REGEXP_OP_SUB_FROM = "from";
	public static final TokenDescriptor DTOKEN_OP_SUB_FROM = new TokenDescriptor(TOKEN_OP_SUB_FROM, REGEXP_OP_SUB_FROM);
	public static final int TOKEN_OP_DIV = 7;
	public static final String REGEXP_OP_DIV = "divide";
	public static final TokenDescriptor DTOKEN_OP_DIV = new TokenDescriptor(TOKEN_OP_DIV, REGEXP_OP_DIV);
	public static final int TOKEN_OP_MULT = 8;
	public static final String REGEXP_OP_MULT = "multiply";
	public static final TokenDescriptor DTOKEN_OP_MULT = new TokenDescriptor(TOKEN_OP_MULT, REGEXP_OP_MULT);
	public static final int TOKEN_OP_DIV_MULT_BY = 9;
	public static final String REGEXP_OP_DIV_MULT_BY = "by";
	public static final TokenDescriptor DTOKEN_OP_DIV_MULT_BY = new TokenDescriptor(TOKEN_OP_DIV_MULT_BY, REGEXP_OP_DIV_MULT_BY);
	public static final int TOKEN_PRINT = 10;
	public static final String REGEXP_PRINT = "repeat after me:";
	public static final TokenDescriptor DTOKEN_PRINT = new TokenDescriptor(TOKEN_PRINT, REGEXP_PRINT);
	public static final int TOKEN_RETURN = 11;
	public static final String REGEXP_RETURN = "give back";
	public static final TokenDescriptor DTOKEN_RETURN = new TokenDescriptor(TOKEN_RETURN, REGEXP_RETURN);
	public static final int TOKEN_ASSN_PUT = 12;
	public static final String REGEXP_ASSN_PUT = "put";
	public static final TokenDescriptor DTOKEN_ASSN_PUT = new TokenDescriptor(TOKEN_ASSN_PUT, REGEXP_ASSN_PUT);
	public static final int TOKEN_ASSN_IN = 13;
	public static final String REGEXP_ASSN_IN = "in";
	public static final TokenDescriptor DTOKEN_ASSN_IN = new TokenDescriptor(TOKEN_ASSN_IN, REGEXP_ASSN_IN);
	public static final int TOKEN_VARNAME = 14;
	public static final String REGEXP_VARNAME = "the [a-zA-z][\\w]*";
	public static final TokenDescriptor DTOKEN_VARNAME = new TokenDescriptor(TOKEN_VARNAME, REGEXP_VARNAME);
	public static final int TOKEN_INT = 15;
	public static final String REGEXP_INT = "[0-9]+";
	public static final TokenDescriptor DTOKEN_INT = new TokenDescriptor(TOKEN_INT, REGEXP_INT);
	public static final int TOKEN_STRING = 16;
	public static final String REGEXP_STRING = "\"[\\w]*\"";
	public static final TokenDescriptor DTOKEN_STRING = new TokenDescriptor(TOKEN_STRING, REGEXP_STRING);
	public static final int TOKEN_BOOL_TRUE = 17;
	public static final String REGEXP_BOOL_TRUE = "true";
	public static final TokenDescriptor DTOKEN_BOOL_TRUE = new TokenDescriptor(TOKEN_BOOL_TRUE, REGEXP_BOOL_TRUE);
	public static final int TOKEN_BOOL_FALSE = 18;
	public static final String REGEXP_BOOL_FALSE = "false";
	public static final TokenDescriptor DTOKEN_BOOL_FALSE = new TokenDescriptor(TOKEN_BOOL_FALSE, REGEXP_BOOL_FALSE);
	public static final int TOKEN_BOOLEXPR_SMALLER = 19;
	public static final String REGEXP_BOOLEXPR_SMALLER = "is smaller than";
	public static final TokenDescriptor DTOKEN_BOOLEXPR_SMALLER = new TokenDescriptor(TOKEN_BOOLEXPR_SMALLER, REGEXP_BOOLEXPR_SMALLER);
	public static final int TOKEN_BOOLEXPR_BIGGER = 20;
	public static final String REGEXP_BOOLEXPR_BIGGER = "is bigger than";
	public static final TokenDescriptor DTOKEN_BOOLEXPR_BIGGER = new TokenDescriptor(TOKEN_BOOLEXPR_BIGGER, REGEXP_BOOLEXPR_BIGGER);
	public static final int TOKEN_BOOLEXPR_LIKE = 21;
	public static final String REGEXP_BOOLEXPR_LIKE = "is like";
	public static final TokenDescriptor DTOKEN_BOOLEXPR_LIKE = new TokenDescriptor(TOKEN_BOOLEXPR_LIKE, REGEXP_BOOLEXPR_LIKE);
	public static final int TOKEN_BOOLEXPR_NOTLIKE = 22;
	public static final String REGEXP_BOOLEXPR_NOTLIKE = "is not like";
	public static final TokenDescriptor DTOKEN_BOOLEXPR_NOTLIKE = new TokenDescriptor(TOKEN_BOOLEXPR_NOTLIKE, REGEXP_BOOLEXPR_NOTLIKE);
	public static final int TOKEN_FUNDEF_LEARN = 23;
	public static final String REGEXP_FUNDEF_LEARN = "learn to dance";
	public static final TokenDescriptor DTOKEN_FUNDEF_LEARN = new TokenDescriptor(TOKEN_FUNDEF_LEARN, REGEXP_FUNDEF_LEARN);
	public static final int TOKEN_FUNDEF_USING = 24;
	public static final String REGEXP_FUNDEF_USING = "using";
	public static final TokenDescriptor DTOKEN_FUNDEF_USING = new TokenDescriptor(TOKEN_FUNDEF_USING, REGEXP_FUNDEF_USING);
	public static final int TOKEN_FUNDEF_LEARNED = 25;
	public static final String REGEXP_FUNDEF_LEARNED = "you've learned";
	public static final TokenDescriptor DTOKEN_FUNDEF_LEARNED = new TokenDescriptor(TOKEN_FUNDEF_LEARNED, REGEXP_FUNDEF_LEARNED);
	public static final int TOKEN_FUNCALL_DO = 26;
	public static final String REGEXP_FUNCALL_DO = "do";
	public static final TokenDescriptor DTOKEN_FUNCALL_DO = new TokenDescriptor(TOKEN_FUNCALL_DO, REGEXP_FUNCALL_DO);
	public static final int TOKEN_FUNCALL_WITH = 27;
	public static final String REGEXP_FUNCALL_WITH = "with";
	public static final TokenDescriptor DTOKEN_FUNCALL_WITH = new TokenDescriptor(TOKEN_FUNCALL_WITH, REGEXP_FUNCALL_WITH);
	public static final int TOKEN_IF_MAYBE = 28;
	public static final String REGEXP_IF_MAYBE = "maybe";
	public static final TokenDescriptor DTOKEN_IF_MAYBE = new TokenDescriptor(TOKEN_IF_MAYBE, REGEXP_IF_MAYBE);
	public static final int TOKEN_IF_ELSEIF = 29;
	public static final String REGEXP_IF_ELSEIF = "but maybe";
	public static final TokenDescriptor DTOKEN_IF_ELSEIF = new TokenDescriptor(TOKEN_IF_ELSEIF, REGEXP_IF_ELSEIF);
	public static final int TOKEN_IF_ENDIF = 30;
	public static final String REGEXP_IF_ENDIF = "but then again";
	public static final TokenDescriptor DTOKEN_IF_ENDIF = new TokenDescriptor(TOKEN_IF_ENDIF, REGEXP_IF_ENDIF);
	public static final int TOKEN_LOOP_ASLONG = 31;
	public static final String REGEXP_LOOP_ASLONG = "as long as";
	public static final TokenDescriptor DTOKEN_LOOP_ASLONG = new TokenDescriptor(TOKEN_LOOP_ASLONG, REGEXP_LOOP_ASLONG);
	public static final int TOKEN_LOOP_STOP = 32;
	public static final String REGEXP_LOOP_STOP = "stop looping!";
	public static final TokenDescriptor DTOKEN_LOOP_STOP = new TokenDescriptor(TOKEN_LOOP_STOP, REGEXP_LOOP_STOP);
	public static final int TOKEN_COMMA = 33;
	public static final String REGEXP_TOKEN_COMMA = ",";
	public static final TokenDescriptor DTOKEN_COMMA = new TokenDescriptor(TOKEN_COMMA, REGEXP_TOKEN_COMMA);
	
	// Op types
	public static final int OP_ADD = 0;
	public static final int OP_SUB = 1;
	public static final int OP_DIV = 2;
	public static final int OP_MULT = 3;
	
	// Boolean expression types
	public static final int BOOL_GREATER = 0;
	public static final int BOOL_SMALLER = 1;
	public static final int BOOL_EQUAL = 2;
	public static final int BOOL_NOTEQUAL = 3;
	
	// Type system
	public static final int TYPE_UNDEFINED = 0;
	public static final int TYPE_INT = 1;
	public static final int TYPE_STRING = 2;
	public static final int TYPE_BOOL = 3;
	
	public static void initAllTokens() {
		allTokens.add(Constants.DTOKEN_EOF);
		allTokens.add(Constants.DTOKEN_NEWLINE);
		allTokens.add(Constants.DTOKEN_LINESTART);
		allTokens.add(Constants.DTOKEN_OP_ADD);
		allTokens.add(Constants.DTOKEN_OP_ADD_TO);
		allTokens.add(Constants.DTOKEN_OP_SUB);
		allTokens.add(Constants.DTOKEN_OP_SUB_FROM);
		allTokens.add(Constants.DTOKEN_OP_DIV);
		allTokens.add(Constants.DTOKEN_OP_MULT);
		allTokens.add(Constants.DTOKEN_OP_DIV_MULT_BY);
		allTokens.add(Constants.DTOKEN_PRINT);
		allTokens.add(Constants.DTOKEN_RETURN);
		allTokens.add(Constants.DTOKEN_ASSN_PUT);
		allTokens.add(Constants.DTOKEN_ASSN_IN);
		allTokens.add(Constants.DTOKEN_VARNAME);
		allTokens.add(Constants.DTOKEN_INT);
		allTokens.add(Constants.DTOKEN_STRING);
		allTokens.add(Constants.DTOKEN_BOOL_TRUE);
		allTokens.add(Constants.DTOKEN_BOOL_FALSE);
		allTokens.add(Constants.DTOKEN_BOOLEXPR_SMALLER);
		allTokens.add(Constants.DTOKEN_BOOLEXPR_BIGGER);
		allTokens.add(Constants.DTOKEN_BOOLEXPR_LIKE);
		allTokens.add(Constants.DTOKEN_BOOLEXPR_NOTLIKE);
		allTokens.add(Constants.DTOKEN_FUNDEF_LEARN);
		allTokens.add(Constants.DTOKEN_FUNDEF_USING);
		allTokens.add(Constants.DTOKEN_FUNDEF_LEARNED);
		allTokens.add(Constants.DTOKEN_FUNCALL_DO);
		allTokens.add(Constants.DTOKEN_FUNCALL_WITH);
		allTokens.add(Constants.DTOKEN_IF_MAYBE);
		allTokens.add(Constants.DTOKEN_IF_ELSEIF);
		allTokens.add(Constants.DTOKEN_IF_ENDIF);
		allTokens.add(Constants.DTOKEN_LOOP_ASLONG);
		allTokens.add(Constants.DTOKEN_LOOP_STOP);
		allTokens.add(Constants.DTOKEN_COMMA);
	}
}