package part01;

import java.time.LocalDate;

public class ImageRecord {
	// Initialise necessary variables
	private int imageId;
	private String imageName;
	private String imageDescription;
	private ImageType imageGenre;
	private LocalDate imageDate;
	private String thumbnail;
	private static int id = 1;

	/**
	 * Set up the constructor for data to be set increment the static id
	 * 
	 * @param name
	 * @param description
	 * @param genre
	 * @param date
	 */
	public ImageRecord(String name, String description, ImageType genre, String date, String thumbnail) {
		this.imageId = id;
		this.imageName = setImageName(name);
		this.imageDescription = setDescriptionName(description);
		this.imageGenre = setImageGenre(genre);
		this.imageDate = setImageDate(date);
		this.thumbnail = setImageThumbNail(thumbnail);

		id++;
	}

	// Set getters to retrieve the different values
	/**
	 * returns imageId to the constructor
	 * 
	 * @return imageId
	 */
	public int getImageID() {
		return imageId;
	}

	/**
	 * returns imageName to the constructor
	 * 
	 * @return imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * returns imageDescription to the constructor
	 * 
	 * @return imageDesscription
	 */
	public String getImageDescription() {
		return imageDescription;
	}

	/**
	 * returns imageGenre to the constructor
	 * 
	 * @return imageGenre
	 */
	public ImageType getImageGenre() {
		return imageGenre;
	}

	/**
	 * returns imageDate to the constructor
	 * 
	 * @return imageDate
	 */
	public LocalDate getImageDate() {
		return imageDate;
	}

	/**
	 * returns thumbnail to the constructor
	 * 
	 * @return thumbnail
	 */
	public String getThumbnailName() {
		return thumbnail;
	}

	/**
	 * Setters where necessary to set the each value of the constructor use
	 * validation to make sure the inputed data is valid data make methods private
	 * as they are only accessed within the class
	 */

	/**
	 * Validates to check if if the inputed name is not null or blank, returns
	 * trimmed name if name is null or blank returns null
	 * 
	 * @param name
	 * @param trimmedName
	 * @return trimmedName or null
	 */
	private String setImageName(String name) {
		String trimmedName = name.trim();
		if (name != null && !(name.isBlank())) {
			return trimmedName;
		} else {
			System.out.println("Bad name inputed");
		}

		return null;
	}

	/**
	 * Validates to check if if the inputed description is null or blank, returns
	 * null if name is not null or blank returns trimmed description
	 * 
	 * @param description
	 * @return description.trim() or null
	 */
	private String setDescriptionName(String description) {
		if (description == null || description.isBlank()) {
			return "Description not specified";
		}
		return description.trim();
	}

	/**
	 * Validates to check if if the inputed genre is not null, returns genre if
	 * genre is null returns ImageType.OTHER
	 * 
	 * @param genre
	 * @return genre or ImageType.OTHER
	 */
	private ImageType setImageGenre(ImageType genre) {
		if (genre != null) {
			return genre;
		}
		return ImageType.OTHER;
	}

	/**
	 * Validates to check if if the inputed date is not null or blank, returns
	 * trimmed date in LocalDate format if date is null or blank returns null
	 * 
	 * @param date
	 * @param trimmedDate
	 * @return LocalDate.parse(trimmedDate) or null
	 */
	private LocalDate setImageDate(String date) {
		String trimmedDate = date.trim();
		if (date != null && !(date.isBlank())) {
			try {
				return LocalDate.parse(trimmedDate);
			} catch (Exception e) {
				System.out.println("Invalid date inputted");
				e.printStackTrace();
			}
		} else {
			System.out.print("Bad date inputed");
			return null;
		}
		return null;
	}

	/**
	 * Validates to check if if the inputed thumbnail is not null or blank, checks
	 * to see if the string contains a ".", if it does returns trimmedThumbnail else
	 * it returns null else if name is null or blank returns null
	 * 
	 * @param thumbnail
	 * @param trimmedThumbnail
	 * @return trimmedThumbnail or null
	 */
	private String setImageThumbNail(String thumbnail) {
		String trimmedThumbnail = thumbnail.trim();
		if (thumbnail != null && !(thumbnail.isBlank())) {
			if (trimmedThumbnail.contains(".")) {
				return trimmedThumbnail;
			} else {
				System.out.println("Bad thumbnail syntax. Has to contain a period \".\" e.g. thumbnail.png ");
				return null;
			}
		} else {
			System.out.println("Bad thumbnail inputed");
			return null;
		}

	}

	/**
	 * toString function which returns the image information in a professional
	 * format without line breaks
	 * 
	 * @param str
	 * @return str
	 */
	public String toString() {
		String str = "Image ID: " + imageId + " Image Name: " + imageName + " Image Description: " + imageDescription
				+ " Image Genre: " + imageGenre + " Image Date: " + imageDate + " Image Thumbnail: " + thumbnail;

		return str;

	}

}
