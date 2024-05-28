package backend_esame4.Dispositivi_Aziendali.repository;

import backend_esame4.Dispositivi_Aziendali.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository <Dipendente, Integer>{
    public Optional<Dipendente> findByEmail(String email);

}
