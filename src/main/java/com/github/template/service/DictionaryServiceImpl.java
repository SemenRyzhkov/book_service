package com.github.template.service;

import com.github.template.model.db.Dictionary;
import com.github.template.repository.DictionaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    @Override
    public List<Dictionary> getAll() {
        return dictionaryRepository.findAll();
    }
}
