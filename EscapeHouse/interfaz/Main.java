package EscapeHouse.interfaz;

import EscapeHouse.sistema.EscapeHouse;

public class Main {
    public static void main(String[] args) {
        EscapeHouse miEscapeHouse = new EscapeHouse();
        Menu menu = new Menu();
        menu.mostrarMenuPrincipal(miEscapeHouse);
        
    }
}
