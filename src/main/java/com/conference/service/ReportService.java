package com.conference.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportService {

    public void generatePdfReport(List<RoomUsage> roomUsages, String filePath) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileWriter(filePath));
        document.open();
        document.addTitle("Room Usage Report");
        for (RoomUsage usage : roomUsages) {
            document.add(new Paragraph(usage.toString()));
        }
        document.close();
    }

    public void generateCsvReport(List<RoomUsage> roomUsages, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            String[] header = {"Room Name", "Usage Duration", "Date"};
            writer.writeNext(header);
            for (RoomUsage usage : roomUsages) {
                writer.writeNext(new String[]{usage.getRoomName(), String.valueOf(usage.getUsageDuration()), usage.getDate().toString()});
            }
        }
    }
}

class RoomUsage {
    private String roomName;
    private long usageDuration; // in minutes
    private Date date;

    // Getters and toString() method omitted for brevity
}