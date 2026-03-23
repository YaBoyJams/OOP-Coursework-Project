package part02;

import java.util.ArrayList;

public class ImageAlbum {
	// Initialise necessary variables
	// Array to go through all the images
	private ArrayList<ImageRecord> album;
	private int index = -1;

	/**
	 * Constructor which adds all images from the array It sorts it via a sorting
	 * method
	 * 
	 * @param list
	 */
	public ImageAlbum(ArrayList<ImageRecord> list) {
		album = new ArrayList<ImageRecord>();
		for (ImageRecord image : list) {
			// Adds image to sorting algorithm and adds it to the array
			sortAddedImages(image);
		}
	}

	/**
	 * Algorithm adds images to the array and sorts them based on the date
	 * 
	 * @param image
	 */
	private void sortAddedImages(ImageRecord image) {
		boolean added = false;
		// If array is empty then add image
		if (album.size() == 0) {
			album.add(image);
			return;
		}
		// Loop to check if the date is earlier than the one currently in the array
		if (album.size() > 0) {
			for (int i = 0; i < album.size(); i++) {
				if (image.getImageDate().isBefore(album.get(i).getImageDate())) {
					album.add(i, image);
					added = true;
					return;
				}
			}
			if (!added) {
				// Adds image at the end of the array if no earlier date is found
				album.add(image);
			}
		}
	}

	// Add in methods specified in the appendix
	/**
	 * Validates if there are any images in album, if album.size() = 0 returns null
	 * sets the index to 0
	 * 
	 * @return album object at index or null
	 */
	public ImageRecord getFirst() {
		if (album.size() == 0) {
			return null;
		}
		index = 0;
		return album.get(index);
	}

	/**
	 * Validates if there are any images in album, if album.size() = 0 returns null
	 * checks if the index is greater than or equal to the size as the album, if it
	 * is returns null checks if index is less than album.size(), if it then
	 * increments index
	 * 
	 * @return album object at index or null
	 */
	public ImageRecord getNext() {
		// If there are no images or no next images return null
		if (album.size() == 0) {
			return null;
		}
		if (index >= album.size() - 1) {
			return null;
		}
		// Checks if index is below the array size, if it does adds 1 to index
		if (index < album.size() - 1) {
			index++;
		}
		return album.get(index);
	}

	/**
	 * Validates if there are any images in album, if album.size() = 0 returns null
	 * checks if the index is less than or equal to 0, if it is returns null checks
	 * if index is less than album.size(), if it then decrements index
	 * 
	 * @return album object at index or null
	 */
	public ImageRecord getPrevious() {
		// If there are no images or no previous images return null
		if (album.size() == 0) {
			return null;
		}
		if (index <= 0) {
			return null;
		}
		// Checks if index is above 0, if it does subtracts 1 from index
		if (index > 0) {
			index--;
		}
		return album.get(index);
	}

	// Getter to retrieve the album size so there can be dynamic values instead of
	// static preset ones
	public int getAlbumSize() {
		return album.size();
	}

}
