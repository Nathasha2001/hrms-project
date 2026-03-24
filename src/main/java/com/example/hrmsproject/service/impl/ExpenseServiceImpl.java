package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.Expense;
import com.example.hrmsproject.repository.ExpenseRepository;
import com.example.hrmsproject.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Expense submitExpense(Expense expense) {
        expense.setStatus("pending");
        return expenseRepository.save(expense);
    }

    @Override
    public Expense approveExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!"pending".equalsIgnoreCase(expense.getStatus())) {
            throw new RuntimeException("Only pending expenses can be approved");
        }

        expense.setStatus("accepted");
        return expenseRepository.save(expense);
    }

    @Override
    public Expense rejectExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!"pending".equalsIgnoreCase(expense.getStatus())) {
            throw new RuntimeException("Only pending expenses can be rejected");
        }

        expense.setStatus("denied");
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> getExpensesByEmployeeId(Long employeeId) {
        return expenseRepository.findByEmployeeId(employeeId);
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }
}