package br.com.etec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Operacoes {
	
	@FXML
	private TextField txfUsuario;
	@FXML
	private PasswordField psfSenha;
	@FXML
	private Button btnAcessar;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnFechar;
	@FXML
	private Stage acpPalco;
	
	//Botão Acessar!
	@FXML
	private void validarUsuario(ActionEvent event) throws SQLException {
		String nomeUsuario;
		nomeUsuario = txfUsuario.getText();
		String senhaUsuario;
		senhaUsuario = psfSenha.getText();
		
			if(nomeUsuario.isEmpty() || senhaUsuario.isEmpty()) {
				if(nomeUsuario.isEmpty()) {
					mostrarAlerta(Alert.AlertType.WARNING, "INFORME UM NOME!", "É necessário colocar o nome de usuário.");
				}else {
					if(senhaUsuario.isEmpty()) {
					mostrarAlerta(Alert.AlertType.WARNING, "INFORME SENHA!", "É necessário colocar uma senha.");
					}
				}
			}
			else {
				if(verificarUsuarioSenha(nomeUsuario, senhaUsuario)) {
					mostrarAlerta(Alert.AlertType.CONFIRMATION, "ACESSO PERMITIDO!", "Login bem sucedido!");
				}
				else {
					mostrarAlerta(Alert.AlertType.ERROR, "ACESSO NEGADO!", "Usuário ou Senha inválidos");
				}
			}
	}
	
	//Botão Cadastrar
	@FXML
	private void cadastrarUsuario(ActionEvent event) throws SQLException {
		String nomeUsuario;
		nomeUsuario = txfUsuario.getText();
		String senhaUsuario;
		senhaUsuario = psfSenha.getText();
		
			if(nomeUsuario.isEmpty() || senhaUsuario.isEmpty()) {
				if(nomeUsuario.isEmpty()) {
					mostrarAlerta(Alert.AlertType.WARNING, "INFORME UM NOME!", "É necessário colocar o nome de usuário.");
				}else {
					if(senhaUsuario.isEmpty()) {
					mostrarAlerta(Alert.AlertType.WARNING, "INFORME SENHA!", "É necessário colocar uma senha.");
					}
				}
			}
			else {
				if(cadastrarUsuarioSenha(nomeUsuario, senhaUsuario)) {
					mostrarAlerta(Alert.AlertType.CONFIRMATION, "Usuário Cadastrado!", "Cadastro Efetuado!");
				}
				else {
					mostrarAlerta(Alert.AlertType.ERROR, "Tente Novamente!", "Erro No Cadastro");
				}
			}
	}

	
	private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
		
		Alert alerta = new Alert(tipo);
		alerta.setTitle(titulo);
		alerta.setContentText(mensagem);
		alerta.showAndWait();
	}
	
	@FXML
	private void fecharTelaLogin(ActionEvent event) {
		acpPalco = (Stage) btnFechar.getScene().getWindow();
		acpPalco.close();
	}
	
	private boolean verificarUsuarioSenha(String usuario, String senha) throws SQLException {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean usuarioValido = false;

        try {
            conexao = Conexao.conectar();
            String sql = "SELECT * FROM tabelasenha WHERE usuario = ? AND senha = SHA2(?, 256)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuarioValido = true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            Conexao.fechar(conexao);
        }

        return usuarioValido;
    }
	
	private boolean cadastrarUsuarioSenha(String usuario, String senha) throws SQLException {
	    Connection conexao = null;
	    PreparedStatement stmt = null;
	    boolean usuarioValido = false;

	    try {
	        conexao = Conexao.conectar();
	        String sql = "INSERT INTO tabelasenha (usuario, senha) VALUES (?, SHA2(?, 256))";
	        stmt = conexao.prepareStatement(sql);
	        stmt.setString(1, usuario);
	        stmt.setString(2, senha);
	        
	        // Execute o comando de inserção
	        int linhasAfetadas = stmt.executeUpdate();

	        // Verifica se a inserção foi bem-sucedida
	        if (linhasAfetadas > 0) {
	            usuarioValido = true;
	            mostrarAlerta(Alert.AlertType.INFORMATION, "Cadastro Sucesso", "Usuário cadastrado com sucesso!");
	        }
	    } catch (SQLException e) {
	        mostrarAlerta(Alert.AlertType.ERROR, "Erro no Cadastro", "Ocorreu um erro ao cadastrar o usuário: " + e.getMessage());
	    } finally {
	        if (stmt != null) {
	            stmt.close();
	        }
	        Conexao.fechar(conexao);
	    }
	    return usuarioValido;
	}

}
