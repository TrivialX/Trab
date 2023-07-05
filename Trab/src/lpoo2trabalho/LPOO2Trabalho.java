/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lpoo2trabalho;

import DAO.ClienteDao;
import DAO.ConnectionFactory;
import DAO.ContaDao;
import Model.Cliente;
import static Model.Cliente.ordenaNome;
import static Model.Cliente.ordenaSalario;
import static Model.Cliente.ordenaSobrenome;
import Model.Conta;
import Model.ContaCorrente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T-GAMER
 */
public class LPOO2Trabalho {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try {
            ClienteDao dao = new ClienteDao(new ConnectionFactory());
            ContaDao cdao = new ContaDao();
            List<Cliente> lista= new ArrayList<>();
            Conta conta = null;
            conta = cdao.buscar("21212121212");
          //  Conta conta = new ContaCorrente();
           // Cliente client = new Cliente();
           // conta = cdao.buscar("51515151515");
           /* System.out.println(conta.getNumero());
            System.out.println(conta.getTipo().toString().replace("_"," "));
            System.out.println(conta.getSaldo());
            System.out.println(conta.getDono().getCPF());
            System.out.println(conta.getDono().getNome());*/
            lista = dao.listar();
            ordenaNome(lista);
            for (Cliente cliente: lista){
                System.out.println(cliente.getNome());
            }
            ordenaSalario(lista);
        } catch (Exception ex) {
            Logger.getLogger(LPOO2Trabalho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}