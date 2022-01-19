package window;


public class Main {

    public static int state = 0;
    public static Thread mainThread;
    public static MenuWindow menu;
    public static GameWindow window;

    public static void main(String[] args) throws InterruptedException {
        menu = new MenuWindow();
        mainThread = new Thread(menu);
        mainThread.start();
    }

    public static void changeState(int newState) throws InterruptedException {
        if(newState == 1 && state == 0){
            menu.stop();
            window = GameWindow.getINSTANCE();
            mainThread = new Thread(window);
            mainThread.start();
            mainThread.join();
            window.hardResetGame();
        }else if(newState == 0 && state == 1){
            window.stop();
            menu = new MenuWindow();
            mainThread = new Thread(menu);
            mainThread.start();
            mainThread.join();
        }
        state = newState;
    }
}
