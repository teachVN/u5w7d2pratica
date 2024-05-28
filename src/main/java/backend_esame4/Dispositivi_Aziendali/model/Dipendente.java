package backend_esame4.Dispositivi_Aziendali.model;


import backend_esame4.Dispositivi_Aziendali.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@Entity
public class Dipendente implements UserDetails {

    @Id
    @GeneratedValue
    private int username;
    private String nome;
    private String cognome;
    private String email;
    private String foto;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "dipendente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dispositivo> dispositivi;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername() {
        return String.valueOf(username);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
