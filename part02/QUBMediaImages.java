package part02;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.util.Scanner;
import console.Console;

public class QUBMediaImages {
	// Initialise constructors from other classes
	private static String title = "Queen's Univeristy Belfast Image Manager";
	private static String options[] = { "Add an image", "Search for an image", "View all images", "Quit application" };
	private static ImageType genre[] = { ImageType.ASTRONOMY, ImageType.ARCHITECTURE, ImageType.SPORT,
			ImageType.LANDSCAPE, ImageType.PORTRAIT, ImageType.NATURE, ImageType.AERIAL, ImageType.FOOD,
			ImageType.OTHER };
	private static String genreName[] = { "Astronomy", "Architecture", "Sport", "Landscape", "Portrait", "Nature",
			"Aerial", "Food", "Other" };

	// Scanner to get user's input
	static Scanner in = new Scanner(System.in);
	// Creates a "universal" console all methods can access
	private static Console mainConsole = createNewConsole();

	// Initialise constructors from other classes
	private static ImageManager manager = new ImageManager();
	private static Menu menu = new Menu(title, options, mainConsole);
	private static DisplayImages showImages;

	// Method to add predefined images into manager
	private static void addPredefinedImages() {
		manager.addImage("Andromeda Galaxy", "Image of the Andromeda galaxy.", ImageType.ASTRONOMY, "2023-01-01",
				"Andromeda.png");
		manager.addImage("Lanyon QUB", "An image of the QUB Lanyon building.", ImageType.ARCHITECTURE, "2023-02-01",
				"LanyonQUB.png");
		manager.addImage("Kermit Plays Golf", "An image of Kermit the frog playing golf.", ImageType.SPORT,
				"2023-03-01", "KermitGolf.png");
		manager.addImage("Mourne Mountains", "A panoramic view of the Mourne mountains.", ImageType.LANDSCAPE,
				"2023-04-01", "Mournes.png");
		manager.addImage("Homer Simpson", "Homer Simpson- A portrait of the man.", ImageType.PORTRAIT, "2023-03-01",
				"Homer.png");
		manager.addImage("Red Kite", "A Red Kite bird of prey in flight.", ImageType.NATURE, "2023-03-01",
				"RedKite.png");
		manager.addImage("Central Park", "An overhead view of Central Park New York USA.", ImageType.AERIAL,
				"2023-05-01", "CentralPark.png");
		manager.addImage("Apples", "A bunch of apples.", ImageType.FOOD, "2023-06-01", "Apples.png");
		manager.addImage("Programming Meme", "A Chat GPT programming meme.", ImageType.OTHER, "2023-07-01",
				"ChatGPT.png");
	}

	/**
	 * Method creates a new console each time it's called with "default" values
	 * 
	 * @return newConsole
	 */
	private static Console createNewConsole() {
		// True allows keyboard input from user
		Console newConsole = new Console(true);
		// Set console characteristics
		newConsole.setBgColour(Color.BLACK);
		newConsole.setSize(1280, 720);
		newConsole.setFont(new Font("Ariel", Font.PLAIN, 12));
		newConsole.setColour(Color.WHITE);
		newConsole.setVisible(true);

		return newConsole;
	}

