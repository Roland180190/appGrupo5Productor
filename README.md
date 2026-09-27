<div align="center">

# 📤 App Grupo 5 Productor

### Publicador de números enteros hacia RabbitMQ

<p>
  <img src="https://img.shields.io/badge/Java-25-orange?logo=openjdk" alt="Java 25">
  <img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen?logo=springboot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring%20Cloud-2025.1.3-blue?logo=spring" alt="Spring Cloud">
  <img src="https://img.shields.io/badge/RabbitMQ-3.13-ff6600?logo=rabbitmq" alt="RabbitMQ">
</p>

</div>

---

## 🎯 Objetivo

Este proyecto implementa el **productor RabbitMQ** del Grupo 5 para la evaluación T1 de **Desarrollo de Aplicaciones Web II – Cibertec**.

Su función es recibir una lista de números enteros mediante una API REST y publicarla en RabbitMQ para que el consumidor pueda procesarla y ordenarla.

## ⚙️ Funcionamiento

```mermaid
sequenceDiagram
    participant Cliente
    participant Productor
    participant Exchange as RabbitMQ Exchange
    participant Queue as Grupo5Queue
    participant Consumidor

    Cliente->>Productor: GET /api/numbers?numbers=9;3;15;1;8
    Productor->>Exchange: Publica la cadena de números
    Exchange->>Queue: Enruta con Grupo5Routing
    Queue->>Consumidor: Entrega el mensaje
    Productor-->>Cliente: Lista enviada a RabbitMQ correctamente.
```

## 🧰 Tecnologías utilizadas

| Tecnología | Versión |
|---|---|
| Java | 25 |
| Spring Boot | 4.1.1 |
| Spring Cloud | 2025.1.3 |
| Spring AMQP | Compatible con Spring Boot |
| RabbitMQ | 3.13 |
| Maven | 3.9+ |
| Docker Compose | 2.x |

## 🌐 Endpoint REST

### Enviar números a RabbitMQ

```http
GET /api/numbers?numbers=9;3;15;1;8
```

URL completa:

```text
http://localhost:8081/api/numbers?numbers=9;3;15;1;8
```

En Windows CMD:

```cmd
curl "http://localhost:8081/api/numbers?numbers=9%3B3%3B15%3B1%3B8"
```

Respuesta exitosa:

```text
Lista enviada a RabbitMQ correctamente.
```

El parámetro `numbers` debe contener números enteros separados por punto y coma:

```text
10;4;25;1;8
```

## 🐇 Configuración RabbitMQ

| Configuración | Valor |
|---|---|
| Queue | `Grupo5Queue` |
| Exchange | `Grupo5Exchange` |
| Routing Key | `Grupo5Routing` |
| Tipo de Exchange | Direct |
| Host | `localhost` |
| Puerto | `5672` |
| Usuario | `guest` |
| Contraseña | `guest` |

## 🐳 Ejecutar RabbitMQ con Docker

Iniciar el contenedor:

```bash
docker compose up -d
```

Verificar el estado:

```bash
docker compose ps
```

Panel de administración:

```text
http://localhost:15672
```

Credenciales:

```text
Usuario: guest
Contraseña: guest
```

Detener RabbitMQ:

```bash
docker compose down
```

## ▶️ Ejecutar el productor

Con RabbitMQ iniciado, ejecutar:

```bash
mvn spring-boot:run
```

La aplicación estará disponible en:

```text
http://localhost:8081
```

## 📦 Estructura del proyecto

```text
src
└── main
    ├── java
    │   └── pe.cibertec.grupo5.productor
    │       ├── AppGrupo5ProductorApplication.java
    │       ├── config
    │       │   └── RabbitMqConfig.java
    │       └── controller
    │           └── NumbersController.java
    └── resources
        └── application.properties
```

## 🔑 Componentes principales

### `NumbersController`

Expone el endpoint REST, recibe el parámetro `numbers` y publica el mensaje en RabbitMQ.

### `RabbitMqConfig`

Define la cola, el exchange y la clave de enrutamiento utilizada por el productor y el consumidor.

### `RabbitTemplate`

Permite enviar la cadena de números al exchange configurado:

```java
rabbitTemplate.convertAndSend(
        RabbitMqConfig.EXCHANGE_NAME,
        RabbitMqConfig.ROUTING_KEY,
        numbers
);
```

## ✅ Validación del flujo

Para validar la comunicación completa:

1. Iniciar RabbitMQ con Docker.
2. Ejecutar el consumidor.
3. Ejecutar este productor.
4. Enviar una lista mediante el endpoint.
5. Revisar la salida ordenada en los logs del consumidor.

Ejemplo:

```text
Entrada: 9;3;15;1;8
Salida:  [1, 3, 8, 9, 15]
```

## 👤 Aporte personal

Este repositorio contiene la implementación del productor RabbitMQ del Grupo 5, incluyendo:

- Creación del endpoint REST.
- Validación del parámetro `numbers`.
- Configuración de RabbitMQ.
- Publicación de mensajes mediante `RabbitTemplate`.
- Configuración de Docker Compose.
- Integración con el consumidor del proyecto.

---

<div align="center">

### 🚀 Grupo 5 | Productor RabbitMQ

</div>
