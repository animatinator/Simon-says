// Main.java

package simonSays;

import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;


public class Main {	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: simon <filename>");
			return;
		}
		
		String filename = args[0];
		System.out.println("Compiling file: " + filename);
		System.out.println();
		
		// Read from the file
		String text = "";
		
		try {
			FileReader input = new FileReader(filename);
			BufferedReader bufReader = new BufferedReader(input);
			
			String line;
			line = bufReader.readLine();
			
			while (line != null) {
				text += line+"\n";
				line = bufReader.readLine();
			}
			
			bufReader.close();
		} catch (Exception e) {  // Fuck you Java, I'm not going to play your exceptions game
			System.out.println("Ballsacks. " + e.getMessage());
		}
		
		System.out.println("Lexing...");
		Lexer lex = new Lexer(text);
		ArrayList<Token> tokens = lex.lex();
		
		for (int i = 0; i < tokens.size(); i++) {
			if (Constants.DEBUG_LEXER) System.out.println(i+": "+Token.getString(tokens.get(i)));
		}
		
		System.out.println("Lexing complete!");
		System.out.println();
		
		System.out.println("Generating syntax tree...");
		Parser parser = new Parser(tokens);
		SyntaxTree tree = parser.parse();
		System.out.println("Done!");
	}
}