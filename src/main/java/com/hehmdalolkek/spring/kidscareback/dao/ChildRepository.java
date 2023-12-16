package com.hehmdalolkek.spring.kidscareback.dao;

import com.hehmdalolkek.spring.kidscareback.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Integer> {

    public List<Child> findAllByGroupId(int id);

}
