package BasicLogic;

import java.util.ArrayList;
import java.util.List;

enum for_defIC_only{
	DEFIC
}

public class IntegCirc extends LogicComponent{
	public static List<IntegCirc> ICRegister;
	public static IntegCirc defIC;
	
	public final int inp;
	public final int out;
	public final int regID;
	List<LogicComponent> components;
	BasicGate[] outputGates;
	List<BasicGate>[] inputGates;
	List<Integer>[] inputIDs;
	
	public IntegCirc(for_defIC_only  var) {
		super(BasicGate.sconfig, var);
		inp = 0;
		out = 0;
		regID = ICRegister.size();
		ICRegister.add(this);
		components = new ArrayList<>();
	}
	
	public IntegCirc() {
		super(BasicGate.sconfig);
		inp = 1;
		out = 1;
		regID = ICRegister.size();
		ICRegister.add(this);
		setStates();
	}
	
	public 	IntegCirc(int inp) {
		super(BasicGate.sconfig);
		this.inp = inp;
		out = 1;
		regID = ICRegister.size();
		ICRegister.add(this);
		setStates();
	}
	
	public 	IntegCirc(int inp, int out) {
		super(BasicGate.sconfig);
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
