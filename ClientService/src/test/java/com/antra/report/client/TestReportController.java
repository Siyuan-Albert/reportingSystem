package com.antra.report.client;

import com.antra.report.client.pojo.FileType;
import com.antra.report.client.pojo.reponse.GeneralResponse;
import com.antra.report.client.pojo.request.ReportRequest;
import com.antra.report.client.service.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Albert
 * @date: 3/11/21 14:31
 * @description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestReportController {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    ReportService reportService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


//    @GetMapping("/report")
//    public ResponseEntity<GeneralResponse> listReport() {
//        log.info("Got Request to list all report");
//        return ResponseEntity.ok(new GeneralResponse(reportService.getReportList()));
//    }

    @Test
    public void TestGetAllReports() throws Exception {
        RequestBuilder request = null;
        //构造请求
        request = get("/report");
        //执行请求
        mockMvc.perform(request)
                .andExpect(status().isOk())//表示页面被重定向
                .andDo(print());//打印结果
    }
//    @PostMapping("/report/sync")
//    public ResponseEntity<GeneralResponse> createReportDirectly(@RequestBody @Validated ReportRequest request) {
//        log.info("Got Request to generate report - sync: {}", request);
//        request.setDescription(String.join(" - ", "Sync", request.getDescription()));
//        return ResponseEntity.ok(new GeneralResponse(reportService.generateReportsSync(request)));
//    }
    String reportRequest = "{\n" +
            "    \"description\":\"Student Math Course Report\",\n" +
            "    \"headers\": [\"Student #\",\"Name\",\"Class\",\"Score\"],\n" +
            "    \"data\": [[\"s-008\",\"Sarah\",\"Class-A\",\"B\"]],\n" +
            "    \"submitter\": \"Mrs. York\"\n" +
            "}";
    @Test
    public void TestSyncGenerateReport() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders
                        .request(HttpMethod.POST,"/report/sync")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reportRequest)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void TestAsyncGenerateReport() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders
                        .request(HttpMethod.POST,"/report/async")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reportRequest)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }
//    @GetMapping("/report/content/{reqId}/{type}")
//    public void downloadFile(@PathVariable String reqId, @PathVariable FileType type, HttpServletResponse response) throws IOException {
    @Test
    public void TestDownloadFile()throws Exception{

//        mockMvc.perform(
//                MockMvcRequestBuilders
//                        .request(HttpMethod.GET,"/report/content/{reqId}/{type}","Req-9c9b2f68-ed84-4d0f-be0a-bbdd2a92bfbf", FileType.PDF)
//        ).andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(print())
//                .andReturn();

        InputStream fis = reportService.getFileBodyByReqId("Req-1c2df229-829a-45a0-b06a-f87a171f31d5", FileType.PDF);
        if(fis==null){
            System.out.println("No file with this reqId");
        }
    }
}
