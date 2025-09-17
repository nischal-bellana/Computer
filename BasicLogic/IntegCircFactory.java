package BasicLogic;

public class IntegCircFactory {
	private ICtypes type = ICtypes.CLK;

	public ICtypes getType() {
		return type;
	}
	public void setType(ICtypes type) {
		this.type = type;
	}
	
	public IntegCirc getIC() {
        return switch (type) {
            case CLK -> createCLK();
            case JK -> createJK();
            case ED -> createED();
        };

    }
	
	private IntegCirc createCLK() {
		IntegCirc clock = new IntegCirc(0,1);
		
		GateBuilder gbuilder = new GateBuilder();
		gbuilder.setType(Gates.NOT);
		gbuilder.setIC(clock);
		BasicGate gate = gbuilder.getGate();
		gate.connect(0, gate);
		
		clock.setOutputGate(0, gate);
		
		return clock;
	}
	
	private IntegCirc createJK() {
		IntegCirc jk = new IntegCirc(3,2);
		
		GateBuilder gbuilder = new GateBuilder();
		gbuilder.setType(Gates.NAND);
		gbuilder.setIC(jk);
		gbuilder.setInp(3);
		gbuilder.setInitInp().setDelay(0.05f);
		boolean[] initinp = gbuilder.getInitInp();
		
		BasicGate gate1 = gbuilder.getGate();
		initinp[0] = true;
		BasicGate gate2 = gbuilder.getGate();
		
		gbuilder.setInp(2);
		gbuilder.setInitInp();
		initinp = gbuilder.getInitInp();
		
		initinp[0] = true;
		initinp[1] = false;
		BasicGate gate3 = gbuilder.getGate();
		initinp[1] = true;
		BasicGate gate4 = gbuilder.getGate();
		
		jk.setInputGate(0, 1, gate1);
		jk.setInputGate(1, 2, gate1);
		jk.setInputGate(1, 2, gate2);
		jk.setInputGate(2, 1, gate2);
		
		jk.setOutputGate(0, gate3);
		jk.setOutputGate(1, gate4);
		
		gate1.connect(0, gate4);
		gate2.connect(0, gate3);
		gate3.connect(0, gate1);
		gate3.connect(1, gate4);
		gate4.connect(0, gate2);
		gate4.connect(1, gate3);
		
		return jk;
	}
	
	private IntegCirc createED() {
		IntegCirc ed = new IntegCirc(1,1);
		
		GateBuilder gbuilder = new GateBuilder();
		BasicGate andgate = gbuilder.setIC(ed).setType(Gates.AND).setDelay(0.05f).setInp(2).getGate();
		BasicGate notgate = gbuilder.setType(Gates.NOT).setDelay(0.4f).getGate();
		
		andgate.connect(0, notgate);
		
		ed.setInputGate(0, 0, notgate);
		ed.setInputGate(0, 1, andgate);
		ed.setOutputGate(0, andgate);
		
		return ed;
	}
	
}
