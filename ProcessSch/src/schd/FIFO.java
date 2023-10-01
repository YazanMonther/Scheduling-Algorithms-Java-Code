package schd;

import java.util.ArrayList;
public class FIFO {
	public static ArrayList<ProcessInfo> q1= new ArrayList<ProcessInfo>();
	int A=1,notWorkC=0,ProcessWorkingTime=0,ProcessNotWorkingTime=0,TTCounter=0,WCounter=0,RC=0;
	int n;
	int countPWorking=0;
	public FIFO(int n,ArrayList<ProcessInfo> q1) {
		this.n=n;
		for(int i=0;i<n;i++) {
			 int Pno=q1.get(i).Pno;
			 int BrustTime=q1.get(i).BrustTime;
			 int ArrivalTime=q1.get(i).ArrivalTime;
			ProcessInfo p=new ProcessInfo(Pno,BrustTime,ArrivalTime);
			FIFO.q1.add(p);
			}
		
	}
		
	public void calculate() {
		for(int i=0;i<n;i++) {
			int p=FIFO.q1.get(0).Pno;
			int ArrivalTime=FIFO.q1.get(0).ArrivalTime;
			int Brust=FIFO.q1.get(0).BrustTime;
			countPWorking +=Brust;
			if(FIFO.q1.get(0).ArrivalTime>ProcessWorkingTime) {
				ProcessNotWorkingTime=ArrivalTime-ProcessWorkingTime;
				notWorkC+=ProcessNotWorkingTime;
				ProcessWorkingTime=ArrivalTime;
				System.out.println("   processor not working for "+ProcessNotWorkingTime);
				}
			int WaitingTime=ProcessWorkingTime;
			RC+=WaitingTime;
			ProcessWorkingTime +=Brust;
		printPInfo( p, ArrivalTime, Brust,WaitingTime,ProcessWorkingTime);
			WCounter +=(WaitingTime-ArrivalTime);
			TTCounter +=(ProcessWorkingTime-ArrivalTime);
			FIFO.q1.remove(0);
			
		}
		 printAvg(WCounter,TTCounter,RC,countPWorking, notWorkC); 
	}
	
//	 print process info
	void printPInfo(int p,int ArrivalTime,int Brust,int WaitingTime,int ProcessWorkingTime) {
		System.out.print("P "+p); 
		System.out.print("   A "+ArrivalTime); 
		System.out.print("   B "+Brust);
		System.out.print("   W "+(WaitingTime-ArrivalTime));
		System.out.print("   R "+(WaitingTime));
		System.out.println("   TT "+(ProcessWorkingTime-ArrivalTime));
	}
//	print avg
	void printAvg(int WCounter,int TTCounter,int RC,int countPWorking, int notWorkC) {
		System.out.println("Avg waiting time = "+((double)WCounter/n));
		System.out.println("Avg Turnaroundtime = "+((double)TTCounter/n));
		System.out.println("Avg Respondtime = "+((double)RC/n));
		System.out.println("Process Working Time "+(countPWorking));
		System.out.println("Process Not Working Time "+notWorkC);
		double utility=((double)countPWorking/(notWorkC+countPWorking))*100;
		System.out.println("UTILITY = "+utility+"%");
	}
}
