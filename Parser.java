// Parser.java

package simonSays;

import java.util.ArrayList;
import java.util.HashMap;


public class Parser {
	/*
		FUNCTION CONTRACT:
		curToken is currently /viewed/ token (not yet consumed)
		tokenIndex points to the next curToken (getToken increments this /after/ updating curToken)
		a function runs under the assumption that it should be looking at curToken
		a function which fails should restore curToken to what it was before the function ran
		a completed function should advance curToken to the token after the last one it consumed
			(in keeping with the third line of the contract)
	*/
	
	private ArrayList<Token> tokenStream;
	private Token curToken;
	private int tokenIndex;
	private HashMap<String, Integer> declaredVariables;  // Used to find if variables have been defined yet (includes types)
	private ArrayList<String> declaredFunctions;  // Used to find it functions have been defined yet
	
	public Parser(ArrayList<Token> tokenStream) {
		this.tokenStream = tokenStream;
		tokenIndex = 0;
		getToken();
		
		this.declaredVariables = new HashMap<String, Integer>();
		this.declaredFunctions = new ArrayList<String>();
	}
	
	private void getToken() {
		if (tokenIndex < tokenStream.size()) {
			curToken = tokenStream.get(tokenIndex);
			tokenIndex++;
			if (Constants.DEBUG_TOKENSTREAM) System.out.println(curToken.getStringRepr());
		}
		else {
			System.out.println("ERROR: Attempted scan beyond length of token stream");
		}
	}
	
	private void rewindToken() {
		if (tokenIndex > 1) {
			tokenIndex--;
			curToken = tokenStream.get(tokenIndex - 1);
		}
	}
	
	private SyntaxTree rdLine() {
		if (curToken.getType() == Constants.TOKEN_LINESTART) {
			if (Constants.DEBUG_PARSER) System.out.println("Line starting...");
			getToken();
			rdExpr();
			if (curToken.getType() == Constants.TOKEN_NEWLINE) {
				getToken();
				return new NullNode();
			}
			else {
				System.out.println("Errorzor! Expected newline, got \'"+curToken.getStringRepr()+"\'!");
				return null;
			}
		}
		else {
			if (Constants.DEBUG_PARSER) System.out.println("Invalid line start token: " + curToken.getStringRepr());
			return null;
		}
	}
	
	private SyntaxTree rdExpr() {
		if (Constants.DEBUG_PARSER) System.out.println("Parsing the line's expression...");
		SyntaxTree result;
		
		result = rdAssn();
		if (result == null) result = rdOp();
		if (result == null) result = rdCmd();
		if (result == null) result = rdDefun();
		if (result == null) {  // No match!
			System.out.println("Oh noes! An expression can't start with the token \'"+curToken.getStringRepr()+"\'!");
			return null;
		}
		// Otherwise, return what we've got
		else return new NullNode();
	}
	
	private SyntaxTree rdAssn() {
		if (Constants.DEBUG_PARSER) System.out.println("Assignment?");
		if (curToken.getType() == Constants.TOKEN_ASSN_PUT) {  // Check for 'put' keyword
			if (Constants.DEBUG_PARSER) System.out.println("Reading an assign!");
			getToken();
			
			// Read the constant value to be stored
			SyntaxTree t = rdConstant();
			if (t == null) {
				System.out.println("Constant value expected in assigment");
				return null;
			}
			
			if (curToken.getType() == Constants.TOKEN_ASSN_IN) getToken();  // Check for 'in'
			else {
				System.out.println("Invalid token during assignment: \'"+curToken.getStringRepr()+"\' (expected \'in\')");
				return null;
			}
			
			// Read the varname in which to store the constant value
			if (curToken.getType() == Constants.TOKEN_VARNAME) {
				getToken();
				return new NullNode();
			}
			else {
				System.out.println("Invalid token during assignment - expected varname, got \'"+curToken.getStringRepr()+"\'");
				return null;
			}
		}
		else return null;
	}
	
