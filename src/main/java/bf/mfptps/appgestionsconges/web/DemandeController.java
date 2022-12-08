package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.service.DemandeService;
import bf.mfptps.appgestionsconges.service.ModalitePaieService;
import bf.mfptps.appgestionsconges.service.dto.DemandeDTO;
import bf.mfptps.appgestionsconges.service.dto.PayInitData;
import bf.mfptps.appgestionsconges.service.dto.PayReturnData;
import bf.mfptps.appgestionsconges.utils.HeaderUtil;
import bf.mfptps.appgestionsconges.utils.PaginationUtil;
import bf.mfptps.appgestionsconges.utils.ResponseUtil;
import bf.mfptps.appgestionsconges.web.exceptions.BadRequestAlertException;
import bf.mfptps.appgestionsconges.web.exceptions.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@RestController
@RequestMapping(path = "/api")
public class DemandeController {

    private final Logger log = LoggerFactory.getLogger(DemandeController.class);

    private static final String ENTITY_NAME = "demande";

    @Value("${application.name}")
    private String applicationName;

    private final DemandeService demandeService;

    private final ModalitePaieService modalitePaieService;

    public DemandeController(DemandeService demandeService,
            ModalitePaieService modalitePaieService) {
        this.demandeService = demandeService;
        this.modalitePaieService = modalitePaieService;
    }

    /**
     * Access granted to ADMIN ...
     *
     * @param demande
     * @return
     * @throws URISyntaxException
     */
    @PostMapping(path = "/demandes")
    //  @PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<DemandeDTO> create(@Valid @RequestParam(value = "demande") String demandeJson, @RequestParam(value = "files", required = false) MultipartFile[] fichiersJoint) throws URISyntaxException {
        log.debug("Création de la Demande : {}", demandeJson);
        ObjectMapper mapper = new ObjectMapper();
        DemandeDTO demande;
        try {
            demande = mapper.readValue(demandeJson, DemandeDTO.class);
        } catch (Exception e) {
            throw new CustomException("Echec lors du formatage des donnees du formulaire");
        }
        DemandeDTO dem = demandeService.create(demande, fichiersJoint);
        return ResponseEntity.created(new URI("/api/demandes/" + dem.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, dem.getId().toString()))
                .body(dem);
    }

    /**
     * Access granted to ADMIN ...
     *
     * @param demande
     * @return
     * @throws URISyntaxException
     */
    @PutMapping(path = "/demandes")
    // @PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<DemandeDTO> updateDemande(@Valid @RequestBody DemandeDTO demande) throws URISyntaxException {
        log.debug("Mis à jour de la Demande : {}", demande);
        if (demande.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        DemandeDTO result = demandeService.update(demande);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, demande.getId().toString()))
                .body(result);
    }

    @GetMapping(path = "/demandes/{code}")
    public ResponseEntity<Demande> getDemande(@PathVariable(name = "code") String code) {
        log.debug("Consultation du Demande : {}", code);
        Optional<Demande> demande = demandeService.get(code);
        return ResponseUtil.wrapOrNotFound(demande);
    }

    @GetMapping(path = "/demandes")
    public ResponseEntity<List<DemandeDTO>> findAllDemandes(Pageable pageable) {
        Page<DemandeDTO> minsiteres = demandeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), minsiteres);
        return ResponseEntity.ok().headers(headers).body(minsiteres.getContent());
    }

    @DeleteMapping(path = "/demandes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Suppression de la Demande : {}", id);
        demandeService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }

    @PostMapping(path = "/demandes/initPay")
    public ResponseEntity<PayReturnData> initPay(@RequestBody PayInitData data) {
        PayReturnData value = modalitePaieService.initPay(data);
        return ResponseEntity.ok(value);
    }

    @GetMapping(path = "/demandes/{matricule}/{structure}")
    public ResponseEntity<List<DemandeDTO>> getAllByMatriculeStructure(@PathVariable String matricule, @PathVariable String structure) {
        return null;
    }

}
