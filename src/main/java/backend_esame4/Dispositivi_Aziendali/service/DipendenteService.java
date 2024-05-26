package backend_esame4.Dispositivi_Aziendali.service;

import backend_esame4.Dispositivi_Aziendali.dto.DipendenteDto;
import backend_esame4.Dispositivi_Aziendali.dto.DispositivoDto;
import backend_esame4.Dispositivi_Aziendali.exception.DipendenteNonTrovatoException;
import backend_esame4.Dispositivi_Aziendali.exception.DispositivoNonTrovatoException;
import backend_esame4.Dispositivi_Aziendali.model.Dipendente;

import backend_esame4.Dispositivi_Aziendali.model.Dispositivo;
import backend_esame4.Dispositivi_Aziendali.repository.DipendenteRepository;
import backend_esame4.Dispositivi_Aziendali.repository.DispositivoRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private Cloudinary cloudinary;

    public String saveDipendente(DipendenteDto dipendenteDto) {
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(dipendenteDto.getNome());
        dipendente.setCognome(dipendenteDto.getCognome());
        dipendente.setEmail(dipendenteDto.getEmail());

        dipendenteRepository.save(dipendente);
        return "Dipendente con username " + dipendente.getUsername() + " salvato correttamente";
    }

    public List<Dipendente> getDipendenti(){
        return dipendenteRepository.findAll();
    }

    public Optional<Dipendente> getDipendenteByUsername(int username){
        return dipendenteRepository.findById(String.valueOf(username));
    }

    public  Dipendente updateDipendente(int username, DipendenteDto dipendenteDto){
        Optional<Dipendente> dipendenteOptional = getDipendenteByUsername(Integer.parseInt(String.valueOf(username)));

        if (dipendenteOptional.isPresent()){
            Dipendente dipendente = dipendenteOptional.get();
            dipendente.setNome(dipendenteDto.getNome());
            dipendente.setCognome(dipendenteDto.getCognome());
            dipendente.setEmail(dipendenteDto.getEmail());

            Optional<Dispositivo> dispositivoOptional = dispositivoRepository.findById((long) dipendenteDto.getUsername());

            if (dispositivoOptional.isPresent()) {
               Dispositivo dispositivo = dispositivoOptional.get();
                dipendente.setDispositivi((List<Dispositivo>) dispositivo);
                dipendenteRepository.save(dipendente);
                return dipendente;
            }

            else {
                throw new DispositivoNonTrovatoException("Dispositivo con id=" + dipendenteDto.getDispositiviId() + " non trovato");
            }
        }
        else {
            throw new DipendenteNonTrovatoException("Dipendente con username=" + username + " non trovato");
        }
    }

    public String deleteDipendente(int username) {
        Optional<Dipendente> dipendenteOptional = dipendenteRepository.findById(String.valueOf(username));

        if (dipendenteOptional.isPresent()) {
            dipendenteRepository.delete(dipendenteOptional.get());
            return "Studente con username=" + username + " cancellato con successo";
        } else {
            throw new DipendenteNonTrovatoException("Dipendente con username=" + username + " non trovato");
        }
    }


    public String patchFotoDipendente(int username, MultipartFile foto) throws IOException {
        Optional<Dipendente> dipendenteOptional = getDipendenteByUsername(username);

        if(dipendenteOptional.isPresent()){           //upload invio della foto    prendiamo la mappa   get da il valore di map
            String url = (String) cloudinary.uploader().upload(foto.getBytes(), Collections.emptyMap()).get("url");  //restituisce una mappa. Da questa estraiamo il valore della chiave url
            Dipendente dipendente = dipendenteOptional.get(); //estrazione del dipendente e associazione
            dipendente.setFoto(url);
            dipendenteRepository.save(dipendente);
            return "Dipendente con username=" + username + " aggiornato correttamente con la foto inviata";
        }
        else{
            throw new DipendenteNonTrovatoException("Dipendente con username=" + username + " non trovato");
        }
    }

}