	private SyntaxTree rdOp() {
		if (Constants.DEBUG_PARSER) System.out.println("Op?");
		int opType;
		
		// Work out the op type based on the first token ('add', 'take' etc.)
		if (curToken.getType() == Constants.TOKEN_OP_ADD) {
			opType = Constants.TOKEN_OP_ADD;
			getToken();
		}
		else if (curToken.getType() == Constants.TOKEN_OP_SUB) {
			opType = Constants.TOKEN_OP_SUB;
			getToken();
		}
		else if (curToken.getType() == Constants.TOKEN_OP_DIV) {
			opType = Constants.TOKEN_OP_DIV;
			getToken();
		}
		else if (curToken.getType() == Constants.TOKEN_OP_MULT) {
			opType = Constants.TOKEN_OP_MULT;
			getToken();
		}
		else return null;
		
		if (Constants.DEBUG_PARSER) System.out.println("Yep B-)");
		
		// Get the two operands
		SyntaxTree operand1;
		
		// Adding or subtracting -> constant op variable
		if (opType == Constants.TOKEN_OP_ADD || opType == Constants.TOKEN_OP_SUB) {
			operand1 = rdConstant();
		}
		else {
			if (curToken.getType() == Constants.TOKEN_VARNAME) {
				if (Constants.DEBUG_PARSER) System.out.println("Got a varname! "+curToken.getStringRepr()+"!");
				operand1 = new NullNode();  // TODO: Use the variable
				getToken();
			}
			else {
				if (Constants.DEBUG_PARSER) System.out.println("Invalid operand supplied: \'"+curToken.getStringRepr()+"\'");
				return null;
			}
		}
		
		// Check to see if the appropriate token has been used between operands
		// (e.g. add x TO y, take b FROM a etc.)
		if (curToken.getType() == Constants.TOKEN_OP_ADD_TO && opType == Constants.TOKEN_OP_ADD) getToken();
		else if (curToken.getType() == Constants.TOKEN_OP_SUB_FROM && opType == Constants.TOKEN_OP_SUB) getToken();
		else if (curToken.getType() == Constants.TOKEN_OP_DIV_MULT_BY && 
			(opType == Constants.TOKEN_OP_DIV || opType == Constants.TOKEN_OP_MULT)) getToken();
		else {
			System.out.println("Invalid token in between operands: \'"+curToken.getStringRepr()+"\'");
			return null;
		}
		
		SyntaxTree operand2;
		
		// Dividing or multiplying -> variable op constant
		if (opType == Constants.TOKEN_OP_DIV || opType == Constants.TOKEN_OP_MULT) {
			operand2 = rdConstant();
		}
		else {
			if (curToken.getType() == Constants.TOKEN_VARNAME) {
				if (Constants.DEBUG_PARSER) System.out.println("Got a varname! "+curToken.getStringRepr()+"!");
				operand1 = new NullNode();  // TODO: Use the variable
				getToken();
			}
			else {
				System.out.println("Invalid operand supplied: \'"+curToken.getStringRepr()+"\'");
				return null;
			}
		}
		
		return new NullNode();
	}
	
