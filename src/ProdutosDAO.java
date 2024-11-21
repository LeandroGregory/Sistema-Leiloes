    

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
            prep = this.conn.prepareStatement(sql);
            
            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor());
            prep.setString(3, produto.getStatus());  
            
            prep.execute();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso! ");
           
        }       
        
        catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto");
        }
          
    }
    
    public void venderProduto (ProdutosDTO produto){        
                
        try{           
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            
            conn = new conectaDAO().connectDB();
            prep = this.conn.prepareStatement(sql);
            
            prep.setInt(1, produto.getId());
           
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro na atualização do produto");
        }
        
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
            
        try {
            
            String sql = "SELECT * FROM produtos";
        
            conn = new conectaDAO().connectDB();
            
                    prep = this.conn.prepareStatement(sql);                                   
                    
                    ResultSet rs = prep.executeQuery();        
                   
                    while (rs.next()) {
                        ProdutosDTO produtos = new ProdutosDTO();
                                                
                      produtos.setId(rs.getInt("id"));
                      produtos.setNome(rs.getString("nome"));
                      produtos.setValor(rs.getDouble("valor"));
                      produtos.setStatus(rs.getString("status"));                                       
                      
                        
                        listagem.add(produtos);
                    }
                    return listagem;
                    
                    //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma filme, então retornará null
                }     
        catch (SQLException e) {
                    return null;
                }
        
       
    }
    
    
     
        
}

