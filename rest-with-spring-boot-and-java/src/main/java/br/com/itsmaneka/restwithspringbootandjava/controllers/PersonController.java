package br.com.itsmaneka.restwithspringbootandjava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itsmaneka.restwithspringbootandjava.data.vo.v1.PersonVO;
import br.com.itsmaneka.restwithspringbootandjava.data.vo.v2.PersonVOV2;
import br.com.itsmaneka.restwithspringbootandjava.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PersonVO> findAll() {
        return service.findAll();
    }
    
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
        return service.findById(id);
    }
    
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public PersonVO create(@RequestBody PersonVO PersonVO) throws Exception {
        return service.create(PersonVO);
    }
    
    @PostMapping(value = "/v2", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 PersonVOV2) throws Exception {
        return service.createV2(PersonVOV2);
    }
    
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public PersonVO update(@RequestBody PersonVO PersonVO) throws Exception {
        return service.update(PersonVO);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}