# Sistema de Consultas Médicas - Projeto Tomorrow

## 📋 Descrição

Sistema de gerenciamento de consultas médicas desenvolvido em Spring Boot como parte do curso na UFBA - Projeto Tomorrow. O sistema permite o cadastro de médicos e pacientes, agendamento de consultas, controle de status e funcionalidades de reconsulta.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **Spring Security**
- **JWT (JSON Web Token)**
- **H2 Database** (em memória)
- **Lombok**
- **SpringDoc OpenAPI** (Swagger)
- **Maven**

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```
src/main/java/projeto_tomorrow/demo/
├── Controller/          # Controladores REST
├── Service/            # Lógica de negócio
├── Repository/         # Acesso a dados
├── Entity/            # Entidades JPA
├── DTO/               # Data Transfer Objects
├── security/          # Configurações de segurança
├── Exceptions/        # Tratamento de exceções
└── config/           # Configurações da aplicação
```

## 📊 Modelo de Dados

### Entidades Principais

- **User**: Usuário base do sistema (médico, paciente ou admin)
- **PerfilMedico**: Perfil específico para médicos com especialidade
- **PerfilPaciente**: Perfil específico para pacientes com dados pessoais
- **Consulta**: Consulta médica com relacionamentos entre médico e paciente

### Enums

- **UserRole**: ADMIN, MEDICO, PACIENTE
- **StatusConsulta**: AGENDADA, REALIZADA, CONCLUIDA, CANCELADA, RECONSULTA
- **EspecialidadeMedica**: 12 especialidades médicas disponíveis
- **Convenio**: UNIMED, BRADESCO_SAUDE, AMIL, SULAMERICA, HAPVIDA, PARTICULAR

## 🔐 Segurança

- Autenticação via JWT
- Endpoints protegidos por Spring Security
- Criptografia de senhas com BCrypt
- Filtro de segurança personalizado

## 📡 API Endpoints

### Autenticação
- `POST /auth/login` - Login do usuário

### Consultas
- `POST /consulta` - Criar nova consulta
- `GET /consulta` - Listar todas as consultas
- `GET /consulta/{id}` - Buscar consulta por ID
- `PUT /consulta/{id}` - Atualizar consulta
- `PUT /consulta/confirmarConsulta/{id}` - Confirmar consulta
- `PUT /consulta/concluirConsulta/{id}` - Concluir consulta
- `PUT /consulta/cancelarConsulta/{id}` - Cancelar consulta
- `POST /consulta/reconsulta/{id}` - Marcar reconsulta
- `GET /consulta/status?status={status}` - Buscar por status
- `DELETE /consulta/{id}` - Deletar consulta

### Médicos
- Endpoints para gerenciamento de perfis médicos

### Pacientes
- Endpoints para gerenciamento de perfis de pacientes

## 🛠️ Como Executar

### Pré-requisitos
- Java 21
- Maven 3.6+

### Passos

1. **Clone o repositório**
```bash
git clone <url-do-repositorio>
cd demo
```

2. **Execute a aplicação**
```bash
mvn spring-boot:run
```

3. **Acesse a aplicação**
- API: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console
- Swagger UI: http://localhost:8080/swagger-ui.html

### Configuração do H2

- URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (vazio)

## 📝 Funcionalidades

### Gestão de Consultas
- ✅ Agendamento de consultas
- ✅ Controle de status (Agendada → Realizada → Concluída)
- ✅ Cancelamento de consultas
- ✅ Sistema de reconsultas
- ✅ Filtros por status
- ✅ Observações nas consultas

### Gestão de Usuários
- ✅ Cadastro de médicos com especialidades
- ✅ Cadastro de pacientes com convênios
- ✅ Sistema de autenticação JWT
- ✅ Controle de acesso por roles

### Recursos Técnicos
- ✅ Validação de dados
- ✅ Tratamento global de exceções
- ✅ Documentação automática da API
- ✅ Inicialização de dados de teste

## 🔄 Fluxo de Status das Consultas

```
AGENDADA → REALIZADA → CONCLUIDA
    ↓
CANCELADA
    ↓
RECONSULTA (nova consulta vinculada à original)
```


## 📚 Documentação da API

Após executar a aplicação, acesse:
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## 🤝 Contribuição

Este é um projeto educacional desenvolvido como parte do curso na UFBA - Projeto Tomorrow.

## 📄 Licença

Projeto desenvolvido para fins educacionais.

---

**Desenvolvido com ❤️ para o Projeto Tomorrow - UFBA**