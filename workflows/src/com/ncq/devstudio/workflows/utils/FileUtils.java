package com.ncq.devstudio.workflows.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Aroua Souabni
 */
public final class FileUtils {

    private FileUtils() {
        // Empty constructor
    }

    /**
     * Generate the full file name from the name and extension.
     *
     * @param name the file name
     * @param extension the file extension
     * @return the full file name
     */
    public static String generateFileName(String name, String extension) {
        return new StringBuilder(name).append(".").
                append(extension).toString();
    }

    /**
     * Generate absolute file path.
     *
     * @param repoPath the location of the repository of files
     * @param id the category unique identifier
     * @param name the file name
     * @param extension the file extension
     * @return the absolute file name
     */
    public static String generateFullFileName(
            String repoPath, String id, String name, String extension) {
        return new StringBuilder(repoPath).append(id).
                append(generateFileName(name, extension)).toString();
    }

    /**
     * A new file tree will be used to avoid manipulating all the files in a
     * unique directory. The following tree is used:<br>
     * * The root directory configured in application.properties ** The year
     * *** The week of the year **** All the files uploaded in a same week.
     *
     * @param date the category creation date
     * @param path the repository path configured in application.properties
     * @return the constructed file path
     */
    public static String constructFileTree(Date date, String path) {
        StringBuilder sb = new StringBuilder(path);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        sb.append(cal.get(Calendar.YEAR));
        sb.append(File.separator);
        sb.append(cal.get(Calendar.WEEK_OF_YEAR));
        sb.append(File.separator);
        return sb.toString();
    }

    public static String saveFile(String fileBase64, Date creationDate,
            String repoPath, String id, String name, String extension) throws IOException {
        String path = constructFileTree(creationDate, repoPath);
        String fullName = FileUtils.generateFileName(name, extension);
        File f = new File(path + id + fullName);
        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(Base64.getDecoder().decode(fileBase64));
        }

        return path + id + fullName;
    }
}
