package de.benjosua.minecraftserverwrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class MinecraftServerWrapperApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MinecraftServerWrapperApplication.class, args);
        startCloudWatch();
        startMinecraft();
        while (true) ;
    }

    public static void startMinecraft() {
        ProcessBuilder builder = new ProcessBuilder("java", "-Xms1G", "-Xmx3G", "-jar", "paper.jar", "--nogui");
        builder.directory(new File(System.getProperty("user.home") + "/Minecraft"));
        builder.redirectOutput(new File("out.txt"));
        try {
            Process process = builder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startCloudWatch() {
        ProcessBuilder builder = new ProcessBuilder("sudo", "/opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl", "-a", "fetch-config", "-m", "ec2", "-s", "-c", "file:configuration-file-path");
        try {
            Process process = builder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
