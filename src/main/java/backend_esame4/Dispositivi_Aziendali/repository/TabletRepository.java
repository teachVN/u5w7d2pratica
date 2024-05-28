package backend_esame4.Dispositivi_Aziendali.repository;
import backend_esame4.Dispositivi_Aziendali.model.Laptop;
import backend_esame4.Dispositivi_Aziendali.model.Tablet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabletRepository extends JpaRepository<Tablet, Integer> {
}
