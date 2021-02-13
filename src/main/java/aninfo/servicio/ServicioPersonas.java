package aninfo.servicio;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aninfo.modelo.Persona;

@Service
public class ServicioPersonas {

    public Collection<Persona> obtenerPersonas() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Persona[]> personas = restTemplate.getForEntity(
                "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos",
                Persona[].class);
        return Arrays.asList(personas.getBody());
    }

}
