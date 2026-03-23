package part01;

import java.time.LocalDate;
import java.util.ArrayList;

public class ImageManager {
	// Initialise necessary variables
	private ArrayList<ImageRecord> manager;

	// Constructor for ImageManager, initialised the array.
	public ImageManager() {
		manager = new ArrayList<ImageRecord>();
	}

	// Add in methods specified in the appendix
	/**
	 * addImage method takes an input of name, description, genre, date, thumbnail
	 * and returns it to ImageRecord
	 * 
	 * @param name
	 * @param description
	 * @param genre
	 * @param date
	 * @param thumbnail
	 */
	public void addImage(String name, String description, ImageType genre, String date, String thumbnail) {
		ImageRecord newImage = new ImageRecord(name, description, genre, date, thumbnail);
		manager.add(newImage);
	}

	/**
	 * Validates if there are any images in manager, if manager.size() = 0 returns
	 * null validates the input of the user, if invalid returns null if it passes
	 * validation returns the id of the image
	 * 
	 * @param id
	 * @return id or null
	 */
	public ImageRecord searchId(int id) {
		// Validation to check if manager array size == 0, if id is out of bounds for
		// manager array size and return null if so
		if (manager.size() == 0) {
			return null;
		} else if (id <= 0) {
			return null;
		} else if (id > manager.size()) {
			return null;
		}
		for (ImageRecord image : manager) {
			if (id == image.getImageID()) {
				return image;
			}
		}
		return null;
	}

	/**
	 * Validates if there are any images in manager, if manager.size() = 0 returns
	 * null validates the input of the user, if invalid returns null if it passes
	 * validation returns the title/name of the image
	 * 
	 * @param str
	 * @return ImageAlbum(matches) or null
	 */
	public ImageAlbum searchTitle(String str) {
		// Validation to check if manager array size == 0, if name is null and return
		// null if so
		if (manager.size() == 0) {
			return null;
		} else if (str == null || str.isBlank()) {
			return null;
		}
		// ArrayList with array "matches", will add a record to matches if title is
		// found
		ArrayList<ImageRecord> matches = new ArrayList<ImageRecord>();
		for (ImageRecord image : manager) {
			if (image.getImageName().toUpperCase().contains(str.trim().toUpperCase())) {
				matches.add(image);
			}
		}
		if (matches.size() == 0) {
			return null;
		}
		return new ImageAlbum(matches);
	}

	/**
	 * Validates if there are any images in manager, if manager.size() = 0 returns
	 * null validates the input of the user, if invalid returns null if it passes
	 * validation returns the description of the image
	 * 
	 * @param str
	 * @return ImageAlbum(matches) or null
	 */
	public ImageAlbum searchDescription(String str) {
		// Validation to check if manager array size == 0, if description is null and
		// return null if so
		if (manager.size() == 0) {
			return null;
		} else if (str == null || str.isBlank()) {
			return null;
		}
		// ArrayList with array "matches", will add a record to matches if description
		// is found
		ArrayList<ImageRecord> matches = new ArrayList<ImageRecord>();
		for (ImageRecord image : manager) {
			if (image.getImageDescription().toUpperCase().contains(str.trim().toUpperCase())) {
				matches.add(image);
			}
		}
		if (matches.size() == 0) {
			return null;
		}
		return new ImageAlbum(matches);
	}

	/**
	 * Validates if there are any images in manager, if manager.size() = 0 returns
	 * null validates the input of the user, if invalid returns null if it passes
	 * validation returns the genre of the image
	 * 
	 * @param type
	 * @return ImageAlbum(matches) or null
	 */
	public ImageAlbum searchGenre(ImageType type) {
		// Validation to check if manager array size == 0, if type for genre is null and
		// return null if so
		if (manager.size() == 0) {
			return null;
		} else if (type == null) {
			return null;
		}
		// ArrayList with array "matches", will add a record to matches if genre is
		// found
		ArrayList<ImageRecord> matches = new ArrayList<ImageRecord>();
		for (ImageRecord image : manager) {
			if (image.getImageGenre() == type) {
				matches.add(image);
			}
		}
		if (matches.size() == 0) {
			return null;
		}
		return new ImageAlbum(matches);
	}

	/**
	 * Validates if there are any images in manager, if manager.size() = 0 returns
	 * null validates the input of the user, if invalid returns null if it passes
	 * validation returns the genre of the image
	 * 
	 * @param start
	 * @param end
	 * @return ImageAlbum(matches) or null
	 */
	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		// Validation to check if manager array size == 0, if the start and end dates
		// are null and return null if so
		if (manager.size() == 0) {
			return null;
		} else if (start == null || end == null) {
			return null;
		}
		// ArrayList with array "matches", will add a record to matches if an image in
		// between the start and end dates
		ArrayList<ImageRecord> matches = new ArrayList<ImageRecord>();
		for (ImageRecord image : manager) {
			if ((image.getImageDate().isAfter(start) || image.getImageDate().isEqual(start))
					&& (image.getImageDate().isBefore(end) || image.getImageDate().isEqual(end))) {
				matches.add(image);
			}
		}
		if (matches.size() == 0) {
			return null;
		}
		return new ImageAlbum(matches);
	}

	/**
	 * Validates if there are any images in manager, if manager.size() = 0 returns
	 * null else will pass all the images from manager to ImageAlbum to sort them
	 * via date
	 * 
	 * @return ImageAlbum(matches)
	 */
	public ImageAlbum getAllImages() {
		// Validation to check if manager array size == 0 and return null if so
		if (manager.size() == 0) {
			return null;
		}
		return new ImageAlbum(manager);
	}

	// Getter to retrieve the manager array size so there can be dynamic values
	// instead of static preset ones
	public int getManagerSize() {
		return manager.size();
	}
}
