/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import DTO.UsuarioDTO;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user3
 */
public class UsuarioDAO {

    Connection conecte;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<UsuarioDTO> listar = new ArrayList<>();

    public ResultSet autenticarUser(UsuarioDTO objuser) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "select * from Funcionario where email=? and senha=? ";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, objuser.getEmail());
            pstm.setString(2, objuser.getSenha());
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
    }

    public void cadastrarUser(UsuarioDTO objusuarioDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "insert into Funcionario(nome,senha,admissao,email,funcao,cpf,data_cadastro) values(?,?,?,?,?,?,?)";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, objusuarioDTO.getNome());
            pstm.setString(2, objusuarioDTO.getSenha());
            pstm.setString(3, objusuarioDTO.getAdmissao());
            pstm.setString(4, objusuarioDTO.getEmail());
            pstm.setString(5, objusuarioDTO.getFuncao());
            pstm.setString(6, objusuarioDTO.getCpf());
            pstm.setString(7, objusuarioDTO.getData_cadastro());
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }

    public ResultSet checarDados(UsuarioDTO objusuarioDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "select *from Funcionario where cpf=? or email=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, objusuarioDTO.getCpf());
            pstm.setString(2, objusuarioDTO.getEmail());
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
    }

    public ResultSet Exibir(UsuarioDTO mst) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "select *from Funcionario where id_usuario=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setDouble(1, mst.getId_usuario());
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
    }

    public void alterarUser(UsuarioDTO obDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "update Funcionario set nome=?, senha=?, admissao=?, email=?, funcao=?, cpf=?, data_cadastro=? where id_usuario=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, obDTO.getNome());
            pstm.setString(2, obDTO.getSenha());
            pstm.setString(3, obDTO.getAdmissao());
            pstm.setString(4, obDTO.getEmail());
            pstm.setString(5, obDTO.getFuncao());
            pstm.setString(6, obDTO.getCpf());
            pstm.setString(7, obDTO.getData_cadastro());
            pstm.setDouble(8, obDTO.getId_usuario());
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }

    public void excluir(UsuarioDTO eDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "delete from Funcionario where id_usuario=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setDouble(1, eDTO.getId_usuario());
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }

    public ArrayList<UsuarioDTO> PesquisarFiltro(UsuarioDTO filDTO) {
        String opcao = filDTO.getFiltro();
        String pesquisa = filDTO.getTxtPesq();
        String sql = "";
        conecte = new Conexao().Conectar();
        try {
            if (opcao.equals("Nome")) {
                sql = "select *from Funcionario where nome like '%" + pesquisa + "%'order by data_cadastro desc";
            } else if (opcao.equals("Função")) {
                sql = "select *from Funcionario where funcao like '%" + pesquisa + "%' order by data_cadastro desc";
            } else if (opcao.equals("Ano de Admissão")) {
                sql = "select *from Funcionario where admissao like '%" + pesquisa + "%' order by data_cadastro desc";
            } else if (opcao.equals("CPF")) {
                sql = "select *from Funcionario where cpf like '%" + pesquisa + "%' order by data_cadastro desc";
            } else if(opcao.equals("Não filtrar")){
               sql = "select *from Funcionario order by data_cadastro desc";
            }

            pstm = conecte.prepareStatement(sql);
            UsuarioDTO dto = new UsuarioDTO();
            dto.setSqls(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                UsuarioDTO pesqDTO = new UsuarioDTO();
                pesqDTO.setNome(rs.getString("nome"));
                pesqDTO.setFuncao(rs.getString("funcao"));
                pesqDTO.setEmail(rs.getString("email"));
                String adm = rs.getString("admissao");
                String formAdm = adm.replace("-", "");
                String ano = formAdm.substring(0, 4);
                String mes = formAdm.substring(4, 6);
                String dia = formAdm.substring(6, 8);
                String aprAdm = dia + "/" + mes + "/" + ano;

                pesqDTO.setCpf(rs.getString("cpf"));

                pesqDTO.setAdmissao(aprAdm);
                pesqDTO.setId_usuario(rs.getDouble("id_usuario"));

                listar.add(pesqDTO);

            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
        return listar;

    }
    public ResultSet pegarId(UsuarioDTO objusuarioDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "select *from Funcionario where email=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, objusuarioDTO.getEmail());
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
    }
}
