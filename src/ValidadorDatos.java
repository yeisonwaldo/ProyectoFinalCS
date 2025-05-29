class ValidadorDatos {

    public static boolean validarCodigo(String codigo) {
        return codigo != null && !codigo.trim().isEmpty();
    }

    public static boolean validarNombre(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }

    public static boolean validarPrecio(String precioStr) {
        try {
            double precio = Double.parseDouble(precioStr);
            return precio > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarCantidad(String cantidadStr) {
        try {
            int cantidad = Integer.parseInt(cantidadStr);
            return cantidad > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static double convertirPrecio(String precioStr) throws NumberFormatException {
        return Double.parseDouble(precioStr);
    }

    public static int convertirCantidad(String cantidadStr) throws NumberFormatException {
        return Integer.parseInt(cantidadStr);
    }
}
