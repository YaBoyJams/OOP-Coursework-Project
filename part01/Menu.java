package part01;

import java.util.Scanner;

public class Menu {
	private String items[];
	private String title;
	private Scanner input;
	
	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
		this.input = new Scanner(System.in);
	}
	
	private void display() {
		System.out.println(title);
		for(int count=0;count<title.length();count++) {
			System.out.print("+");
		}
		System.out.println();
		for(int option=1; option<=items.length; option++) {
			System.out.println(option + ". " + items[option-1] );
		}
		System.out.println();
	}
	private void displayWithoutTitle() {
		for(int option=1; option<=items.length; option++) {
			System.out.println(option + ". " + items[option-1] );
		}
		System.out.println();
	}
	
	public int getUserChoice() {
		// Modify getuserChoice to handle errors from non-string.
		display();
		System.out.print("Enter a number between 1 and 4: ");
		int value = 0;
		try {
		value = input.nextInt();
		return value;
		}catch(Exception e) {
			System.out.println("Error reading inputed number");
			e.printStackTrace();
		}
		// nextLine to eat leftover space from nextInt
		input.nextLine();
		return value;
	}
	
	// For use in the searchChoice method in QUBImages
	public int getUserSearchChoice() {
		// Modify getuserChoice to handle errors from non-string.
		displayWithoutTitle();
		System.out.print("Enter a number between 1 and 6: ");
		int value = 0;
		try {
		value = input.nextInt();
		return value;
		}catch(Exception e) {
			System.out.println("Error reading inputed number");
			e.printStackTrace();
		}
		// nextLine to eat leftover space from nextInt
		input.nextLine();
		return value;
	}
}
