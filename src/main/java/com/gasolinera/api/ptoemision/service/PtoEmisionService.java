package com.gasolinera.api.ptoemision.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasolinera.api.ptoemision.model.PtoEmision;
import com.gasolinera.api.ptoemision.repository.PtoEmisionRepository;

@Service
public class PtoEmisionService {
	
	@Autowired
	PtoEmisionRepository  ptoEmisionRepository;
	
	public void incrementarSecuencial(PtoEmision ptoEmision) {
		Integer secuencial = ptoEmision.getSecuencial() + 1;
		ptoEmision.setSecuencial(secuencial);
		ptoEmisionRepository.save(ptoEmision);
	}
	
	public PtoEmision ptoEmisionPorId(Integer ptoEmisionId) {
		return ptoEmisionRepository.findById(ptoEmisionId).orElse(null);
	}
	
	public List<PtoEmision> buscarTodos(){
		return (List<PtoEmision>) ptoEmisionRepository.findAll();
	}
	
	public PtoEmision guardar(PtoEmision pto) {
		return ptoEmisionRepository.save(pto);
	}
}
