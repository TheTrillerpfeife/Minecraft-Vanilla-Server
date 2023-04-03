package de.benjosua.minecraftserverwrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class MinecraftServerWrapperApplication {

    public static Logger logger = LoggerFactory.getLogger(MinecraftServerWrapperApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MinecraftServerWrapperApplication.class, args);
        startMinecraft();
        while (true);
    }

    public static void startMinecraft() {
        logger.info("Starting Minecraft Server");
        ProcessBuilder builder = new ProcessBuilder("java", "-Xms1G", "-Xmx3G", "-jar", "paper.jar", "--nogui");
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        builder.redirectError(ProcessBuilder.Redirect.INHERIT);
        builder.directory(new File(System.getProperty("user.home") + "/Minecraft"));
        try {
            Process process = builder.start();
            process.getOutputStream().write("/gamerule playersSleepingPercentage 60".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
