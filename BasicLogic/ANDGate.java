package BasicLogic;

public class ANDGate extends BasicGate{

	public ANDGate(float delay) {
		super(delay);
		simOut();
		// TODO Auto-generated constructor stub
	}

	public ANDGate() {
		super();
		simOut();
		// TODO Auto-generated constructor stub
	}

	public ANDGate(float delay, int inp) {
		super(delay, inp);
		simOut();
		// TODO Auto-generated constructor stub
	}

	public ANDGate(float delay, int inp, IntegCirc IC, boolean[] initinp) {
		super(delay, inp, IC, initinp);
		simOut();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void simOut() {
		// TODO Auto-generated method stub
		setOutput(true);
		for(int i=0; i<inp; i++) {
			if(!inpBuffer[i].pollLast()) setOutput(false);
		}
	}
	
	
	
}
