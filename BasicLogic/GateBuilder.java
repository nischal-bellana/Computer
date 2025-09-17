package BasicLogic;

public class GateBuilder {
	private float delay = 1;
	private int inp = 1;
	private Gates type = Gates.AND;
	private IntegCirc IC = IntegCirc.defIC;
	private boolean[] initinp;
	
	public float getDelay() {
		return delay;
	}
	public GateBuilder setDelay(float delay) {
		this.delay = delay;
		return this;
	}
	
	public int getInp() {
		return inp;
	}
	public GateBuilder setInp(int inp) {
		this.inp = inp;
		return this;
	}
	
	public Gates getType() {
		return type;
	}
	public GateBuilder setType(Gates type) {
		this.type = type;
		return this;
	}
	
	public IntegCirc getIC() {
		return IC;
	}
	public GateBuilder setIC(IntegCirc iC) {
		IC = iC;
		return this;
	}
	
	public boolean[] getInitInp() {
		return initinp;
	}
	public GateBuilder setInitInp() {
		if(initinp==null || initinp.length!=inp) {
			initinp = new boolean[inp];
		}
		return this;
	}

	public BasicGate getGate() {
		switch(type) {
		case AND:
			return new ANDGate(delay, inp, IC, initinp);
		case OR:
			return new ORGate(delay, inp, IC, initinp);
		case NOT:
			return new NOTGate(delay, 1, IC, initinp);
		case NAND:
			return new NANDGate(delay, inp, IC, initinp);
		case NOR:
			return new NORGate(delay, inp, IC, initinp);
		}
		
		return new ANDGate(delay, inp, IC, initinp);
	}
	
}