	/**
	 * Uses switch case statement so user can select which option they want
	 * calls different methods depending on what the user wants to do
	 * loops until the user wants to leave the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Implement menu using case switch to perform actions such as add an image,
		// search a specific image, or view all images
		boolean quit = false;
		int option = 0;
		boolean clear = true;
		boolean print = true;
		// Calls method to add the predefined images
		addPredefinedImages();
		do {
			// Clears any previous text from the console
			if (clear == true) {
				mainConsole.clear();
				option = menu.getUserChoice();
				// If an invalid input occurs doesn't clear console or print out the title +
				// menu again, just error message and reads in an int
			} else if (clear == false) {
				// Method allows if there are no images for it to dispay "No images found" and print out the option again without the console clearing
				if(!print) {
					option = menu.getUserChoiceNoDisplay();
				}else {
					option = menu.getInt();
				}
			}
			switch (option) {
			case 1:
				// Implement way to add images
				addImages();
				clear = true;
				break;
			case 2:
				// Implement search function, using case switch
				searchChoice();
				clear = true;
				break;
			case 3:
				// Display all the images within the system
				print = printAllImages();
				// Clears console if images are returned
				if(print) {
					clear = true;
				}else {
					clear = false;
				}
				break;
			case 4:
				quit = true; // Quits the application
				clear = true;
				;
				break;
			default:
				mainConsole.print("Invalid input. To select an option input a number between 1 and 4 e.g. 1: ");
				clear = false;
				;
			}
			// Exit the menu
		} while (!quit);
		mainConsole.setVisible(false);
	}

	/**
	 * Adds a new image into the system user is asked if they want to add an image
	 * if they hit it by accident user is asked if they want to add another image
	 * once they've added one loops until user quits calls other methods to do
	 * validation and enter the name counts how many images were added
	 */
	private static void addImages() {
		// Clears console of previous lines
		mainConsole.clear();
		// Setup booleans for validation
		boolean leaveApp = false;
		boolean validInput = false;

		// Setup variables;
		String name = "";
		String description = "";
		ImageType genre;
		LocalDate date;
		String thumbnail = "";
		String yesNo = "";
		int count = 0;

		// Ask user if they want to add an image
		mainConsole.print("Do you want to add a new image? (Y/N): ");
		do {
			yesNo = mainConsole.readLn();
			if (yesNo.trim().toUpperCase().equals("N") || yesNo.trim().toUpperCase().equals("NO")) {
				leaveApp = true;
				validInput = true;
			} else if (yesNo.trim().toUpperCase().equals("Y") || yesNo.trim().toUpperCase().equals("YES")) {
				validInput = true;
			} else {
				mainConsole.print("Please enter a valid input (Y for yes/ N for no): ");
			}
		} while (!validInput);
		if (leaveApp == false) {
			do {
				// Reset values as they were previously used
				yesNo = "";
				validInput = false;

				name = addName();
				description = addDescription();
				genre = getGenreChoice();
				date = getImageDate();
				thumbnail = addThumbnail();

				manager.addImage(name, description, genre, date.toString(), thumbnail);

				mainConsole.println("Image successfully added.");
				count++;

				// Ask user if they want to add another image
				mainConsole.print("Do you want to add another image? (Y/N): ");
				do {
					yesNo = mainConsole.readLn();
					if (yesNo.toUpperCase().equals("N") || yesNo.toUpperCase().equals("NO")) {
						leaveApp = true;
						validInput = true;
					} else if (yesNo.toUpperCase().equals("Y") || yesNo.toUpperCase().equals("YES")) {
						validInput = true;
					} else {
						mainConsole.print("Please enter a valid input (Y for yes/ N for no): ");
					}
				} while (!validInput);
			} while (!leaveApp);
		}
		mainConsole.println("You have added " + count + " image(s) to the sytem.");
	}

	/*
	 * Adds name to addImages validates that the name isn't null or a number
	 * 
	 * @return name
	 */
	private static String addName() {
		String name;
		boolean validName = false;
		do {
			mainConsole.print("Please enter the images name: ");
			name = mainConsole.readLn();
			if ((name != null) && !(name.isBlank()) && !(name.trim().matches("-?\\d+"))) {
				validName = true;
			} else {
				mainConsole.println("Please enter a valid name. It cannot be null or a number.");
			}
		} while (!validName);
		return name;
	}

