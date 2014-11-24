package com.makingdevs.curso.jms;

import com.makingdevs.asembly.vo.Cliente;

public interface MyMessageProducer {
	void operacionPesadaConCliente(Cliente cliente);
}
