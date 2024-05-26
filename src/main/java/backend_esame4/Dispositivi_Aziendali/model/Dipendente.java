package backend_esame4.Dispositivi_Aziendali.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.List;


@Data
@Entity
public class Dipendente {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private int username;
    private String nome;
    private String cognome;
    private String email;
    private String foto;


    @OneToMany(mappedBy = "dipendente", cascade = CascadeType.ALL)
    private List<Dispositivo> dispositivi;
}
