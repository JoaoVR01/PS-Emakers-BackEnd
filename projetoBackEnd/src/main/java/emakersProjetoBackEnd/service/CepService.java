package emakersProjetoBackEnd.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import emakersProjetoBackEnd.data.dto.response.CepResponseDTO;
import emakersProjetoBackEnd.exceptions.cep.InvalidCepException;

@Service
public class CepService {

    public CepResponseDTO consultaCep(String cep){

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://viacep.com.br/ws/"+ cep + "/json/";
        CepResponseDTO response = restTemplate.getForObject(apiUrl, CepResponseDTO.class);
        
        if(response == null || Boolean.TRUE.equals(response.erro())) {
            throw new InvalidCepException();
        }

        return response;
    }

}
    