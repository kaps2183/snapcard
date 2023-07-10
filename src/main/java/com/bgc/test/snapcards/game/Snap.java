package com.bgc.test.snapcards.game;


import com.bgc.test.snapcards.model.Card;
import com.bgc.test.snapcards.model.Pack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Snap {
    public static final int TASK_MULTIPLIER = 52;
    private final Scanner scanner;

    private volatile Card lastPlayedCard;
    private int players;
    private int packCount;

    private List<Pack> packList;

    public Snap(Scanner scanner) {

        this.scanner = scanner;
        this.packCount = scanner.nextInt();
        this.players = 2;
        this.packList= new ArrayList(packCount);

        for (int i = 0; i < packCount; i++) {
            Pack pack = new Pack();
            packList.add(pack);
        }
    }

    public void startGame() throws InterruptedException {
        System.out.println("Packs -> "+packCount);
        shufflePacks();
        ExecutorService executorService = Executors.newFixedThreadPool(players);

        for (int i = 0; i < packCount* TASK_MULTIPLIER; i++) {
            executorService.execute(new GamePlayTask(scanner));
        }
        executorService.awaitTermination(30000, TimeUnit.MILLISECONDS);

        System.out.println("Game Finished, no more cards left.");
    }

    private void shufflePacks(){
        packList.stream().forEach(pack -> pack.shuffle());
    }

    private synchronized Card dealCard(){
        Pack randomPack = packList.stream().filter(pack -> pack.cardsLeft() > 0).findFirst().orElse(null);

        if(randomPack != null){
            System.out.println("Deal Card");
            return randomPack.dealCard();
        }else{
            System.out.println("Null Card");
            return null;
        }
    }

    class GamePlayTask implements Runnable{

        Logger log = LoggerFactory.getLogger(GamePlayTask.class);
        private final Scanner scanner;

        public GamePlayTask(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName());

            Card dealCard = dealCard();

            if (lastPlayedCard != null && lastPlayedCard.compareTo(dealCard) == 0){
                System.out.println("Await User Input -> ");
                //scanner.nextLine();
            }
            lastPlayedCard = dealCard;

        }
    }

}
