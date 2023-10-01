package schd;

import java.util.ArrayList;


public class SPT {

	public static ArrayList<ProcessInfo> q1=new ArrayList<ProcessInfo>(); ;
	int A=1,notWorkC=0,ProcessWorkingTime=0,ProcessNotWorkingTime=0,TTCounter=0,WCounter=0,RC=0;
	int n;
	int WaitingTime=0;
	int countPWorking=0;
	public SPT(int n,ArrayList<ProcessInfo> q1) {
		this.n=n;
		for(int i=0;i<n;i++) {
			 int Pno=q1.get(i).Pno;
			 int BrustTime=q1.get(i).BrustTime;
			 int ArrivalTime=q1.get(i).ArrivalTime;
			ProcessInfo p=new ProcessInfo(Pno,BrustTime,ArrivalTime);
			SPT.q1.add(p);
			}
	}
			
		
	public void calculate() {
		for(int i=0;i<n;i++) {
			int x=0;
			var tmp=SPT.q1.get(0);
			
			while(!SPT.q1.isEmpty() && x<SPT.q1.size()) {
			if((notWorkC+countPWorking)>=SPT.q1.get(x).ArrivalTime)
			if(tmp.BrustTime>SPT.q1.get(x).BrustTime) {
				tmp =SPT.q1.get(x); 
				SPT.q1.set(x,SPT.q1.get(0) ); 
				SPT.q1.set(0,tmp ); 
			}
			x++; 
		}
			int p=SPT.q1.get(0).Pno;
			int ArrivalTime=SPT.q1.get(0).ArrivalTime;
			int Brust=SPT.q1.get(0).BrustTime;
			countPWorking +=Brust;
			if(SPT.q1.get(0).ArrivalTime>ProcessWorkingTime ) {
				ProcessNotWorkingTime=ArrivalTime-ProcessWorkingTime;
				notWorkC+=ProcessNotWorkingTime;
				ProcessWorkingTime=ArrivalTime;
				System.out.println("   processor not working for "+ProcessNotWorkingTime);
				}
			 WaitingTime=ProcessWorkingTime;
			RC+=WaitingTime;
			ProcessWorkingTime +=Brust;
			printPInfo( p, ArrivalTime, Brust,WaitingTime,ProcessWorkingTime);
			WCounter +=(WaitingTime-ArrivalTime);
			TTCounter +=(ProcessWorkingTime-ArrivalTime);
			SPT.q1.remove(0);
			
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
