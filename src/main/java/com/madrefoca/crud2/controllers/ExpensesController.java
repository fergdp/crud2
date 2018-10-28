/*
 * Copyright (c) 2018, Southwest Airlines Co. All rights reserved.
 */
package com.madrefoca.crud2.controllers;

import com.madrefoca.crud2.entities.Expense;
import com.madrefoca.crud2.exceptions.ResourceNotFoundException;
import com.madrefoca.crud2.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path="/expenses") // This means URL's start with /expenses (after Application path)
public class ExpensesController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ExpenseRepository expenseRepository;

    @PostMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewExpense (@Valid @RequestBody Expense expense) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        expenseRepository.save(expense);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Expense> getAllExpenses() {
        // This returns a JSON or XML with the users
        return expenseRepository.findAll();
    }

    // Get a Single expense
    //todo: this is not working
    @GetMapping("get/{id}")
    public Expense getExpenseById(@PathVariable("id") Long expenseId) {
        return expenseRepository.findById(expenseId).get();
    }

    @GetMapping("expense/{id}")
    public ResponseEntity<Expense> getExpenseById2(@PathVariable("id") Long id) {
        Expense expense = expenseRepository.findById(id).get();
        return new ResponseEntity<Expense>(expense, HttpStatus.OK);
    }

    // Update a Expense
    @PutMapping("/expense/{id}")
    public Expense updateExpense(@PathVariable(value = "id") Long expenseId,
                           @Valid @RequestBody Expense expenseDescription) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", expenseId));

        expense.setDescription(expenseDescription.getDescription());

        Expense updatedExpense = expenseRepository.save(expense);
        return updatedExpense;
    }

    // Delete a Expense
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable(value = "id") Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", expenseId));

        expenseRepository.delete(expense);

        return ResponseEntity.ok().build();
    }




    @GetMapping(path="/test")
    public @ResponseBody String testing() {
        // This returns a JSON or XML with the users
        return "Hi Fer! test successful";
    }
}
