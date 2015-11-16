// Lexer.java

package simonSays;

import java.util.ArrayList;
import java.util.regex.*;


public class Lexer {
	private ArrayList<TokenDescriptor> allTokens;
	private ArrayList<Token> tokens;
	private String input;
	
	public Lexer(String input) {
		this.input = input;
		initAllTokens();
		this.tokens = new ArrayList<Token>();
	}
	
	public void initAllTokens() {
		Constants.initAllTokens();
		allTokens = Constants.allTokens;
	}
	
	public ArrayList<Token> lex() {
		Pattern p;
		Matcher match;
		MatchResult m;
		TokenDescriptor curTokenType;
		int longestMatchLength;
		Token longestMatch;
		boolean justHadNewline = false;  // Used to filter double-newlines
		
		while(input.length() > 0) {
			longestMatchLength = 0;
			longestMatch = null;
			
			for (int i = 0; i < allTokens.size(); i++) {
				curTokenType = allTokens.get(i);
				p = Pattern.compile(curTokenType.getRegExp());
				match = p.matcher(input);
				if (match.find()) m = match.toMatchResult();
				else continue;
				
				if (m.start() == 0) {
					Token newToken = new Token(curTokenType.getType(), input.substring(m.start(), m.start() + m.end()));
					
					// If token is longer than longest match, set new longest match to a token created for the purpose
					int length = m.end();
					
					if (length > longestMatchLength) {
						longestMatchLength = length;
						longestMatch = newToken;
					}
				}
			}
			
			if (longestMatch != null) {
				// Add the new token (filtering double-newlines)
				if (longestMatch.getType() != Constants.TOKEN_NEWLINE || (!justHadNewline)) tokens.add(longestMatch);
				if (longestMatch.getType() == Constants.TOKEN_NEWLINE) justHadNewline = true;
				else justHadNewline = false;
				input = input.substring(longestMatchLength, input.length());
			}
			else {  // Maybe it's a space? Advance by 1 until it goes away...
				input = input.substring(1, input.length());
			}
		}
		
		tokens.add(new Token(Constants.TOKEN_EOF, "EOF"));
		
		return tokens;
	}
}