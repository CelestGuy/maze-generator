package net.ddns.celestgames;

import net.ddns.celestgames.maze.game.Game;

public class Main {
    public static final int ONE_SEC_IN_NANO = 1000000000;
    public static final int ONE_SEC_IN_MICRO = 1000000;

    public static void main(String[] args) {
        Game game = new Game(99, 99);
        /*main.labBuilderMathVersion = new LabBuilderMathVersion(main, main.size);
        main.recursiveBacktrack = new RecursiveBacktrack(main, main.size);

        if (main.genMode == GenMode.Mathieu) {
            main.labBuilderMathVersion.createPath();
        } else {
            main.recursiveBacktrack.createPath();
        }*/
    }
}