	/**
	 * Adds description to addImages validates that the description isn't null or a
	 * number
	 * 
	 * @return description
	 */
	private static String addDescription() {
		String description;
		boolean validDescription = false;
		do {
			mainConsole.print("Please enter the images description: ");
			description = mainConsole.readLn();
			if ((description != null) && !(description.isBlank()) && !(description.trim().matches("-?\\d+"))) {
				validDescription = true;
			} else {
				mainConsole.println("Please enter a valid description. It cannot be null or a number.");
			}

		} while (!validDescription);
		return description;
	}

	/**
	 * Adds thumbnail to addImages validates that the thumbnail isn't null or a
	 * number and contains a period e.g. "." to be in the correct format
	 * 
	 * @return thumbnail
	 */
	private static String addThumbnail() {
		String thumbnail;
		boolean validThumbnail = false;
		do {
			mainConsole.print(
					"Please enter the images thumbnail. Please put it in the format of a file name e.g. Andromeda.png: ");
			thumbnail = mainConsole.readLn();
			if ((thumbnail != null) && thumbnail.trim().contains(".")) {
				validThumbnail = true;
			} else {
				mainConsole.println(
						"Please enter a valid thumbnail. It cannot be null or a number and must contain a period e.g. \".\".");
			}
		} while (!validThumbnail);
		return thumbnail;
	}

	/**
	 * Implements menu class and case switch loops unit user decides to exit user's
	 * selected input decides which case and therefore which method is used
	 */
	private static void searchChoice() {
		String choice[] = { "Search by image id", "Search by image name.", "Search by image description",
				"Search by image genre", "Search for images in between two dates", "Quit Search Menu" };
		Menu choiceMenu = new Menu(title, choice, mainConsole);
		boolean exit = false;
		int index = 0;
		boolean clear = true;
		do {
			// Clears any previous text from the console
			if (clear == true) {
				mainConsole.clear();
				index = choiceMenu.getUserSearchChoice();
				// If an invalid input occurs doesn't clear console or print out the title +
				// menu again, just error message and reads in an int
			} else if (clear == false) {
				index = choiceMenu.getUserSearchChoice();
			}
			// prints return back a boolean to indicate if they have found an image (will
			// clear console)
			// or if they haven't found an image (won't clear console and will keep the no
			// image found message)
			switch (index) {
			case 1:
				clear = printImageId();
				break;
			case 2:
				clear = printImageName();
				break;
			case 3:
				clear = printImageDescription();
				break;
			case 4:
				clear = printGenreChoice();
				break;
			case 5:
				clear = printImagesBetweenDates();
				break;
			case 6:
				exit = true;
				clear = true;
				break;
			default:
				mainConsole.print("Invalid input. To select an option input a number between 1 and 6 e.g. 1: ");
				clear = false;
			}
			// Exit the menu
		} while (!exit);
	}

	/**
	 * Method which displays available genres user will input a value between 1-9 to
	 * select a genre goes through validation to make sure an int was inputed and it
	 * was in between 1-9
	 * 
	 * @return object within array genre between 1-9
	 */
	private static ImageType getGenreChoice() {
		mainConsole.println("Please enter the genre of the image from 1-9: ");
		for (int i = 0; i < genre.length; i++) {
			mainConsole.println("Genre choice: " + (i + 1) + " " + genreName[i] + " Genre Description: " + genre[i]);
		}
		int selection = 0;
		boolean genreAdded = false;
		do {
			try {
				selection = Integer.parseInt(mainConsole.readLn());
			} catch (Exception e) {
				mainConsole.println("Error reading inputed number. Please enter a valid number.");
				e.printStackTrace();
			}
			// nextLine to eat leftover space from nextInt
			// input.nextLine();
			if (selection >= 1 && selection <= genre.length) {
				genreAdded = true;
				return genre[selection - 1];
			} else {
				mainConsole.print("Invalid genre. To select an option input a number between 1 and 9: ");
			}
		} while (!genreAdded);

		return null;
	}

