import java.util.*;

public class Test {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		final int MAX = 3;

		ArrayList<String> list = new ArrayList<String>();

		while(list.size() != 3) {
			System.out.print("Add:");
			list.add(scan.nextLine());
		} 


		System.out.println(list.size());

	}

}