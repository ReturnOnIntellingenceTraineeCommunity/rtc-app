package net.github.rtc.app.service.export.table;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;

import java.io.IOException;

@RunWith(BlockJUnit4ClassRunner.class)
public class XLSNXTableTest {

    @InjectMocks
    private XLSNXTable xlsxTable = new XLSNXTable(new XSSFWorkbook(), "XLSXTest");
    @InjectMocks
    private XLSNXTable xlsTable = new XLSNXTable(new HSSFWorkbook(), "XLSTest");

    @Test
    public void testCreateAndWriteXLSXTableToFile() throws IOException {
        for (int i = 0; i < 5; i++) {
            xlsxTable.createRow(i);
            for (int j = 0; j < 5; j++) {
                xlsxTable.createCell(i, j, "");
            }
        }
        xlsxTable.writeToFile("XLSXTest");
    }

    @Test
    public void testCreateAndWriteXLSTableToFile() throws IOException {
        for (int i = 0; i < 5; i++) {
            xlsTable.createRow(i);
            for (int j = 0; j < 5; j++) {
                xlsTable.createCell(i, j, "");
            }
        }
        xlsTable.writeToFile("XLSTest");
    }
}
