

package tcpclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;



public class BtnListener implements ActionListener{
    private JTextArea messText;

    public BtnListener(JTextArea messText) {
        this.messText = messText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(e.getSource() instanceof JButton)) {
            return;
        }
        JButton btn = (JButton) e.getSource();
        if(messText.getText()!=null&&!messText.getText().trim().equals("")){
            TcpClient.setTextToSend(messText.getText());
        }

        messText.setText("");
    }

}
