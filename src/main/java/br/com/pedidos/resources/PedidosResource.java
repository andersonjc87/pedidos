package br.com.pedidos.resources;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedidos.body.Pedidos;
import br.com.pedidos.entities.PedidosEntity;
import br.com.pedidos.exception.PedidosNotFound;
import br.com.pedidos.services.PedidosService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "brasilprev")
public final class PedidosResource {

    @Autowired
    private PedidosService service;

    @ResponseBody
    @ApiOperation(value = "Buscar por todos os pedidos")
    @GetMapping(path = "/pedidos")
    public ResponseEntity<?> getAll(Pageable pageable) {

        Page<PedidosEntity> pedidos = service.getAll(pageable);

        if (pedidos == null) {
            return (ResponseEntity<?>) ResponseEntity.noContent();
        }
        return ResponseEntity.ok(pedidos);

    }

    @ResponseBody
    @ApiOperation(value = "buscar por id de pedido")
    @GetMapping(path = "/pedidos/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {

        PedidosEntity pedidos = service.get(id);

        if (pedidos == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);

    }

    @RequestBody
    @ApiOperation(value = "Novo pedido")
    @PostMapping(path = "/pedidos/novo")
    public ResponseEntity<?> post(Pedidos pedidos) {

        try {
            pedidos.setData(LocalDateTime.now());
            pedidos.setStatus("PROCESSANDO");
            return ResponseEntity.ok(service.insertOrUpdate(pedidos));

        } catch (Exception e) {

            return ResponseEntity.status(500).build();
        }

    }

    @RequestBody
    @ApiOperation(value = "Atualizar pedido")
    @PutMapping(path = "/pedidos/alterar")
    public ResponseEntity<?> put(Pedidos pedidos) {

        try {
            pedidos.setData(LocalDateTime.now());
            return ResponseEntity.ok(service.insertOrUpdate(pedidos));

        } catch (Exception e) {

            return ResponseEntity.status(500).build();
        }

    }

    @RequestBody
    @ApiOperation(value = "excluir pedido")
    @DeleteMapping(path = "/pedidos/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(200).build();
        } catch (PedidosNotFound e) {
            return ResponseEntity.noContent().build();
        }
    }

}
