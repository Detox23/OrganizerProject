package com.example.src;

import com.example.src.entities.GreetingDb;
import com.example.src.repositories.GreetingRepository;
import org.fastnate.generator.EntitySqlGenerator;
import org.fastnate.generator.context.GeneratorContext;
import org.fastnate.generator.dialect.H2Dialect;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("does-not-exist")
public class DatabaseInitializer implements ApplicationRunner {

    private final GreetingRepository greetingRepository;

    public DatabaseInitializer(GreetingRepository greetingRepository){
        this.greetingRepository = greetingRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> greetings = new ArrayList<>();
        greetings.add("Hello!");
        greetings.add("Good morning.");
        greetings.add("Good afternoon.");
        greetings.add("Good evening.");
        greetings.add("It's nice to meet you.");
        greetings.add("It's a pleasure to meet you.");
        List<GreetingDb> greetingList = greetings.stream().map(greeting -> new GreetingDb(greeting)).collect(Collectors.toList());
        this.generateInsertSql(greetingList);
    }

    public <E> void generateInsertSql(final Iterable<? extends E> entities) throws IOException{
        PrintWriter printWriter = new PrintWriter(System.out);
        EntitySqlGenerator sqlGenerator = new EntitySqlGenerator(new GeneratorContext(new H2Dialect()), printWriter);
        sqlGenerator.write(entities);
        sqlGenerator.flush();
        sqlGenerator.close();
        printWriter.close();
    }
}
