package schd;  

import java.util.ArrayList;

public class SRT {

	public static ArrayList<ProcessInfo> q1= new ArrayList<ProcessInfo>();
	int A=1,notWorkC=0,ProcessWorkingTime=0,ProcessNotWorkingTime=0,TTCounter=0,WCounter=0,RC=0;
	int n;
	int WaitingTime=0;
	
	public SRT(int n,ArrayList<ProcessInfo> q1) {
		this.n=n;
		for(int i=0;i<n;i++) {
			 int Pno=q1.get(i).Pno;
			 int BrustTime=q1.get(i).BrustTime;
			 int ArrivalTime=q1.get(i).ArrivalTime;
			ProcessInfo p=new ProcessInfo(Pno,BrustTime,ArrivalTime);
			SRT.q1.add(p);
			}
	}
	int countPWorking=0;
		
	public void calculate() {
	ArrayList<ProcessInfo> enteredProc= new ArrayList<ProcessInfo>();
		while(!SRT.q1.isEmpty()) { 
			var tmp=SRT.q1.get(0);
//			sort
			sortingListByBrust(tmp,notWorkC,countPWorking);
			int p=SRT.q1.get(0).Pno;
			int ArrivalTime=SRT.q1.get(0).ArrivalTime;
			int Brust=SRT.q1.get(0).BrustTime;
			int BrustC=SRT.q1.get(0).BrustTime;
//			check if there is gap
			if(ArrivalTime>ProcessWorkingTime ) {
				sortingListByarrival(tmp,notWorkC,countPWorking);
				 p=SRT.q1.get(0).Pno;
				ArrivalTime=SRT.q1.get(0).ArrivalTime;
				Brust=SRT.q1.get(0).BrustTime;
				BrustC=SRT.q1.get(0).BrustTime;
				if(ArrivalTime>ProcessWorkingTime ) {
				ProcessNotWorkingTime=ArrivalTime-ProcessWorkingTime;
				notWorkC+=ProcessNotWorkingTime;
				ProcessWorkingTime=ArrivalTime;
				sortingListByBrust(tmp,notWorkC,countPWorking);
				 p=SRT.q1.get(0).Pno;
				ArrivalTime=SRT.q1.get(0).ArrivalTime;
				Brust=SRT.q1.get(0).BrustTime;
				BrustC=SRT.q1.get(0).BrustTime;
				System.out.println("   processor not working for "+ProcessNotWorkingTime);
				}
				}
			WaitingTime=ProcessWorkingTime;
			if(SRT.q1.size()>1)
			for(int j=0;j<BrustC;j++) {	
			int s=0;
			boolean c=false;
			ProcessWorkingTime ++;
			Brust--;
			countPWorking++;
			SRT.q1.get(0).BrustTime=Brust;
			while(s<SRT.q1.size()) {
		if(SRT.q1.get(s).ArrivalTime<=ProcessWorkingTime && SRT.q1.get(s).BrustTime<Brust) {
			SRT.q1.get(0).ArrivalTime=ProcessWorkingTime;
				c=true;
			}	
			s++;
			}
			if(c)
				break;
			}
			else {
				ProcessWorkingTime +=Brust;
				countPWorking+=Brust;
				Brust=0;
			}
	printPInfo( p, ArrivalTime, BrustC,WaitingTime,ProcessWorkingTime);
			WCounter +=(WaitingTime-ArrivalTime);
			TTCounter +=(ProcessWorkingTime-ArrivalTime);
			
			if(Brust==0) {
				if(!enteredProc.contains(SRT.q1.get(0)))
					RC+=WaitingTime;
			SRT.q1.remove(0);
			}
			else
				enteredProc.add(SRT.q1.get(0));
			
		}
		printAvg(WCounter,TTCounter,RC,countPWorking, notWorkC);
	
	}
	
//	sort
	void sortingListByBrust(ProcessInfo tmp,int notWorkC,int countPWorking) {
		for(int j=1;j<SRT.q1.size();j++){
			if((notWorkC+countPWorking)>=SRT.q1.get(j).ArrivalTime)
			if(tmp.BrustTime>SRT.q1.get(j).BrustTime) {
				tmp =SRT.q1.get(j); 
				SRT.q1.set(j,SRT.q1.get(0) ); 
				SRT.q1.set(0,tmp ); 
			}
		}
	}
	
//	sort by arrival
	void sortingListByarrival(ProcessInfo tmp,int notWorkC,int countPWorking) {
		for(int j=1;j<SRT.q1.size();j++)
			if((notWorkC+countPWorking)>=SRT.q1.get(j).ArrivalTime) {
				tmp =SRT.q1.get(j); 
				SRT.q1.set(j,SRT.q1.get(0) ); 
				SRT.q1.set(0,tmp ); 
				break;
			}
		}
	
	
//	 print process info
	void printPInfo(int p,int ArrivalTime,int BrustC,int WaitingTime,int ProcessWorkingTime) {
		System.out.print("P "+p); 
		System.out.print("   A "+ArrivalTime); 
		System.out.print("   B "+BrustC);
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
