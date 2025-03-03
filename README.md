# A Sample Spring AI Project
This is a sample Spring AI Project using Ollama with RAG, advisors, stateful chat, function calling and structured output capabilities. 

You can visit [my Medium post](https://medium.com/@senoritadeveloper/spring-ai-with-ollama-and-pgvector-da5bd0afbb3a) to read the details about the implementation.

The project is implemented with Java 21, Spring Boot 3.4.1, Spring AI 1.0.0-M5 and Postgres PGvector (as a VectorDB for RAG) .

[![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white)](#)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff)](#)
[![Postgres](https://img.shields.io/badge/Postgres-%23316192.svg?logo=postgresql&logoColor=white)](#)

## Screenshots
<img src="https://raw.githubusercontent.com/senoritadeveloper01/nils-spring-ai/main/screenshots/postman-sc-01.png" width="500" />

<img src="https://raw.githubusercontent.com/senoritadeveloper01/nils-spring-ai/main/screenshots/postman-sc-02.png" width="500" />

<img src="https://raw.githubusercontent.com/senoritadeveloper01/nils-spring-ai/main/screenshots/postman-sc-03.png" width="500" />

<img src="https://raw.githubusercontent.com/senoritadeveloper01/nils-spring-ai/main/screenshots/postman-sc-04.png" width="500" />

<img src="https://raw.githubusercontent.com/senoritadeveloper01/nils-spring-ai/main/screenshots/postman-sc-05.png" width="500" />

<img src="https://raw.githubusercontent.com/senoritadeveloper01/nils-spring-ai/main/screenshots/postman-sc-06.png" width="500" />

<img src="https://raw.githubusercontent.com/senoritadeveloper01/nils-spring-ai/main/screenshots/postman-sc-07.png" width="500" />

<img src="https://raw.githubusercontent.com/senoritadeveloper01/nils-spring-ai/main/screenshots/postman-sc-08.png" width="500" />

<img src="https://raw.githubusercontent.com/senoritadeveloper01/nils-spring-ai/main/screenshots/postman-sc-09.png" width="500" />

<img src="https://raw.githubusercontent.com/senoritadeveloper01/nils-spring-ai/main/screenshots/postman-sc-10.png" width="500" />

<img src="https://raw.githubusercontent.com/senoritadeveloper01/nils-spring-ai/main/screenshots/postman-sc-11.png" width="500" />

## Installation
##### Ollama
From [Ollama download page](https://ollama.com/download), download and install the app. 

You can then pull and run "llama3.2" model with the following command:
```sh
ollama run llama3.2
```

##### PGvector
You can install PostgreSQL with Homebrew. You can follow [this tutorial from Datacamp](https://www.datacamp.com/tutorial/brew-install-postgres) step by step. Do not skip the step that adds PostgreSQL to the system path.

Create your superuser and connect to PostgreSQL (I did not give my super user a password):
```sh
createuser -s postgres
```
```sh
psql postgres
```

Now, you should install PGvector with Homebrew.
```sh
brew install pgvector
```

After that, connect to PostgreSQL (if you are not connected) and then run the following command:
```sh
CREATE EXTENSION vector;
```

You can also install PgAdmin (a management tool for PostgreSQL) with Homebrew. You can follow the instructions in [this post](https://dev.to/celestialchips/setting-up-pgadmin4-with-homebrew-1pp0). You can use it as a GUI to inspect your tables and records.

## Usage
You can use Postman Collection (located under "postman" folder) to test APIs.

## Reference Projects:
These are the reference projects used during implementation:
- https://github.com/ThomasVitale/llm-apps-java-spring-ai
- https://github.com/eugenp/tutorials/tree/master/spring-ai
- https://github.com/spring-petclinic/spring-petclinic-ai
- https://github.com/danvega/spring-ai-workshop
- https://github.com/l-trotta/rag-with-java-springai-elasticsearch.git

## Contributors

<img src="https://readme-typing-svg.demolab.com?font=Open+Sans&size=16&pause=1000&color=A6F73F&height=50&width=200&lines=Nil+Seri"/>

[Github 1](https://github.com/senoritadeveloper01)

[Github 2](https://github.com/nilseri01)

[Medium](https://senoritadeveloper.medium.com/)

## Copyright & Licensing Information

This project is licensed under the terms of the MIT license.