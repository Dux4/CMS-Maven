package cms;

public class Main {
    public static void main(String[] args) {
        User currentUser = null;
        TextUserInterface TUI = new TextUserInterface();

        while(true){
            if(currentUser == null){
                currentUser = TUI.menuPrincipal();
            }else {
                currentUser = TUI.menuContent(currentUser);
            }
        }
    }
}