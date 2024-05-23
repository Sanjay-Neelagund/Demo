package DataDrivenTesting;

import java.util.Random;

public class RandomNumber {
public static void main(String[] args) {
//	Random ran=new Random();
//	int ranNum=ran.nextInt(1000);
//	System.out.println(ranNum);

	//Random AlphaNumeric Value
	int n=20;
	String Alphanumeric="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
	StringBuilder sb=new StringBuilder(n);
	
	for (int i = 0; i < n; i++) {
		int index=(int) (Alphanumeric.length()*Math.random());
		sb.append(Alphanumeric.charAt(index));
		
	}
	System.out.println(sb);
	
	
			
}
}
