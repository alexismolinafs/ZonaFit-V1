package zona_fit.service;

import zona_fit.model.Cliente;

import java.util.List;

public interface IClienteDao {
    List<Cliente> listarClientes();

    boolean buscarClientePorId(Cliente cliente);

    boolean agregarCliente(Cliente cliente);

    boolean actualizarCliente(Cliente cliente);

    boolean eliminarCliente(Cliente cliente);
}
