package BasicLogic;

public class NOTGate extends BasicGate{

	public NOTGate() {
		super(1,1);
		simOut();
		// TODO Auto-generated constructor stub
	}

	public NOTGate(float delay) {
		super(delay,1);
		simOut();
		// TODO Auto-generated constructor stub
	}

	public NOTGate(float delay, int inp, IntegCirc IC, boolean[] initinp) {
		super(delay, inp, IC, initinp);
		simOut();
		// TODO Auto-generated constructor stub
	}

	public NOTGate(float delay, int inp) {
		super(delay, inp);
		simOut();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void simOut() {
		// TODO Auto-generated method stub
		setOutput(!inpBuffer[0].pollLast());
	}
	
}
