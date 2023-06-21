/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PedidosDTO;
import DTO.RelatoriosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author user3
 */
public class PedidosDAO {

    Connection conecte;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<PedidosDTO> listar = new ArrayList<>();
    ArrayList<PedidosDTO> pedidosE = new ArrayList<>();
    ArrayList<PedidosDTO> consultar = new ArrayList<>();
    ArrayList<PedidosDTO> consultar2 = new ArrayList<>();
    ArrayList<PedidosDTO> itens = new ArrayList<>();
    ArrayList<PedidosDTO> preco = new ArrayList<>();
    ArrayList<PedidosDTO> tickets = new ArrayList<>();

    public ArrayList<PedidosDTO> FiltrarNomes(PedidosDTO pedDTO) {
        String sql = "";
        conecte = new Conexao().Conectar();
        try {
            //sql = "select * from tbl_venda where statusC='pendente' order by cd_venda asc";
            sql = "select * from tbl_venda where statusC='pendente'";
            pstm = conecte.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                PedidosDTO pdDTO = new PedidosDTO();
                pdDTO.setNo_Ticket(rs.getString("no_Ticket"));
                pdDTO.setCd_cliente(rs.getString("cd_cliente"));
                
                
                listar.add(pdDTO);

            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
        return listar;

    }

    public ArrayList<PedidosDTO> Exibir(PedidosDTO pddDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "select *from tbl_venda where no_Ticket=? and statusC='pendente'";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, pddDTO.getBusca());
            rs = pstm.executeQuery();
            while (rs.next()) {
                PedidosDTO pdDTO = new PedidosDTO();
                pdDTO.setCd_comida(rs.getString("cd_comida"));
                pdDTO.setQt_comida(rs.getString("qt_comida"));
                pdDTO.setNome_cliente(rs.getString("nome_cliente"));
                pdDTO.setVl_item(rs.getString("vl_item"));
                pdDTO.setVl_total_item(rs.getString("vl_total_item"));
                pdDTO.setCd_mesa(rs.getString("cd_mesa"));
                pdDTO.setCd_cliente(rs.getString("cd_cliente"));
                pdDTO.setCdg_garcom(rs.getString("cdg_garcom"));
                pdDTO.setRua(rs.getString("rua"));
                pdDTO.setBairro(rs.getString("bairro"));
                pdDTO.setNumero(rs.getString("numero"));
                pdDTO.setCidade(rs.getString("cidade"));
                pdDTO.setEstado(rs.getString("estado"));
                pdDTO.setOpcao(rs.getString("opcao"));
                pedidosE.add(pdDTO);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
        return pedidosE;
    }

    public ArrayList<PedidosDTO> Exibir2(PedidosDTO pddsDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "select * from tbl_comidas where cd_comida=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, pddsDTO.getBusca2());
            rs = pstm.executeQuery();
            while (rs.next()) {
                PedidosDTO pdeDTO = new PedidosDTO();
                pdeDTO.setNm_comida(rs.getString("nm_comida"));
                //pdeDTO.setVl_preco("vl_preco");
                consultar.add(pdeDTO);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
        return consultar;
    }

    public void alterarStatus(PedidosDTO pddsDTO) {
        conecte = new Conexao().Conectar();
        String sql = "";
        try {
            if (pddsDTO.getOp().equals("ENTREGUE")) {
                sql = "update tbl_venda set statusC='entregue' where cd_venda='"+pddsDTO.getCd_venda()+"'";
                pstm = conecte.prepareStatement(sql);
                //pstm.setString(1, pddsDTO.getCd_venda());
            } else {
                sql = "delete from tbl_venda where cd_venda='"+pddsDTO.getCd_venda()+"'";
                pstm = conecte.prepareStatement(sql);
                //pstm.setString(1, pddsDTO.getCd_venda());
            }
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }

    public ArrayList<PedidosDTO> Exibir3(PedidosDTO pddsDTO) {
        conecte = new Conexao().Conectar();
        String sql = "select * from tbl_venda where cd_cliente='" + pddsDTO.getBusca2() + "' and statusC='pendente'";
        try {
            pstm = conecte.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                PedidosDTO pdeDTO = new PedidosDTO();
                pdeDTO.setCd(rs.getString("cd_venda"));
                consultar2.add(pdeDTO);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
        return consultar2;
    }

    public ArrayList<PedidosDTO> Item(PedidosDTO pedDTO) {
        String sql = "";
        conecte = new Conexao().Conectar();
        try {
            sql = "select * from tbl_comidas where nm_comida like '%" + pedDTO.getBusca() + "%'";
            pstm = conecte.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                PedidosDTO pdDTO = new PedidosDTO();
                pdDTO.setNm_comida(rs.getString("nm_comida"));
                pdDTO.setCd_comida(rs.getString("cd_comida"));
                itens.add(pdDTO);

            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
        return itens;

    }

    public void NovoPedido(PedidosDTO pdiDTO) {
        conecte = new Conexao().Conectar();
        String sql = "";
        try {
            sql = "insert into tbl_venda(no_Ticket, nome_cliente,cd_cliente, cd_comida,qt_comida, vl_item, dt_venda, cd_mesa, statusC, cdg_garcom,rua,bairro,numero,cidade,estado,opcao) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstm = conecte.prepareStatement(sql);
            
            pstm.setString(1, pdiDTO.getNo_Ticket());
            pstm.setString(2, pdiDTO.getNome_cliente());
            pstm.setString(3, pdiDTO.getCd_cliente());
            pstm.setString(4, pdiDTO.getCd_comida());
            pstm.setString(5, pdiDTO.getQt_comida());
            pstm.setString(6, pdiDTO.getVl_item());
            //pstm.setString(5, pdiDTO.getVl_total_item());
            pstm.setString(7, pdiDTO.getDt_venda());
            pstm.setString(8, pdiDTO.getCd_mesa());
            pstm.setString(9, "pendente");
            pstm.setString(10, pdiDTO.getCdg_garcom());
            if (pdiDTO.getOpcao().toString().equals("Delivery")) {
                pstm.setString(11, pdiDTO.getRua());
                pstm.setString(12, pdiDTO.getBairro());
                pstm.setString(13, pdiDTO.getNumero());
                pstm.setString(14, pdiDTO.getCidade());
                pstm.setString(15, pdiDTO.getEstado());
            } else {
                pstm.setString(11, null);
                pstm.setString(12, null);
                pstm.setString(13, null);
                pstm.setString(14, null);
                pstm.setString(15, null);
            }
            pstm.setString(16, pdiDTO.getOpcao());
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }

    public ArrayList<PedidosDTO> Valores(PedidosDTO pddsDTO) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "select * from tbl_comidas where cd_comida=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setInt(1, pddsDTO.getNconsul());
            rs = pstm.executeQuery();
            while (rs.next()) {
                PedidosDTO pdeDTO = new PedidosDTO();
                pdeDTO.setVl_preco(rs.getString("vl_preco"));
                //pdeDTO.setVl_preco("vl_preco");
                preco.add(pdeDTO);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
        return preco;
    }
    public ArrayList<PedidosDTO> pegarTickets(PedidosDTO p1DTO){
        conecte = new Conexao().Conectar();
        try {
            String sql = "select * from tbl_venda where cd_cliente=? and statusC='pendente'";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, p1DTO.getCd_cliente());
            rs = pstm.executeQuery();
            while (rs.next()) {
                PedidosDTO pdeDTO = new PedidosDTO();
                pdeDTO.setNo_Ticket(rs.getString("no_Ticket"));
                //pdeDTO.setVl_preco("vl_preco");
                tickets.add(pdeDTO);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
        return tickets;
    }
    /*public ResultSet Somar(PedidosDTO p1DTO){
        conecte = new Conexao().Conectar();
        try {
            String sql = "select vl_total_item from tbl_venda where statusC='pendente' and cd_cliente=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, p1DTO.getCd_venda());
            rs = pstm.executeQuery();
            double somas=0;
            while(rs.next()){
                String num=rs.getString("vl_total_item");
                somas=Double.valueOf(num)+somas;
            }
            //System.out.println(somas);
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
    }*/
}
