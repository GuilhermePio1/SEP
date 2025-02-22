package com.paroquia_santo_afonso.sep.SEP.modules.equipista.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SheetsService {
    @Value("${google.credentials.path}")
    private String credentialsFilePath;

    @Value("${google.application.name:MyApp}")
    private String applicationName;

    private Sheets getSheetsService() throws Exception {
        try (InputStream in = new ClassPathResource(credentialsFilePath).getInputStream()) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                    .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));

            return new Sheets.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    new HttpCredentialsAdapter(credentials))
                    .setApplicationName(applicationName)
                    .build();

        }
    }

    public List<List<Object>> getRows(String spreadsheetId, String range) throws Exception {
        Sheets sheets = getSheetsService();

        List<String> rangesList = Arrays.stream(range.split(";"))
                .map(String::trim)
                .filter(r -> !r.isEmpty())
                .collect(Collectors.toList());

        BatchGetValuesResponse response = sheets.spreadsheets().values()
                .batchGet(spreadsheetId)
                .setRanges(rangesList)
                .execute();

        List<ValueRange> valueRanges = response.getValueRanges();
        if (valueRanges == null || valueRanges.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<List<Object>>> allRangesRows = valueRanges.stream()
                .map(vr -> vr.getValues() != null ? vr.getValues() : Collections.<List<Object>>emptyList())
                .toList();

        int maxRows = allRangesRows.stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);

        List<List<Object>> combinedRows = new ArrayList<>();
        for (int rowIndex = 0;rowIndex < maxRows; rowIndex++) {
            List<Object> combinedRow = new ArrayList<>();
            for (List<List<Object>> rangeRows : allRangesRows) {
                if (rowIndex < rangeRows.size()) {
                    List<Object> rowValues = rangeRows.get(rowIndex);
                    combinedRow.addAll(rowValues);
                }
            }
            combinedRows.add(combinedRow);
        }

        return combinedRows;
    }

}
