package mainPack;

import java.awt.*;
import java.util.Deque;
import java.util.List;

import BasicLogic.*;

import javax.swing.*;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		long Ts = (int)(BasicGate.ON.getTs()*1000);

		IntegCircFactory icfac = new IntegCircFactory();
		icfac.setType(ICtypes.CLK);
		IntegCirc clock = icfac.getIC();
		LogicComponent.handbag.put("clock", clock);

		Thread th = new Thread(new MyPanel());
		th.start();

		long startTime = System.currentTimeMillis();
		long currentTime = 0;

		while(currentTime/1000 < 30){
			currentTime = System.currentTimeMillis() - startTime;

			stepSimulate();

			System.out.print("\r" + (clock.getOutputGate(0).Output()?"T":"F"));

			Thread.sleep(Ts);
		}

	}
	
	public static void stepSimulate() {
		List<BasicGate> lis = BasicGate.GateRegister;

        for (BasicGate li : lis) {
            li.simOut();
        }
        for (BasicGate li : lis) {
            li.inpUpd();
        }
		
	}

	public static void resetLine(){
		System.out.print("\r");
		for(int i = 0; i < 200; i++){
			System.out.print("-");
		}
		System.out.print("\r");
	}

	public static void printClockBuffer(Deque<Boolean> inpbuffer){
		boolean val = inpbuffer.getFirst();

		for (boolean it: inpbuffer){
			if(val^it){
				System.out.print("|");
				val = it;
			}
			System.out.print(val ? "`" : "_");
		}

	}

}
