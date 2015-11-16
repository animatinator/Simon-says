// OpNode.java

package simonSays;


public class OpNode extends SyntaxTree {
	private ConstNode op1;
	private ConstNode op2;
	private int opType;
	
	public OpNode(ConstNode op1, ConstNode op2, int opType) throws STypeError {
		super(Constants.TYPE_INT);
		this.op1 = op1;
		this.op2 = op2;
		this.opType = opType;
		
		// Type-check this node's children
		if (!((op1.getType() == Constants.TYPE_INT || op1.getType() == Constants.TYPE_UNDEFINED)
			&& (op2.getType() == Constants.TYPE_INT || op2.getType() == Constants.TYPE_UNDEFINED))) {
			throw new STypeError("Non-integer arguments supplied to arithmetic operation!");
		}
	}
}