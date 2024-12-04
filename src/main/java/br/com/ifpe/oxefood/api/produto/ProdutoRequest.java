package br.com.ifpe.oxefood.api.produto;
import org.hibernate.validator.constraints.Length;

import br.com.ifpe.oxefood.modelo.produto.Produto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {
    
    @NotBlank(message = "O Código é de preenchimento obrigatório")
    private String codigo;
    
    @NotBlank(message = "O Título é de preenchimento obrigatório")
    @Length(max = 100, message = "O título deverá ter no máximo {max} caracteres")
    private String titulo;
    
    @NotBlank(message = "A Descrição do produto é de preenchimento obrigatório")
    private String descricao;
        
    private Double valorUnitario;
    
    
    private Integer tempoEntregaMinimo;
    
    
    private Integer tempoEntregaMaximo;

    public Produto build(){
        
        return Produto.builder()
            .codigo(codigo)
            .titulo(titulo)
            .descricao(descricao)
            .valorUnitario(valorUnitario)
            .tempoEntregaMinimo(tempoEntregaMinimo)
            .tempoEntregaMaximo(tempoEntregaMaximo)
            .build();
    }
}