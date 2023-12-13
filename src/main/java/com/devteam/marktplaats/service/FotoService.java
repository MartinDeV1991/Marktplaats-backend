package com.devteam.marktplaats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.model.Foto;
import com.devteam.marktplaats.persistence.FotoRepository;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;
	
	
	public List<Foto> getAllFotos() {
		return fotoRepository.findAll();
	}

	public Optional<Foto> findById(long id) {
		return this.fotoRepository.findById(id); 
	}
	
	public Foto createOrUpdate(Foto foto) {
		return this.fotoRepository.save(foto);
	}

	public void deleteById(long id) {
		this.fotoRepository.deleteById(id);
		
	}
	
}
