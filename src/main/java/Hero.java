import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;

public class Hero {
    private Position position;

    public Hero(int x, int y) {
        this.position = new Position(x, y);
    }
    public void draw(Screen screen) throws IOException {
        screen.setCharacter(this.position.getX(), this.position.getY(), TextCharacter.fromCharacter('X')[0]);
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }
    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
