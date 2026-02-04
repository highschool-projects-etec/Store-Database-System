package lojabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBD {
    private String status = "Sem conexão...";
    
    // Dados para acesso ao SGBD
    private String endServidor = "localhost";
    private String bd = "loja";
    private String url = "jdbc:mysql://" + endServidor + "/" + bd;
    private String usuario = "root";
    private String senha = "";
    
    // Identificador da conexão
    Connection con;
    
    public java.sql.Connection conecta(){
        try {
            // Carrega o driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            
            // Conect com o SGBD
            con = DriverManager.getConnection(url, usuario, senha);
            
            if (con != null) {
                status = "STATUS: Conectado ao BD...";
            } else {
                status = "STATUS: Não foi possível realizar a conexão";
            }
            
            return con;
        } catch (ClassNotFoundException ex) {
            System.out.println("O DRIVER especificado não foi encontrado!\n"
                    + "ERRO: " + ex.getLocalizedMessage());
            return null;            
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao Banco de Dados!\n"
                    + "ERRO: " + ex.getLocalizedMessage());
            return null;
        }
    }
    
    public boolean desconecta() {
        try {
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getStatus(){
        return status;
    }
    
}
