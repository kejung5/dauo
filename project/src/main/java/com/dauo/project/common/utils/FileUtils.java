package com.dauo.project.common.utils;

import com.dauo.project.common.code.ErrorCode;
import com.dauo.project.common.exception.ApiException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class FileUtils {
    private static final char EXTENSION_SEPARATOR = '.';
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';

    public static Path getPath(String path) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            return Paths.get(resource.getURI());
        } catch (IOException e) {
            throw new ApiException(ErrorCode.FILE_PATH_FAILED);
        }
    }

    public static String getExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        int index = indexOfExtension(fileName);
        if (index == -1) {
            return "";
        } else {
            return fileName.substring(index + 1);
        }
    }

    public static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        }
        int extensionPos = filename.lastIndexOf(EXTENSION_SEPARATOR);
        int lastSeparator = indexOfLastSeparator(filename);
        return (lastSeparator > extensionPos ? -1 : extensionPos);
    }

    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
        int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    public static <T> List<T> readAllDataAtOnce(Path path, Class<T> cls, boolean isHeader, char separator, Charset charset) {
        try (InputStream inputStream = Files.newInputStream(path)) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset.name());

            CsvToBeanBuilder<T> builder = new CsvToBeanBuilder<T>(inputStreamReader)
                    .withType(cls)
                    .withSkipLines(isHeader ? 1 : 0)
                    .withSeparator(separator)
                    .withIgnoreLeadingWhiteSpace(true);

            CsvToBean<T> csvToBean = builder.build();
            return csvToBean.parse();
        } catch (IOException e) {
            throw new ApiException(ErrorCode.FILE_IMPORT_FAILED);
        }
    }

    public static List<String> readAllDataAtOnce(Path path, boolean isHeader, Charset charset) {
        try {
            List<String> lines = Files.readAllLines(path, charset);
            if (isHeader) lines.remove(0);
            return lines;
        } catch (IOException e) {
            throw new ApiException(ErrorCode.FILE_IMPORT_FAILED);
        }
    }
}