	private SyntaxTree rdCmd() {
		if (Constants.DEBUG_PARSER) System.out.println("Command?");
		if (curToken.getType() == Constants.TOKEN_PRINT) {  // Print statements ('repeat after me')
			if (Constants.DEBUG_PARSER) System.out.println("Print something!");
			getToken();
			SyntaxTree t = rdConstant();  // The thing to print
			
			if (t != null) return t;
			
			else {
				System.out.println("Error: must supply a value to a \'repet after me\' command!");
				return null;
			}
		}
		else if (curToken.getType() == Constants.TOKEN_RETURN) {  // Return statements ('give back')
			if (Constants.DEBUG_PARSER) System.out.println("Returning something!");
			getToken();
			SyntaxTree t = rdConstant();  // The thing to return
			
			if (t != null) return t;
			
			else {
				System.out.println("Error: must supply a value to be returned in \'return\' statement!");
				return null;
			}
		}
		else if (curToken.getType() == Constants.TOKEN_FUNCALL_DO) {  // Function calls
			SyntaxTree t = rdFuncall();
			if (t != null) return t;
			else {
				System.out.println("Error reading function call!");
				return null;
			}
		}
		else if (curToken.getType() == Constants.TOKEN_IF_MAYBE) {
			SyntaxTree t = rdIfs();
			if (t != null) return t;
			else {
				System.out.println("Error reading \'if\' statements!");
				return null;
			}
		}
		else if (curToken.getType() == Constants.TOKEN_LOOP_ASLONG) {
			SyntaxTree t = rdLoop();
			if (t != null) return t;
			else {
				System.out.println("Error reading loop statement!");
				return null;
			}
		}
		else return null;
	}
	
	private SyntaxTree rdDefun() {
		if (Constants.DEBUG_PARSER) System.out.println("Function definition?");
		if (curToken.getType() == Constants.TOKEN_FUNDEF_LEARN) {  // Check for the 'learn to dance' token
			if (Constants.DEBUG_PARSER) System.out.println("Defining a function!");
			getToken();
			
			if (curToken.getType() == Constants.TOKEN_VARNAME) {  // The function's varname
				if (Constants.DEBUG_PARSER) System.out.println("Function name is \'"+Token.getString(curToken)+"\'");
				declaredFunctions.add(Token.getString(curToken));  // Add to the list of defined functions
				getToken();
				
				if (curToken.getType() == Constants.TOKEN_FUNDEF_USING) {  // The 'using' keyword (followed by argslist)
					getToken();
					rdArgsList();  // Read the args list
					
					// Check for the newline at the end of the function header
					if (curToken.getType() == Constants.TOKEN_NEWLINE) getToken();
					else {
						System.out.println("Newline expected after first line of function declaration!");
						return null;
					}
					
					// Read the function body
					while (true) {
						if (Constants.DEBUG_PARSER) System.out.println("Finished the definition?");
						SyntaxTree t = rdEnddef();  // See if a definition close can be read
						
						if (t != null) {  // Definition is complete
							rewindToken();  // Function definitions read onto the next line,
							// so this must be undone for calling rdLine to complete successfully
							return new NullNode();
						}
						else {  // If not, read another line of the body
							if (Constants.DEBUG_PARSER) System.out.println("Nope!");
							t = rdLine();
						}
					}
				}
				else {
					System.out.println("Function must have an args list! Got \'"+curToken.getStringRepr()+"\' instead");
					return null;
				}
			}
			else {
				System.out.println("Invalid token: \'"+curToken.getStringRepr()+"\'; expecting a variable name");
				return null;
			}
		}
		else return null;
	}
	
	private SyntaxTree rdArgsList() {
		if (curToken.getType() == Constants.TOKEN_VARNAME) {  // Look for the initial variable
			if (Constants.DEBUG_PARSER) System.out.println("Argument: "+Token.getString(curToken));
			getToken();
			
			while (true) {  // Loop through ", varname" pairs to find all the included arguments
				// If the current token is a comma, the list is still going
				if (curToken.getType() == Constants.TOKEN_COMMA) {
					getToken();
					if (curToken.getType() == Constants.TOKEN_VARNAME) {  // Read the variable name
						if (Constants.DEBUG_PARSER) System.out.println("Argument: "+Token.getString(curToken));
						getToken();
					}
					else {
						System.out.println("Extra comma at end of args list with no argument following");
					}
				}
				
				else return new NullNode();
			}
		}
		
		else {
			System.out.println("Error in args list - expected a varname, got \'"+curToken.getStringRepr()+"\'");
			return null;
		}
	}
	
