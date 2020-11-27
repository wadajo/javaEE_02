package service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Cliente;
import model.Cuenta;
import model.Movimiento;
import repository.BancaRepository;

@Service
public class BancaServiceImpl implements BancaService {
	@Autowired
	BancaRepository repository;
	
	@Transactional
	@Override
	public void asociarClienteACuenta(int idCuenta, int idCliente) {
		Cuenta aActualizar=repository.devolverCuenta(idCuenta);
		Cliente aAgregar=repository.devolverCliente(idCliente);
		if(null!=aActualizar&&null!=aAgregar) {
			aAgregar.getCuentas().add(aActualizar);
			repository.actualizarCliente(aAgregar);
		}
	}

	@Override
	public List<Cuenta> devolverCuentasDeCliente(int dni) {
		return repository.devolverCuentasDeCliente(dni);
	}

	@Override
	public List<Cuenta> devolverCuentasNoDeCliente(int dni) {
		return repository.devolverCuentasNoDeCliente(dni);
	}

	@Override
	public List<Cliente> devolverClientesDeCuenta(int numeroCuenta) {
		return repository.devolverClientesDeCuenta(numeroCuenta);
	}

	@Override
	public List<Cliente> devolverClientes() {
		return repository.devolverClientes();
	}

	@Override
	public Cliente devolverCliente(int dni) {
		Cliente aDevolver=repository.devolverCliente(dni);
		if(null!=aDevolver)
			return aDevolver;
		else return null;
	}

	@Override
	public Cuenta devolverCuenta(int numeroCuenta) {
		Cuenta aDevolver=repository.devolverCuenta(numeroCuenta);
		if(null!=aDevolver)
			return repository.devolverCuenta(numeroCuenta);
		else return null;
	}

	@Transactional
	@Override
	public void ingresarDinero(int numeroCuenta, double dinero) {
		Cuenta cuenta=devolverCuenta(numeroCuenta);
		cuenta.setSaldo(cuenta.getSaldo()+dinero);
		for (Cliente unCliente : cuenta.getClientes()) {
			repository.actualizarCliente(unCliente);			
		}
		repository.registrarMovimiento(new Movimiento(
				dinero,
				Date.from(Instant.now()),
				"ingreso",
				cuenta
				));
	}

	@Transactional
	@Override
	public void extraerDinero(int numeroCuenta, double dinero) {
		Cuenta cuenta=devolverCuenta(numeroCuenta);
		cuenta.setSaldo(cuenta.getSaldo()-dinero);
		for (Cliente unCliente : cuenta.getClientes()) {
			repository.actualizarCliente(unCliente);			
		}
		repository.registrarMovimiento(new Movimiento(
				dinero,
				Date.from(Instant.now()),
				"extracción",
				cuenta
				));
	}

	@Override
	public boolean comprobarSaldo(int numeroCuenta, double aExtraer) {
		Cuenta cuenta=devolverCuenta(numeroCuenta);
		return cuenta.getSaldo()>aExtraer?true:false;
	}

	@Override
	public double devolverSaldo(int numeroCuenta) {
		Cuenta cuenta=devolverCuenta(numeroCuenta);
		return cuenta.getSaldo();
	}

	@Transactional
	@Override
	public void hacerTransferencia(int numeroCuentaOrigen, int numeroCuentaDestino, double cantidad) {
		extraerDinero(numeroCuentaOrigen, cantidad);
		ingresarDinero(numeroCuentaDestino, cantidad);
	}

}
