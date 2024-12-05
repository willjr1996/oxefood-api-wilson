package br.com.ifpe.oxefood.api.categoriaProduto;
import br.com.ifpe.oxefood.modelo.categoriaProduto.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProdutoRequest {

    @NotBlank (message = "A descrição é de preenchimento obrigatório")
    private String descricao;
    
    public CategoriaProduto build() {

        return CategoriaProduto.builder()
                .descricao(descricao)
                .build();
    }
}