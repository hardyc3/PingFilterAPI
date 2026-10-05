**AI Usage**
- Used to review warnings in the Filter class caused by the parameterized class use
  - prompt: "Take a look at the code in this repo and give me some suggestions to fix the compiler warnings due to the type erasure that's happening in some places like the Filter class"
- duckduckgo ai to help compose the docker and docker-compose files
- duckduckgo ai feeding it logs and error messages to debug why the database wasn't getting populated in the docker container the same way it was in intellij. Ultimately I needed to add a dependency I was missing in the pom file.
- duckduckgo ai help getting the default datasource in grafana running along with a dashboard that I downloaded from Grafana Labs (https://grafana.com/grafana/dashboards/19004-spring-boot-statistics/) 

**Running**
- From Intellij
  - Add a maven run configuration with the command `spring-boot:run`
- From the CLI
  - run the command `mvn flyway:clean flyway:repair flyway:migrate jooq-codegen:generate -Dflyway.cleanDisabled=false -Dskip.jooq.generation=false`
  - next run the command `mvn spring-boot:run`
- Full app with grafana, prometheus and redis:
  - docker network create shared_network
  - docker compose up --build
- Swagger URL : http://localhost:8000/api/swagger-ui/index.html
- Grafana URL : http://localhost:3000/d/spring_boot_21/spring-boot-3-x-statistics?from=now-5m&to=now&timezone=browser&var-application=&var-Namespace=&var-instance=pingfilterapi-filterapi-1:8000&var-hikaricp=HikariPool-1&var-memory_pool_heap=$__all&var-memory_pool_nonheap=$__all
  - user: admin
  - pass: admin
  - skip updating the password

**Testing**
- Unit tests: the unit test package contains tests for the FilterAPI
- Adding tests: Start with the Filter class, use the builder() method.

