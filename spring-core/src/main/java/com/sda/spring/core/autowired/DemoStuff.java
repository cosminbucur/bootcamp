package com.sda.spring.core.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoStuff {

    public static void main(String[] args) {
        ApplicationContext appContext =
                new AnnotationConfigApplicationContext(BookConfig.class);

        BookController controller = appContext.getBean(BookController.class);
        controller.post();
    }
}
