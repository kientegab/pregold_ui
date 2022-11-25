package bf.mfptps.appgestionsconges.service.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

    public class TypeDemandeDTO  {

        private Long id;
        private String libelle;
        private Boolean modePaie;
        private String description;
        private Set<TypeVisaDTO> typeVisas = new HashSet<TypeVisaDTO>();
        private String soldeAnnuel;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLibelle() {
            return libelle;
        }

        public void setLibelle(String libelle) {
            this.libelle = libelle;
        }

        public Boolean getModePaie() {
            return modePaie;
        }

        public void setModePaie(Boolean modePaie) {
            this.modePaie = modePaie;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    public Set<TypeVisaDTO> getTypeVisas() {
        return typeVisas;
    }

    public void setTypeVisas(Set<TypeVisaDTO> typeVisas) {
        this.typeVisas = typeVisas;
    }

    public void addTypeVisa(TypeVisaDTO typeVisa) {
        this.typeVisas.add(typeVisa);
    }
    
    

    public String getSoldeAnnuel() {
		return soldeAnnuel;
	}

	public void setSoldeAnnuel(String soldeAnnuel) {
		this.soldeAnnuel = soldeAnnuel;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDemandeDTO that = (TypeDemandeDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TypeDemande{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", modePaie=" + modePaie +
                ", description='" + description + '\'' +
                ", typeVisas=" + typeVisas +
                ", soldeAnnuel=" + soldeAnnuel +
                '}';
    }
}
