package backend_esame4.Dispositivi_Aziendali.dto;

import jakarta.validation.constraints.NotBlank;

public class TabletDto extends DispositivoDto {
    @NotBlank(message = "Il tipo non può essere vuoto")
    private String tipo = "Tablet";
}
