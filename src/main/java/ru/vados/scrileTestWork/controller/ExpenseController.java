package ru.vados.scrileTestWork.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vados.scrileTestWork.model.InfoGetExpensesRequest;
import ru.vados.scrileTestWork.repository.ExpensProjection;
import ru.vados.scrileTestWork.repository.ExpenseRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExpenseController {

    ExpenseRepository expenseRepository;
    ObjectMapper objectMapper;

    @Autowired
    public ExpenseController(ExpenseRepository expenseRepository, ObjectMapper objectMapper) {
        this.expenseRepository = expenseRepository;
        this.objectMapper = objectMapper;
    }

    @ResponseBody
    @PostMapping("getExpensesOnThisCost")
    private String getExpensesOnThisCost(@RequestBody InfoGetExpensesRequest getExpensesRequest) throws JsonProcessingException {

        List<ExpensProjection> expenses = expenseRepository.getAllExpenseMoreMinSum(getExpensesRequest.getId(),getExpensesRequest.getMinSum());

        Map<String,List<Map<String,String>>>expenseByCategory = new LinkedHashMap<>();

        for (ExpensProjection expense:expenses) {

            if(expenseByCategory.containsKey(expense.getCategory())) {
                Map<String, String> mapWithinfo = new LinkedHashMap<>();
                mapWithinfo.put("userId", expense.getUserid().toString());
                mapWithinfo.put("amount", expense.getAmount().toString());

                List<Map<String,String>> listWithAllinfo = new ArrayList<>();
                listWithAllinfo.add(mapWithinfo);
                listWithAllinfo.addAll(expenseByCategory.get(expense.getCategory()));

                expenseByCategory.put(expense.getCategory(),listWithAllinfo);
            } else {
                Map<String, String> mapWithinfo = new LinkedHashMap<>();
                mapWithinfo.put("userId", expense.getUserid().toString());
                mapWithinfo.put("amount", expense.getAmount().toString());

                List<Map<String,String>> listWithAllinfo = new ArrayList<>();
                listWithAllinfo.add(mapWithinfo);

                expenseByCategory.put(expense.getCategory(), listWithAllinfo);
            }
        };
        return objectMapper.writeValueAsString(expenseByCategory);

    }

}
