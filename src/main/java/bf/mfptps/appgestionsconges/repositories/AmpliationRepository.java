/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.Ampliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author HEBIE
 */
public interface AmpliationRepository extends JpaRepository<Ampliation, Long>, JpaSpecificationExecutor<Ampliation> {

}
