package br.com.ifpe.oxefood.api.entregador;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import br.com.ifpe.oxefood.modelo.entregador.EntregadorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/entregador")
@CrossOrigin
@Tag(
    name = "API Entregador",
    description = "API responsável pelos serviços de entregador no sistema"
)

public class EntregadorController {
    
    @Autowired
    private EntregadorService entregadorService;

    @Operation(
        summary = "Serviço responsável por salvar um entregador no sistema.",
        description = "api/entregador"
    )
    @PostMapping
    public ResponseEntity<Entregador> save(@RequestBody @Valid EntregadorRequest request){
        Entregador entregador = entregadorService.save(request.build());
        return new ResponseEntity<Entregador>(entregador, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Serviço responsável por pegar os dados de todos os entregadores do sistema.",
        description = "api/entregador"
    ) 
    @GetMapping
    public List <Entregador> listarTodos(){
        return entregadorService.listarTodos();
    }

    @Operation(
        summary = "Serviço responsável por pegar os dados de um entregador específico",
        description = "api/entregador/id"
    ) 
    @GetMapping("/{id}")
    public Entregador obterPorID(@PathVariable long id){
        return entregadorService.obterPorID(id);
    }

    @Operation(
        summary = "Serviço responsável por editar os dados de um entregador específico",
        description = "api/entregador/id"
    ) 
    @PutMapping("/{id}")
    public ResponseEntity<Entregador> update(@PathVariable("id") Long id, @RequestBody EntregadorRequest request) {
        entregadorService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Serviço responsável por deletar os dados de um entregador específico",
        description = "api/entregador/id"
    ) 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entregadorService.delete(id);
        return ResponseEntity.ok().build();
    }
}