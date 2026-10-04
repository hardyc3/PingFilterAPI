**AI Usage**
- Used to review warnings in the Filter class caused by the parameterized class use
  - prompt: "Take a look at the code in this repo and give me some suggestions to fix the compiler warnings due to the type erasure that's happening in some places like the Filter class"
- duckduckgo ai to help compose the docker and docker-compose files

**Running**
- From Intellij
  - Add a maven run configuration with the command `spring-boot:run`
- From the CLI
  - run the command `mvn flyway:clean flyway:repair flyway:migrate jooq-codegen:generate -Dflyway.cleanDisabled=false -Dskip.jooq.generation=false`
  - next run the command `mvn spring-boot:run`

**Testing**
- Unit tests: the unit test package contains tests for the FilterAPI
- Adding tests: Start with the Filter class, use the builder() method.