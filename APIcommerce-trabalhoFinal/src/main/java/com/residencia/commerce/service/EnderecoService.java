package com.residencia.commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.commerce.dto.ApiCepDTO;
import com.residencia.commerce.dto.EnderecoDTO;
import com.residencia.commerce.entity.Endereco;
import com.residencia.commerce.exception.CepException;
import com.residencia.commerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	ViaCepService servicoCep;

	public List<Endereco> findAllEndereco() {
		return enderecoRepository.findAll();
	}

	public Endereco findEnderecoById(Integer id) {
		return enderecoRepository.findById(id).isPresent() ? enderecoRepository.findById(id).get() : null;
	}

	public Endereco saveEndereco(Endereco endereco) {

		if (endereco.getCepEndereco().isEmpty() || endereco.getCepEndereco().length() < 8) {
			throw new CepException("Cep Invalido");
		}
		
		String cep = endereco.getCepEndereco();
		ApiCepDTO cepRetornado = servicoCep.consultar(cep);

		endereco.setBairroEndereco(cepRetornado.getBairro());
		endereco.setRuaEndereco(cepRetornado.getLogradouro());
		endereco.setUfEndereco(cepRetornado.getUf());
		endereco.setCidadeEndereco(cepRetornado.getLocalidade());

		return enderecoRepository.save(endereco);
	}

	public Endereco updateEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public void deleteEnderecoById(Integer id) {
		Endereco endereco = enderecoRepository.findById(id).get();
		enderecoRepository.delete(endereco);
	}

	public Endereco ConverteDTOToEntidade(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();

		endereco.setBairroEndereco(enderecoDTO.getBairroEndereco());
		endereco.setCepEndereco(enderecoDTO.getCepEndereco());
		endereco.setCidadeEndereco(enderecoDTO.getCidadeEndereco());
		endereco.setComplemetnoEndereco(enderecoDTO.getComplemetnoEndereco());
		endereco.setIdEndereco(enderecoDTO.getIdEndereco());
		endereco.setNumeroEndereco(enderecoDTO.getNumeroEndereco());
		endereco.setRuaEndereco(enderecoDTO.getRuaEndereco());
		endereco.setUfEndereco(enderecoDTO.getUfEndereco());

		return endereco;
	}
}
