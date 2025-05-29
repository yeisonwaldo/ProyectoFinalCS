class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private String categoria;
    private double valorTotal;

    public Producto(String codigo, String nombre, double precio, int cantidad, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.valorTotal = precio * cantidad;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
        actualizarValorTotal();
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        actualizarValorTotal();
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    private void actualizarValorTotal() {
        this.valorTotal = this.precio * this.cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", categoria='" + categoria + '\'' +
                ", valorTotal=" + valorTotal +
                '}';
    }
}