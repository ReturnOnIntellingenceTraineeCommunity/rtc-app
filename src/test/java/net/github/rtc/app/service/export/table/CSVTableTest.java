package net.github.rtc.app.service.export.table;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class CSVTableTest {

    @InjectMocks
    private CSVTable csvTable = new CSVTable();

    private static final String CSV_TEST_FILE_PATH = "src/test/resources/files/CSVTest";

    @Test
    public void testCreateAndWriteCSVTableToFile() throws IOException {
        for (int i = 0; i < 3; i++) {
            csvTable.createRow(i);
            for (int j = 0; j < 3; j++) {
                csvTable.createCell(i, j, i + "" + j);
            }
        }
        csvTable.writeToFile(CSV_TEST_FILE_PATH);
        String expectedValueOfCreatedFile = "00,01,02,10,11,12,20,21,22,";
        assertEquals(expectedValueOfCreatedFile, readCreatedFile(CSV_TEST_FILE_PATH));
    }

    private String readCreatedFile(String fileName) {
        final StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                builder.append(currentLine);
            }
        } catch (IOException e) {
            builder.append(e);
        }
        return builder.toString();
    }
}
