import window.GameWindow;
import window.MenuWindow;

public class Main {
    public static void main(String[] args){
        MenuWindow window = new MenuWindow();
        Thread t1 = new Thread(window);
        t1.start();

    }
}
