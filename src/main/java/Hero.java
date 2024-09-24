import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero {
    int x;
    int y;

    public Hero(int x, int y) {
        this.x=x;
        this.y=y;
    }
    public void draw(Screen screen) throws IOException {
        screen.setCharacter(this.x, this.y, TextCharacter.fromCharacter('X')[0]);
    }

    public void moveUp() {
        this.y = this.y-1;
    }
    public void moveDown() {
        this.y = this.y+1;
    }
    public void moveLeft() {
        this.x = this.x-1;
    }
    public void moveRight() {
        this.x = this.x+1;
    }
}
