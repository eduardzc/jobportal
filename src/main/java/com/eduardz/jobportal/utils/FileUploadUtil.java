package com.eduardz.jobportal.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)) {

            Files.createDirectories(uploadPath);

        }

        try(InputStream in = multipartFile.getInputStream()) {
            Path path = uploadPath.resolve(fileName);
            System.out.println("Path: " + path);
            System.out.printf("File name: %s\n", fileName);
            Files.copy(in,path, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException e){
            throw new IOException("Could not save image file: " + fileName, e);
        }

    }

}
