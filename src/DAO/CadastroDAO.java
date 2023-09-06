
package DAO;

import data.Cadastro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CadastroDAO {
    
        Connection conn;
        PreparedStatement st;
        ResultSet rs;

        String url = "jdbc:mysql://localhost:3306/cenaflix";
        String user = "root";
        String pass = "admin";

        public boolean conectar(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url,user,pass);
                return true;

            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Erro ao conectar: " + ex.getMessage());
                return false;

            }
        }
        
        public void desconectar(){
            try {
                conn.close();

            } catch (SQLException ex) {
                //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
            }
        }
        
        public int salvar (Cadastro cadastro){

            int status;

            try {
                st = conn.prepareStatement("INSERT INTO filmes VALUES(?,?,?,?)");
                String query = "SELECT MAX(id) FROM filmes";

                ResultSet resultSet = st.executeQuery(query);

                int lastId = 0;
                if (resultSet.next()) {
                    lastId = resultSet.getInt(1);
                }
                int nextId = lastId + 1;

                st.setInt(1, nextId);
                st.setString(2,cadastro.getNome());
                st.setString(3,cadastro.getData());
                st.setString(4,cadastro.getCategoria());

                status = st.executeUpdate();
                return status; //retornar 1

            } catch (SQLException ex) {
                System.out.println("Erro ao conectar: " + ex.getMessage());
                return ex.getErrorCode();

            }
        }

         public Cadastro consultar (String procuraFilme){
         //1	Dois	1995-05-15	Drama
        int status;

            try {
            Cadastro newCadastro = new Cadastro();
            st = conn.prepareStatement("SELECT * from filmes WHERE id= ?");
            st.setString(1, procuraFilme);
            rs = st.executeQuery();
            //verificar se a consulta encontrou o funcionário com a matrícula informada
            if(rs.next()){ // se encontrou o funcionário, vamos carregar os dados
                newCadastro.setNome(rs.getString("nome"));
                newCadastro.setData(rs.getString("datalancamento"));
                newCadastro.setCategoria(rs.getString("categoria"));
                return newCadastro;
            }else{
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
    }
         
         public boolean excluir(String cadastro){
        try {
            st = conn.prepareStatement("DELETE FROM filmes WHERE id = ?");
            st.setString(1,cadastro);
            st.executeUpdate();
            return true;
            } catch (SQLException ex) {
                return false;
        }
    }
     
         public int atualizar(Cadastro cadastro){
        int status;
        try {
            st = conn.prepareStatement("UPDATE filmes SET nome = '?', datalancamento = '?', categoria = '?' WHERE id = ?");
            st.setString(1,cadastro.getNome());
            st.setString(2,cadastro.getData());
            st.setString(3, cadastro.getCategoria());
            st.setInt(4, cadastro.getId());
            status = st.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
    }
         

}
