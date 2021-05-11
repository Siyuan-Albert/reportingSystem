package com.antra.report.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author: Albert
 * @date: 3/11/21 14:49
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLoginController {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    String loginVo = "{\n" +
            "    \"mobile\":123456,\n" +
            "    \"password\": \"123456\"\n" +
            "}";

    @Test
    public void TestLogin() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders
                        .request(HttpMethod.POST,"/login/do_login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginVo)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();


    }



}
