

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    // Método para cadastrar os produtos
    public void cadastrarProduto (ProdutosDTO produto){
        
        try{
            conn = new conectaDAO().connectDB();
            
            
            String sql = "INSERT INTO produtos(nome,valor,status) VALUES (?, ?, ?)";
            PreparedStatement query = this.conn.prepareStatement(sql);
            
            query.setString(1, produto.getNome());
            query.setDouble(2, produto.getValor());
            query.setString(3, produto.getStatus());  
            
            query.execute();
           
        }       
        
        catch (SQLException e) {
                    System.out.println(e);
        }
          
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

