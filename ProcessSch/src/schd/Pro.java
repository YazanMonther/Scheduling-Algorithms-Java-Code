package schd;


public class Pro {

	public static void main(String[] args) {
//		generate process randomly
//	generateP  g=new generateP(100);
//		var generate=g.generateRandomly();
		redProInfo red=new redProInfo();
		var generate=red.redPro("C:\\Users\\DELL\\Downloads\\schedulingDATA(100).txt");
	//	System.out.println("\n FIFO");
//		fifo scd
		
			//FIFO fifoSch=new FIFO(100,generate);
		//	fifoSch.calculate();
//			System.out.println("\n shortest remaining time");
//				Shortest remaining  time	
//			SRT srt=new SRT(100,generate);
//			srt.calculate();
//			System.out.println("\n Shortest process first");
//			
//          shortest process first
			
//			SPT spt=new SPT(100,generate);
//			spt.calculate();	 
//			System.out.println("\n round robin");
//			round robin
				RR Rr=new RR(100,5,generate);
				Rr.calculate();
//				
//				System.out.println("\n HRRN");
////				HRRN
//				HRRN hrrn=new HRRN(100,generate);
//					hrrn.calculate();
		
	}
}
