package com.genspark.spring.item.utils;

import com.genspark.spring.item.domain.Detail;
import com.genspark.spring.item.domain.Review;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;


public class HibernateAnnotationUtil {

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private HibernateAnnotationUtil() {}

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    private static SessionFactory buildSessionFactory() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(dbSettings())
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Detail.class)
                .addAnnotatedClass(Review.class)
                .buildMetadata();

        return metadata.buildSessionFactory();
    }

    private static Map<String, String> dbSettings() {
        Map<String, String> dbSettings = new HashMap<>();
        dbSettings.put(Environment.URL, "jdbc:mysql://localhost:3306/item_db");
        dbSettings.put(Environment.USER, "dev");
        dbSettings.put(Environment.PASS, "@devPass1");
        dbSettings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        dbSettings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        dbSettings.put(Environment.SHOW_SQL, "true");
        dbSettings.put(Environment.HBM2DDL_AUTO, "create");
        return dbSettings;
    }
}