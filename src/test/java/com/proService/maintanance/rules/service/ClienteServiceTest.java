package com.proService.maintanance.rules.service;

import com.proService.maintanance.rules.domain.entity.Cliente;
import com.proService.maintanance.rules.domain.repository.ClienteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ClienteServiceTest {

    @InjectMocks
    @Autowired
    private ClienteService clienteService;

    @Mock
    @Autowired
    private ClienteRepository clienteRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {

        Cliente clientePopulado = getClientePopulado();
        Cliente clienteAdicionado = clienteService.save(clientePopulado);

        Assert.assertEquals(clientePopulado.getNome(), clienteAdicionado.getNome());
        Assert.assertEquals(clientePopulado.getEmail(), clienteAdicionado.getEmail());
        Assert.assertEquals(clientePopulado.getEndereco(), clienteAdicionado.getEndereco());
        Assert.assertEquals(clientePopulado.getTelefone(), clienteAdicionado.getTelefone());
    }

    @Test
    void update() {
        Cliente clienteAdicionado = clienteService.save(getClientePopulado());
        clienteAdicionado.setNome("João");
        Cliente clienteAlterado = clienteService.update(clienteAdicionado);

        Assert.assertSame(clienteAlterado.getNome(), clienteAdicionado.getNome());
    }

    @Test
    void findById(){
        Cliente clienteAdicionado = clienteService.save(getClientePopulado());
        Cliente clienteBusca = clienteService.findById(clienteAdicionado.getId()).get();
        Assert.assertEquals(clienteBusca.getId(), clienteAdicionado.getId());
    }

    @Test
    void findAll() {
        clienteService.save(getClientePopulado());
        Assert.assertFalse(clienteService.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        Cliente clienteAdicionado = clienteService.save(getClientePopulado());
        Assert.assertTrue(clienteService.findById(clienteAdicionado.getId()).isPresent());
        clienteService.deleteById(clienteAdicionado.getId());
        Optional<Cliente> clienteBusca = clienteService.findById(clienteAdicionado.getId());
        Assert.assertFalse(clienteBusca.isPresent());
    }

    private Cliente getClientePopulado(){
        Cliente clientePopulado = new Cliente();
        clientePopulado.setNome("Gabriel Mandelli");
        clientePopulado.setEmail("gabriel@gmail.com");
        clientePopulado.setEndereco("São Bento Baixo, Nova Veneza");
        clientePopulado.setTelefone("48 999999999");
        return clientePopulado;
    }

}