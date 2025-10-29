package org.example;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ChartGenerator {

    public static void showCaseChart(Case caseData) {
        if (caseData.getHistory().isEmpty()) {
            System.out.println("No data to display for " + caseData.getName());
            return;
        }

        // Convert LocalDate → java.util.Date for XChart
        List<Date> dates = caseData.getHistory().stream()
                .map(entry -> Date.from(entry.getDate()
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()))
                .collect(Collectors.toList());

        List<Double> prices = caseData.getHistory().stream()
                .map(PriceEntry::getPrice)
                .collect(Collectors.toList());

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(500)
                .title(caseData.getName() + " Price History")
                .xAxisTitle("Date")
                .yAxisTitle("Price (€)")
                .build();

        chart.addSeries(caseData.getName(), dates, prices)
                .setMarker(SeriesMarkers.CIRCLE);

        // Format the x-axis to show readable dates
        chart.getStyler().setDatePattern("MM-dd");
        chart.getStyler().setLegendVisible(false);

        new SwingWrapper<>(chart).displayChart();
    }
}