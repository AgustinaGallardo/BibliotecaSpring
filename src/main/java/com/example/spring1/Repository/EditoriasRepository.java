package com.example.spring1.Repository;

import com.example.spring1.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoriasRepository extends JpaRepository<Editorial,String> {

}
