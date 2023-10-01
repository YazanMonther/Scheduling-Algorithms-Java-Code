package schd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class RR {
	public static ArrayList<ProcessInfo> q1 = new ArrayList<ProcessInfo>();
	ArrayList<ProcessInfo> enteredProc= new ArrayList<ProcessInfo>();
	static Queue<ProcessInfo> waitingQ = new LinkedList<ProcessInfo> ();
	int A=1,ProcessWorkingTime=0,ProcessNotWorkingTime=0,TTCounter=0,WCounter=0,notWorkC=0,RCo=0;
	int n;
	int q;
	int RC;
	public RR(int n,int q,ArrayList<ProcessInfo> q1) {
		this.n=n;
		this.q=q;
		for(int i=0;i<n;i++) {
			 int Pno=q1.get(i).Pno;
			 int BrustTime=q1.get(i).BrustTime;
			 int ArrivalTime=q1.get(i).ArrivalTime;
			ProcessInfo p=new ProcessInfo(Pno,BrustTime,ArrivalTime);
			RR.q1.add(p);
			}
	}
	int countPWorking=0;
	public void calculate() {
		int WaitingTime=0;
		int currentTime=0;
		while(!RR.q1.isEmpty() || !RR.waitingQ.isEmpty()) {
			processInWaiting( currentTime);
			int p=RR.waitingQ.peek().Pno;
			int ArrivalTime=RR.waitingQ.peek().ArrivalTime;
			int Brust=RR.waitingQ.peek().BrustTime;
			int BrustC=Brust;
			if(RR.waitingQ.peek().ArrivalTime>currentTime ) {
				if(RR.q1.size()>1) 
				 nextOne(currentTime);
		if(RR.waitingQ.peek().ArrivalTime>currentTime) {
				ProcessNotWorkingTime=ArrivalTime-currentTime;
				notWorkC+=ProcessNotWorkingTime;
				currentTime+=ProcessNotWorkingTime;
				ProcessWorkingTime=ArrivalTime;
				System.out.println("   processor not working for "+ProcessNotWorkingTime);
				}
				else {
					 p=RR.waitingQ.peek().Pno;
					 ArrivalTime=RR.waitingQ.peek().ArrivalTime;
					 Brust=RR.waitingQ.peek().BrustTime;
					 BrustC=Brust;
				}
				}
			WaitingTime=ProcessWorkingTime;
			if(Brust<=q) {
			if(!enteredProc.contains(RR.waitingQ.peek()))
				RC+=WaitingTime;
			RR.q1.remove(RR.waitingQ.peek());
			enteredProc.remove(RR.waitingQ.peek());
			RR.waitingQ.poll();
			ProcessWorkingTime +=Brust;
			currentTime+=Brust;
			}
			else if(RR.q1.size()>=1){ 
				ProcessWorkingTime +=q;
				currentTime+=q;
				if(!enteredProc.contains(RR.waitingQ.peek()))
					RC+=WaitingTime;
				enteredProc.add(RR.waitingQ.peek());
				RR.waitingQ.peek().BrustTime=Brust-q;
				RR.waitingQ.peek().ArrivalTime=WaitingTime+q;
				ProcessInfo temp=RR.waitingQ.peek();
				RR.q1.remove(temp);
				RR.waitingQ.poll();
				RR.q1.add(temp);
				
			}
			else {
				ProcessWorkingTime +=q;
				enteredProc.add(RR.waitingQ.peek());
				RR.waitingQ.peek().BrustTime=Brust-q;
				RR.waitingQ.peek().ArrivalTime=WaitingTime+q;
				ProcessInfo temp=RR.waitingQ.peek();
				RR.q1.remove(temp);
				RR.waitingQ.poll();
				RR.q1.add(temp);
				currentTime+=q;
			}
//			PRINT
			printPInfo( p, ArrivalTime, BrustC,WaitingTime,ProcessWorkingTime);
			WCounter +=(WaitingTime-ArrivalTime);
			TTCounter +=(ProcessWorkingTime-ArrivalTime);
			
		}

		printAvg(WCounter,TTCounter,RC,currentTime, notWorkC);
	}
	 
//	 print process info
	void printPInfo(int p,int ArrivalTime,int BrustC,int WaitingTime,int ProcessWorkingTime) {
		System.out.print("P "+p); 
		System.out.print("   A "+ArrivalTime); 
		System.out.print("   B "+BrustC);
		System.out.print("   W "+(WaitingTime-ArrivalTime));
		System.out.print("   R "+(WaitingTime));
		System.out.print("   c "+(ProcessWorkingTime));
		System.out.println("   TT "+(ProcessWorkingTime-ArrivalTime));
	}
	
//	print avg
	void printAvg(int WCounter,int TTCounter,int RC,int currentTime, int notWorkC) {
		System.out.println("Avg waiting time = "+((double)WCounter/1));
		System.out.println("Avg Turnaroundtime = "+((double)TTCounter/1));
		System.out.println("Avg Respondtime = "+((double)RC/n));
		System.out.println("Process Working Time "+(currentTime-notWorkC));
		System.out.println("Process Not Working Time "+notWorkC);
		double utility=((double)(currentTime-notWorkC)/(currentTime))*100;
		System.out.println("UTILITY = "+utility+"%");
	}
	
//	sort by arrival
	void processInWaiting(int currentTime) {
		boolean flag=true;
		for(int j=0;j<RR.q1.size();j++) {
			if((currentTime)>=RR.q1.get(j).ArrivalTime) {
				waitingQ.add(RR.q1.get(j));
				RR.q1.remove(j);
				flag=false;
				break;
			}
				}
		if(flag && !RR.q1.isEmpty()) {
			waitingQ.add(RR.q1.get(0));
			RR.q1.remove(0);
		}
	}
//		find next process 
		void nextOne(int currentTime) {
			int min= RR.q1.get(0).ArrivalTime;
			int minI=0;
			boolean flag=false;
			for(int j=0;j<RR.q1.size();j++) {
				if(enteredProc.contains(RR.q1.get(j)))
	if((currentTime>=RR.q1.get(j).ArrivalTime) && (RR.q1.get(j).ArrivalTime<=min)) {
				min=RR.q1.get(j).ArrivalTime;	
				minI=j;
				flag=true;
				}
					}
				if(flag){	
			ProcessInfo temp=waitingQ.poll();
			waitingQ.add(RR.q1.get(minI));
			RR.q1.remove(minI);
			RR.q1.add(temp);
		} 
		}

	
	
}