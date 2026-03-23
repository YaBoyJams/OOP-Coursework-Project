package part01;

import java.time.LocalDate;
import java.util.Scanner;

public class QUBImages {
	// Initialise constructors from other classes
	private static String title = "Queen's Univeristy Belfast Image Manager";
	private static String options[] = { "Add an image", "Search for an image", "View all images", "Quit application" };
	private static ImageType genre[] = { ImageType.ASTRONOMY, ImageType.ARCHITECTURE, ImageType.SPORT,
			ImageType.LANDSCAPE, ImageType.PORTRAIT, ImageType.NATURE, ImageType.AERIAL, ImageType.FOOD,
			ImageType.OTHER };
	private static String genreName[] = { "Astronomy", "Architecture", "Sport", "Landscape", "Portrait", "Nature",
			"Aerial", "Food", "Other" };

	// Scanner to get user's input
	static Scanner input = new Scanner(System.in);

	// Initialise constructors from other classes
	private static ImageManager manager = new ImageManager();
	private static Menu menu = new Menu(title, options);

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

	public static void main(String[] args) {
		// Implement menu using case switch to perform actions such as add an image,
		// search a specific image, or view all images
		boolean quit = false;
		int option = 0;
		// Calls method to add the predefined images
		addPredefinedImages();
		do {
			option = menu.getUserChoice();
			switch (option) {
			case 1:
				// Implement way to add images
				addImages();
				break;
			case 2:
				// Implement search function, using case switch
				searchChoice();
				break;
			case 3:
				// Display all the images within the system
				printAllImages();
				break;
			case 4:
				quit = true; // Quits the application
				break;
			default:
				System.out.println("Invalid input. To select an option input a number between 1 and 4");
			}
			// Exit the menu
		} while (!quit);
	}

