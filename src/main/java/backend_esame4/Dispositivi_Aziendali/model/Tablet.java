package backend_esame4.Dispositivi_Aziendali.model;


import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Tablet extends Dispositivo {
        public Tablet() {
            this.setTipo("Tablet");
        }
}