	/**
	 * Validates user input making sure it isn't null
	 * 
	 * @return imageName
	 */
	private static String getImageName() {
		String imageName = "";
		boolean validName = false;
		mainConsole.print("Please enter the name of the image you want to view: ");
		do {
			imageName = mainConsole.readLn();
			if (imageName == null || imageName.isBlank()) {
				mainConsole.print("Invalid name entered. Please enter a name that isn't empty (null).");
			} else if (imageName.trim().matches("-?\\d+")) {
				mainConsole.print("Invalid name entered. Please enter a name that isn't a number: ");
			} else {
				validName = true;
				return imageName.trim();
			}
		} while (!validName);

		return null;
	}

	/**
	 * Validates user input making sure it isn't null
	 * 
	 * @return imageName
	 */
	private static String getImageDescription() {
		String imageDescription = "";
		boolean validDescription = false;
		mainConsole.print("Please enter the description of the image you want to view: ");
		do {
			imageDescription = mainConsole.readLn();
			if (imageDescription == null || imageDescription.isBlank()) {
				mainConsole.print("Invalid description entered. Please enter a name that isn't empty (null).");
			} else if (imageDescription.trim().matches("-?\\d+")) {
				mainConsole.print("Invalid description entered. Please enter a name that isn't a number: ");
			} else {
				validDescription = true;
				return imageDescription.trim();
			}
		} while (!validDescription);

		return null;
	}

	/**
	 * Validates user input making sure it isn't null or a char/string checks if the
	 * id within bounds with the array size
	 * 
	 * @return id
	 */
	private static int getImageId() {
		int id = -1;
		boolean validId = false;
		mainConsole.print("Please enter the ID of the image you want to view: ");

		do {
			try {
				id = Integer.parseInt(mainConsole.readLn());
				validId = true;
			} catch (Exception e) {
				mainConsole.println("Error reading inputed number. Please enter a valid number.");
				e.printStackTrace();
			}
		} while (!validId);

		return id;
	}

	/**
	 * User inputs a date, try catch within a loop checks if the input was valid if
	 * valid input returns the date if invalid loops user
	 * 
	 * @return date
	 */
	private static LocalDate getImageDate() {
		String startDate = "";
		LocalDate date = null;
		boolean validDate = false;
		do {
			mainConsole.print("Please enter the date of the image in the format yyyy-mm-dd: ");
			startDate = mainConsole.readLn();
			try {
				date = LocalDate.parse(startDate);
				validDate = true;
				return date;
			} catch (Exception e) {
				mainConsole.println("Error validating date. Please Make sure it is in the format yyyy-mm-dd");
				e.printStackTrace();
			}
		} while (!validDate);

		return null;
	}

	/**
	 * User inputs a date, try catch within a loop checks if the input was valid if
	 * valid input returns the date if invalid loops user
	 * 
	 * @return start
	 */
	private static LocalDate getStartDate() {
		String startDate = "";
		LocalDate start = null;
		boolean validDate = false;
		do {
			mainConsole.print("Please enter the start date in the format yyyy-mm-dd: ");
			startDate = mainConsole.readLn();
			try {
				start = LocalDate.parse(startDate);
				validDate = true;
				return start;
			} catch (Exception e) {
				mainConsole.println("Error validating date. Please Make sure it is in the format yyyy-mm-dd");
				e.printStackTrace();
			}
		} while (!validDate);

		return null;
	}

	/**
	 * User inputs a date, try catch within a loop checks if the input was valid if
	 * valid input returns the date if invalid loops user
	 * 
	 * @return end
	 */
	private static LocalDate getEndDate() {
		String endDate = "";
		LocalDate end = null;
		boolean validDate = false;
		do {
			mainConsole.print("Please enter the start end in the format yyyy-mm-dd : ");
			endDate = mainConsole.readLn();
			try {
				end = LocalDate.parse(endDate);
				validDate = true;
				return end;
			} catch (Exception e) {
				mainConsole.println("Error validating date. Please Make sure it is in the format yyyy-mm-dd");
				e.printStackTrace();
			}
		} while (!validDate);

		return null;
	}

