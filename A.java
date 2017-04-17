import java.util.*;

public class A {

	private Scanner scan;
	private int number;

	public A() {
		
		scan = new Scanner(System.in);

		ask(); // initial ask

		// keep on asking until condition is met
		while(number != 5) {
			ask();
		}

		System.out.println("Hooray!");
	}

	private void ask() {
		System.out.print("Number: ");
		number = scan.nextInt();
	}

}