package ricoh.core.fileutils;

import java.io.File;

public class FileUtilities {

	public static String fileNameToSearch, filePath;

	/**
	 * @purpose : To search given file in given path
	 * author-date :
	 *reviewer-date:
	 */
	public static String searchFileInDirectory(File directory, String fileToSearch) {
		fileNameToSearch = fileToSearch;
		if (directory.isDirectory()) {
			filePath = searchFile(directory);
		} else {
			System.out.println(directory.getAbsoluteFile() + " is not a directory!");
		}
		return filePath;

	}
	
	private static String searchFile(File file) {
		if (file.isDirectory()) {
			// do you have permission to read this directory?
			if (file.canRead()) {
				for (File temp : file.listFiles()) {
					if (temp.isDirectory()) {
						searchFile(temp);
					} else {
						if ((temp.getAbsolutePath().endsWith(fileNameToSearch))) {
							filePath = temp.getAbsolutePath();
							break;
						}
					}
				} // For loop

			} else {
				System.out.println(file.getAbsoluteFile() + "Permission Denied");
			}
		} // main if
		return filePath;
	}
}
