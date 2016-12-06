/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import midi.Drum;
import midi.Note;
import midi.Tools;



/**
 *
 * @author ZozulyaRV
 */
public class TcpClient {
    private static final String DEFAULT_HOST="localhost";
    private static final int DEFAULT_PORT=6666;
    
    private static String textToSend=null;
    
    public static void setTextToSend(String textToSend) {
       TcpClient.textToSend = textToSend;
    }
    
    public static String getTextToSend() {
        return textToSend;
    }
    
    private static void playSound() {
        Tools.playDrum(Drum.d36_Bass_Drum_1, 100, 2000);
        
    }  
    
    public static void main(String[] args) {
        String host=DEFAULT_HOST;
        int port = DEFAULT_PORT;
        Socket socket=null;
        try {
            socket = new Socket(host,port);
        } catch (UnknownHostException ex) {
            System.out.println("Неизвестный хост: "+host);
            System.exit(-1);
        } catch (IOException ex) {
            System.out.println("Ошибка ввода-вывода при создании сокета "+host+":"+port);
            System.exit(-1);
        }
        
        BufferedReader socketReader=null;
        PrintWriter socketWriter=null;
        try {
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketWriter = new PrintWriter(socket.getOutputStream());
            socketWriter.println(JOptionPane.showInputDialog("Введите имя"));
            socketWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(TcpClient.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }

        GUI gui = new GUI();

        String messageFromServer=null;
        try {
            while(true){
                if(socketReader.ready()){
                    messageFromServer=socketReader.readLine();
                    gui.sendMessToArea(messageFromServer);
                    System.out.println(messageFromServer);
                    System.out.flush();
                    playSound();
                }
                if(TcpClient.getTextToSend()!=null){
                    socketWriter.println(TcpClient.getTextToSend());
                    socketWriter.flush();
                    TcpClient.setTextToSend(null);
                }
                
            }    
        } catch (IOException ex) {
            Logger.getLogger(TcpClient.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }

        
    }

}
