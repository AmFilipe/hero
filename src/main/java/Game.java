import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Game {

    private Position position;
    private final TerminalScreen screen;
    private final Arena arena;


    public Game(int  width, int height) throws IOException {
        // Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        TerminalSize terminalSize = new TerminalSize(width, height);
        // DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        arena = new Arena(width, height);
    }

    private void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        arena.draw(graphics);
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
        arena.processKey(key);
        }


}