	// Methods to print messages

	/**
	 * Creates a new ImageAlbum object and checks if it is null or not
	 * calls a method to print out the images
	 * boolean so it returns true or false depending if an image was found or not
	 * 
	 * @return true or false
	 */
	private static boolean printAllImages() {
		ImageAlbum allImages = manager.getAllImages();
		if (allImages == null) {
			mainConsole.println("No images to display. Try again or choose another option.");
			return false;
		}
		showImages = new DisplayImages(allImages);
		showImages.printAllImages();
		return true;
	}

	/**
	 * Calls getGenreChoice so user can input their wanted genre Creates a new
	 * ImageAlbum object and prints the first one then loops through it printing the
	 * next objects
	 * boolean so it returns true or false depending if an image was found or not
	 * 
	 * @return true or false
	 */
	private static boolean printGenreChoice() {
		ImageType selectedGenre = getGenreChoice();
		ImageAlbum imageGenre = manager.searchGenre(selectedGenre);
		if (imageGenre == null) {
			mainConsole.println("No images with that genre to display. Try again or choose another option.");
			return false;
		}
		showImages = new DisplayImages(imageGenre);
		showImages.printImageAlbum();
		return true;
	}

	/**
	 * Calls getImageName so user can input their wanted name Creates a new
	 * ImageAlbum object and prints the first one then loops through it printing the
	 * next objects
	 * boolean so it returns true or false depending if an image was found or not
	 * 
	 * @return true or false
	 */
	private static boolean printImageName() {
		String selectedName = getImageName();
		ImageAlbum imageName = manager.searchTitle(selectedName);
		if (imageName == null) {
			mainConsole.println("No images with that name to display. Try again or choose another option.");
			return false;
		}
		showImages = new DisplayImages(imageName);
		showImages.printImageAlbum();
		return true;
	}

	/**
	 * Calls getImageDescription so user can input their wanted description Creates
	 * a new ImageAlbum object and prints the first one then loops through it
	 * printing the next objects
	 * boolean so it returns true or false depending if an image was found or not
	 * 
	 * @return true or false
	 */
	private static boolean printImageDescription() {
		String selectedDescription = getImageDescription();
		ImageAlbum imageDescription = manager.searchDescription(selectedDescription);
		if (imageDescription == null) {
			mainConsole.println("No images with that description to display. Try again or choose another option.");
			return false;
		}
		showImages = new DisplayImages(imageDescription);
		showImages.printImageAlbum();
		return true;
	}

	/**
	 * Calls getImageId so user can input their wanted id Creates a new ImageAlbum
	 * object and prints the first one then loops through it printing the next
	 * objects
	 * boolean so it returns true or false depending if an image was found or not
	 * 
	 * @return true or false
	 */
	private static boolean printImageId() {
		int selectedId = getImageId();
		ImageRecord imageId = manager.searchId(selectedId);
		if (imageId == null) {
			mainConsole.println("No images with that id to display. Try again or choose another option.");
			return false;
		}
		showImages = new DisplayImages(imageId);
		showImages.printImageRecord();
		return true;
	}

	/**
	 * Calls getStartDate and getEndDate so user can search between the two dates
	 * Creates a new ImageAlbum object and prints the first one then loops through
	 * it printing the next objects
	 * boolean so it returns true or false depending if an image was found or not
	 * 
	 * @return true or false
	 */
	private static boolean printImagesBetweenDates() {
		LocalDate startDate = getStartDate();
		LocalDate endDate = getEndDate();
		ImageAlbum imageDates = manager.searchDates(startDate, endDate);
		if (imageDates == null) {
			mainConsole.println("No images within those dates to display. Try again or choose another option.");
			return false;
		}
		showImages = new DisplayImages(imageDates);
		showImages.printImageAlbum();
		return true;
	}
}
