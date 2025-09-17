package BasicLogic;

import java.util.HashMap;

public class LogicComponent {
	public static HashMap<String, LogicComponent> handbag = new HashMap<>();

	public final IntegCirc IC;
	public final int ICCompNo;

	public LogicComponent(boolean defaultICPassAny){
		IC = null;
		ICCompNo = 0;
	}

	public LogicComponent() {
		this(IntegCirc.defIC);
	}
	
	public LogicComponent(IntegCirc IC) {
		this.IC = IC;
		ICCompNo = IC.components.size();
		IC.components.add(this);
	}
	
	public float getTs() {
		return SimuConfig.Ts;
	}

	public void connect(int inpID, BasicGate source_gate){

	}

	public void connect(int inpID, int sourceID, IntegCirc source){
		BasicGate source_gate = source.getOutputGate(sourceID);
		connect(inpID, source_gate);
	}

}