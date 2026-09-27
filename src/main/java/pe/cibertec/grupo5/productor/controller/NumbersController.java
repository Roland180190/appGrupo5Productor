package pe.cibertec.grupo5.productor.controller;

import pe.cibertec.grupo5.productor.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/numbers")
public class NumbersController {

    private final RabbitTemplate rabbitTemplate;

    public NumbersController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String sendNumbers(@RequestParam String numbers) {
        if (numbers == null || numbers.isBlank()) {
            throw new IllegalArgumentException("El parámetro numbers no puede estar vacío");
        }

        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE_NAME,
                RabbitMqConfig.ROUTING_KEY,
                numbers
        );

        return "Lista enviada a RabbitMQ correctamente.";
    }
}
