package BasicLogic;

import java.util.ArrayList;
import java.util.List;

public class IntegCirc extends LogicComponent{
	public static List<IntegCirc> ICRegister = new ArrayList<>();
	public static IntegCirc defIC = new IntegCirc(true);

	public final int inp;
	public final int out;
	public final int regID;
	List<LogicComponent> components;
	BasicGate[] outputGates;
	List<BasicGate>[] inputGates;
	List<Integer>[] inputIDs;

	/**
	 * This is constructor for an unique IC which is used as a default IC
	 * for components that are not really part of any other IC.
	 * */
	public IntegCirc(boolean defaultICPassAny) {
		super(defaultICPassAny);
		inp = 0;
		out = 0;
		regID = ICRegister.size();
		ICRegister.add(this);
		components = new ArrayList<>();
	}
	
	public IntegCirc() {
		super();
		inp = 1;
		out = 1;
		regID = ICRegister.size();
		ICRegister.add(this);
		setStates();
	}
	
	public 	IntegCirc(int inp) {
		super();
		this.inp = inp;
		out = 1;
		regID = ICRegister.size();
		ICRegister.add(this);
		setStates();
	}
	
	public 	IntegCirc(int inp, int out) {
		super();
		this.inp = inp;
		this.out = out;
		regID = ICRegister.size();
		ICRegister.add(this);
		setStates();
	}
	
	@SuppressWarnings("unchecked")
	private void setStates() {
		components = new ArrayList<>();
		
		if(inp!=0) {
			inputGates = new List[inp];
			inputIDs = new List[inp];
		}
		
		if(out!=0) {
			outputGates = new BasicGate[out];
		
			for(int i=0; i<out; i++) {
				outputGates[i] = BasicGate.OFF;
			}
		}
		
	}

	public void setInputGate(int inpID, int gateInpID, BasicGate gate) {
		if(inputGates[inpID]==null) {
			inputGates[inpID] = new ArrayList<>();
			inputIDs[inpID] = new ArrayList<>();
		}
		inputIDs[inpID].add(gateInpID);
		inputGates[inpID].add(gate);
	}
	
	public void setOutputGate(int outID, BasicGate gate) {
		outputGates[outID] = gate;
	}
	
	public boolean Output(int outID) {
		return outputGates[outID].Output();
	}
	
	public void connect(int inpID, BasicGate gate) {
		List<BasicGate> list1 = inputGates[inpID];
		List<Integer> list2 = inputIDs[inpID];
		if(list1==null) return;
		
		for(int i=0; i<list1.size(); i++) {
			list1.get(i).connect(list2.get(i), gate);
		}
	}
	
	public BasicGate getOutputGate(int outID) {
		return outputGates[outID];
	}
	
}
