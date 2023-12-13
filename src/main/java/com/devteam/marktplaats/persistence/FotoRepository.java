package com.devteam.marktplaats.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devteam.marktplaats.model.Foto;

public interface FotoRepository extends JpaRepository<Foto, Long> {
}
