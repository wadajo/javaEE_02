package service;

import java.util.List;

import model.Cliente;
import model.Cuenta;

public interface BancaService {
	void asociarClienteACuenta(int idCuenta, int idCliente);
	List<Cuenta> devolverCuentasDeCliente(int dni);
	List<Cuenta> devolverCuentasNoDeCliente(int dni);
	List<Cliente> devolverClientesDeCuenta(int numeroCuenta);
	List<Cliente> devolverClientes();
	Cliente devolverCliente(int dni);
	Cuenta devolverCuenta(int numeroCuenta);
	void ingresarDinero(int numeroCuenta, double dinero);
	void extraerDinero(int numeroCuenta, double dinero);
	boolean comprobarSaldo(int numeroCuenta, double aExtraer);
	double devolverSaldo(int numeroCuenta);
	void hacerTransferencia(int numeroCuentaOrigen,int numeroCuentaDestino,double cantidad);
}
