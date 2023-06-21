/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CardapioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jaine
 */
public class CardapioDAO {
    Connection conecte;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<CardapioDTO> listar = new ArrayList<>();

    public void adicionarProduto(CardapioDTO cdpDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "insert into tbl_comidas(nm_comida,vl_preco,nm_categoria,img_comida,qt_estoque,data_cadastro) values(?,?,?,?,?,?)";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, cdpDTO.getNm_comida());
            pstm.setString(2, cdpDTO.getVl_preco());
            pstm.setString(3, cdpDTO.getNm_categoria());
            pstm.setString(4, cdpDTO.getImg_comida());
            pstm.setInt(5, cdpDTO.getQt_estoque());
            pstm.setString(6, cdpDTO.getData_cadastro());
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }
    public ArrayList<CardapioDTO> FiltrarEstoque(CardapioDTO cdDTO) {
        String opcao = cdDTO.getFiltro();
        String pesquisa = cdDTO.getTxtPesq();
        String sql = "";
        conecte = new Conexao().Conectar();
        try {
            if (opcao.equals("Não filtrar")) {
                sql = "select *from tbl_comidas order by data_cadastro desc";
            }else if (opcao.equals("Quantidade")) {
                sql = "select *from tbl_comidas order by qt_estoque asc";
            } else if (opcao.equals("Produto")) {
                sql = "select *from tbl_comidas where nm_comida like '%" + pesquisa + "%' order by data_cadastro desc";
            } else if (opcao.equals("Categoria")) {
                sql = "select *from tbl_comidas where nm_categoria like '%" + pesquisa + "%' order by data_cadastro desc";
            }

            pstm = conecte.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                CardapioDTO cdpoDTO = new CardapioDTO();
                cdpoDTO.setCd_comida(rs.getInt("cd_comida"));
                cdpoDTO.setImg_comida("C:\\xampp\\htdocs\\FamintosWeb\\produtos_img\\"+rs.getString("img_comida")+".jpg");
                cdpoDTO.setNm_comida(rs.getString("nm_comida"));
                cdpoDTO.setNm_categoria(rs.getString("nm_categoria"));
                cdpoDTO.setQt_estoque(rs.getInt("qt_estoque"));
                cdpoDTO.setVl_preco("R$"+rs.getString("vl_preco"));

                listar.add(cdpoDTO);

            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
        return listar;

    }
    public ResultSet Exibir(CardapioDTO cds) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "select *from tbl_comidas where cd_comida=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setInt(1, cds.getCd_comida());
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
    }
    public void altereProduto(CardapioDTO cdpDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "update tbl_comidas set nm_comida=?,vl_preco=?,nm_categoria=?,img_comida=?,qt_estoque=?,data_cadastro=? where cd_comida=? ";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, cdpDTO.getNm_comida());
            pstm.setString(2, cdpDTO.getVl_preco());
            pstm.setString(3, cdpDTO.getNm_categoria());
            pstm.setString(4, cdpDTO.getImg_comida());
            pstm.setInt(5, cdpDTO.getQt_estoque());
            pstm.setString(6, cdpDTO.getData_cadastro());
            pstm.setInt(7, cdpDTO.getCd_comida());
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }
    public void excluir(CardapioDTO cdpsDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "delete from tbl_comidas where cd_comida=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setInt(1, cdpsDTO.getCd_comida());
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }
}
