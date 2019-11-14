package br.com.pedidos.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pedidos.body.Pedidos;
import br.com.pedidos.entities.PedidosEntity;
import br.com.pedidos.exception.PedidosNotFound;
import br.com.pedidos.repositories.PedidosRepository;

@Service
public class PedidosService {

    @Autowired
    private PedidosRepository repository;

    public Page<PedidosEntity> getAll(Pageable pageable) {
        return repository.findAll(pageable);

    }

    public PedidosEntity get(Long idPedido) {
        Optional<PedidosEntity> subEmitterOp = repository.findById(idPedido);

        if (subEmitterOp.isPresent()) {
            return subEmitterOp.get();
        }

        return null;
    }

    public PedidosEntity insertOrUpdate(Pedidos pedidos) {
        return this.repository.save(convertToEntity(pedidos));
    }

    public PedidosEntity convertToEntity(Pedidos pedido) {

        PedidosEntity entity = new PedidosEntity();

        entity.setIdPedido(pedido.getIdPedidos());
        entity.setIdClente(pedido.getIdCliente());
        entity.setSessao(pedido.getSessao());
        entity.setData(LocalDateTime.now());
        entity.setStatus(pedido.getStatus());

        return entity;
    }

    public void delete(Long id) throws PedidosNotFound {

        Optional<PedidosEntity> entityOp = repository.findById(id);

        if (entityOp.isPresent()) {
            PedidosEntity entity = entityOp.get();
            entity.setStatus("CANCELADO");
            entity.setData(LocalDateTime.now());
            repository.save(entity);
        } else {
            throw new PedidosNotFound("Range Bins not found");
        }

    }

}