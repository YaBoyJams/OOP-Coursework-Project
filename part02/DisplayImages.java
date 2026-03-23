package part02;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

import console.Console;

public class DisplayImages {
	/**
	 * Take in ImageAlbum or ImageRecord data types use .parts to brake up the
	 * string and get the thumbnail using thumbnail to reference the dir of images
	 * to display the images
	 */
	private ImageAlbum albumImage;
	private ImageRecord recordImage;
	private Console imageConsole = createConsole();

	/**
	 * Method creates a new console each time it's called with "default" values
	 * 
	 * @return newConsole
	 */
	private static Console createConsole() {
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

	/*
	 * Takes in an ImageAlbum type and sets it uses printImageAlbum method to
	 * display the images
	 */
	public DisplayImages(ImageAlbum image) {
		this.albumImage = image;

	}

	/*
	 * Overrides the first constructor takes in an ImageAlbum type and sets it uses
	 * printImageRecord to display the images
	 */
	public DisplayImages(ImageRecord image) {
		this.recordImage = image;

	}

	/**
	 * Sets up a loop, if the user exits the loop the console closes uses preset
	 * path + getThumbnailName() to retrieve the image directory prints out the
	 * image to the console then the image details
	 */
	public void printImageRecord() {
		// booleans for do while loops
		boolean validInput = false;
		boolean leaveApp = false;
		String yesNo;
		// Used to find user directory for images
		String userdir = System.getProperty("user.dir");
		String path = userdir + "\\src\\Images\\";

		do {
			String thumbnail = path + recordImage.getThumbnailName();
			ImageIcon img = new ImageIcon(thumbnail);
			imageConsole.println(recordImage);
			imageConsole.println(img);
			// Ask if user wants to close the window
			imageConsole.print("Close window? (Y/N): ");
			do {
				yesNo = imageConsole.readLn();
				if (yesNo.toUpperCase().equals("N") || yesNo.toUpperCase().equals("NO")) {
					validInput = true;
				} else if (yesNo.toUpperCase().equals("Y") || yesNo.toUpperCase().equals("YES")) {
					validInput = true;
					leaveApp = true;
				} else {
					imageConsole.print("Please enter a valid input (Y for yes/ N for no): ");
				}
			} while (!validInput);
		} while (!leaveApp);
		imageConsole.setVisible(false);
	}

	/**
	 * Sets up a loop, if the user exits the loop the console closes uses preset
	 * path + getThumbnailName() to retrieve first image directory prints out the
	 * image to the console then the image details repeats until it reaches the last
	 * image in the list
	 */
	public void printImageAlbum() {
		// booleans for do while loops
		boolean validInput = false;
		boolean leaveApp = false;
		String yesNo;
		// Used to find user directory for images
		String userdir = System.getProperty("user.dir");
		String path = userdir + "\\src\\Images\\";

		do {
			// Cast the first record in the album to ImageRecord to get the thumbnail
			ImageRecord imageDetails = (ImageRecord) albumImage.getFirst();
			String thumbnail = path + imageDetails.getThumbnailName();
			ImageIcon img = new ImageIcon(thumbnail);
			imageConsole.println(albumImage.getFirst());
			imageConsole.println(img);
			// Loops through image album printing the next image
			if (albumImage.getAlbumSize() >= 2) {
				for (int i = 1; i < albumImage.getAlbumSize(); i++) {
					ImageRecord nextImage = albumImage.getNext();
					imageDetails = (ImageRecord) nextImage;
					thumbnail = path + imageDetails.getThumbnailName();
					img = new ImageIcon(thumbnail);
					imageConsole.println(nextImage);
					imageConsole.println(img);
				}
			}
			// Ask if user wants to close the window
			imageConsole.print("Close window? (Y/N): ");
			do {
				yesNo = imageConsole.readLn();
				if (yesNo.toUpperCase().equals("N") || yesNo.toUpperCase().equals("NO")) {
					validInput = true;
				} else if (yesNo.toUpperCase().equals("Y") || yesNo.toUpperCase().equals("YES")) {
					validInput = true;
					leaveApp = true;
				} else {
					imageConsole.print("Please enter a valid input (Y for yes/ N for no): ");
				}
			} while (!validInput);
		} while (!leaveApp);
		imageConsole.setVisible(false);
	}

	/**
	 * Sets up a loop, if the user exits the loop the console closes uses preset
	 * path + getThumbnailName() to retrieve first image directory prints out the
	 * image uses a switch case to ask the user for previous or next image, checking
	 * if the return is valid or null image in the list makes use of getNext() and
	 * getPrevious to cycle through all the images
	 */
	public void printAllImages() {
		// booleans for do while loops
		boolean leaveApp = false;
		boolean validInput = false;
		// Default "invalid" input
		int input = -1;
		// Used to find user directory for images
		String userdir = System.getProperty("user.dir");
		String path = userdir + "\\src\\Images\\";
		// Cast the first record in the album to ImageRecord to get the thumbnail
		ImageRecord imageDetails = (ImageRecord) albumImage.getFirst();
		String thumbnail = path + imageDetails.getThumbnailName();
		ImageIcon img = new ImageIcon(thumbnail);
		imageConsole.println("Image 1 out of " + albumImage.getAlbumSize());
		imageConsole.println(albumImage.getFirst());
		imageConsole.println(img);
		imageConsole.print(
				"Press 1 to view the next image, press 2 to view the previous image, press 9 to close the window: ");
		// loop to make user input a valid number.
		do {
			do {
				try {
					input = Integer.parseInt(imageConsole.readLn());
					validInput = true;
				} catch (Exception e) {
					imageConsole.print("Error reading inputed number. Please enter a valid number: ");
					e.printStackTrace();
				}
			} while (!validInput);
			switch (input) {
			case 1:
				// Gets the image details for the next image and displays it
				ImageRecord nextImage = albumImage.getNext();
				if (nextImage == null) {
					imageConsole.print(
							"No next image to display. Press 2 to view the previous image, press 9 to close the window: ");
					break;
				}
				imageDetails = (ImageRecord) nextImage;
				thumbnail = path + imageDetails.getThumbnailName();
				img = new ImageIcon(thumbnail);
				imageConsole.println(nextImage);
				imageConsole.println(img);
				imageConsole.print(
						"Press 1 to view the next image, press 2 to view the previous image, press 9 to close the window: ");
				break;
			case 2:
				// Gets the image details for the previous image and displays it
				ImageRecord nextPrevious = albumImage.getPrevious();
				if (nextPrevious == null) {
					imageConsole.print(
							"No previous image to display. Press 1 to view next image, press 9 to close the window: ");
					break;
				}
				imageDetails = (ImageRecord) nextPrevious;
				thumbnail = path + imageDetails.getThumbnailName();
				img = new ImageIcon(thumbnail);
				imageConsole.println(nextPrevious);
				imageConsole.println(img);
				imageConsole.print(
						"Press 1 to view the next image, press 2 to view the previous image, press 9 to close the window: ");
				break;
			case 9:
				// Leaves the loop
				leaveApp = true;
				break;
			default:
				// Default value specifies only 1, 2, and 9 are allowed
				imageConsole.print("Invalid input. Please enter 1 for next, 2 for previous, 9 to close window: ");
				break;
			}
		} while (!leaveApp);
		imageConsole.setVisible(false);
	}
}