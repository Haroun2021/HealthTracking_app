
package com.healthtracker;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PDFExporter {
    private Context context;

    public PDFExporter(Context context) {
        this.context = context;
    }

    public void exportHealthReport(ArrayList<HealthGoal> healthGoals) {
        PdfDocument pdfDocument = new PdfDocument();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        int y = 20;
        page.getCanvas().drawText("Health Tracker Report", 10, y, null);

        y += 20;
        for (HealthGoal goal : healthGoals) {
            String line = goal.getGoalName() + ": " + goal.getProgress() + "/" + goal.getTarget();
            page.getCanvas().drawText(line, 10, y, null);
            y += 20;
        }

        pdfDocument.finishPage(page);

        File file = new File(Environment.getExternalStorageDirectory(), "HealthTrackerReport.pdf");
        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(context, "Report saved to " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, "Failed to save report.", Toast.LENGTH_SHORT).show();
        } finally {
            pdfDocument.close();
        }
    }
}
