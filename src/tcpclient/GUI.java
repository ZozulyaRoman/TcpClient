

package tcpclient;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;



public class GUI {
    private JFrame frame;
    private JPanel panel;
    private JScrollPane scrollPaneIn;
    private JScrollPane scrollPaneOut;
    private JTextArea chatText;
    private JTextArea messText;
    private JButton button;
    private JPasswordField registrationForm;

    public GUI() {
        CreateGUI();
    }

    private void CreateGUI(){
    
    createTextFields();
    createButton();
    createScrollPane();
    createPanel();
    createFrame();
    frame.setVisible(true);
    
    }

    private void createFrame() {
        frame = new JFrame("MyMessenger`s Client");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 500));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(scrollPaneIn);
        frame.add(panel);
    }

    private void createButton() {
        button=new JButton("Send");
        button.addActionListener(new BtnListener(messText));
        button.setPreferredSize(new Dimension(70, 100));
    }

    private void createTextFields() {
        messText = new JTextArea("");
        messText.setBorder(BorderFactory.createEtchedBorder());
        messText.setLineWrap(true);
        
        chatText = new JTextArea();
        chatText.setBackground(new Color(250, 250, 240));
        chatText.setFocusable(false);
        chatText.setLineWrap(true);
    }

    private void createPanel() {
        panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(380,120));
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.add(scrollPaneOut);
        panel.add(button);
    }

    private void createScrollPane() {
        scrollPaneIn = new JScrollPane(chatText);
        scrollPaneIn.setBorder(BorderFactory.createEtchedBorder());
        scrollPaneIn.setPreferredSize(new Dimension(380, 335));
        
        scrollPaneOut = new JScrollPane(messText);
        scrollPaneOut.setBorder(BorderFactory.createEtchedBorder());
        scrollPaneOut.setPreferredSize(new Dimension(280, 100));

    }
    
    public void sendMessToArea(String text){
        chatText.setSelectedTextColor(Color.BLUE);
            chatText.append(text);
            chatText.append("\n");
        chatText.setCaretPosition(chatText.getText().length());

    }

}
