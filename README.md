README - Aplicação de Gerenciamento Laboratorial

Descrição
Bem-vindo à nossa aplicação de gerenciamento laboratorial! Esta API, desenvolvida em Java 11 com Spring Boot, tem como objetivo fornecer dados detalhados sobre laboratórios, informações de pessoas e propriedades relacionadas. Utilizamos um banco de dados PostgreSQL para armazenar e recuperar dados de maneira eficiente.

Endpoints
/laboratorio
Este endpoint fornece informações sobre laboratórios. Você pode realizar as seguintes operações:

GET /laboratorio: Recupera a lista de todos os laboratórios cadastrados.

GET /laboratorio/{id}: Recupera detalhes de um laboratório específico com base no ID.

GET /laboratorio/informacoes: Recupera detalhes de laboratórios com mais detalhes e pessoas que fazem parte do mesmo.

POST /laboratorio: Adiciona um novo laboratório ao sistema. Os detalhes devem ser fornecidos no corpo da solicitação.

PUT /laboratorio/{id}: Atualiza os detalhes de um laboratório existente com base no ID.

DELETE /laboratorio/{id}: Remove um laboratório do sistema com base no ID.

/pessoas
Este endpoint fornece informações sobre pessoas relacionadas aos laboratórios:

GET /pessoas: Recupera a lista de todas as pessoas registradas.

GET /pessoas/{id}: Recupera detalhes de uma pessoa específica com base no ID.

POST /pessoas: Adiciona uma nova pessoa ao sistema. Os detalhes devem ser fornecidos no corpo da solicitação.

PUT /pessoas/{id}: Atualiza os detalhes de uma pessoa existente com base no ID.

DELETE /pessoas/{id}: Remove uma pessoa do sistema com base no ID.

/propriedades
Este endpoint fornece informações sobre propriedades relacionadas aos laboratórios:

GET /propriedades: Recupera a lista de todas as propriedades registradas.

GET /propriedades/{id}: Recupera detalhes de uma propriedade específica com base no ID.

POST /propriedades: Adiciona uma nova propriedade ao sistema. Os detalhes devem ser fornecidos no corpo da solicitação.

PUT /propriedades/{id}: Atualiza os detalhes de uma propriedade existente com base no ID.

DELETE /propriedades/{id}: Remove uma propriedade do sistema com base no ID.

Arquitetura
A aplicação segue uma arquitetura RESTful, proporcionando uma comunicação eficiente entre cliente e servidor. O uso do Spring Boot facilita o desenvolvimento e a manutenção, enquanto o banco de dados PostgreSQL garante um armazenamento robusto e confiável.

Gerenciador de Dependências
O Maven é o gerenciador de dependências escolhido para este projeto, garantindo uma fácil configuração e controle de bibliotecas.

Configuração
As configurações da aplicação podem ser encontradas no arquivo application.properties. Certifique-se de ajustar as configurações do banco de dados e outras propriedades conforme necessário.

Agradecemos por escolher nossa aplicação de gerenciamento laboratorial. Sinta-se à vontade para explorar os endpoints e fornecer feedback!  
