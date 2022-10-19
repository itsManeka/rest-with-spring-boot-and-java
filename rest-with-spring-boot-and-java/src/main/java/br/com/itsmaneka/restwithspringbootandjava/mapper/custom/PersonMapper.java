package br.com.itsmaneka.restwithspringbootandjava.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.itsmaneka.restwithspringbootandjava.data.vo.v2.PersonVOV2;
import br.com.itsmaneka.restwithspringbootandjava.model.Person;

@Service
public class PersonMapper {
    public PersonVOV2 convertEntityToVo(Person person) {
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthday(new Date());
        vo.setFirstName(person.getFirstName());
        vo.setGender(person.getGender());
        vo.setLastName(person.getLastName());
        return vo;
    }

    public Person convertVoToEntity(PersonVOV2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        //entity.setBirthday(new Date());
        entity.setFirstName(person.getFirstName());
        entity.setGender(person.getGender());
        entity.setLastName(person.getLastName());
        return entity;
    }
}
