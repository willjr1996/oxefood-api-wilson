package br.com.ifpe.oxefood.modelo.acesso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefood.util.entity.EntidadeNegocio;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends EntidadeNegocio implements UserDetails {

   @Column(nullable = false, unique = true)
   private String username;

   @JsonIgnore
   @Column(nullable = false)
   private String password;

   @JsonIgnore
   @ElementCollection(fetch = FetchType.EAGER)
   @Builder.Default
   private List<Perfil> roles = new ArrayList<>();

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       return roles;
   }

   @Override
   public String getUsername() {
       return username;
   }

   public String getPassword() {
       return password;
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