package DataDrivenTesting;

public class ReadTestDataFromCMD {
	public static void main(String[] args) {
		System.out.println(args.length);
		for (String cm : args) {
			System.out.println(cm);
		}
	}

}
