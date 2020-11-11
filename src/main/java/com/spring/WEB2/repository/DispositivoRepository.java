package com.spring.WEB2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.WEB2.model.Dispositivo;

@Repository	
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long>{

}
