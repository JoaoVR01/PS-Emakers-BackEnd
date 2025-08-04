package emakersProjetoBackEnd.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import emakersProjetoBackEnd.data.dto.response.CepResponseDTO;

@Service
public class CepService {

    public CepResponseDTO consultaCep(String cep){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://viacep.com.br/ws/"+ cep + "/json/";
        return restTemplate.getForObject(apiUrl, CepResponseDTO.class);
    }

}
    