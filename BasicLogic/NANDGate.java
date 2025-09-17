package BasicLogic;

public class NANDGate extends BasicGate{

	public NANDGate() {
		super();
		simOut();
		// TODO Auto-generated constructor stub
	}

	public NANDGate(float delay) {
		super(delay);
		simOut();
		// TODO Auto-generated constructor stub
	}

	public NANDGate(float delay, int inp) {
		super(delay, inp);
		simOut();
		// TODO Auto-generated constructor stub
	}

	public NANDGate(float delay, int inp, IntegCirc IC, boolean[] initinp) {
		super(delay, inp, IC, initinp);
		simOut();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void simOut() {
		// TODO Auto-generated method stub
		setOutput(false);
		for(int i=0; i<inp; i++) {
			if(!inpBuffer[i].pollLast()) setOutput(true);
		}
	}
	
	
	
}
