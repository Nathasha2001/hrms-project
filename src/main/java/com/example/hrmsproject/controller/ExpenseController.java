package com.example.hrmsproject.controller;

import com.example.hrmsproject.entity.Expense;
import com.example.hrmsproject.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense submitExpense(@RequestBody Expense expense) {
        return expenseService.submitExpense(expense);
    }

    @PutMapping("/approve/{id}")
    public Expense approveExpense(@PathVariable Long id) {
        return expenseService.approveExpense(id);
    }

    @PutMapping("/reject/{id}")
    public Expense rejectExpense(@PathVariable Long id) {
        return expenseService.rejectExpense(id);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Expense> getExpensesByEmployeeId(@PathVariable Long employeeId) {
        return expenseService.getExpensesByEmployeeId(employeeId);
    }
}