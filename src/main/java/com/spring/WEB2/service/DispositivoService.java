package com.spring.WEB2.service;

import java.util.List;

import com.spring.WEB2.model.Dispositivo;

public interface DispositivoService {
	
	List<Dispositivo> findAll();
	Dispositivo findById( long id);
	Dispositivo save(Dispositivo dispositivo);
}
