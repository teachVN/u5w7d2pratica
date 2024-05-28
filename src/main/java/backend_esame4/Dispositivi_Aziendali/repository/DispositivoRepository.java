package backend_esame4.Dispositivi_Aziendali.repository;

import backend_esame4.Dispositivi_Aziendali.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {

}
