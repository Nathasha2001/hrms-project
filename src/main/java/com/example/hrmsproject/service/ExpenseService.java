package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense submitExpense(Expense expense);
    Expense approveExpense(Long id);
    Expense rejectExpense(Long id);
    List<Expense> getAllExpenses();
    List<Expense> getExpensesByEmployeeId(Long employeeId);
    Expense getExpenseById(Long id);
}