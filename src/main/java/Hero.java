import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;

public class Hero {
    private Position position;

    public Hero(int hx, int hy) {
        this.position = new Position(hx, hy);
    }
    public void draw(TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(position.getX(), position.getY(), "X");
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