	private SyntaxTree rdFuncallArgs() {
		SyntaxTree t = rdConstant();  // The first argument
		if (t != null) {
			if (Constants.DEBUG_PARSER) System.out.println("Argument: "+Token.getString(tokenStream.get(tokenIndex - 2)));
			
			while (true) {  // Loop through remaining arguments
				// Got a comma? Got another argument!
				if (curToken.getType() == Constants.TOKEN_COMMA) {
					getToken();
					t = rdConstant();  // Try to read the argument
					
					if (t != null) {  // Gottit!
						if (Constants.DEBUG_PARSER) System.out.println("Argument: "+Token.getString(tokenStream.get(tokenIndex - 2)));
					}
					else {  // Nothing there :/
						System.out.println("Extra comma at end of args list with no argument following");
					}
				}
				
				else return new NullNode();
			}
		}
		
		else {  // No arguments supplied! D:
			System.out.println("Error in args supplied to function call - expected a constant value, got \'"+curToken.getStringRepr()+"\'");
			return null;
		}
	}
	
	private SyntaxTree rdEnddef() {
		// Needs to start with 'simon says'
		if (curToken.getType() == Constants.TOKEN_LINESTART) {
			getToken();
			
			if (curToken.getType() == Constants.TOKEN_FUNDEF_LEARNED) {  // 'you've learned'
				getToken();
				if (curToken.getType() == Constants.TOKEN_VARNAME) {  // The function's varname
					getToken();
					if (curToken.getType() != Constants.TOKEN_NEWLINE) {  // Need a newline at the end!
						System.out.println("Expected a newline at the end of function definition close");
						return null;
					}
					
					if (Constants.DEBUG_PARSER) System.out.println("Function definition completed!");
					getToken();
					return new NullNode();
				}
				else {
					System.out.println("Error reading function definition close - expected function name, got \'"+curToken.getStringRepr()+"\'");
					return null;
				}
			}
			else {
				rewindToken();
				return null;
			}
		}
		
		else return null;
	}
	
	private SyntaxTree rdConstant() {
		if (Constants.DEBUG_PARSER) System.out.println("Reading constant value");
		if (curToken.getType() == Constants.TOKEN_VARNAME) {  // Variable name
			if (Constants.DEBUG_PARSER) System.out.println("Got a varname: "+Token.getString(curToken)+"!");
			VarNode n = new VarNode(Token.getString(curToken));
			getToken();
			return n;
		}
		else {  // Must be a value then, so try to read one
			SyntaxTree t;
			t = rdValue();
			
			if (t == null) {  // Didn't get one. No other options for constant; evidently the programmer is a failure
				System.out.println("Invalid constant value: \'"+curToken.getStringRepr()+"\'");
				return null;
			}
			
			else return t;
		}
	}
	
	private SyntaxTree rdValue() {
		if (Constants.DEBUG_PARSER) System.out.println("Reading a value");
		if (curToken.getType() == Constants.TOKEN_INT) {  // Check for an integer literal
			if (Constants.DEBUG_PARSER) System.out.println("Got an integer constant!");
			int val = Integer.parseInt(curToken.getStringRepr());
			getToken();
			return new IntNode(val);
		}
		else if (curToken.getType() == Constants.TOKEN_STRING) {  // Check for a string literal
			if (Constants.DEBUG_PARSER) System.out.println("Got a string constant!");
			String val = curToken.getStringRepr().substring(1, curToken.getStringRepr().length() - 1);
			getToken();
			return new StringNode(val);
		}
		// Check for a boolean
		else if (curToken.getType() == Constants.TOKEN_BOOL_TRUE || curToken.getType() == Constants.TOKEN_BOOL_FALSE) {
			if (Constants.DEBUG_PARSER) System.out.println("Got a constant boolean value!");
			boolean val = (curToken.getType() == Constants.TOKEN_BOOL_TRUE);
			getToken();
			return new BoolNode(val);
		}
		else {  // Otherwise, check for a function call
			SyntaxTree t;
			t = rdFuncall();
			
			if (t == null) {  // Didn't find one - this can't be a value
				System.out.println("Non-value token found: \'"+curToken.getStringRepr()+"\'");
				return null;
			}
			
			else return new NullNode();
		}
	}
	
