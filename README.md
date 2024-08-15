
### Build the Project
```sh
./gradlew build
```

### Run the Application
```sh
./gradlew bootRun
```

## Endpoints

### Greeting Endpoints
- **GET /hello-world**: Returns "Hello World" or a 403 Forbidden error based on the `throwException` parameter.
- **POST /hello-world**: Returns "Hello World" or a 403 Forbidden error based on the `throwException` parameter.
- **GET /hello-world-1**: Always throws an `IllegalStateException`.

### Error Handling
Custom error handling is implemented in the `ErrorController` class, which extends `AbstractErrorController` to provide detailed error responses.

## Reference Documentation
For further reference, please consider the following sections:
- [Official Gradle documentation](https://docs.gradle.org)
- [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.2/gradle-plugin)
- [Create an OCI image](https://docs.spring.io/spring-boot/3.3.2/gradle-plugin/packaging-oci-image.html)
- [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:
- [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
```
