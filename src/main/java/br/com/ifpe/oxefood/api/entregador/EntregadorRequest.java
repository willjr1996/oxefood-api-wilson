package br.com.ifpe.oxefood.api.entregador;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorRequest {

@NotNull(message = "O Nome é de preenchimento obrigatório")
@NotEmpty(message = "O Nome é de preenchimento obrigatório")
@Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")
private String nome;

@NotBlank(message = "O CPF é de preenchimento obrigatório")
@CPF
private String cpf;

@NotBlank(message = "O RG é de preenchimento obrigatório")
private String rg;

@Past
@JsonFormat(pattern = "dd/MM/yyyy")
private LocalDate dataNascimento;

@Length(min = 8, max = 20, message = "O campo Fone Celular tem que ter entre {min} e {max} caracteres")
private String foneCelular;

@Length(min = 8, max = 20, message = "O campo Fone Fixo tem que ter entre {min} e {max} caracteres")
private String foneFixo;

private Integer qtdEntregasRealizadas;

private Double valorFrete;

@NotBlank(message = "A rua é de preenchimento obrigatório")
private String enderecoRua;


private String enderecoComplemento;

@NotBlank(message = "O número da resdência é de preenchimento obrigatório")
private String enderecoNumero;

@NotBlank(message = "O Bairro é de preenchimento obrigatório")
private String enderecoBairro;

@NotBlank(message = "A Cidade é de preenchimento obrigatório")
private String enderecoCidade;

@NotBlank(message = "O CEP é de preenchimento obrigatório")
private String enderecoCep;

@NotBlank(message = "O Estado é de preenchimento obrigatório")
private String enderecoUf;

private Boolean ativo;

public Entregador build() {

        return Entregador.builder()
                .nome(nome)
                .cpf(cpf)
                .rg(rg)
                .dataNascimento(dataNascimento)
                .foneCelular(foneCelular)
                .foneFixo(foneFixo)
                .qtdEntregasRealizadas(qtdEntregasRealizadas)
                .valorFrete(valorFrete)
                .enderecoRua(enderecoRua)
                .enderecoComplemento(enderecoComplemento)
                .enderecoNumero(enderecoNumero)
                .enderecoBairro(enderecoBairro)
                .enderecoCidade(enderecoCidade)
                .enderecoCep(enderecoCep)
                .enderecoUf(enderecoUf)
                .ativo(ativo)
                .build();
    }
}