	private SyntaxTree rdFuncall() {
		// Check for the 'do' keyword
		if (curToken.getType() == Constants.TOKEN_FUNCALL_DO) getToken();
		else return null;
		// Check for the function's name
		if (curToken.getType() == Constants.TOKEN_VARNAME) {
			if (!functionDeclared(Token.getString(curToken))) {
				System.out.println("Undefined function: \'" + Token.getString(curToken) + "\'!");
				rewindToken();
				return null;
			}
			getToken();
		}
		else {
			rewindToken();  // Need to rewind to the point in the token stream we were at before checking for a function call
			return null;
		}
		// Check for the 'with' keyword
		if (curToken.getType() == Constants.TOKEN_FUNCALL_WITH) getToken();
		else {
			rewindToken();
			rewindToken();
			return null;
		}
		
		if (Constants.DEBUG_PARSER) System.out.println("Waiting for an argslist guys!!!");
		SyntaxTree t = rdFuncallArgs();  // Try and read the list of supplied arguments
		
		if (t != null) return new NullNode();  // All good
		else {  // All bad
			System.out.println("Problem reading args list!");
			return null;
		}
	}
	
	private SyntaxTree rdIfs() {
		if (curToken.getType() == Constants.TOKEN_IF_MAYBE) {
			if (Constants.DEBUG_PARSER) System.out.println("Got an \'if\' statement!");
			getToken();
		}
		else return null;
		
		SyntaxTree b = rdBoolExpr();
		if (b == null) {
			System.out.println("Invalid boolean expression supplied to conditional branch!");
			return null;
		}
		
		getToken();
		
		// Read all ifs and elseifs
		while (true) {
			// Read the current conditional section
			while (true) {
				//if (Constants.DEBUG_PARSER) System.out.println("Finished the statement body?");
				
				if (!(reachedElseif() || reachedEndif())) {
					//if (Constants.DEBUG_PARSER) System.out.println("Nope!");
					SyntaxTree t = rdLine();
				}
				else break;
			}
			
			SyntaxTree e = rdEndif();
			
			if (e != null) return new NullNode();
			else {
				e = rdElseif();
				if (e != null) {		
					b = rdBoolExpr();
					if (b == null) {
						System.out.println("Invalid boolean expression supplied to conditional branch!");
						return null;
					}
					
					getToken();  // Advance over the newline
					continue;
				}
				else {
					System.out.println("Syntax error in conditional blocks");
					return null;
				}
			}
		}
	}
	
	private boolean reachedElseif() {
		if (curToken.getType() == Constants.TOKEN_LINESTART) getToken();
		else return false;
		if (Constants.DEBUG_PARSER) System.out.println("Elseif?");
		
		boolean result;
		
		if (curToken.getType() == Constants.TOKEN_IF_ELSEIF) result = true;
		else result = false;
		if (Constants.DEBUG_PARSER && result) System.out.println("Yep");
		
		rewindToken();
		return result;
	}
	
	private SyntaxTree rdElseif() {
		if (curToken.getType() == Constants.TOKEN_LINESTART) getToken();
		else return null;
		
		if (curToken.getType() == Constants.TOKEN_IF_ELSEIF) {
			getToken();
			return new NullNode();
		}
		else {
			rewindToken();
			return null;
		}
	}
	
	private boolean reachedEndif() {
		if (curToken.getType() == Constants.TOKEN_LINESTART) getToken();
		else return false;
		if (Constants.DEBUG_PARSER) System.out.println("Endif?");
		
		boolean result;
		
		if (curToken.getType() == Constants.TOKEN_IF_ENDIF) result = true;
		else result = false;
		if (Constants.DEBUG_PARSER && result) System.out.println("Yep");
		
		rewindToken();
		return result;
	}
	
