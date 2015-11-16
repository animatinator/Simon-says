javac simonSays/BoolNode.java
javac simonSays/Constants.java
javac simonSays/ConstNode.java
javac simonSays/IntNode.java
javac simonSays/Lexer.java
javac simonSays/Main.java
javac simonSays/NullNode.java
javac simonSays/OpNode.java
javac simonSays/Parser.java
javac simonSays/StringNode.java
javac simonSays/STypeError.java
javac simonSays/SyntaxTree.java
javac simonSays/Token.java
javac simonSays/TokenDescriptor.java
javac simonSays/VarNode.java

jar -cfe simon.jar simonSays.Main simonSays/*.*

@echo off
echo.
echo.
echo Finished!
echo.

set /p run="Run file? (y/n): "
if %run%==y java -jar simon.jar test.smn