package br.com.pedidos.exception;

public class PedidosNotFound extends Exception {

    private static final long serialVersionUID = 1L;

    public PedidosNotFound(String msg) {
        super(msg);
    }

    public PedidosNotFound(String msg, Throwable cause) {
        super(msg, cause);
    }

}
