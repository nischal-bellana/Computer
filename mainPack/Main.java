package mainPack;

import java.util.List;

import BasicLogic.BasicGate;
import BasicLogic.ICtypes;
import BasicLogic.IntegCirc;
import BasicLogic.IntegCircFactory;

public class Main {
	
	public static void main(String[] args) {
		long Ts = (int)(BasicGate.ON.getTs()*1000);
		
		IntegCircFactory icfac = new IntegCircFactory();
		IntegCirc clock = icfac.getIC();
		
		icfac.setType(ICtypes.ED);
		IntegCirc ed = icfac.getIC();
		
		ed.connect(0, clock.getOutputGate(0));
		
		icfac.setType(ICtypes.JK);
		IntegCirc jk = icfac.getIC();
		
		jk.connect(0, BasicGate.ON);
		jk.connect(1, BasicGate.ON);
		jk.connect(2, BasicGate.ON);
		
		long startTime = System.currentTimeMillis();
		long curTime = 0;
		
		while(curTime/1000<30) {
			curTime = System.currentTimeMillis()-startTime;
			
			simulateAll();
			
			System.out.printf("\r%.2f ",curTime/1000f);
			System.out.printf("%c %c %c %c ",clock.Output(0)?'T':'F', jk.Output(0)?'T':'F', jk.Output(1)?'T':'F', ed.Output(0)?'T':'F');
			System.out.print("   ");
			
			
			try {
				Thread.sleep(Ts);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void simulateAll() {
		List<BasicGate> lis = BasicGate.GateRegister;
		
		for(int i=0; i<lis.size(); i++) {
			lis.get(i).simOut();
		}
		for(int i=0; i<lis.size(); i++) {
			lis.get(i).inpUpd();
		}
		
	}
	
}
