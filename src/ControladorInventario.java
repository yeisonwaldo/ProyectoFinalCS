import java.util.ArrayList;
import java.util.List;

class ControladorInventario {
    private List<Producto> productos;

    public ControladorInventario() {
        this.productos = new ArrayList<>();
    }

    public boolean agregarProducto(Producto producto) {
        // Verificar si ya existe un producto con el mismo código
        if (buscarProductoPorCodigo(producto.getCodigo()) != null) {
            return false; // Ya existe
        }
        return productos.add(producto);
    }

    public boolean actualizarProducto(int indice, Producto producto) {
        if (indice >= 0 && indice < productos.size()) {
            productos.set(indice, producto);
            return true;
        }
        return false;
    }

    public boolean eliminarProducto(int indice) {
        if (indice >= 0 && indice < productos.size()) {
            productos.remove(indice);
            return true;
        }
        return false;
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return new ArrayList<>(productos);
    }

    public int obtenerCantidadProductos() {
        return productos.size();
    }

    public double obtenerValorTotalInventario() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getValorTotal();
        }
        return total;
    }

    public void limpiarInventario() {
        productos.clear();
    }
}