package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Cliente;
import model.Cuenta;
import model.Movimiento;

@Repository
public class BancaRepositoryImplJPA implements BancaRepository {
	@PersistenceContext(unitName = "bancaPU")
	private EntityManager em;
	
	@Override
	public Cliente devolverCliente(int dni) {
		return em.find(Cliente.class, dni);
	}

	@Override
	public Cuenta devolverCuenta(int numeroCuenta) {
		return em.find(Cuenta.class, numeroCuenta);
	}

	@Override
	public List<Cliente> devolverClientes() {
		TypedQuery<Cliente> query=em.createNamedQuery("Cliente.findAll", Cliente.class);
		return query.getResultList();
	}

	@Override
	public List<Cuenta> devolverCuentasDeCliente(int idCliente) {
		String jpql="Select distinct c From Cuenta c join c.clientes cliente Where cliente.dni =?1";
		TypedQuery<Cuenta> query=em.createQuery(jpql, Cuenta.class);
		query.setParameter(1, idCliente);		
		return query.getResultList();
	}

	@Override
	public List<Cuenta> devolverCuentasNoDeCliente(int idCliente) {
		String jpql="Select c From Cuenta c Where c Not In ";
		jpql+="(Select c From Cuenta c join c.clientes cliente Where cliente.dni=?1)";
		TypedQuery<Cuenta> query=em.createQuery(jpql, Cuenta.class);
		query.setParameter(1, idCliente);		
		return query.getResultList();
	}

	@Override
	public List<Cliente> devolverClientesDeCuenta(int idCuenta) {
		String jpql="Select c From Cliente c join c.cuentas cuenta Where cuenta.numeroCuenta =?1";
		TypedQuery<Cliente> query=em.createQuery(jpql, Cliente.class);
		query.setParameter(1, idCuenta);		
		return query.getResultList();
	}

	@Override
	public void registrarMovimiento(Movimiento aRegistrar) {
		em.merge(aRegistrar);
	}

	@Transactional 
	@Override
	public void altaCliente(Cliente nuevo) {
		em.persist(nuevo);
	}

	@Transactional 
	@Override
	public void altaCuenta(Cuenta nueva) {
		em.persist(nueva);
	}

	@Transactional 
	@Override
	public void sumarDinero(Cuenta actual, double cantidad) {
		actual.setSaldo(actual.getSaldo()+cantidad);
		em.merge(actual);
	}

	@Transactional 
	@Override
	public void quitarDinero(Cuenta actual, double cantidad) {
		actual.setSaldo(actual.getSaldo()-cantidad);
		em.merge(actual);
	}

	@Transactional 
	@Override
	public void actualizarCliente(Cliente cliente) {
		em.merge(cliente);
	}
}
