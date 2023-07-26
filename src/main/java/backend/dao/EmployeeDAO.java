package backend.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import backend.model.Employee;

@Repository
public class EmployeeDAO {

    private RestTemplate restTemplate;
    private final String BASE_URL = "https://dummy.restapiexample.com/api/v1";

    @Autowired
    public EmployeeDAO(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public List<Employee> getAllEmployees() {
        String url = BASE_URL + "/employees";
        System.out.println(url);

        try {
            ResponseEntity<ApiResponse<List<Employee>>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<List<Employee>>>() {}
            );
            if (response.getBody() != null) {
                return response.getBody().getData();
            } else {
                // Puedes lanzar una excepción personalizada aquí si lo deseas
                throw new IllegalStateException("La respuesta del servidor no contiene datos.");
            }
        } catch (RestClientException e) {
            // Manejar excepciones relacionadas con la comunicación con el servidor (por ejemplo, problemas de red)
            e.printStackTrace();
            // Puedes lanzar una excepción personalizada aquí si lo deseas
            throw new IllegalStateException("Error al obtener los empleados. Por favor, inténtalo de nuevo más tarde.");
        }
    }

    public Employee getEmployeeById(int employeeId) {
        String url = BASE_URL + "/employee/" + employeeId;

        try {
            ResponseEntity<ApiResponse<Employee>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<Employee>>() {}
            );
            if (response.getBody() != null) {
                return response.getBody().getData();
            } else {
                // Puedes lanzar una excepción personalizada aquí si lo deseas
                throw new IllegalStateException("La respuesta del servidor no contiene datos.");
            }
        } catch (RestClientException e) {
            // Manejar excepciones relacionadas con la comunicación con el servidor (por ejemplo, problemas de red)
            e.printStackTrace();
            // Puedes lanzar una excepción personalizada aquí si lo deseas
            throw new IllegalStateException("Error al obtener el empleado. Por favor, inténtalo de nuevo más tarde.");
        }
    }
}