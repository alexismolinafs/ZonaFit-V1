package zona_fit.presentation;

import zona_fit.model.Cliente;
import zona_fit.service.ClientesServ;
import zona_fit.service.IClienteDao;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    public static void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);

        IClienteDao dao = new ClientesServ();
        while (!salir) {
            try {
                var option = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, option, dao);

            } catch (Exception e) {
                System.out.println("Error al ejecutar las opciones");
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.print("""
                *** Zona Fit App - GYM ***
                1. Listar Clientes
                2. Buscar Clientes
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                - Selecciona una opción: \s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int option, IClienteDao dao) {
        var salir = false;
        switch (option) {
            case 1 -> {
                System.out.println(" *-* Listado de clientes *-*");
                var list = dao.listarClientes();
                list.forEach(System.out::println);
            }

            case 2 -> {
                System.out.println(" O_o Buscar cliente o_o");
                System.out.println("Introduce el id del cliente a buscar: ");
                var IdCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(IdCliente);
                var bus = dao.buscarClientePorId(cliente);
                if (bus)
                    System.out.println("Cliente encontrado: " + cliente);
                else
                    System.out.println("Cliente no encontrado: " + cliente);
            }
            case 3 -> {
                System.out.println(" +++ Agregar cliente +++");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellidos: ");
                var apellidos = consola.nextLine();
                System.out.print("Membresía: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(nombre, apellidos, membresia);
                var agregado = dao.agregarCliente(cliente);
                if (agregado)
                    System.out.println("Cliente agregado correctamente: " + cliente);
                else
                    System.out.println("Ocurrió un error al agregar el cliente");
            }
            case 4 -> {
                System.out.println(" +++ Modificar cliente +++");
                System.out.println("Id del cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellidos: ");
                var apellidos = consola.nextLine();
                System.out.print("Membresía: ");
                var membresia = Integer.parseInt(consola.nextLine());

                var cliente = new Cliente(idCliente, nombre, apellidos, membresia);
                var modificado = dao.actualizarCliente(cliente);
                if (modificado)
                    System.out.println("Cliente modificado correctamente: " + cliente);
                else
                    System.out.println("Ocurrió un error al modificar el cliente " + cliente);
            }
            case 5 -> {
                System.out.println(" --- Eliminar cliente ---");
                System.out.print("Id del cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());

                var cliente = new Cliente(idCliente);
                var eliminado = dao.eliminarCliente(cliente);
                if (eliminado)
                    System.out.println("El cliente se ha eliminado correctamente " + cliente);
                else
                    System.out.println("Hubo un error al eliminar el cliente " + cliente);
            }
            case 6 -> {
                System.out.println("¡Hasta Proto!");
                salir = true;
            }
            default -> System.out.println("Opción no valida");
        }
        return salir;
    }
}