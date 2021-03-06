package ui;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import chatClient.ChatClientSocket;
import keeptoo.Drag;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author libtv
 */
public class HanbatPhotoShop_CLIENT extends javax.swing.JPanel {
	String nickname;
	String ip;
	int port;
	Socket socket;
	final String token = "@@##:";			// 토큰 정보
	
    /**
     * Creates new form Frame
     */
    public HanbatPhotoShop_CLIENT(String nickname, String ip, int port) {
    	this.ip = ip;
    	this.port = port;
    	this.nickname = nickname;
    	initsock();
        initComponents();
    }

    private void initsock() {
    	socket = new Socket();
        try {
            socket.connect( new InetSocketAddress(ip, port) );
            new PhotoShopClientReceiveThread(socket).start();

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            String request = "join" + token + nickname + "\r\n";
            pw.println(request);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        btn_exit = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField2 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(102, 102, 102));
        setOpaque(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setkEndColor(new java.awt.Color(102, 102, 102));
        kGradientPanel1.setkStartColor(new java.awt.Color(102, 102, 102));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_exit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_exit.setForeground(new java.awt.Color(255, 255, 255));
        btn_exit.setText("X");
        btn_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_exitMouseClicked(evt);
            }
        });
        kGradientPanel1.add(btn_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 0, -1, -1));

        kGradientPanel3.setkEndColor(new java.awt.Color(153, 153, 153));
        kGradientPanel3.setkStartColor(new java.awt.Color(153, 153, 153));
        kGradientPanel1.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 710));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        kGradientPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 930, 60));

        jTextField2.setText("jTextField2");
        jTextField2.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                char keyCode = e.getKeyChar();
                if (keyCode == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        kGradientPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 770, 930, 30));

        add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 800));
    }

    private void btn_exitMouseClicked(java.awt.event.MouseEvent evt) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            String request = "quit " + token + "\r\n";
            pw.println(request);
            okButtonAction();
        }
        catch (IOException e1) {
            e1.printStackTrace();
            okButtonAction();
        }
    }//GEN-LAST:event_btn_exitMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseMoved

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseDragged        
    
    private void sendMessage() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            String message = jTextField2.getText();
            String request = "message" + token + message + "\r\n";
            pw.println(request);

            jTextField2.setText("");
            jTextField2.requestFocus();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void okButtonAction() {
        Window win = SwingUtilities.getWindowAncestor(this);
        if (win != null) {
           win.dispose();
        }
     }
    
    private class PhotoShopClientReceiveThread extends Thread {
        Socket socket = null;

        PhotoShopClientReceiveThread(Socket socket){
            this.socket = socket;
        }
        
        public void run() {
            try {
                DataInputStream br = new DataInputStream(socket.getInputStream());
                while(true) {
                	
                    String msg = br.readUTF();
                    String[] tokens = msg.split(token);
                    
                    if("message".equals(tokens[0])) {
                        jTextArea1.append(br.readUTF());
                        jTextArea1.append("\n");
                    } else if("bufferedimage".equals(tokens[0])) {
                    	int len = br.readInt();
                        byte[] data = new byte[len];
                        br.readFully(data);
                        InputStream ian = new ByteArrayInputStream(data);
                        BufferedImage bImage = ImageIO.read(ian);
                        Image_draw(bImage);
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void Image_draw(BufferedImage bi) {
		// 이미지 resize 하기
    	Image resizeImage;
    	BufferedImage photoshop_Image;
		int w = kGradientPanel3.getWidth();
		int h = kGradientPanel3.getHeight();
		resizeImage = bi.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		photoshop_Image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);		// 새로운 이미지를 만들어서 rgb 형태로 만들고
		
		// photoshop_Image 에서는 그리기
		Graphics g = photoshop_Image.getGraphics();								
		g.drawImage(resizeImage, 0, 0, null);
		g.dispose();
		
		Graphics2D draw = (Graphics2D)kGradientPanel3.getGraphics();					// 패널을 알아내서
		draw.drawImage(photoshop_Image, 0, 0, null);									// 패널에 그리기

    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JLabel btn_exit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel3;
    // End of variables declaration                 
}
