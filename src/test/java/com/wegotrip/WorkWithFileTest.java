package com.wegotrip;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkWithFileTest {
    // Скачивание, если нет атрибута href
//        static {
//            Configuration.fileDownload = FileDownloadMode.PROXY;
//        }
//
    ClassLoader cl = WorkWithFileTest.class.getClassLoader();

    @DisplayName("ZipCsv test")
    @Test
    void zipCsvTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/archive.zip"))) {

            ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("archive.zip"));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
                        List<String[]> content = reader.readAll();
                        String[] position_1 = content.get(1);
                        String[] position_2 = content.get(2);
                        String[] position_3 = content.get(3);
                        assertThat(position_1[0]).isEqualTo("frontend");
                        assertThat(position_1[1]).isEqualTo("Ilya");
                        assertThat(position_2[0]).isEqualTo("backend");
                        assertThat(position_2[1]).isEqualTo("Nikita");
                        assertThat(position_3[0]).isEqualTo("qa");
                        assertThat(position_3[1]).isEqualTo("Anvar");
                    }
                }
            }
        }
    }
    @DisplayName("ZipPDF test")
    @Test
    void zipPdfTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/archive.zip"))) {

            ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("archive.zip"));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        PDF pdf = new PDF(inputStream);
                        assertThat(pdf.text).contains("Ajax");
                        assertThat(pdf.text).contains("resolve these problems");
                    }
                }
            }
        }
    }
    @DisplayName("ZipXlsx test")
    @Test
    void zipXlsxTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/archive.zip"))) {

            ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("archive.zip"));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".xlsx")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        XLS xls = new XLS(inputStream);
                        assertThat(
                                xls.excel.getSheetAt(0)
                                        .getRow(2)
                                        .getCell(0)
                                        .getStringCellValue()
                        ).isEqualTo("Russia");
                        assertThat(
                                xls.excel.getSheetAt(0)
                                        .getRow(2)
                                        .getCell(1)
                                        .getStringCellValue()
                        ).isEqualTo("Moscow");
                    }
                }
            }
        }
    }

}
