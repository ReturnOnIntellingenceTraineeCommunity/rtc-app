package net.github.rtc.app.service.export.table;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;

import java.io.IOException;

@RunWith(BlockJUnit4ClassRunner.class)
public class CSVTableTest {

    @InjectMocks
    private CSVTable csvTable = new CSVTable();

    @Test
    public void testCreateAndWriteCSVTableToFile() throws IOException {
        for (int i = 0; i < 5; i++) {
            csvTable.createRow(i);
            for (int j = 0; j < 5; j++) {
                csvTable.createCell(i, j, "");
            }
        }
        csvTable.writeToFile("CSVTest");
    }
}
