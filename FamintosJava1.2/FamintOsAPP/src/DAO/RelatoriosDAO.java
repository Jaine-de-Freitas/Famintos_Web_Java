/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.RelatoriosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jaine
 */
public class RelatoriosDAO {

    Connection conecte;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<RelatoriosDTO> listar = new ArrayList<>();
    ArrayList<RelatoriosDTO> consultar = new ArrayList<>();

    public ArrayList<RelatoriosDTO> PdfGere(RelatoriosDTO rdto) {
        String sql = "";
        String tp_relat, periodo, tp_dado, mes, semana, dia;
        int ano;
        tp_relat = rdto.getTp_relat();
        periodo = rdto.getPeriodo();
        tp_dado = rdto.getTp_dado();
        mes = rdto.getMes();
        semana = rdto.getSemana();
        dia = rdto.getDia();
        ano = rdto.getAno();
        conecte = new Conexao().Conectar();
        try{
        if (tp_relat.equals("VENDAS")) {
            if (periodo.equals("DIÁRIO")) {
                sql = "select *from tbl_venda where dt_venda like'%" + ano + "-" + mes + "-" + dia + "%' and statusC like '%entregue%'";
            } else if (periodo.equals("SEMANAL")) {
                if (semana.equals("SEMANA 1")) {
                    sql = "select *from tbl_venda where dt_venda between '" + ano + "-" + mes + "-" + "01" + "' and '" + ano + "-" + mes + "-" + "07" + "' and statusC like '%entregue%'";
                } else if (semana.equals("SEMANA 2")) {
                    sql = "select *from tbl_venda where dt_venda between '" + ano + "-" + mes + "-" + "08" + "' and '" + ano + "-" + mes + "-" + "14" + "' and statusC like '%entregue%'";
                } else if (semana.equals("SEMANA 3")) {
                    sql = "select *from tbl_venda where dt_venda between '" + ano + "-" + mes + "-" + "15" + "' and '" + ano + "-" + mes + "-" + "21" + "' and statusC like '%entregue%'";
                } else if (semana.equals("SEMANA 4")) {
                    sql = "select *from tbl_venda where dt_venda between '" + ano + "-" + mes + "-" + "22" + "' and '" + ano + "-" + mes + "-" + "31" + "' and statusC like '%entregue%'";
                }
            } else if (periodo.equals("MENSAL")) {
                sql = "select *from tbl_venda where dt_venda between '" + ano + "-" + mes + "-" + "01" + "' and '" + ano + "-" + mes + "-" + "31" + "' and statusC like '%entregue%'";
            } else if (periodo.equals("ANUAL")) {
                sql = "select *from tbl_venda where dt_venda between '" + ano + "-" + "01" + "-" + "01" + "' and '" + ano + "-" + "12" + "-" + "31" + "' and statusC like '%entregue%'";
            }
        } else {
            if (tp_dado.equals("ENTRADAS")) {
                if (periodo.equals("DIÁRIO")) {
                    sql = "select *from gerir_estoque where date(data_cadastro) like'%" + ano + "-" + mes + "-" + dia + "%' and operacao like '%entrada%'";
                } else if (periodo.equals("SEMANAL")) {
                    if (semana.equals("SEMANA 1")) {
                        sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + mes + "-" + "01" + "' and '" + ano + "-" + mes + "-" + "07" + "' and operacao like '%entrada%'";
                    } else if (semana.equals("SEMANA 2")) {
                        sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + mes + "-" + "08" + "' and '" + ano + "-" + mes + "-" + "14" + "' and operacao like '%entrada%'";
                    } else if (semana.equals("SEMANA 3")) {
                        sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + mes + "-" + "15" + "' and '" + ano + "-" + mes + "-" + "21" + "' and operacao like '%entrada%'";
                    } else if (semana.equals("SEMANA 4")) {
                        sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + mes + "-" + "22" + "' and '" + ano + "-" + mes + "-" + "31" + "' and operacao like '%entrada%'";
                    }
                } else if (periodo.equals("MENSAL")) {
                    sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + mes + "-" + "01" + "' and '" + ano + "-" + mes + "-" + "31" + "' and operacao like '%entrada%'";
                } else if (periodo.equals("ANUAL")) {
                    sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + "01" + "-" + "01" + "' and '" + ano + "-" + "12" + "-" + "31" + "' and operacao like '%entrada%'";
                }
            } else if (tp_dado.equals("SAIDAS")) {
                if (periodo.equals("DIÁRIO")) {
                    sql = "select *from gerir_estoque where data_cadastro like'%" + ano + "-" + mes + "-" + dia + "%' and operacao like '%saida%'";
                } else if (periodo.equals("SEMANAL")) {
                    if (semana.equals("SEMANA 1")) {
                        sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + mes + "-" + "01" + "' and '" + ano + "-" + mes + "-" + "07" + "' and operacao like '%saida%'";
                    } else if (semana.equals("SEMANA 2")) {
                        sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + mes + "-" + "08" + "' and '" + ano + "-" + mes + "-" + "14" + "' and operacao like '%saida%'";
                    } else if (semana.equals("SEMANA 3")) {
                        sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + mes + "-" + "15" + "' and '" + ano + "-" + mes + "-" + "21" + "' and operacao like '%saida%'";
                    } else if (semana.equals("SEMANA 4")) {
                        sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + mes + "-" + "22" + "' and '" + ano + "-" + mes + "-" + "31" + "' and operacao like '%saida%'";
                    }
                } else if (periodo.equals("MENSAL")) {
                    sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + mes + "-" + "01" + "' and '" + ano + "-" + mes + "-" + "31" + "' and operacao like '%saida%'";
                } else if (periodo.equals("ANUAL")) {
                    sql = "select *from gerir_estoque where date(data_cadastro) between '" + ano + "-" + "01" + "-" + "01" + "' and '" + ano + "-" + "12" + "-" + "31" + "' and operacao like '%saida%'";
                }
            } else if (tp_dado.equals("EM ESTOQUE/GERAL")) {
                sql = "select *from Produtos";
            }
        }
            pstm = conecte.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (sql.contains("tbl_venda")) {
                while (rs.next()) {
                    RelatoriosDTO relDTO = new RelatoriosDTO();
                    relDTO.setCd_venda(rs.getString("cd_venda"));
                    relDTO.setNo_Ticket(rs.getString("no_Ticket"));
                    relDTO.setNome_cliente(rs.getString("nome_cliente"));
                    relDTO.setCd_cliente(rs.getString("cd_cliente"));
                    relDTO.setCd_comida(rs.getString("cd_comida"));
                    relDTO.setQt_comida(rs.getString("qt_comida"));
                    relDTO.setVl_item(rs.getString("vl_item"));
                    relDTO.setVl_total_item(rs.getString("vl_total_item"));
                    relDTO.setDt_venda(rs.getString("dt_venda"));
                    relDTO.setCd_mesa(rs.getString("cd_mesa"));
                    relDTO.setStatusC(rs.getString("statusC"));
                    listar.add(relDTO);
                }
            } else if (sql.contains("Produtos")) {
                while (rs.next()) {
                    RelatoriosDTO relDTO = new RelatoriosDTO();
                    relDTO.setId_produto(rs.getString("id_produto"));
                    relDTO.setProduto(rs.getString("produto"));
                    relDTO.setCategoria(rs.getString("categoria"));
                    relDTO.setLote(rs.getString("lote"));
                    relDTO.setVencimento(rs.getString("vencimento"));
                    relDTO.setQuantidade(rs.getString("quantidade"));
                    relDTO.setValor(rs.getString("valor"));
                    relDTO.setData_cadastro(rs.getString("data_cadastro"));
                    listar.add(relDTO);
                }
            } else if (sql.contains("gerir_estoque")) {
                while (rs.next()) {
                    RelatoriosDTO relDTO = new RelatoriosDTO();
                    relDTO.setId_dado(rs.getString("id_dado"));
                    relDTO.setId_produtoG(rs.getString("id_produto"));
                    relDTO.setProdutoG(rs.getString("produto"));
                    relDTO.setCategoriaG(rs.getString("categoria"));
                    relDTO.setLoteG(rs.getString("lote"));
                    relDTO.setVencimentoG(rs.getString("vencimento"));
                    relDTO.setQtd_estoque(rs.getString("qtd_estoque"));
                    relDTO.setValorG(rs.getString("valor"));
                    relDTO.setData_cadastroG(rs.getString("data_cadastro"));
                    relDTO.setOperacao(rs.getString("operacao"));
                    relDTO.setEntrada(rs.getString("entrada"));
                    relDTO.setSaida(rs.getString("saida"));
                    relDTO.setVl_total(rs.getString("vl_total"));
                    listar.add(relDTO);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RelatoriosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listar;
    }
    public ArrayList<RelatoriosDTO> Exibir(RelatoriosDTO rdto) {
        conecte = new Conexao().Conectar();
        try {
            String sql = "select * from tbl_comidas where cd_comida=?";
            pstm = conecte.prepareStatement(sql);
            pstm.setString(1, rdto.getBusca());
            rs = pstm.executeQuery();
            while (rs.next()) {
                RelatoriosDTO relsDTO = new RelatoriosDTO();
                relsDTO.setNm_comida(rs.getString("nm_comida"));
                consultar.add(relsDTO);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
            return null;
        }
        return consultar;
    }
}
