import java.util.*;

public class A {

	private Scanner scan;
	private String input;
	private char symbol;

	public A() {
		
		scan = new Scanner(System.in);

		while(symbol != 'a') {
			System.out.print("Char:");
			input = scan.nextLine();

			try {
				symbol = input.charAt(0);
			} catch(StringIndexOutOfBoundsException e) {
				System.out.println("Invalid value.");
			}

		}
		
	}

}