import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    public static Object processKey;
    private int width;
    private int height;
    private Hero hero;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero (10,10);
    }


    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
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
    }

    private void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }
}
