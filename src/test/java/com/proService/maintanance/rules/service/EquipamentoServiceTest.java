package com.proService.maintanance.rules.service;

import com.proService.maintanance.rules.domain.entity.Cliente;
import com.proService.maintanance.rules.domain.entity.Equipamento;
import com.proService.maintanance.rules.domain.repository.ClienteRepository;
import com.proService.maintanance.rules.domain.repository.EquipamentoRepository;
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
class EquipamentoServiceTest {

    @InjectMocks
    @Autowired
    private EquipamentoService equipamentoService;

    @InjectMocks
    @Autowired
    private ClienteService clienteService;

    @Mock
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Mock
    @Autowired
    private ClienteRepository clienteRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        Equipamento equipamentoPopulado = getEquipamentoPopulado();
        Equipamento equipamentoAdicionado = equipamentoService.save(equipamentoPopulado);

        Assert.assertEquals(equipamentoPopulado.getCliente().getId(), equipamentoAdicionado.getCliente().getId());
        Assert.assertEquals(equipamentoPopulado.getTipo(), equipamentoAdicionado.getTipo());
        Assert.assertEquals(equipamentoPopulado.getMarca(), equipamentoAdicionado.getMarca());
        Assert.assertEquals(equipamentoPopulado.getDescricao(), equipamentoAdicionado.getDescricao());
    }

    @Test
    void update() {
        Equipamento equipamento = equipamentoService.save(getEquipamentoPopulado());
        equipamento.setDescricao("Número de série: 213874876392847632897468329");
        Equipamento equipamentoAlterado = equipamentoService.update(equipamento);

        Assert.assertSame(equipamentoAlterado.getDescricao(), equipamento.getDescricao());
    }

    @Test
    void findById(){
        Equipamento equipamentoAdicionado = equipamentoService.save(getEquipamentoPopulado());
        Equipamento equipamentoBusca = equipamentoService.findById(equipamentoAdicionado.getId()).get();
        Assert.assertEquals(equipamentoBusca.getId(), equipamentoAdicionado.getId());
    }

    @Test
    void findAll() {
        equipamentoService.save(getEquipamentoPopulado());
        Assert.assertFalse(equipamentoService.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        Equipamento equipamentoPopulado = equipamentoService.save(getEquipamentoPopulado());
        Assert.assertTrue(equipamentoService.findById(equipamentoPopulado.getId()).isPresent());
        equipamentoService.deleteById(equipamentoPopulado.getId());
        Optional<Equipamento> equipamentoBusca = equipamentoService.findById(equipamentoPopulado.getId());
        Assert.assertFalse(equipamentoBusca.isPresent());
    }

    private Equipamento getEquipamentoPopulado(){
        Cliente clienteAdicionado = clienteService.save(getClientePopulado());

        Equipamento equipamentoPopulado = new Equipamento();
        equipamentoPopulado.setCliente(clienteAdicionado);
        equipamentoPopulado.setTipo("Placa de video");
        equipamentoPopulado.setMarca("ASUS");
        equipamentoPopulado.setDescricao("Número de série: 1287321897638927456392865723");

        return equipamentoPopulado;
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