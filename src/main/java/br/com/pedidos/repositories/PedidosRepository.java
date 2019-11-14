package br.com.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedidos.entities.PedidosEntity;

@Repository
public interface PedidosRepository extends JpaRepository<PedidosEntity, Long> {

}
