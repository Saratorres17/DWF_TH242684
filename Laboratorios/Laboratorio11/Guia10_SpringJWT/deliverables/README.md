
# Entregable – Guía 10 DWF (Spring Boot JWT)

Este paquete incluye:
- **Proyecto backend**: `spring-jwt-basic/` (tu ZIP original, sin cambios).
- **Cliente web sencillo**: `jwt-client.html` para probar el login y consumir un endpoint protegido.
- **Documento de entrega**: `Guia_10_DWF_Entrega.docx` con objetivos, procedimiento, evidencias, pruebas y referencias.

## Cómo ejecutar
1. Importa el proyecto `spring-jwt-basic` en IntelliJ (Community o Ultimate).
2. Verifica dependencias con Maven (`clean` y `package`).
3. Ejecuta la aplicación (`SpringJwtBasicApplication` en puerto 8080).
4. Abre `deliverables/jwt-client.html` en tu navegador y prueba el login.

## Endpoint protegido opcional
Si tu proyecto no tiene un endpoint protegido, agrega este controlador:

```java
package sv.edu.udb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class HelloSecureController {
    @GetMapping("/hello")
    public String hello() {
        return "Hola, este es un recurso protegido con JWT";
    }
}
```

> Asegúrate que tu `SecurityConfig` siga protegiendo todo excepto `/api/auth/login`.

## Pruebas rápidas (curl)
```bash
# Login (recibe el token como texto plano)
curl -s -X POST http://localhost:8080/api/auth/login   -H "Content-Type: application/json"   -d '{"username":"testuser","password":"testpassword"}'

# Con el token en la variable TOKEN, invocar un protegido
curl -s http://localhost:8080/api/secure/hello -H "Authorization: Bearer $TOKEN"
```
