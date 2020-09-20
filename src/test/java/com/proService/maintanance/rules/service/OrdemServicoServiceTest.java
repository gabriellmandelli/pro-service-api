package com.proService.maintanance.rules.service;

import com.proService.maintanance.rules.domain.entity.*;
import com.proService.maintanance.rules.domain.enums.SituacaoManutencao;
import com.proService.maintanance.rules.domain.repository.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class OrdemServicoServiceTest {

    @InjectMocks
    @Autowired
    private OrdemServicoService ordemServicoService;

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
    private OrdemServicoRespository ordemServicoRespository;

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

    @Test
    void save() {
        OrdemServico ordemServico = getOrdemServicoPopulado();
        OrdemServico ordemServicoAdicionado = ordemServicoService.save(ordemServico);

        Assert.assertEquals(ordemServico.getManutencao().getId(), ordemServicoAdicionado.getManutencao().getId());
        Assert.assertEquals(ordemServico.getDataOcorrencia(), ordemServicoAdicionado.getDataOcorrencia());
        Assert.assertEquals(ordemServico.getDescricao(), ordemServicoAdicionado.getDescricao());
        Assert.assertEquals(ordemServico.getSituacao(), ordemServicoAdicionado.getSituacao());
    }

    @Test
    void findById(){
        OrdemServico ordemServicoAdicionado = ordemServicoService.save(getOrdemServicoPopulado());
        OrdemServico ordemServicoBusca = ordemServicoService.findById(ordemServicoAdicionado.getId()).get();
        Assert.assertEquals(ordemServicoBusca.getId(), ordemServicoAdicionado.getId());
    }

    @Test
    void findAll() {
        ordemServicoService.save(getOrdemServicoPopulado());
        Assert.assertFalse(ordemServicoService.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        OrdemServico ordemServicoAdicionado = ordemServicoService.save(getOrdemServicoPopulado());
        Assert.assertTrue(ordemServicoService.findById(ordemServicoAdicionado.getId()).isPresent());
        ordemServicoService.deleteById(ordemServicoAdicionado.getId());
        Optional<OrdemServico> ordemServicoBusca = ordemServicoService.findById(ordemServicoAdicionado.getId());
        Assert.assertFalse(ordemServicoBusca.isPresent());
    }

    @Test
    void findByManutencaoFuncionarioId() {
        OrdemServico ordemServicoAdicionado = ordemServicoService.save(getOrdemServicoPopulado());
        List<OrdemServico> ordemServicoList = ordemServicoService.findByManutencaoFuncionarioId(ordemServicoAdicionado.getManutencao().getFuncionario().getId());
        Assert.assertFalse(ordemServicoList.isEmpty());
    }

    @Test
    void findByManutencaoId() {
        OrdemServico ordemServicoAdicionado = ordemServicoService.save(getOrdemServicoPopulado());
        List<OrdemServico> ordemServicoList = ordemServicoService.findByManutencaoId(ordemServicoAdicionado.getManutencao().getId());
        Assert.assertFalse(ordemServicoList.isEmpty());
    }

    private OrdemServico getOrdemServicoPopulado(){
        Manutencao manutencaoAdicionado = manutencaoService.save(getManutencaoPopulado());

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setManutencao(manutencaoAdicionado);
        ordemServico.setDataOcorrencia(Date.from(Instant.now()));
        ordemServico.setDescricao("Levado placa para limpeza.");
        ordemServico.setSituacao(SituacaoManutencao.INICIADO);

        return ordemServico;
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