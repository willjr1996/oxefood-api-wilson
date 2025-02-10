package br.com.ifpe.oxefood.api.cliente;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import br.com.ifpe.oxefood.modelo.produto.Produto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin
@Tag(
    name = "API Cliente",
    description = "API responsável pelos serviços de cliente no sistema"
)
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
        summary = "Serviço responsável por salvar um cliente no sistema.",
        description = "api/cliente"
    ) 
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest clienteRequest, HttpServletRequest request) {
        Cliente cliente = clienteService.save(clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Serviço responsável por pegar os dados de todos os clientes no sistema.",
        description = "api/cliente"
    )
 
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @Operation(
        summary = "Serviço responsável por pegar os dados de um cliente no sistema.",
        description = "api/cliente/id"
    ) 
    @GetMapping("/{id}")
    public Cliente obterPorID(@PathVariable Long id) {
        return clienteService.obterPorID(id);
    }

    @Operation(
        summary = "Serviço responsável por editar um cliente no sistema.",
        description = "api/cliente/id"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") Long id,
            @RequestBody @Valid ClienteRequest clienteRequest, HttpServletRequest request) {
        clienteService.update(id, clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Serviço responsável por deletar um cliente no sistema.",
        description = "api/cliente/id"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Serviço responsável por pegar todos os endereços de um cliente no sistema.",
        description = "api/cliente/endereco/clienteid"
    )
    // endereço
    @GetMapping("/endereco/{clienteId}")
    public EnderecoCliente obterEnderecoPorID(@PathVariable("clienteId") Long clienteId) {
        return clienteService.obterEnderecoPorID(clienteId);
    }
    
    @Operation(
        summary = "Serviço responsável por cadastrar endereço vinculado a um cliente no sistema.",
        description = "api/cliente/endereco/clienteid"
    )
    @PostMapping("/endereco/{clienteId}")
    public ResponseEntity<EnderecoCliente> adicionarEnderecoCliente(@PathVariable("clienteId") Long clienteId,
            @RequestBody @Valid EnderecoClienteRequest request) {

        EnderecoCliente endereco = clienteService.adicionarEnderecoCliente(clienteId, request.build());
        return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Serviço responsável por editar um endereço específico",
        description = "api/cliente/endereco/enderecoid"
    )
    @PutMapping("/endereco/{enderecoId}")
    public ResponseEntity<EnderecoCliente> atualizarEnderecoCliente(@PathVariable("enderecoId") Long enderecoId,
            @RequestBody EnderecoClienteRequest request) {

        EnderecoCliente endereco = clienteService.atualizarEnderecoCliente(enderecoId, request.build());
        return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.OK);
    }

    @Operation(
        summary = "Serviço responsável por delatar um endereço específico",
        description = "api/cliente/endereco/enderecoid"
    )
    @DeleteMapping("/endereco/{enderecoId}")
    public ResponseEntity<Void> removerEnderecoCliente(@PathVariable("enderecoId") Long enderecoId) {

        clienteService.removerEnderecoCliente(enderecoId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Serviço responsável por filtrar dados por cliente ou cpf",
        description = "api/cliente/filtrar/"
    )
    @PostMapping("/filtrar")
    public List<Cliente> filtrar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "cpf", required = false) String cpf) {

        return clienteService.filtrar(nome, cpf);
    }

}