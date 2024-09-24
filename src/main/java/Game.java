import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Game {

    private int x = 10;
    private int y = 10;
    private final TerminalScreen screen;
    public Game(int  width, int height) throws IOException {
        // Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        TerminalSize terminalSize = new TerminalSize(width, height);
        // DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
    }

    private void draw() throws IOException {
        screen.clear();
        //screen.setCharacter(0, 0, TextCharacter.fromCharacter('X')[0]);
        //screen.setCharacter(0, 30, TextCharacter.fromCharacter('X')[0]);
        //screen.setCharacter(30, 0, TextCharacter.fromCharacter('X')[0]);
        //screen.setCharacter(30, 30, TextCharacter.fromCharacter('X')[0]);
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();

    }


    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
            processKey(key);
        }
    }


    private void processKey(KeyStroke key) {
        System.out.println(key);
        String keyT = key.getKeyType().toString();
        switch (keyT) {
            case "ArrowUp":
                y = y - 1;
                break;
            case "ArrowDown":
                y = y + 1;
                break;
            case "ArrowLeft":
                x = x - 1;
                break;
            case "ArrowRight":
                x = x + 1;
                break;
        }
    }
}


