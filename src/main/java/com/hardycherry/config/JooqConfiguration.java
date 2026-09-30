package com.hardycherry.config;

import org.jooq.SQLDialect;
import org.jooq.conf.MappedSchema;
import org.jooq.conf.RenderMapping;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JooqConfiguration {

    @Autowired
    DataSource dataSource;

    public DefaultConfiguration jooqConfiguration() {
        DefaultConfiguration config = new DefaultConfiguration();
        config.set(SQLDialect.H2);
        config.setDataSource(dataSource);
        config.set(new Settings()
                .withRenderSchema(false)
                .withRenderMapping(new RenderMapping().withSchemata(new MappedSchema().withInput("public").withOutput("public"))));
        return config;
    }

    @Bean
    public DefaultDSLContext dslContext() {
        return new DefaultDSLContext(jooqConfiguration());
    }
}
