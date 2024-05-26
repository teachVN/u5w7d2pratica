package backend_esame4.Dispositivi_Aziendali.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Laptop extends Dispositivo{
    public Laptop() {
        this.setTipo("Laptop");
    }

}
