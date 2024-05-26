package backend_esame4.Dispositivi_Aziendali.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Smartphone extends Dispositivo {
    public Smartphone() {
        this.setTipo("Smartphone");
    }
}
