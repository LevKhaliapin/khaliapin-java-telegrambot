package org.telbot.telran.info;

/*
1. User - ()
2. Channel list ()
3. Schedule for every user
4. Events for every user
5. Statistic block

// сделать краткое описание API (свагер можно использовать, библиотека для паблика)

 */
/*
https://github.com/rubenlagus/TelegramBots/blob/master/telegrambots-spring-boot-starter/src/main/java/org/telegram/telegrambots/starter/TelegramBotStarterConfiguration.java

 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telbot.telran.info.configuration.BotConfiguration;

@SpringBootApplication
@Import(BotConfiguration.class)
@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
