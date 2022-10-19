package br.com.itsmaneka.restwithspringbootandjava.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itsmaneka.restwithspringbootandjava.data.vo.v1.PersonVO;
import br.com.itsmaneka.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import br.com.itsmaneka.restwithspringbootandjava.mapper.DozerMapper;
import br.com.itsmaneka.restwithspringbootandjava.model.Person;
import br.com.itsmaneka.restwithspringbootandjava.repositories.PersonRepository;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll() {
        logger.info("Finding all!");
        
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one PersonVO!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem dados para esse ID"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating one PersonVO!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating one PersonVO!");
        
        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Sem dados para esse ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        logger.info("deleting one PersonVO!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem dados para esse ID"));
        repository.delete(entity);
    }
}
