package ru.vados.scrileTestWork.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExpenseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ExpenseController expenseController;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void test() throws Exception{
        ObjectNode dataForRequest = mapper.createObjectNode();
        dataForRequest.put("id", "1");
        dataForRequest.put("minSum", "1");

        MvcResult result = mockMvc.perform(post("/getExpensesOnThisCost").contentType(APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(dataForRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{")))
                .andExpect(content().string(containsString("[")))
                .andReturn();

        String responseData = result.getResponse().getContentAsString();

        assertThat(responseData, containsString("спорт"));
        assertThat(responseData, containsString("питание"));
        assertThat(responseData, containsString("развлечения"));

    }

 }
