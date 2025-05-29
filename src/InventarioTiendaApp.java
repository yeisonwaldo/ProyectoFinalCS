import javax.swing.SwingUtilities;

public class InventarioTiendaApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazInventario();
            }
        });
    }
}