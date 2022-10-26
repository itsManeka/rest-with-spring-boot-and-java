package br.com.itsmaneka.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itsmaneka.controllers.PersonController;
import br.com.itsmaneka.data.vo.v1.PersonVO;
import br.com.itsmaneka.exceptions.RequiredObjectIsNullException;
import br.com.itsmaneka.exceptions.ResourceNotFoundException;
import br.com.itsmaneka.mapper.DozerMapper;
import br.com.itsmaneka.model.Person;
import br.com.itsmaneka.repositories.PersonRepository;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll() {
        logger.info("Finding all!");
        
        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons
            .stream()
            .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        return persons;
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one PersonVO!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem dados para esse ID"));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one PersonVO!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one PersonVO!");
        
        var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("Sem dados para esse ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("deleting one PersonVO!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem dados para esse ID"));
        repository.delete(entity);
    }
}
