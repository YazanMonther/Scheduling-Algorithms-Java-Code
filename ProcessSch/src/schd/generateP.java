package schd;

import java.util.ArrayList;

public class generateP {
	private int n;
	public static ArrayList<ProcessInfo> q1 = new ArrayList<ProcessInfo>();
		
		public generateP(int n) {
			this.n=n;
		}

		public ArrayList<ProcessInfo> generateRandomly() {
			int A=1;
//			generate process randomly 
			for(int i=0;i<n;i++) {
				if(i==0)
				A=0;
				else
					A=(int) (A+Math.random()*10);
				int B=(int) (1+Math.random()*20);
				ProcessInfo p=new ProcessInfo(i,B,A);

				generateP.q1.add(p); 	
	
			 
			}
			return generateP.q1;
		}
}
