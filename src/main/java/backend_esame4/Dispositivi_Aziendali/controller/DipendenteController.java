package backend_esame4.Dispositivi_Aziendali.controller;
import backend_esame4.Dispositivi_Aziendali.dto.DipendenteDto;
import backend_esame4.Dispositivi_Aziendali.dto.DispositivoDto;
import backend_esame4.Dispositivi_Aziendali.exception.BadRequestException;
import backend_esame4.Dispositivi_Aziendali.exception.DipendenteNonTrovatoException;
import backend_esame4.Dispositivi_Aziendali.model.Dipendente;
import backend_esame4.Dispositivi_Aziendali.model.Dispositivo;
import backend_esame4.Dispositivi_Aziendali.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/api/dipendenti")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveDipendente(@RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult){ //BindRes contiene i risultati della validazione
        if(bindingResult.hasErrors()){
            throw new BadRequestException((bindingResult.getAllErrors().stream(). //creiamo una stringa unica con tt gli errori
                    map(objectError -> objectError.getDefaultMessage()).reduce("", (s, s2) -> s+s2))); //map da ad ogni errore il messaggio; reduce per fusione che concatena accumulatore con ogni stringa
        }
        return dipendenteService.saveDipendente(dipendenteDto);
    }

    @GetMapping("/api/dipendenti")
    public List<Dipendente> getDipendenti(){
        return dipendenteService.getDipendenti();
    }

    @GetMapping("/api/dipendenti/{username}")
    public Dipendente getDipendenteByUsername(@PathVariable int username){
        Optional<Dipendente> dipendenteOptional = dipendenteService.getDipendenteByUsername(username);

        if (dipendenteOptional.isPresent()){
            return dipendenteOptional.get();
        }
        else{
            throw new DipendenteNonTrovatoException("Dipendente con username=" + username + " non trovato");
        }
    }


    @PutMapping("/api/dipendenti/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Dipendente updateDipendente(@PathVariable int username, @RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new BadRequestException((bindingResult.getAllErrors().stream(). //creiamo una stringa unica con tt gli errori
                    map(objectError -> objectError.getDefaultMessage()).reduce("", (s, s2) -> s+s2))); //map da ad ogni errore il messaggio; reduce per fusione che concatena accumulatore con ogni stringa
        }
        return dipendenteService.updateDipendente(username, dipendenteDto);

    }


    @DeleteMapping("/api/dipendenti/{username}")
    public String deleteDipendente(@PathVariable int username){

        if (dipendenteService.getDipendenteByUsername(username).isPresent()){
            return dipendenteService.deleteDipendente(username);
        }
        else{
            throw new DipendenteNonTrovatoException("Dipendente con username=" + username + " non trovato");
        }
    }

    @PatchMapping("/api/dipendenti/{username}")
    public String patchFotoDipendente(@RequestBody MultipartFile foto, @PathVariable int username) throws IOException {
        return dipendenteService.patchFotoDipendente(username, foto);
    }
}


