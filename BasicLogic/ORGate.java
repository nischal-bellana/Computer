package BasicLogic;

public class ORGate extends BasicGate{

	public ORGate(float delay, int inp, IntegCirc IC, boolean[] initinp) {
		super(delay, inp, IC, initinp);
		simOut();
		// TODO Auto-generated constructor stub
	}

	public ORGate(float delay) {
		super(delay);
		simOut();
		// TODO Auto-generated constructor stub
	}

	public ORGate() {
		super();
		simOut();
		// TODO Auto-generated constructor stub
	}

	public ORGate(float delay, int inp) {
		super(delay, inp);
		simOut();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void simOut() {
		setOutput(false);
		for(int i=0; i<inp; i++) {
			if(inpBuffer[i].pollLast()) setOutput(true);
		}
	}
	
}
