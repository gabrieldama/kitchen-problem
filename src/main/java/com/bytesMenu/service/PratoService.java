package com.bytesMenu.service;

import com.bytesMenu.entity.Prato;
import com.bytesMenu.repository.PratoRepository;
import com.bytesMenu.dto.PratoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PratoService {

    @Autowired
    private PratoRepository pratoRepository;

    public Prato criar(PratoRequestDTO dto) {
        Prato prato = Prato.builder()
                .nome(dto.name())
                .descricao(dto.description())
                .preco(dto.price())
                .disponivel(dto.available())
                .build();

        return pratoRepository.save(prato);
    }

    public List<Prato> listarTodos() {
        return pratoRepository.findAll();
    }

    public List<Prato> listarDisponiveis() {
        return pratoRepository.findByDisponivelTrue();
    }

    public Prato buscarPorId(Long id) {
        return pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado com id: " + id));
    }

    public Prato atualizar(Long id, PratoRequestDTO dto) {
        Prato prato = buscarPorId(id);

        prato.setNome(dto.name());
        prato.setDescricao(dto.description());
        prato.setPreco(dto.price());

        return pratoRepository.save(prato);
    }

    public void deletar(Long id) {
        if (!pratoRepository.existsById(id)) {
            throw new RuntimeException("Prato não encontrado com id: " + id);
        }
        pratoRepository.deleteById(id);
    }

    public Prato disponivel(Long id) {
        Prato prato = buscarPorId(id);

        if (!prato.getDisponivel()) {
            prato.setDisponivel(true);
        } else {
            prato.setDisponivel(false);
        }
        return pratoRepository.save(prato);
    }
}