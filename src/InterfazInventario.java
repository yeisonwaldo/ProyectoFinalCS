import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class InterfazInventario extends JFrame {
    // Componentes de la interfaz
    private JLabel lblCodigo, lblNombre, lblPrecio, lblCantidad, lblCategoria;
    private JTextField txtCodigo, txtNombre, txtPrecio, txtCantidad;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar;
    private JRadioButton rbAlimentos, rbElectronicos, rbRopa, rbOtros;
    private ButtonGroup bgCategorias;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JMenuBar menuBar;
    private JMenu menuArchivo, menuAyuda, menuInventario;
    private JMenuItem miGuardar, miCargar, miSalir, miAcercaDe, miEstadisticas;

    // Controlador
    private ControladorInventario controlador;

    public InterfazInventario() {
        super("Sistema de Inventario Básico para Tienda");
        this.controlador = new ControladorInventario();

        configurarVentana();
        inicializarComponentes();
        configurarEventos();

        setVisible(true);
    }

    private void configurarVentana() {
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Establecer look and feel nativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarComponentes() {
        crearMenuBar();
        crearPanelFormulario();
        crearTabla();
    }

    private void crearMenuBar() {
        menuBar = new JMenuBar();

        // Menú Archivo
        menuArchivo = new JMenu("Archivo");
        miGuardar = new JMenuItem("Guardar Inventario");
        miCargar = new JMenuItem("Cargar Inventario");
        miSalir = new JMenuItem("Salir");

        menuArchivo.add(miGuardar);
        menuArchivo.add(miCargar);
        menuArchivo.addSeparator();
        menuArchivo.add(miSalir);

        // Menú Inventario
        menuInventario = new JMenu("Inventario");
        miEstadisticas = new JMenuItem("Estadísticas");

        menuInventario.add(miEstadisticas);

        // Menú Ayuda
        menuAyuda = new JMenu("Ayuda");
        miAcercaDe = new JMenuItem("Acerca de");

        menuAyuda.add(miAcercaDe);

        // Agregar menús a la barra
        menuBar.add(menuArchivo);
        menuBar.add(menuInventario);
        menuBar.add(menuAyuda);

        setJMenuBar(menuBar);
    }

    private void crearPanelFormulario() {
        // Panel principal del formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Labels y TextFields
        lblCodigo = new JLabel("Código:");
        txtCodigo = new JTextField(15);

        lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(20);

        lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField(10);

        lblCantidad = new JLabel("Cantidad:");
        txtCantidad = new JTextField(10);

        lblCategoria = new JLabel("Categoría:");

        // RadioButtons y ButtonGroup
        rbAlimentos = new JRadioButton("Alimentos");
        rbElectronicos = new JRadioButton("Electrónicos");
        rbRopa = new JRadioButton("Ropa");
        rbOtros = new JRadioButton("Otros");
        rbAlimentos.setSelected(true);

        bgCategorias = new ButtonGroup();
        bgCategorias.add(rbAlimentos);
        bgCategorias.add(rbElectronicos);
        bgCategorias.add(rbRopa);
        bgCategorias.add(rbOtros);

        // Panel para RadioButtons
        JPanel panelRadios = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRadios.add(rbAlimentos);
        panelRadios.add(rbElectronicos);
        panelRadios.add(rbRopa);
        panelRadios.add(rbOtros);

        // Agregar componentes al formulario con GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(lblCodigo, gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtCodigo, gbc);

        gbc.gridx = 2;
        panelFormulario.add(lblNombre, gbc);
        gbc.gridx = 3;
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(lblPrecio, gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtPrecio, gbc);

        gbc.gridx = 2;
        panelFormulario.add(lblCantidad, gbc);
        gbc.gridx = 3;
        panelFormulario.add(txtCantidad, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(lblCategoria, gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        panelFormulario.add(panelRadios, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Acciones"));

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        // Mejorar apariencia de botones
        btnAgregar.setIcon(new ImageIcon()); // Aquí podrías agregar iconos
        btnActualizar.setIcon(new ImageIcon());
        btnEliminar.setIcon(new ImageIcon());
        btnLimpiar.setIcon(new ImageIcon());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // Panel superior completo
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
    }

    private void crearTabla() {
        // Columnas de la tabla
        String[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Categoría", "Valor Total"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };

        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaProductos.getTableHeader().setReorderingAllowed(false);

        // Configurar ancho de columnas
        tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(80);  // Código
        tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre
        tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(80);  // Precio
        tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(80);  // Cantidad
        tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(100); // Categoría
        tablaProductos.getColumnModel().getColumn(5).setPreferredWidth(100); // Valor Total

        JScrollPane scrollTabla = new JScrollPane(tablaProductos);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Inventario de Productos"));

        add(scrollTabla, BorderLayout.CENTER);
    }

    private void configurarEventos() {
        // Eventos de botones
        btnAgregar.addActionListener(e -> agregarProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        // Evento de selección en tabla
        tablaProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaProductos.getSelectedRow() != -1) {
                mostrarProductoSeleccionado();
            }
        });

        // Eventos de menú
        miGuardar.addActionListener(e -> guardarInventario());
        miCargar.addActionListener(e -> cargarInventario());
        miSalir.addActionListener(e -> System.exit(0));
        miEstadisticas.addActionListener(e -> mostrarEstadisticas());
        miAcercaDe.addActionListener(e -> mostrarAcercaDe());
    }

    private void agregarProducto() {
        if (!validarFormulario()) {
            return;
        }

        try {
            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            double precio = ValidadorDatos.convertirPrecio(txtPrecio.getText().trim());
            int cantidad = ValidadorDatos.convertirCantidad(txtCantidad.getText().trim());
            String categoria = obtenerCategoriaSeleccionada();

            Producto nuevoProducto = new Producto(codigo, nombre, precio, cantidad, categoria);

            if (controlador.agregarProducto(nuevoProducto)) {
                actualizarTabla();
                limpiarFormulario();
                JOptionPane.showMessageDialog(this,
                        "Producto agregado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Ya existe un producto con ese código",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error en el formato de los números",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarProducto() {
        int filaSeleccionada = tablaProductos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un producto para actualizar",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarFormulario()) {
            return;
        }

        try {
            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            double precio = ValidadorDatos.convertirPrecio(txtPrecio.getText().trim());
            int cantidad = ValidadorDatos.convertirCantidad(txtCantidad.getText().trim());
            String categoria = obtenerCategoriaSeleccionada();

            Producto productoActualizado = new Producto(codigo, nombre, precio, cantidad, categoria);

            if (controlador.actualizarProducto(filaSeleccionada, productoActualizado)) {
                actualizarTabla();
                limpiarFormulario();
                JOptionPane.showMessageDialog(this,
                        "Producto actualizado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error al actualizar el producto",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error en el formato de los números",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProducto() {
        int filaSeleccionada = tablaProductos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un producto para eliminar",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar este producto?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            if (controlador.eliminarProducto(filaSeleccionada)) {
                actualizarTabla();
                limpiarFormulario();
                JOptionPane.showMessageDialog(this,
                        "Producto eliminado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private boolean validarFormulario() {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String precio = txtPrecio.getText().trim();
        String cantidad = txtCantidad.getText().trim();

        if (!ValidadorDatos.validarCodigo(codigo)) {
            JOptionPane.showMessageDialog(this,
                    "El código es obligatorio",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtCodigo.requestFocus();
            return false;
        }

        if (!ValidadorDatos.validarNombre(nombre)) {
            JOptionPane.showMessageDialog(this,
                    "El nombre es obligatorio",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtNombre.requestFocus();
            return false;
        }

        if (!ValidadorDatos.validarPrecio(precio)) {
            JOptionPane.showMessageDialog(this,
                    "El precio debe ser un número mayor a cero",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtPrecio.requestFocus();
            return false;
        }

        if (!ValidadorDatos.validarCantidad(cantidad)) {
            JOptionPane.showMessageDialog(this,
                    "La cantidad debe ser un número entero mayor a cero",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtCantidad.requestFocus();
            return false;
        }

        return true;
    }

    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        rbAlimentos.setSelected(true);
        tablaProductos.clearSelection();
        txtCodigo.requestFocus();
    }

    private void mostrarProductoSeleccionado() {
        int fila = tablaProductos.getSelectedRow();

        if (fila != -1) {
            txtCodigo.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtPrecio.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtCantidad.setText(modeloTabla.getValueAt(fila, 3).toString());

            String categoria = modeloTabla.getValueAt(fila, 4).toString();
            seleccionarCategoria(categoria);
        }
    }

    private void seleccionarCategoria(String categoria) {
        switch (categoria) {
            case "Alimentos":
                rbAlimentos.setSelected(true);
                break;
            case "Electrónicos":
                rbElectronicos.setSelected(true);
                break;
            case "Ropa":
                rbRopa.setSelected(true);
                break;
            default:
                rbOtros.setSelected(true);
                break;
        }
    }

    private String obtenerCategoriaSeleccionada() {
        if (rbAlimentos.isSelected()) return "Alimentos";
        if (rbElectronicos.isSelected()) return "Electrónicos";
        if (rbRopa.isSelected()) return "Ropa";
        return "Otros";
    }

    private void actualizarTabla() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Agregar productos
        for (Producto producto : controlador.obtenerTodosLosProductos()) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getCantidad(),
                    producto.getCategoria(),
                    producto.getValorTotal()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void guardarInventario() {
        JOptionPane.showMessageDialog(this,
                "Funcionalidad de guardar inventario\n" +
                        "Total de productos: " + controlador.obtenerCantidadProductos(),
                "Guardar Inventario",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void cargarInventario() {
        JOptionPane.showMessageDialog(this,
                "Funcionalidad de cargar inventario desde archivo",
                "Cargar Inventario",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarEstadisticas() {
        int totalProductos = controlador.obtenerCantidadProductos();
        double valorTotal = controlador.obtenerValorTotalInventario();

        String estadisticas = String.format(
                "ESTADÍSTICAS DEL INVENTARIO\n\n" +
                        "Total de productos: %d\n" +
                        "Valor total del inventario: $%.2f\n" +
                        "Valor promedio por producto: $%.2f",
                totalProductos,
                valorTotal,
                totalProductos > 0 ? valorTotal / totalProductos : 0
        );

        JOptionPane.showMessageDialog(this,
                estadisticas,
                "Estadísticas",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarAcercaDe() {
        JOptionPane.showMessageDialog(this,
                "Sistema de Inventario Básico v2.0\n" +
                        "Desarrollado con Java Swing\n" +
                        "Arquitectura por capas\n\n" +
                        "Funcionalidades:\n" +
                        "• Gestión de productos\n" +
                        "• Validación de datos\n" +
                        "• Estadísticas básicas",
                "Acerca del Sistema",
                JOptionPane.INFORMATION_MESSAGE);
    }
}