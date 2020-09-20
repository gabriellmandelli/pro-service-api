package com.proService.maintanance.rules.service;

import com.proService.maintanance.rules.domain.entity.Funcionario;
import com.proService.maintanance.rules.domain.repository.FuncionarioRepository;
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
class FuncionarioServiceTest {


    @InjectMocks
    @Autowired
    private FuncionarioService funcionarioService;

    @Mock
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {

        Funcionario funcionarioPopulado = getFuncionarioPopulado();
        Funcionario funcionarioAdicionado = funcionarioService.save(funcionarioPopulado);

        Assert.assertEquals(funcionarioPopulado.getNome(), funcionarioAdicionado.getNome());
        Assert.assertEquals(funcionarioPopulado.getEmail(), funcionarioAdicionado.getEmail());
        Assert.assertEquals(funcionarioPopulado.getCargo(), funcionarioAdicionado.getCargo());
    }

    @Test
    void update() {
        Funcionario funcionarioAdicionado = funcionarioService.save(getFuncionarioPopulado());
        funcionarioAdicionado.setNome("João");
        Funcionario funcionarioAtualizado = funcionarioService.update(funcionarioAdicionado);

        Assert.assertSame(funcionarioAtualizado.getNome(), funcionarioAdicionado.getNome());
    }

    @Test
    void findById(){
        Funcionario funcionarioAdicionado = funcionarioService.save(getFuncionarioPopulado());
        Funcionario funcionarioBusca = funcionarioService.findById(funcionarioAdicionado.getId()).get();
        Assert.assertEquals(funcionarioBusca.getId(), funcionarioAdicionado.getId());
    }

    @Test
    void findAll() {
        funcionarioService.save(getFuncionarioPopulado());
        Assert.assertFalse(funcionarioService.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        Funcionario funcionarioAdicionado = funcionarioService.save(getFuncionarioPopulado());
        Assert.assertTrue(funcionarioService.findById(funcionarioAdicionado.getId()).isPresent());
        funcionarioService.deleteById(funcionarioAdicionado.getId());
        Optional<Funcionario> funcionarioBusca = funcionarioService.findById(funcionarioAdicionado.getId());
        Assert.assertFalse(funcionarioBusca.isPresent());
    }

    private Funcionario getFuncionarioPopulado(){
        Funcionario funcionarioPopulado = new Funcionario();
        funcionarioPopulado.setNome("Gabriel Mandelli");
        funcionarioPopulado.setEmail("gabriel@gmail.com");
        funcionarioPopulado.setCargo("Técnico");
        return funcionarioPopulado;
    }

}