	private SyntaxTree rdEndif() {
		if (curToken.getType() == Constants.TOKEN_LINESTART) getToken();
		else return null;
		
		if (curToken.getType() == Constants.TOKEN_IF_ENDIF) {
			getToken();
			return new NullNode();
		}
		else {
			rewindToken();
			return null;
		}
	}
	
	private SyntaxTree rdLoop() {
		if (curToken.getType() == Constants.TOKEN_LOOP_ASLONG) {
			if (Constants.DEBUG_PARSER) System.out.println("Reading a loop!");
			getToken();
		}
		else return null;
		
		SyntaxTree b = rdBoolExpr();
		if (b == null) {
			System.out.println("Invalid boolean expression supplied to conditional loop!");
			return null;
		}
		
		getToken();
		
		// Read all lines of the loop
		while (true) {
			if (!reachedEndLoop()) {
				if (Constants.DEBUG_PARSER) System.out.println("Next line of the loop coming up!");
				SyntaxTree t = rdLine();
			}
			else break;
		}
		
		if (Constants.DEBUG_PARSER) System.out.println("Endloop B-)");
		rdEndLoop();
		
		return new NullNode();
	}
	
	private boolean reachedEndLoop() {
		if (curToken.getType() == Constants.TOKEN_LINESTART) getToken();
		else return false;
		
		boolean result;
		
		if (curToken.getType() == Constants.TOKEN_LOOP_STOP) result = true;
		else result = false;
		
		rewindToken();
		return result;
	}
	
	private SyntaxTree rdEndLoop() {
		if (curToken.getType() == Constants.TOKEN_LINESTART) getToken();
		else return null;
		
		if (curToken.getType() == Constants.TOKEN_LOOP_STOP) {
			getToken();
			return new NullNode();
		}
		else {
			rewindToken();
			return null;
		}
	}
	
	private SyntaxTree rdBoolExpr() {
		if (curToken.getType() == Constants.TOKEN_BOOL_TRUE || curToken.getType() == Constants.TOKEN_BOOL_FALSE) {
			boolean val = (curToken.getType() == Constants.TOKEN_BOOL_TRUE);
			getToken();
			return new BoolNode(val);
		}
		else {
			SyntaxTree t = rdConstant();  // Read the first constant
			
			if (t == null) {
				System.out.println("Invalid boolean expression!");
				return null;
			}
			
			// Check for a middle value
			if (curToken.getType() == Constants.TOKEN_BOOLEXPR_SMALLER
				|| curToken.getType() == Constants.TOKEN_BOOLEXPR_BIGGER
				|| curToken.getType() == Constants.TOKEN_BOOLEXPR_LIKE
				|| curToken.getType() == Constants.TOKEN_BOOLEXPR_NOTLIKE) getToken();
			else return null;
			
			t = rdConstant();  // Read the second constant
			
			if (t == null) {
				System.out.println("Invalid boolean expression!");
				return null;
			}
			
			return new NullNode();
		}
	}
	
	private boolean functionDeclared(String name) {
		for (int i = 0; i < declaredFunctions.size(); i++) {
			if (declaredFunctions.get(i).equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	public SyntaxTree parse() {
		if (Constants.DEBUG_PARSER) System.out.println("Parsing!");
		
		while (true) {  // Loop through all the lines in the source until an EOF token is reached
			if (curToken.getType() == Constants.TOKEN_EOF) {
				if (Constants.DEBUG_PARSER) System.out.println("Parsed successfully!");
				return new NullNode();
			}
			else {
				SyntaxTree t = rdLine();
				if (t == null) {
					System.out.println("Error parsing file - no EOF token found");
					return null;
				}
			}
		}
	}
}