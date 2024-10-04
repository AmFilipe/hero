import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    public static Object processKey;
    private final List<Wall> walls;
    private final List<Monster> monsters;
    private List<Coin> coins;
    private int width;
    private int height;
    private Hero hero;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero (10,10);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    private List<Wall> createWalls() {
       List<Wall> walls = new ArrayList<>();
       for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
      }
       for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
       }
       return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        while (monsters.size() < 10) {
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            Position monsterPosition = new Position(x, y);
            if (!monsterPosition.equals(hero.getPosition())) {
                monsters.add(new Monster(x, y));
            }
        }
        return monsters;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls) {
            wall.draw(graphics);
        }

        for (Coin coin : coins) {
            coin.draw(graphics);
        }

        for (Monster monster : monsters) {
            monster.draw(graphics);
        }
        hero.draw(graphics);
    }

    public void processKey(KeyStroke key) {
        System.out.println(key);
        String keyT = key.getKeyType().toString();
        switch (keyT) {
            case "ArrowUp":
                moveHero(hero.moveUp());
                break;
            case "ArrowDown":
                moveHero(hero.moveDown());
                break;
            case "ArrowLeft":
                moveHero(hero.moveLeft());
                break;
            case "ArrowRight":
                moveHero(hero.moveRight());
                break;
        }
        retrieveCoins();
        moveMonsters();
        verifyMonsterCollisions();
    }

    private void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        if (position.getX() < 0) return false;
        if (position.getX() > width - 1) return false;
        if (position.getY() < 0) return false;
        if (position.getY() > height - 1) return false;

        for (Wall wall : walls) {

            if  (wall.getPosition().equals(position)){
                return false;
            }
        }

        return true;
    }

    private void moveMonsters() {
        for (Monster monster : monsters) {
            Position newPosition = monster.move();
            if (canMonsterMove(newPosition)) {
                monster.setPosition(newPosition);
            }
        }
    }

    private boolean canMonsterMove(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }

    private void retrieveCoins() {

        for (int i = 0; i < coins.size(); i++) {
            Coin coin = coins.get(i);
            if (coin.getPosition().equals(hero.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }

    private void verifyMonsterCollisions() {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(hero.getPosition())) {
                hero.decreaseEnergy(10);
                System.out.println("O herói perdeu energia!! Energia restante: " + hero.getEnergy());
                if (hero.getEnergy() <= 0) {
                    System.out.println("Game Over! O herói foi capturado por um monstro!");
                    System.exit(0);
                }
            }
        }
    }
}
