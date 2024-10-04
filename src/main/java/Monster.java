import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    public Monster(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }

    public Position move() {
        Random random = new Random();
        int dx = random.nextInt(3) - 1;
        int dy = random.nextInt(3) - 1;

        return new Position(position.getX() + dx, position.getY() + dy);
    }
}
