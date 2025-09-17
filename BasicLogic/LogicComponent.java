package BasicLogic;

public class LogicComponent {
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
}