	/**
	 * Adds a new image into the system user is asked if they want to add an image
	 * if they hit it by accident user is asked if they want to add another image
	 * once they've added one loops until user quits calls other methods to do
	 * validation and enter the name counts how many images were added
	 */
	private static void addImages() {
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
		System.out.print("Do you want to add a new image? (Y/N): ");
		do {
			yesNo = input.nextLine();
			if (yesNo.trim().toUpperCase().equals("N") || yesNo.trim().toUpperCase().equals("NO")) {
				leaveApp = true;
				validInput = true;
			} else if (yesNo.trim().toUpperCase().equals("Y") || yesNo.trim().toUpperCase().equals("YES")) {
				validInput = true;
			} else {
				System.out.print("Please enter a valid input (Y for yes/ N for no): ");
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

				System.out.println("Image successfully added.");
				count++;

				// Ask user if they want to add another image
				System.out.print("Do you want to add another image? (Y/N): ");
				do {
					yesNo = input.nextLine();
					if (yesNo.toUpperCase().equals("N") || yesNo.toUpperCase().equals("NO")) {
						leaveApp = true;
						validInput = true;
					} else if (yesNo.toUpperCase().equals("Y") || yesNo.toUpperCase().equals("YES")) {
						validInput = true;
					} else {
						System.out.print("Please enter a valid input (Y for yes/ N for no): ");
					}
				} while (!validInput);
			} while (!leaveApp);
		}
		System.out.println("You have added " + count + " image(s) to the sytem.");
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
			System.out.print("Please enter the images name: ");
			name = input.nextLine();
			if ((name != null) && !(name.isBlank()) && !(name.trim().matches("-?\\d+"))) {
				validName = true;
			} else {
				System.out.println("Please enter a valid name. It cannot be null or a number.");
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
			System.out.print("Please enter the images description: ");
			description = input.nextLine();
			if ((description != null) && !(description.isBlank()) && !(description.trim().matches("-?\\d+"))) {
				validDescription = true;
			} else {
				System.out.println("Please enter a valid description. It cannot be null or a number.");
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
			System.out.print(
					"Please enter the images thumbnail. Please put it in the format of a file name e.g. Andromeda.png: ");
			thumbnail = input.nextLine();
			if ((thumbnail != null) && thumbnail.trim().contains(".")) {
				validThumbnail = true;
			} else {
				System.out.println(
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
		Menu choiceMenu = new Menu(title, choice);
		boolean exit = false;
		int index = 0;
		do {
			index = choiceMenu.getUserSearchChoice();
			switch (index) {
			case 1:
				printImageId();
				break;
			case 2:
				printImageName();
				break;
			case 3:
				printImageDescription();
				break;
			case 4:
				printGenreChoice();
				break;
			case 5:
				printImagesBetweenDates();
				break;
			case 6:
				exit = true;
				break;
			default:
				System.out.println("Invalid input. To select an option input a number between 1 and 6");
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
		System.out.println("Please enter the genre of the image from 1-9: ");
		for (int i = 0; i < genre.length; i++) {
			System.out.println("Genre choice: " + (i + 1) + " " + genreName[i] + " Genre Description: " + genre[i]);
		}
		int selection = 0;
		boolean genreAdded = false;
		do {
			try {
				selection = input.nextInt();
			} catch (Exception e) {
				System.out.println("Error reading inputed number");
				e.printStackTrace();
			}
			// nextLine to eat leftover space from nextInt
			input.nextLine();
			if (selection >= 1 && selection <= genre.length) {
				genreAdded = true;
				return genre[selection - 1];
			} else {
				System.out.print("Invalid genre. To select an option input a number between 1 and 9: ");
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
		System.out.print("Please enter the name of the image you want to view: ");
		do {
			imageName = input.nextLine();
			if (imageName == null || imageName.isBlank()) {
				System.out.print("Invalid name entered. Please enter a name that isn't empty (null).");
			} else if (imageName.trim().matches("-?\\d+")) {
				System.out.print("Invalid name entered. Please enter a name that isn't a number: ");
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
		System.out.print("Please enter the description of the image you want to view: ");
		do {
			imageDescription = input.nextLine();
			if (imageDescription == null || imageDescription.isBlank()) {
				System.out.print("Invalid description entered. Please enter a name that isn't empty (null).");
			} else if (imageDescription.trim().matches("-?\\d+")) {
				System.out.print("Invalid description entered. Please enter a name that isn't a number: ");
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
		System.out.print("Please enter the ID of the image you want to view: ");

		do {
			try {
				id = input.nextInt();
				validId = true;
			} catch (Exception e) {
				System.out.println("Error reading inputed number");
				e.printStackTrace();
			}
			// nextLine to eat leftover space from nextInt
			input.nextLine();
			
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
			System.out.print("Please enter the date of the image in the format yyyy-mm-dd: ");
			startDate = input.nextLine();
			try {
				date = LocalDate.parse(startDate);
				validDate = true;
				return date;
			} catch (Exception e) {
				System.out.println("Error validating date. Please Make sure it is in the format yyyy-mm-dd");
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
			System.out.print("Please enter the start date in the format yyyy-mm-dd: ");
			startDate = input.nextLine();
			try {
				start = LocalDate.parse(startDate);
				validDate = true;
				return start;
			} catch (Exception e) {
				System.out.println("Error validating date. Please Make sure it is in the format yyyy-mm-dd");
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
			System.out.print("Please enter the start end in the format yyyy-mm-dd : ");
			endDate = input.nextLine();
			try {
				end = LocalDate.parse(endDate);
				validDate = true;
				return end;
			} catch (Exception e) {
				System.out.println("Error validating date. Please Make sure it is in the format yyyy-mm-dd");
				e.printStackTrace();
			}
		} while (!validDate);

		return null;
	}

	// Methods to print messages

	/**
	 * Creates a new ImageAlbum object and prints the first one then loops through
	 * it printing the next objects
	 */
	private static void printAllImages() {
		ImageAlbum allImages = manager.getAllImages();
		if (allImages == null) {
			System.out.println("No images to display.");
			return;
		}
		printAllImage(allImages);
	}

	/**
	 * Calls getGenreChoice so user can input their wanted genre Creates a new
	 * ImageAlbum object and prints the first one then loops through it printing the
	 * next objects
	 */
	private static void printGenreChoice() {
		ImageType selectedGenre = getGenreChoice();
		ImageAlbum imageGenre = manager.searchGenre(selectedGenre);
		if (imageGenre == null) {
			System.out.println("No images with that genre to display.");
			return;
		}
		System.out.println(imageGenre.getFirst());
		if (manager.getManagerSize() >= 2) {
			for (int i = 1; i < imageGenre.getAlbumSize(); i++) {
				System.out.println(imageGenre.getNext());
			}
		}
	}

	/**
	 * Calls getImageName so user can input their wanted name Creates a new
	 * ImageAlbum object and prints the first one then loops through it printing the
	 * next objects
	 */
	private static void printImageName() {
		String selectedName = getImageName();
		ImageAlbum imageName = manager.searchTitle(selectedName);
		if (imageName == null) {
			System.out.println("No images with that name to display.");
			return;
		}
		System.out.println(imageName.getFirst());
		if (manager.getManagerSize() >= 2) {
			for (int i = 1; i < imageName.getAlbumSize(); i++) {
				System.out.println(imageName.getNext());
			}
		}
	}

	/**
	 * Calls getImageDescription so user can input their wanted description Creates
	 * a new ImageAlbum object and prints the first one then loops through it
	 * printing the next objects
	 */
	private static void printImageDescription() {
		String selectedDescription = getImageDescription();
		ImageAlbum imageDescription = manager.searchDescription(selectedDescription);
		if (imageDescription == null) {
			System.out.println("No images with that description to display.");
			return;
		}
		System.out.println(imageDescription.getFirst());
		if (manager.getManagerSize() >= 2) {
			for (int i = 1; i < imageDescription.getAlbumSize(); i++) {
				System.out.println(imageDescription.getNext());
			}
		}
	}

	/**
	 * Calls getImageId so user can input their wanted id Creates a new ImageAlbum
	 * object and prints the first one then loops through it printing the next
	 * objects
	 */
	private static void printImageId() {
		int selectedId = getImageId();
		ImageRecord imageId = manager.searchId(selectedId);
		if (imageId == null) {
			System.out.println("No images with that id to display.");
			return;
		}
		System.out.println(imageId);
	}

	/**
	 * Calls getStartDate and getEndDate so user can search between the two dates
	 * Creates a new ImageAlbum object and prints the first one then loops through
	 * it printing the next objects
	 */
	private static void printImagesBetweenDates() {
		LocalDate startDate = getStartDate();
		LocalDate endDate = getEndDate();
		ImageAlbum imageDates = manager.searchDates(startDate, endDate);
		if (imageDates == null) {
			System.out.println("No images within those dates to display.");
			return;
		}
		System.out.println(imageDates.getFirst());
		if (manager.getManagerSize() >= 2) {
			for (int i = 1; i < imageDates.getAlbumSize(); i++) {
				System.out.println(imageDates.getNext());
			}
		}
	}
	
	/**
	 * Sets up a loop, if the user exits the loop the console closes uses preset
	 * path + getThumbnailName() to retrieve first image directory prints out the
	 * image uses a switch case to ask the user for previous or next image, checking
	 * if the return is valid or null image in the list makes use of getNext() and
	 * getPrevious to cycle through all the images
	 */
	private static void printAllImage(ImageAlbum albumImage) {
		// booleans for do while loops
		boolean leaveApp = false;
		boolean validInput = false;
		// Default "invalid" input
		int inputInt = -1;
		// Cast the first record in the album to ImageRecord to get the thumbnail
		System.out.println("Image 1 out of " + albumImage.getAlbumSize());
		System.out.println(albumImage.getFirst());;
		System.out.print(
				"Press 1 to view the next image, press 2 to view the previous image, press 9 to close the window: ");
		// loop to make user input a valid number.
		do {
			do {
				try {
					inputInt = input.nextInt();
					validInput = true;
				} catch (Exception e) {
					System.out.print("Error reading inputed number. Please enter a valid number: ");
					e.printStackTrace();
				}
				input.nextLine(); // Eat the input after nextInt()
			} while (!validInput);
			switch (inputInt) {
			case 1:
				// Gets the image details for the next image and displays it
				ImageRecord nextImage = albumImage.getNext();
				if (nextImage == null) {
					System.out.print(
							"No next image to display. Press 2 to view the previous image, press 9 to close the window: ");
					break;
				}
				System.out.println(nextImage);
				System.out.print(
						"Press 1 to view the next image, press 2 to view the previous image, press 9 to close the window: ");
				break;
				// Gets the image details for the previous image and displays it
			case 2:
				ImageRecord previousImage = albumImage.getPrevious();
				if ( previousImage == null) {
					System.out.print(
							"No previous image to display. Press 1 to view next image, press 9 to close the window: ");
					break;
				}
				System.out.println(previousImage);
				System.out.print(
						"Press 1 to view the next image, press 2 to view the previous image, press 9 to close the window: ");
				break;
			case 9:
				// Leaves the loop
				leaveApp = true;
				break;
			default:
				// Default value specifies only 1, 2, and 9 are allowed
				System.out.print("Invalid input. Please enter 1 for next, 2 for previous, 9 to close window: ");
				break;
			}
		} while (!leaveApp);
	}
}
