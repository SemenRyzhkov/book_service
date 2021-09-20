package com.github.template.controller;

import com.github.template.model.db.Dictionary;
import com.github.template.service.DictionaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dictionaries")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dictionary>> getAllDictionaries(){
        return ResponseEntity.ok(dictionaryService.getAll());
    }

}
