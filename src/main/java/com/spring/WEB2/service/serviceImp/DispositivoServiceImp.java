package com.spring.WEB2.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.WEB2.model.Dispositivo;
import com.spring.WEB2.repository.DispositivoRepository;
import com.spring.WEB2.service.DispositivoService;

@Service
public class DispositivoServiceImp implements DispositivoService{
	
	@Autowired
	DispositivoRepository dispositivoRepository;
	
	@Override
	public List<Dispositivo> findAll() {
		return dispositivoRepository.findAll();
	}

	@Override
	public Dispositivo findById(long id) {
		// TODO Auto-generated method stub
		return dispositivoRepository.findById(id).get();
	}

	@Override
	public Dispositivo save(Dispositivo dispositivo) {
		return dispositivoRepository.save(dispositivo);
	}

}
