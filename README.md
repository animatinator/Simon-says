# Simon-says
An interpreter for a simple language based on the game 'Simon Says'

## Sample programs

#### Define a function `test(shizzle, bizzle)` returning `shizzle`
```
simon says learn to dance the test using the shizzle, the bizzle
simon says give back the shizzle
simon says you've learned the test
```

#### Define a function `addition(a, b)` which returns the sum of its arguments
```
simon says learn to dance the addition using the a, the b
simon says add the b to the a
simon says give back the a
simon says you've learned the addition
```

#### Store a value `cheese` and call `test(cheese, 9)`, storing the result in `cheese`
```
simon says put 4 in the cheese
simon says put do the test with the cheese, 9 in the cheese
```

#### Print the result of `add(3, cheese)`
```
simon says repeat after me: do the addition with 3, the cheese
```

#### A more complicated example
Note that anything not starting with `simon says` is a comment.

```
if 3 > 1, print 'test'
simon says maybe 3 is bigger than 1
simon says repeat after me: "test"

initialise a counter to zero
simon says put 0 in the counter
While counter < 10
simon says as long as the counter is smaller than 10
print the value of the counter
simon says repeat after me: the counter
End while
simon says stop looping!

If 1 > 100, print "Wrong".
simon says but maybe 1 is bigger than 1000
simon says repeat after me: "Wrong"
End if
simon says but then again
```

## Language grammar

```
PROG := LINE PROG | LINE EOF
LINE := LINESTART EXPR \n | COMMENT \n
LINESTART := simon says
COMMENT := [any old string]
EXPR := ASSN | OP | CMD | DEFUN
OP := ADD | SUB | DIV | MULT
ADD := add CONSTANT to VARNAME
SUB := take CONSTANT from VARNAME
DIV := divide VARNAME by CONSTANT
MULT := multiply VARNAME by CONSTANT
CMD := repeat after me: CONSTANT | give back CONSTANT | FUNCALL | IF | LOOP
ASSN := put CONSTANT in VARNAME
CONSTANT := VARNAME | VALUE
VARNAME := the [just a non-keyword string]
VALUE := INT | STRING | FUNCALL | BOOL
INT := [0-9]*
STRING := ì [any old string] î
BOOL := true | false
BOOLEXPR := BOOL | CONSTANT is smaller than CONSTANT | CONSTANT is bigger than CONSTANT | CONSTANT is like CONSTANT | CONSTANT is not like CONSTANT
DEFUN := learn to dance VARNAME using ARGSLIST \n LINE* ENDDEF
ENDDEF := LINESTART youíve learned VARNAME
ARGSLIST := VARNAME, ARGSLIST | VARNAME
FUNCALL := do VARNAME with FUNCALLARGS
FUNCALLARGS := CONSTANT, ARGSLIST | CONSTANT
IF := maybe BOOLEXPR LINE* ELSES ENDIF | maybe BOOLEXPR LINE* ENDIF
ELSES := but maybe BOOLEXPR ELSES | but maybe BOOLEXPR
ENDIF := LINESTART but then again
LOOP := as long as BOOLEXPR LINE* ENDLOOP
ENDLOOP := LINESTART stop looping!
```

####Tokens & rexexps:

```
EOF - added programmatically
NEWLINE - (\n)
LINESTART - (simon says)
OP_ADD - (add)
OP_ADD_TO - (to)
OP_SUB - (take)
OP_SUB_FROM - (from)
OP_DIV - (divide)
OP_MULT - (multiply)
OP_DIV_MULT_BY - (by)
PRINT - (repeat after me:)
RETURN - (give back)
ASSN_PUT - (put)
ASSN_IN - (in)
VARNAME - (the [a-zA-z][\w]*)
INT - ([0-9]+)
STRING - (ì[\w]*î)
BOOL_TRUE - (true)
BOOL_FALSE - (false)
BOOLEXPR_SMALLER - (is smaller than)
BOOLEXPR_BIGGER - (is bigger than)
BOOLEXPR_LIKE - (is like)
BOOLEXPR_NOTLIKE - (is not like)
FUNDEF_LEARN - (learn to dance)
FUNDEF_USING - (using)
FUNDEF_LEARNED - (youíve learned)
FUNCALL_DO - (do)
FUNCALL_WITH - (with)
IF_MAYBE - (maybe)
IF_ELSEIF - (but maybe)
IF_ENDIF - (but then again)
LOOP_ASLONG - (as long as)
LOOP_STOP - (stop looping!)
COMMA - (,)
```
