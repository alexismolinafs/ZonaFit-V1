package zona_fit.service;

import zona_fit.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.connection.Conexion.getConnection;

public class ClientesServ implements IClienteDao {
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        var sql = "SELECT * FROM clientes ORDER BY id";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e);
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        var con = getConnection();
        var sql = "SELECT * FROM clientes WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setNombre((rs.getString("nombre")));
                cliente.setApellido((rs.getString("apellido")));
                cliente.setMembresia((rs.getInt("membresia")));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el cliente por id: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "INSERT INTO clientes (nombre, apellido, membresia)"
                + "VALUES (?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConnection();
        var sql = "UPDATE clientes SET nombre =?, apellido =?, membresia =?" +
                " WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "DELETE FROM clientes WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

   /* public static void main(String[] args) {
        System.out.println("Listar clientes");
        IClienteDao dao = new ClientesServ();
        var list = dao.listarClientes();
        list.forEach(System.out::println);
    }*/

    //Buscar por Id
    /*public static void main(String[] args) {
        IClienteDao dao = new ClientesServ();
        var cliente1 = new Cliente(2);
        System.out.println("Cliente antes de la búsqueda: "+ cliente1);
        var encontrado = dao.buscarClientePorId(cliente1);
        if(encontrado)
            System.out.println("Cliente encontrado: "+ cliente1);
        else
            System.out.println("No se encontró el cliente " + cliente1.getId());
    }*/

    //Agregar Cliente
    /*public static void main(String[] args) {
        IClienteDao dao = new ClientesServ();
        var newCliente = new Cliente("Daniel", "Ortiz", 300);
        var agregado = dao.agregarCliente(newCliente);
        if (agregado)
            System.out.println("Cliente agregado: " + agregado);
        else
            System.out.println("Cliente no agregado: " + agregado);
        System.out.println("Lista: ");
        var list = dao.listarClientes();
        list.forEach(System.out::println);
    }*/

    //Actualizar Cliente
    /*public static void main(String[] args) {
        IClienteDao dao = new ClientesServ();
        var modificar = new Cliente(4, "Carlos Daniel","Huerta", 300 );
        var modificado = dao.actualizarCliente(modificar);
        if (modificado)
            System.out.println("Cliente modificado: "+ modificar);
        else
            System.out.println("El cliente no se pudo modificar: "+ modificar);
        var list = dao.listarClientes();
        list.forEach(System.out::println);
    }*/

    //Eliminar cliente
    /*public static void main(String[] args) {
        IClienteDao dao = new ClientesServ();
        var deleteClient = new Cliente(4);
        var eliminado = dao.eliminarCliente(deleteClient);
        if (eliminado)
            System.out.println("Cliente eliminado: "+deleteClient);
        else
            System.out.println("Cliente no eliminado: "+deleteClient);
        var list = dao.listarClientes();
        list.forEach(System.out::println);
    }*/
}