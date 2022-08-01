# Desafio 7 Days Of Code Spring

Nesse desafio você vai construir um serviço REST com Spring onde realizará
uma busca no serviço IMDB para listar os TOP 250 filmes. Depois você vai 
armazenar essa lista  localmente para realizar filtros de busca por título,
te guiarei na utilização de paramêtros com query params.

Depois você construirá sua própria lista de filmes favoritos, essa lista será
adicionada via um serviço POST que você criará para buscar via ID o filme que
o seu primeiro endpoint retornou e irá armazená-lo em uma lista de favoritos.

E para finalizar, você criará um outro serviço GET para retornar sua lista de favoritos.

## Se você fizer os extras

Também terá aproximadamente 80% de cobertura de código em testes automatizados, contribuindo para
boas práticas de desenvolvimento de software.

### Linguagem / Framework

Este desafio foi desenvolvido com Java 17 e o framework Spring Boot + dependencia Web.

Versões:
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>2.7.2</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <version>2.7.2</version>
            <scope>test</scope>
        </dependency>
	</dependencies>

## Run

Para rodar este projeto basta ter instalado o maven e o Java no seu computador e executar:
`mvn clean install`
e depois:
`java -jar target/7dayscode-0.0.1-SNAPSHOT.jar`

Ou importe o projeto na sua IDE favorita e rode como uma aplicação Spring, selecionando a classe 
main (Application.class).

## Contato

Para qualquer dúvida ou sugestão fique a vontade para entrar em contato comigo via:
`tino@tech4rh.com.br`.

