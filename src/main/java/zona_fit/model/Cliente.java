package zona_fit.model;

import java.util.Objects;

public class Cliente {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer membresia;


    public Cliente() {

    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(String nombre, String apellido, Integer membresia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.membresia = membresia;
    }

    public Cliente(Integer id, String nombre, String apellido, Integer membresia) {
        this(nombre, apellido, membresia);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getMembresia() {
        return membresia;
    }

    public void setMembresia(Integer membresia) {
        this.membresia = membresia;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "Id: " + id +
                "Nombre: " + nombre + '\'' +
                "Apellido: " + apellido + '\'' +
                "Membresía: " + membresia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido) && Objects.equals(membresia, cliente.membresia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, membresia);
    }
}
