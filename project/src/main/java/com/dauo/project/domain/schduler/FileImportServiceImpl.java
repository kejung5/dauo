package com.dauo.project.domain.schduler;

import com.dauo.project.common.code.ExtensionCode;
import com.dauo.project.common.utils.FileUtils;
import com.dauo.project.domain.channel.Channel;
import com.dauo.project.domain.channel.ChannelRepository;
import com.dauo.project.domain.channel.ImportChannelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileImportServiceImpl implements FileImportService {
    private static final String DATA_PATH = "file";
    private static final String DATA_FILE_NAME = "channel-info.csv";
    private final ChannelRepository channelRepository;

    public void importFile() {
        Path path = FileUtils.getPath(String.format("%s/%s", DATA_PATH, DATA_FILE_NAME));
        String extension = FileUtils.getExtension(String.valueOf(path.getFileName()));

        boolean isHeader = false;
        char separator = '|';
        Charset charset = StandardCharsets.UTF_8;

        if (ExtensionCode.CSV.name().equalsIgnoreCase(extension)) {
            importCsv(path, isHeader, separator, charset);
        } else if (ExtensionCode.TXT.name().equalsIgnoreCase(extension)) {
            importTxt(path, isHeader, separator, charset);
        } /*else if () {} 그 외에 확장자 로직 구현 */

    }

    public void importCsv(Path path, boolean isHeader, char separator, Charset charset) {
        List<ImportChannelDto> csvData = FileUtils.readAllDataAtOnce(path, ImportChannelDto.class, isHeader, separator, charset);

        List<Channel> channels = new ArrayList<>();
        for (ImportChannelDto channelDto : csvData) {
            if (channelDto.valid()) {
                continue;
            }
            channels.add(Channel.of(channelDto));
        }
        channelRepository.saveAll(channels);
        log.info("TODAY importCsv SUCCESS!!!");
    }

    public void importTxt(Path path, boolean isHeader, char separator, Charset charset) {
        List<String> lines = FileUtils.readAllDataAtOnce(path, isHeader, charset);

        List<Channel> channels = new ArrayList<>();
        for (String line : lines) {
            String[] lineData = line.split(String.format("\\%c", separator));
            ImportChannelDto data = new ImportChannelDto(lineData[0],
                    Integer.valueOf(lineData[1]),
                    Integer.valueOf(lineData[2]),
                    lineData[3],
                    lineData[5],
                    lineData[5]);

            if (data.valid()) {
                continue;
            }
            channels.add(Channel.of(data));
        }
        channelRepository.saveAll(channels);
        log.info("TODAY importTxt SUCCESS!!!");
    }
}
