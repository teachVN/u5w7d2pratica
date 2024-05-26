package backend_esame4.Dispositivi_Aziendali.controller;


import backend_esame4.Dispositivi_Aziendali.dto.DispositivoDto;
import backend_esame4.Dispositivi_Aziendali.exception.BadRequestException;
import backend_esame4.Dispositivi_Aziendali.exception.DispositivoNonTrovatoException;
import backend_esame4.Dispositivi_Aziendali.model.Dispositivo;
import backend_esame4.Dispositivi_Aziendali.service.DispositivoService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping("/api/dispositivi")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveDispositivo(@RequestBody @Validated DispositivoDto dispositivoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException((bindingResult.getAllErrors().stream(). //creiamo una stringa unica con tt gli errori
                    map(objectError -> objectError.getDefaultMessage()).reduce("", (s, s2) -> s+s2))); //map da ad ogni errore il messaggio; reduce per fusione che concatena accumulatore con ogni stringa
        }
        return dispositivoService.saveDispositivo(dispositivoDto);
    }

    @GetMapping("/api/dispositivi")
    public List<Dispositivo> getDispositivi(){
        return dispositivoService.getDispositivi();
    }

    @GetMapping("/api/dispositivi/{id}")
    public Dispositivo getDispositivoById(@PathVariable long id) throws DispositivoNonTrovatoException{
        Optional<Dispositivo> dispositivoOptional = dispositivoService.getDispositivoById(id);

        if (dispositivoOptional.isPresent()){
            return dispositivoOptional.get();
        }
        else{
            throw new DispositivoNonTrovatoException("Dispositivo con id=" + id + " non trovato");
        }
    }

    @PutMapping("/api/dispositivi/{id}")
    public Dispositivo updateDispositivo(@PathVariable long id, @RequestBody @Validated DispositivoDto dispositivoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException((bindingResult.getAllErrors().stream(). //creiamo una stringa unica con tt gli errori
                    map(objectError -> objectError.getDefaultMessage()).reduce("", (s, s2) -> s+s2))); //map da ad ogni errore il messaggio; reduce per fusione che concatena accumulatore con ogni stringa
        }
        return dispositivoService.updateDispositivo(id, dispositivoDto);

    }
    @PostMapping("/api/dispositivi/{id}/assegna")
    public Dispositivo assignDipendenteToDispositivo(@PathVariable long id, @RequestParam long dipendenteId) {
        return dispositivoService.assignDipendenteToDispositivo(id, dipendenteId);
    }


    @DeleteMapping("/api/dispositivi/{id}")
    public String deleteDispositivo(@PathVariable long id) throws DispositivoNonTrovatoException {
        return dispositivoService.deleteDispositivo(id);
    }
}
