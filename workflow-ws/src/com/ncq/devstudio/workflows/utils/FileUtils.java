package com.ncq.devstudio.workflows.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 * This a helper class to manipulate files.
 *
 * @author Aroua Souabni
 */
public final class FileUtils {

    /**
     * Empty constructor
     */
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
     * SaConvert file base64 encoded to a file and save it physically.
     * @param fileBase64 file base 64 encoded
     * @param repoPath the path of the folder
     * @param uuid the uuid of the category
     * @param extension file extension
     * @return file path
     * @throws IOException 
     */
    public static String saveFile(String fileBase64,
            String repoPath, String uuid, String extension) throws IOException {
        String fullName = FileUtils.generateFileName(uuid, extension);
        File f = new File(repoPath + fullName);

        File newFile = new File(repoPath + fullName);
        OutputStream os = new FileOutputStream(newFile);
        os.write(Base64.getDecoder().decode(fileBase64));
        os.close();
        return repoPath + fullName;
    }

    /**
     * Convert file to base64.
     * @param path file path
     * @return file base 64 encoded
     * @throws IOException 
     */
    public static String fromFileToBase64(String path) throws IOException {

        return Base64.getEncoder().encodeToString(Files.readAllBytes(
                Paths.get(path)));

    }

    /**
     * Extract file extension from it name or absolute path.
     * @param filePath file path
     * @return  file extension
     */
    public static String extractFileExtension(String filePath) {
        String extension = "";

        int i = filePath.lastIndexOf('.');
        if (i > 0) {
            extension = filePath.substring(i + 1);
        }

        return extension;
    }

}
