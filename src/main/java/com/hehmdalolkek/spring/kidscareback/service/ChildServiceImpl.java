package com.hehmdalolkek.spring.kidscareback.service;

import com.hehmdalolkek.spring.kidscareback.dao.AllergyRepository;
import com.hehmdalolkek.spring.kidscareback.dao.ChildRepository;
import com.hehmdalolkek.spring.kidscareback.dao.GroupRepository;
import com.hehmdalolkek.spring.kidscareback.dao.VaccinationRepository;
import com.hehmdalolkek.spring.kidscareback.entity.Allergy;
import com.hehmdalolkek.spring.kidscareback.entity.Child;
import com.hehmdalolkek.spring.kidscareback.entity.Group;
import com.hehmdalolkek.spring.kidscareback.entity.Vaccination;
import com.hehmdalolkek.spring.kidscareback.exceptions.ChildNotFoundException;
import com.hehmdalolkek.spring.kidscareback.exceptions.GroupNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;
    private final GroupRepository groupRepository;
    private final AllergyRepository allergyRepository;
    private final VaccinationRepository vaccinationRepository;

    @Autowired
    public ChildServiceImpl(ChildRepository childRepository, GroupRepository groupRepository,
                        AllergyRepository allergyRepository, VaccinationRepository vaccinationRepository) {
        this.childRepository = childRepository;
        this.groupRepository = groupRepository;
        this.allergyRepository = allergyRepository;
        this.vaccinationRepository = vaccinationRepository;
    }

    @Override
    @Transactional
    public List<Child> getAllChildrenByGroupId(int id) {
        return childRepository.findAllByGroupId(id);
    }

    @Override
    @Transactional
    public Child getChildById(int id) {
        return childRepository.findById(id)
                .orElseThrow(() -> new ChildNotFoundException(id));
    }

    @Override
    @Transactional
    public void addChild(Child child) {
        groupRepository.findById(child.getGroup().getId())
                .orElseThrow(() -> new GroupNotFoundException(child.getGroup().getId()));

        Child savedChild = childRepository.save(child);

        for (Allergy allergy : savedChild.getAllergies()) {
            allergy.setChild(savedChild);
            allergyRepository.save(allergy);
        }

        for (Vaccination vaccination : savedChild.getVaccinations()) {
            vaccination.setChild(savedChild);
            vaccinationRepository.save(vaccination);
        }
    }

    @Override
    @Transactional
    public void editChildById(int id, Child newChild) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new ChildNotFoundException(id));

        if (newChild.getGroup() != null && !Objects.equals(newChild.getGroup(), child.getGroup())) {
            Group group = groupRepository.findById(newChild.getGroup().getId())
                    .orElseThrow(() -> new GroupNotFoundException(newChild.getGroup().getId()));
            child.setGroup(group);
        }
        if (newChild.getSurname() != null && !Objects.equals(newChild.getSurname(), child.getSurname())) {
            child.setSurname(newChild.getSurname());
        }
        if (newChild.getName() != null && !Objects.equals(newChild.getName(), child.getName())) {
            child.setName(newChild.getName());
        }
        if (newChild.getPatronymic() != null && !Objects.equals(newChild.getPatronymic(), child.getPatronymic())) {
            child.setPatronymic(newChild.getPatronymic());
        }
        if (newChild.getDateOfBirthday() != null && !Objects.equals(newChild.getDateOfBirthday(), child.getDateOfBirthday())) {
            child.setDateOfBirthday(newChild.getDateOfBirthday());
        }
        if (!Objects.equals(newChild.getMainPhone(), child.getMainPhone())) {
            child.setMainPhone(newChild.getMainPhone());
        }
        if (!Objects.equals(newChild.getAdditionalPhone(), child.getAdditionalPhone())) {
            child.setAdditionalPhone(newChild.getAdditionalPhone());
        }
        if (!Objects.equals(newChild.getAllergies(), child.getAllergies())) {
            allergyRepository.deleteAllByChildId(id);
            List<Allergy> allergies = new ArrayList<>();
            for (Allergy allergy : newChild.getAllergies()) {
                allergy.setChild(child);
                allergies.add(allergyRepository.save(allergy));
            }
            child.setAllergies(allergies);
        }
        if (!Objects.equals(newChild.getVaccinations(), child.getVaccinations())) {
            vaccinationRepository.deleteAllByChildId(id);
            List<Vaccination> vaccinations = new ArrayList<>();
            for (Vaccination vaccination : newChild.getVaccinations()) {
                vaccination.setChild(child);
                vaccinations.add(vaccinationRepository.save(vaccination));
            }
            child.setVaccinations(vaccinations);
        }

        childRepository.save(child);
    }

    @Override
    @Transactional
    public void deleteChildById(int id) {
        childRepository.findById(id)
                .orElseThrow(() -> new ChildNotFoundException(id));
        childRepository.deleteById(id);
    }
}
