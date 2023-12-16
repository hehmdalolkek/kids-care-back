package com.hehmdalolkek.spring.kidscareback.dao;

import com.hehmdalolkek.spring.kidscareback.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<Allergy, Integer> {

    public void deleteAllByChildId(int id);
}
