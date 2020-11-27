package repository;

import java.util.List;

import model.Cliente;
import model.Cuenta;
import model.Movimiento;

public interface BancaRepository {
	Cliente devolverCliente(int dni);
	Cuenta devolverCuenta(int numeroCuenta);
	List<Cliente> devolverClientes();
	List<Cuenta> devolverCuentasDeCliente(int idCliente);
	List<Cuenta> devolverCuentasNoDeCliente(int idCliente);
	List<Cliente> devolverClientesDeCuenta(int idCuenta);
	void quitarDinero(Cuenta actual,double cantidad);
	void sumarDinero(Cuenta actual,double cantidad);
	void registrarMovimiento(Movimiento aRegistrar);
	void altaCliente(Cliente nuevo);
	void altaCuenta(Cuenta nueva);
	void actualizarCliente(Cliente cliente);
}
