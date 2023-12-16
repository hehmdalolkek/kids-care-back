package com.hehmdalolkek.spring.kidscareback.dao;

import com.hehmdalolkek.spring.kidscareback.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<Vaccination, Integer> {

    public void deleteAllByChildId(int id);
}
