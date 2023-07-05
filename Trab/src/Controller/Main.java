/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ClienteDao;
import DAO.ConnectionFactory;
import View.JanelaClienteView;

/**
 *
 * @author Gabriel
 */
public class Main {
    public static void main(String args[]){
        JanelaClienteView view = new JanelaClienteView();
        ClienteDao dao = new ClienteDao(new ConnectionFactory());
        ClienteController controller = new ClienteController(view,dao);
    }
    
}
