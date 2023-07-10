package com.bgc.test.snapcards;

import com.bgc.test.snapcards.game.Snap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SnapCardsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SnapCardsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Enter desired number of packs");
        Snap snap = new Snap(new Scanner(System.in));
        snap.startGame();
    }
}
