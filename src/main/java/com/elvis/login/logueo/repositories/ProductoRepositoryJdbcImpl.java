package com.elvis.login.logueo.repositories;

import com.elvis.login.logueo.models.Producto;
import com.elvis.login.logueo.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements Repository<Producto> {
    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery("SELECT a.idarticulo , a.codigo, a.nombre, a.stock, a.descripcion, a.imagen, a.condicion, a.precio, c.nombre as categoria_nombre\n" +
                     "FROM articulo as a\n" +
                     "         INNER JOIN categoria as c ON a.idcategoria = c.idcategoria\n" +
                     "ORDER BY a.codigo ASC")) {
            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }

        return productos;
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setIdProducto(rs.getInt("idarticulo"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecio(rs.getDouble("precio"));
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("idcategoria"));
        p.setNombre(rs.getString("nombre_categoria"));
        p.setCategoria(c);

        return p;
    }

    @Override
    public Producto porId(Integer id) throws SQLException {
        Producto producto = null;
        try(PreparedStatement smt = conn.prepareStatement("SELECT p*, c.nombre as categoria FROM producto as p" +
                "inner join categoria as c ON (p.idcategoria=c.idcategoria WHERE p.idproducto=?)")){
            smt.setInt(1, id);
            try (ResultSet rs = smt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
    String sql;
    if(producto.getIdProducto() != null && producto.getIdProducto() > 0){
        sql = "UPDATE producto SET nombre=?,categoria=?,descripcion=?,precio=? WHERE idproducto=?";

    }else{
        sql = "INSERT INTO producto(nombre,categoria,descripcion,precio,estado) VALUES(?,?,?,?,1)";
    }
    //Seteamos valores que vamos a obtener
    try(PreparedStatement smt = conn.prepareStatement(sql)){
        smt.setString(1, producto.getNombre());
        smt.setInt(2, producto.getCategoria().getIdCategoria());
        smt.setString(3, producto.getDescripcion());
        smt.setDouble(4, producto.getPrecio());
        //Me va a setear el IDPRODUCTO si es distinto de nulo
        if(producto.getIdProducto() !=null && producto.getIdProducto() > 0){
            smt.setInt(5, producto.getIdProducto());
        }//else{
            //smt.setDate(5,Date.valueOf(producto.getFecha()));
        //}
        //Caso contrario me ejecute las dos Consultas
        smt.executeUpdate();
    }
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        String sql= "DELETE FROM producto WHERE idproducto=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

    }

    @Override
    public Producto activar(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Producto desactivar(Integer id) throws SQLException {
        return null;
    }
}
