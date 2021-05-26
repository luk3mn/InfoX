package br.com.infox.dal;

import java.sql.*;

public class ModuloConexao {
    public static Connection conector() {
        // variavel que vai conter as informações de conexao
        java.sql.Connection conexao = null;
        
        // variável com um conector que faz referencia 
        // ao driver carregado na biblioteca
        String driver = "com.mysql.jdbc.Driver";
        
        // Variável com o caminho de acesso ao BD - local
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        
        // Variável com o usuario do DB
        String user = "root";
        
        // Variável com a senha do DB
        String password = "";
        
        // estabelecendo a conexao com o BD
        try {
            
            // executa o driver do BD que está na variável driver
            Class.forName(driver);
            
            // realiza a conexao com os valores de: url, user, password
            // conecta o sistema em java com o BD MySQL
            // salva a informação da conexão na variável conexao
            conexao = DriverManager.getConnection(url, user, password);
            
            // se nao houver nenhum peoblema retorna o valor de conexao
            return conexao;
            
        } catch (Exception e) {
            // se houver algum problema retorna o valor NULL
            return null;
        }
        
    }
}
