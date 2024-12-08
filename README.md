# Criptografia em um serviço de forma transparente para a API

Uma aplicação Spring Boot demonstrando criptografia transparente em nível de campo para dados sensíveis em entidades JPA.

## Visão Geral

Este serviço implementa um sistema seguro de informações de pagamento com criptografia automática de campos sensíveis, como documentos de usuários e tokens de cartão de crédito. A criptografia é tratada de forma transparente usando conversores de atributos JPA, tornando-a integrada para as camadas da aplicação.

## Recursos

- Criptografia/descriptografia transparente em nível de campo
- API RESTful para gerenciamento de informações de pagamento
- Criptografia AES para dados sensíveis
- Banco de dados H2 em memória
- Anotação personalizada (`@Encrypted`) para marcar campos sensíveis

## Pré-requisitos

- Java 11 ou superior
- Maven 3.6 ou superior

## Estrutura do projeto

```
src/main/java/com/example/
├── Application.java
├── controller
│   └── PaymentInfoController.java
├── encryption
│   ├── EncryptionService.java
│   ├── annotation
│   │   └── Encrypted.java
│   └── converter
│       └── AttributeEncryptor.java
├── model
│   └── PaymentInfo.java
├── repository
│   └── PaymentInfoRepository.java
└── service
    └── PaymentInfoService.java
```

## Getting Started

1. Clone the repository
2. Build the project:
   ./mvnw clean install
3. Run the application:
   ./mvnw spring-boot:run

## API Endpoints

### Criar informações de pagamento

curl -X POST http://localhost:8080/api/payments \
  -H "Content-Type: application/json" \
  -d '{
    "userDocument": "36140781833",
    "creditCardToken": "abc123",
    "value": 5999
  }'

### Pegar todos os pagamentos

curl http://localhost:8080/api/payments


### Pegar pagamentos por ID

curl http://localhost:8080/api/payments/{id}

### Atualizar pagamento

curl -X PUT http://localhost:8080/api/payments/{id} \
  -H "Content-Type: application/json" \
  -d '{
    "userDocument": "36140781833",
    "creditCardToken": "abc123",
    "value": 5999
  }'

### Deletar pagamento

curl -X DELETE http://localhost:8080/api/payments/{id}


## Detalhes de implementação

### Encryption

A criptografia é implementada usando AES (Advanced Encryption Standard) com os seguintes componentes:

1. Anotação @Encrypted: Marca campos que devem ser criptografados
2. EncryptionService: Responsável pelas operações de criptografia/descriptografia
3. AttributeEncryptor: Conversor JPA que criptografa/descriptografa automaticamente os campos marcados

### Tabela de bancos de dados

sql
CREATE TABLE payment_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_document VARCHAR(255),
    credit_card_token VARCHAR(255),
    value BIGINT
);

Os campos criptografados (user_document e credit_card_token) são armazenados em sua forma criptografada no banco de dados.

## Consideração de segurança

- A chave de criptografia é armazenada de forma segura e não é comprometida no controle de versão
- Todos os dados sensíveis são criptografados em repouso
- O serviço usa HTTPS para transmissão de dados (configure em produção)
- A validação de entrada é implementada para prevenir ataques de injeção

## Testando

Execute os testes usando:

./mvnw test


## Deploy

Para implantação em produção:

1. Configure uma chave de criptografia segura
2. Use um banco de dados de produção
3. Habilite HTTPS
4. Configure autenticação e autorização adequadas
5. Configure monitoramento e registro de logs


## Contributing

1. Faça um fork do repositório
2. Crie uma branch de recurso
3. Comite suas mudanças
4. Envie para a branch
5. Crie um Pull Request
