package BasicLogic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BasicGate extends LogicComponent{
	public static SimuConfig sconfig;
	public static BasicGate ON;
	public static BasicGate OFF;
	public static List<BasicGate> GateRegister;
	static {
	}
	
	public final int inp;
	public final int regID;
	private float delay = 1;
	public Deque<Boolean>[] inpBuffer;
	public BasicGate[] inputs;
	private boolean output = false;
	
	public BasicGate() {
		super(sconfig);
		inp = 2;
		regID = GateRegister.size();
		GateRegister.add(this);
		setStates(null);
	}
	
	public BasicGate(float delay) {
		super(sconfig);
		inp = 2;
		this.delay = delay;
		regID = GateRegister.size();
		GateRegister.add(this);
		setStates(null);
	}
	
	public BasicGate(float delay, int inp) {
		super(sconfig);
		this.delay = delay;
		this.inp = inp;
		regID = GateRegister.size();
		GateRegister.add(this);
		setStates(null);
	}
	
	public BasicGate(float delay, int inp, boolean output) {
		super(sconfig);
		this.delay = delay;
		this.inp = inp;
		this.output = output;
		regID = GateRegister.size();
		GateRegister.add(this);
		setStates(null);
	}
	
	public BasicGate(float delay, int inp, IntegCirc IC, boolean[] initinp) {
		super(sconfig, IC);
		this.delay = delay;
		this.inp = inp;
		regID = GateRegister.size();
		GateRegister.add(this);
		setStates(initinp);
	}

	
	@SuppressWarnings("unchecked")
	private void setStates(boolean[] initinp) {
		inputs = new BasicGate[inp];
		inpBuffer = new Deque[inp];
		
		int size = (int)(delay/getTs());
		if(size==0) size = 1;
		
		for(int i=0; i<inp; i++) {
			inputs[i] = OFF;
			inpBuffer[i] = new ArrayDeque<Boolean>();
			
			for(int j=0; j<=size; j++) {
				inpBuffer[i].offer(initinp==null?false:initinp[i]);
			}
		}
		
	}
	
	public float getDelay() {
		return delay;
	}
	
	protected void setOutput(boolean val) {
		output = val;
	}
	
	public boolean Output() {
		return output;
	}
	
	public void simOut() {
		
	}
	
	public void inpUpd() {
		for(int i=0; i<inp; i++) {
			inpBuffer[i].offerFirst(inputs[i].Output());
		}
	}
	
	public void connect(int inpID, BasicGate other) {
		inputs[inpID] = other;
	}
	
	public void printBuffer() {
		for(int i=0; i<inp; i++) {
			System.out.print("[");
			for(boolean a: inpBuffer[i]) {
				System.out.printf("%c ",a?'T':'F');
			}
			System.out.print("\b] ");
		}
	}
	
}
