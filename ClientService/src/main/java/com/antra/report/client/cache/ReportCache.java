package com.antra.report.client.cache;

import com.antra.report.client.pojo.reponse.ExcelResponse;
import com.antra.report.client.pojo.reponse.PDFResponse;
import com.antra.report.client.pojo.request.ReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * @author: Albert
 * @date: 3/7/21 19:56
 * @description:
 */

@Component
public class ReportCache{
    @Autowired
    ThreadPoolTaskExecutor es;

    private final ConcurrentHashMap<String, CompletableFuture<ExcelResponse>> excelCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CompletableFuture<PDFResponse>> pdfCache = new ConcurrentHashMap<>();

    RestTemplate rs = new RestTemplate();


    public ExcelResponse generateExcel(ReportRequest request) throws ExecutionException, InterruptedException {
        CompletableFuture<ExcelResponse> response = excelCache.get(request.toString());
        if(response == null){
            CompletableFuture<ExcelResponse> futureExcelResponse =
                    CompletableFuture.supplyAsync(()->rs.postForEntity("http://localhost:8888/excel", request, ExcelResponse.class).getBody(), es);
            excelCache.putIfAbsent(request.toString(),futureExcelResponse);
        }
        return excelCache.get(request.toString()).get();
    }
    public PDFResponse generatePdf(ReportRequest request) throws ExecutionException, InterruptedException {
        CompletableFuture<PDFResponse> response = pdfCache.get(request.toString());
        if(response == null){
            CompletableFuture<PDFResponse> futurePdfResponse =
                    CompletableFuture.supplyAsync(()->rs.postForEntity("http://localhost:9999/pdf", request, PDFResponse.class).getBody(), es);
            pdfCache.putIfAbsent(request.toString(),futurePdfResponse);
        }
        return pdfCache.get(request.toString()).get();
    }
}
