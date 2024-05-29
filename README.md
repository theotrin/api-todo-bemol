### 🛠️ Setup de ferramentas: 

Você vai precisar ter instalado no seu computador:
- Java 21

## Setup do Back-end com Java:
![image](https://github.com/theotrin/apresentacao-desafio-bemol/assets/102327842/f4ff6b90-8ec3-4a70-a829-196ef75471a6)

Se você já tiver a versão 21 do Java no seu computador, é simplesmente abrir o IntelliJ, apertar no Play e o servidor estará de pé!

### Endpoints:

### Listar tarefas:
#### `GET` `http://localhost:8080/api/tasks`

#### Exemplo de resposta:
```javascript
// HTTP RESPONSE: 200 OK.
[
  {
    "id": 1,
    "description": "Abrir loja Bemol",
    "completed": true
  },
  {
    "id": 2,
    "description": "Contratar Theo Trindade como estagiário",
    "completed": true
  },
  {
    "id": 3,
    "description": "Checar relatório de vendas",
    "completed": false
  }
]
```
### Adicionar nova tarefa à lista:
#### `POST` `http://localhost:8080/api/tasks`
#### Exemplo de como deve ser enviado o body (em JSON):
```javascript
{
    "description": "Chamar Theo Trindade para fazer parte da equipe" // descrição da nova tarefa
}
```

#### Exemplo de resposta:
```javascript
// HTTP RESPONSE: 201 Created.
```

### Marcar uma tarefa como concluída:
#### `PUT` `http://localhost:8080/api/tasks/id/complete`
Exemplo: 
#### `PUT` `http://localhost:8080/api/tasks/3/complete`

#### Exemplo de resposta:
```javascript
// HTTP RESPONSE: 200 OK.
{
  "id": 2,
  "description": "Contratar Theo Trindade como estagiário",
  "completed": true
}
```
Se você fizer uma nova requisição, a tarefa vai receber o valor contrário do que ela tem no "completed".
### Exemplo
```javascript
// Primeira chamada na API:
{
  "id": 2,
  "description": "Contratar Theo Trindade como estagiário",
  "completed": true // na próxima chamada vai receber o valor false
}
// Segunda chamada na API:
{
  "id": 2,
  "description": "Contratar Theo Trindade como estagiário",
  "completed": false // na próxima chamada vai receber o valor true
}
```
### Deletar uma tarefa da lista:
#### `DELETE` `http://localhost:8080/api/tasks/idDesejado`
Exemplo:
#### `DELETE` `http://localhost:8080/api/tasks/3`

#### Exemplo de resposta:
```javascript
// HTTP RESPONSE: 204 No Content.
```
