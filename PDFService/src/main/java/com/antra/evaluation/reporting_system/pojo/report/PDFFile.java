package com.antra.evaluation.reporting_system.pojo.report;



import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;

@Primary
@DynamoDBTable(tableName = "PDFFile")
public class PDFFile {
    private String id;
    private String fileName;
    private String fileLocation;
    private String submitter;
    private Long fileSize;
    private String description;
    private LocalDateTime generatedTime;



    public PDFFile(){

    }
    public PDFFile(String id, String fileName, String fileLocation, String submitter, Long fileSize, String description, LocalDateTime generatedTime) {
        this.id = id;
        this.fileName = fileName;
        this.fileLocation = fileLocation;
        this.submitter = submitter;
        this.fileSize = fileSize;
        this.description = description;
        this.generatedTime = generatedTime;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }

    @Override
    public String toString() {
        return "PDFFile{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileLocation='" + fileLocation + '\'' +
                ", submitter='" + submitter + '\'' +
                ", fileSize=" + fileSize +
                ", description='" + description + '\'' +
                ", generatedTime=" + generatedTime +
                '}';
    }
}
