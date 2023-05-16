/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.entities.Acte;
import bf.mfptps.appgestionsconges.service.dto.ActeDTO;
import java.io.File;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author HEBIE
 */
public interface ActeService {

    ActeDTO create(ActeDTO acteDTO);

    ActeDTO update(ActeDTO acteDTO);

    Optional<Acte> findOne(Long id);

    Page<Acte> findAll(Pageable pageable);

    void delete(Long id);

    File generateActe(String referenceActe);

    Acte validerActeCA(Long id);

}
