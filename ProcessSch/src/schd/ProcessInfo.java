package schd;

public class ProcessInfo {

	public int Pno;
	public int BrustTime;
	public int ArrivalTime;
	public int WaitingTime;
	 double Dynamic_Priority;
	public ProcessInfo(int p, int B,int A) {
		this.Pno=p;
		this.BrustTime=B;
		this.ArrivalTime=A;
	}
}
