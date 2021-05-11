package com.antra.evaluation.reporting_system.repo;

import com.antra.evaluation.reporting_system.pojo.report.PDFFile;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

@Primary
@EnableScan
public interface PDFRepository extends CrudRepository<PDFFile, String> {
}
