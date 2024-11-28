package br.com.ifpe.oxefood.modelo.empresa;
import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;

    @Transactional
    public Empresa save(Empresa cliente) {
        cliente.setHabilitado(Boolean.TRUE);
        return repository.save(cliente);
    }

    public List<Empresa> listarTodos() {
        return repository.findAll();
    }

    public Empresa obterPorID(Long id) {
        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Empresa clienteAlterado) {
        Empresa cliente = repository.findById(id).get();
        cliente.setNome(clienteAlterado.getNome());
        cliente.setDataNascimento(clienteAlterado.getDataNascimento());
        cliente.setCpf(clienteAlterado.getCpf());
        cliente.setFoneCelular(clienteAlterado.getFoneCelular());
        cliente.setFoneFixo(clienteAlterado.getFoneFixo());

        repository.save(cliente);
    }

    @Transactional
        public void delete(Long id) {
        Empresa cliente = repository.findById(id).get();
        cliente.setHabilitado(Boolean.FALSE);
        repository.save(cliente);
   }
}