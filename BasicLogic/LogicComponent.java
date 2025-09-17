package BasicLogic;

import java.util.ArrayList;

public class LogicComponent {
	static {
		BasicGate.sconfig = new SimuConfig();
		
		IntegCirc.ICRegister = new ArrayList<>();
		IntegCirc.defIC = new IntegCirc(for_defIC_only.DEFIC);
		
		BasicGate.GateRegister = new ArrayList<>();
		BasicGate.ON = new BasicGate(1,1,true);
		BasicGate.OFF = new BasicGate(1,1,false);
		BasicGate.ON.connect(0, BasicGate.ON);
		BasicGate.OFF.connect(0, BasicGate.OFF);
	}
	
	private SimuConfig SC;
	public final IntegCirc IC;
	public final int ICCompNo;
	
	public LogicComponent(SimuConfig SC, for_defIC_only var) {
		IC = null;
		this.SC = SC;
		ICCompNo = 0;
	}
	
	public LogicComponent(SimuConfig SC) {
		IC = IntegCirc.defIC;
		this.SC = SC;
		ICCompNo = IC.components.size();
		IC.components.add(this);
	}
	
	public LogicComponent(SimuConfig SC, IntegCirc IC) {
		this.IC = IC;
		this.SC = SC;
		ICCompNo = IC.components.size();
		IC.components.add(this);
	}
	
	public float getTs() {
		return SC.Ts;
	}
}