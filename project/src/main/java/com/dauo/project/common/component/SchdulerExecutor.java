package com.dauo.project.common.component;

import com.dauo.project.domain.schduler.FileImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchdulerExecutor {
    private final FileImportService fileImportService;

    @Scheduled(cron = "0 0 0 * * *")
    public void fileImportSchduler() {
        fileImportService.importFile();
    }
}
