/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.EstoqueDTO;
import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author jaine
 */
public class EstoqueDAO {

    Connection conecte;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<EstoqueDTO> listar = new ArrayList<>();

    public void adicionarProduto(EstoqueDTO estDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "insert into Produtos(produto,categoria,lote,vencimento,quantidade,valor,data_cadastro) values(?,?,?,?,?,?,?)";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, estDTO.getProduto());
            pstm.setString(2, estDTO.getCategoria());
            pstm.setString(3, estDTO.getLote());
            pstm.setString(4, estDTO.getVencimento());
            pstm.setInt(5, estDTO.getQuantidade());
            pstm.setString(6, estDTO.getPreco());
            pstm.setString(7, estDTO.getData_cadastro());
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
        
    }
    public ResultSet pgID(EstoqueDTO estDTO) {
        conecte = new Conexao().Conectar();
        String datas = estDTO.getData_cadastro();
        try {
            String sql = "select id_produto from Produtos where data_cadastro='" + datas + "'";
            pstm = conecte.prepareStatement(sql);
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
    }
    public void gestao_estq(EstoqueDTO estDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql= "insert into gerir_estoque(id_produto,produto,categoria,lote,vencimento,qtd_estoque,valor,data_cadastro,operacao,entrada,saida,vl_total) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            pstm = conecte.prepareStatement(sql);
            pstm.setDouble(1, estDTO.getId_p_tbl());
            pstm.setString(2, estDTO.getProduto());
            pstm.setString(3, estDTO.getCategoria());
            pstm.setString(4, estDTO.getLote());
            pstm.setString(5, estDTO.getVencimento());
            pstm.setInt(6, estDTO.getQuantidade());
            pstm.setString(7, estDTO.getPreco());
            pstm.setString(8, estDTO.getData_cadastro());
            pstm.setString(9, "entrada");
            pstm.setInt(10,estDTO.getQuantidade());
            pstm.setDouble(11, 0);
            double mult=Double.parseDouble(estDTO.getPreco());
            mult=(mult*estDTO.getQuantidade());
            String vl = String.valueOf(mult);
            StringTokenizer casasN = new StringTokenizer(vl);
            String inteiro = casasN.nextToken(".");
            String decimal = casasN.nextToken(".");
            if (decimal.length() < 2) {
                decimal = decimal + "0";
            } else {
                decimal = decimal.substring(0, 2);
            }
            vl = inteiro + "." + decimal;
            mult = Double.parseDouble(vl);
            pstm.setDouble(12, mult);
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }

    public ArrayList<EstoqueDTO> FiltrarEstoque(EstoqueDTO estqDTO) {
        String opcao = estqDTO.getFiltro();
        String pesquisa = estqDTO.getTxtPesq();
        String sql = "";
        conecte = new Conexao().Conectar();
        try { 
            if (opcao.equals("Não filtrar")) {
                sql = "select *from Produtos order by data_cadastro desc";
            } else if (opcao.equals("Vencimento")) {
                sql = "select *from Produtos order by vencimento asc";
            } else if (opcao.equals("Quantidade")) {
                sql = "select *from Produtos order by quantidade asc";
            } else if (opcao.equals("Produto")) {
                sql = "select *from Produtos where produto like '%" + pesquisa + "%' order by data_cadastro desc";
            } else if (opcao.equals("Categoria")) {
                sql = "select *from Produtos where categoria like '%" + pesquisa + "%' order by data_cadastro desc";
            } else if (opcao.equals("Lote")) {
                sql = "select *from Produtos where lote like '%" + pesquisa + "%' order by data_cadastro desc";
            }

            pstm = conecte.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                EstoqueDTO pesqDTO = new EstoqueDTO();
                pesqDTO.setId_produto(rs.getDouble("id_produto"));
                pesqDTO.setProduto(rs.getString("produto"));
                pesqDTO.setCategoria(rs.getString("categoria"));
                pesqDTO.setLote(rs.getString("lote"));
                pesqDTO.setQuantidade(rs.getInt("quantidade"));
                pesqDTO.setPreco("R$"+rs.getString("valor"));

                String vmt = rs.getString("vencimento");
                String formVmt = vmt.replace("-", "");
                String ano = formVmt.substring(0, 4);
                String mes = formVmt.substring(4, 6);
                String dia = formVmt.substring(6, 8);
                String ajusteVenc = dia + "/" + mes + "/" + ano;

                pesqDTO.setVencimento(ajusteVenc);

                listar.add(pesqDTO);

            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
        return listar;

    }

    public ResultSet checkLote(EstoqueDTO estqDTO) {
        conecte = new Conexao().Conectar();
        String nLote = estqDTO.getLote();
        try {
            String sql = "select *from Produtos where lote='" + nLote + "'";
            pstm = conecte.prepareStatement(sql);
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
    }
    public void alterarProduto(EstoqueDTO estoqueDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "update Produtos set produto=?,categoria=?,lote=?,vencimento=?,quantidade=?,valor=?,data_cadastro=? where id_produto=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, estoqueDTO.getProduto());
            pstm.setString(2, estoqueDTO.getCategoria());
            pstm.setString(3, estoqueDTO.getLote());
            pstm.setString(4, estoqueDTO.getVencimento());
            pstm.setInt(5, estoqueDTO.getQuantidade());
            pstm.setString(6, estoqueDTO.getPreco());
            pstm.setString(7, estoqueDTO.getData_cadastro());
            pstm.setDouble(8, estoqueDTO.getId_produto());
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }
    public void oprEstoque(EstoqueDTO estoqueDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql= "insert into gerir_estoque(id_produto,produto,categoria,lote,vencimento,qtd_estoque,valor,data_cadastro,operacao,entrada,saida,vl_total) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            pstm = conecte.prepareStatement(sql);
            pstm.setDouble(1, estoqueDTO.getId_p_tbl());
            pstm.setString(2, estoqueDTO.getProduto());
            pstm.setString(3, estoqueDTO.getCategoria());
            pstm.setString(4, estoqueDTO.getLote());
            pstm.setString(5, estoqueDTO.getVencimento());
            pstm.setInt(6, estoqueDTO.getQuantidade());
            pstm.setString(7, estoqueDTO.getPreco());
            pstm.setString(8, estoqueDTO.getData_cadastro());
            pstm.setString(9, estoqueDTO.getOperacao());
            if(estoqueDTO.getOperacao().equals("entrada")){
                pstm.setString(10,estoqueDTO.getValor());
                pstm.setDouble(11, 0);
            }else{
                pstm.setInt(10,0);
                pstm.setString(11, estoqueDTO.getValor());
            }
            
            
            double mult=Double.parseDouble(estoqueDTO.getPreco());
            mult=(mult*estoqueDTO.getQuantidade());
            String vl = String.valueOf(mult);
            StringTokenizer casasN = new StringTokenizer(vl);
            String inteiro = casasN.nextToken(".");
            String decimal = casasN.nextToken(".");
            if (decimal.length() < 2) {
                decimal = decimal + "0";
            } else {
                decimal = decimal.substring(0, 2);
            }
            vl = inteiro + "." + decimal;
            mult = Double.parseDouble(vl);
            pstm.setDouble(12, mult);
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }
    public ResultSet pegarId(EstoqueDTO estDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "select *from Produtos where lote=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, estDTO.getLote());
            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
    }
    public void excluir(EstoqueDTO eDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "delete from Produtos where id_produto=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setDouble(1, eDTO.getId_produto());
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }
}
