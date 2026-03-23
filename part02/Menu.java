package part02;

import console.Console;

public class Menu {
	private String items[];
	private String title;
	private Console menuConsole;

	// Menu changed to support input for console
	public Menu(String title, String data[], Console console) {
		this.title = title;
		this.items = data;
		this.menuConsole = console;
	}

	private void display() {
		menuConsole.println(title);
		for (int count = 0; count < title.length(); count++) {
			menuConsole.print("+");
		}
		menuConsole.println();
		for (int option = 1; option <= items.length; option++) {
			menuConsole.println(option + ". " + items[option - 1]);
		}
		menuConsole.println();
	}

	// Copy of display but without title
	private void displayWithoutTitle() {
		for (int option = 1; option <= items.length; option++) {
			menuConsole.println(option + ". " + items[option - 1]);
		}
		menuConsole.println();
	}
	
	/**
	 * For use within main
	 * reads in user input and validates it, loops of it's not a valid int inputed
	 * 
	 * @return value
	 */
	public int getUserChoice() {
		// Modify getuserChoice to handle errors from non-string.
		display();
		menuConsole.print("Enter a number between 1 and 4: ");
		int value = 0;
		boolean validInput = false;
		String valueString = "";
		do {
			try {
				valueString = menuConsole.readLn();
				value = Integer.parseInt(valueString);
				validInput = true;
				return value;
			} catch (Exception e) {
				menuConsole.print("Error reading inputed number. Please enter a number between 1 and 4 e.g 1: ");
				e.printStackTrace();
			}
		} while (!validInput);
		// nextLine to eat leftover space from nextInt
		// input.nextLine();
		return value;
	}
	
	/**
	 * For use within main and search image
	 * reads in user input and validates it, loops of it's not a valid int inputed
	 * 
	 * @return value
	 */
	public int getInt() {
		int value = 0;
		boolean validInput = false;
		String valueString = "";
		do {
			try {
				valueString = menuConsole.readLn();
				value = Integer.parseInt(valueString);
				validInput = true;
				return value;
			} catch (Exception e) {
				menuConsole.print("Error reading inputed number. Please enter a number between 1 and 4 e.g 1: ");
				e.printStackTrace();
			}
		} while (!validInput);
		return value;
	}

	/**
	 * For use within main for view all images
	 * reads in user input and validates it, loops of it's not a valid int inputed
	 * 
	 * @return value
	 */
	public int getUserChoiceNoDisplay() {
		// Modify getuserChoice to handle errors from non-string.
		displayWithoutTitle();
		menuConsole.print("Enter a number between 1 and 4: ");
		int value = 0;
		boolean validInput = false;
		String valueString = "";
		do {
			try {
				valueString = menuConsole.readLn();
				value = Integer.parseInt(valueString);
				validInput = true;
				return value;
			} catch (Exception e) {
				menuConsole.print("Error reading inputed number. Please enter a number between 1 and 4 e.g 1: ");
				e.printStackTrace();
			}
		} while (!validInput);
		// nextLine to eat leftover space from nextInt
		// input.nextLine();
		return value;
	}
	
	/**
	 * For use within searchImage
	 * reads in user input and validates it, loops of it's not a valid int inputed
	 * 
	 * @return value
	 */
	public int getUserSearchChoice() {
		// Modify getuserChoice to handle errors from non-string.
		displayWithoutTitle();
		menuConsole.print("Enter a number between 1 and 6: ");
		int value = 0;
		boolean validInput = false;
		String valueString = "";
		do {
			try {
				valueString = menuConsole.readLn();
				value = Integer.parseInt(valueString);
				validInput = true;
				return value;
			} catch (Exception e) {
				menuConsole.print("Error reading inputed number. Please enter a number between 1 and 6 e.g 1: ");
				e.printStackTrace();
			}
		} while (!validInput);
		// nextLine to eat leftover space from nextInt
		// input.nextLine();
		return value;
	}
}
