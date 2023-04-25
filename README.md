# authenticator-api
Api de Autenticação JWT com cadastro de usuario

Utilizando Hibernate, Mapper e Liberado o Cors

Para aplicação funcionar é necessario criar um banco de dados postgresql conforme esta no application.properties.

Para realizar o login: 
```
POST - 127.0.0.1:8098/login
```

request: 
```
{
"userName":"authenticator",
"password":"123"
}
```

response:
```
{
"access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcHVyYW1haXMiLCJjbGllbnRJZCI6MywidG9rZW5FeHBpcmF0aW9uIjoxNjgyMDE0NDE5LCJuYW1lIjoiRWR1YXJkbyBNb3VyYSIsInVzZXJUeXBlIjoiR0VTVE9SIn0.2AQHLO1ykrtbNF7ONsKrdKsprUPejpWjaeaUQ8xgz9e9_hUetc0gZUSCMY59C7MFdTJSwo9rF1ej56WADF0SbQ"
}
```


