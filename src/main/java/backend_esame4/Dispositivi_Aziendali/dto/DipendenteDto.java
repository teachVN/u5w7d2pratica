package backend_esame4.Dispositivi_Aziendali.dto;

import backend_esame4.Dispositivi_Aziendali.DispositiviAziendaliApplication;
import backend_esame4.Dispositivi_Aziendali.model.Dispositivo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import org.springframework.context.annotation.Bean;


import java.util.List;


@Data
public class DipendenteDto {

    private int username;
    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il cognome non può essere vuoto")
    private String cognome;

    @Email(message = "Deve essere un indirizzo email valido")
    @NotBlank(message = "L'email non può essere vuota")
    private String email;

    private String foto;


    private List<Dispositivo> dispositivi;


}

