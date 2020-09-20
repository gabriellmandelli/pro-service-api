package com.proService.maintanance.rules.service;

import com.proService.maintanance.rules.domain.entity.Cliente;
import com.proService.maintanance.rules.domain.entity.Equipamento;
import com.proService.maintanance.rules.domain.entity.Funcionario;
import com.proService.maintanance.rules.domain.entity.Manutencao;
import com.proService.maintanance.rules.domain.enums.SituacaoManutencao;
import com.proService.maintanance.rules.domain.repository.ClienteRepository;
import com.proService.maintanance.rules.domain.repository.EquipamentoRepository;
import com.proService.maintanance.rules.domain.repository.FuncionarioRepository;
import com.proService.maintanance.rules.domain.repository.ManutencaoRespository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ManutencaoServiceTest {

    @InjectMocks
    @Autowired
    private ManutencaoService manutencaoService;

    @InjectMocks
    @Autowired
    private EquipamentoService equipamentoService;

    @InjectMocks
    @Autowired
    private ClienteService clienteService;

    @InjectMocks
    @Autowired
    private FuncionarioService funcionarioService;

    @Mock
    @Autowired
    private ManutencaoRespository manutencaoRespository;

    @Mock
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Mock
    @Autowired
    private ClienteRepository clienteRepository;

    @Mock
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        Manutencao manutencaoPopulado = getManutencaoPopulado();
        Manutencao manutencaoAdicionado = manutencaoService.save(manutencaoPopulado);

        Assert.assertEquals(manutencaoPopulado.getEquipamento().getId(), manutencaoAdicionado.getEquipamento().getId());
        Assert.assertEquals(manutencaoPopulado.getFuncionario().getId(), manutencaoAdicionado.getFuncionario().getId());
        Assert.assertEquals(manutencaoPopulado.getDataEntrada(), manutencaoAdicionado.getDataEntrada());
        Assert.assertEquals(manutencaoPopulado.getDataUltimaAtualizacao(), manutencaoAdicionado.getDataUltimaAtualizacao());
        Assert.assertEquals(manutencaoPopulado.getSituacao(), manutencaoAdicionado.getSituacao());
    }

    @Test
    void update() {
        Manutencao manutencao = manutencaoService.save(getManutencaoPopulado());
        manutencao.setProblema("Não era a solda BGA");
        Manutencao manutencaoAlterado = manutencaoService.update(manutencao);

        Assert.assertSame(manutencaoAlterado.getProblema(), manutencao.getProblema());
    }

    @Test
    void findById(){
        Manutencao manutencaoAdicionado = manutencaoService.save(getManutencaoPopulado());
        Manutencao manutencaoBusca = manutencaoService.findById(manutencaoAdicionado.getId()).get();
        Assert.assertEquals(manutencaoBusca.getId(), manutencaoAdicionado.getId());
    }

    @Test
    void findAll() {
        manutencaoService.save(getManutencaoPopulado());
        Assert.assertFalse(manutencaoService.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        Manutencao manutencaoAdicionado = manutencaoService.save(getManutencaoPopulado());
        Assert.assertTrue(manutencaoService.findById(manutencaoAdicionado.getId()).isPresent());
        manutencaoService.deleteById(manutencaoAdicionado.getId());
        Optional<Manutencao> manutencaoBusca = manutencaoService.findById(manutencaoAdicionado.getId());
        Assert.assertFalse(manutencaoBusca.isPresent());
    }

    @Test
    void findManutencaoByFuncionarioId() {
        Manutencao manutencaoAdicionado = manutencaoService.save(getManutencaoPopulado());
        List<Manutencao> manutencaoList = manutencaoService.findManutencaoByFuncionarioId(manutencaoAdicionado.getFuncionario().getId());
        Assert.assertFalse(manutencaoList.isEmpty());
    }

    @Test
    void findManutencaoByFuncionarioIdAndSituacaoManuntencao() {
        Manutencao manutencaoAdicionado = manutencaoService.save(getManutencaoPopulado());
        List<Manutencao> manutencaoList = manutencaoService.findManutencaoByFuncionarioId(manutencaoAdicionado.getFuncionario().getId(), SituacaoManutencao.NAO_INICIADO);
        Assert.assertFalse(manutencaoList.isEmpty());
    }

    private Manutencao getManutencaoPopulado(){
        Equipamento equipamentoAdicionado = equipamentoService.save(getEquipamentoPopulado());
        Funcionario funcionarioAdicionado = funcionarioService.save(getFuncionarioPopulado());

        Manutencao manutencaoPopulado = new Manutencao();
        manutencaoPopulado.setFuncionario(funcionarioAdicionado);
        manutencaoPopulado.setEquipamento(equipamentoAdicionado);
        manutencaoPopulado.setDataEntrada(Date.from(Instant.now()));
        manutencaoPopulado.setDataUltimaAtualizacao(Date.from(Instant.now()));
        manutencaoPopulado.setProblema("Problema na solda BGA.");

        return manutencaoPopulado;
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

    private Funcionario getFuncionarioPopulado(){
        Funcionario funcionarioPopulado = new Funcionario();
        funcionarioPopulado.setNome("Gabriel Mandelli");
        funcionarioPopulado.setEmail("gabriel@gmail.com");
        funcionarioPopulado.setCargo("Técnico");
        return funcionarioPopulado;
    }

}