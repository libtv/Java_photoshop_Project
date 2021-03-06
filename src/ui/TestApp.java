package ui;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import keeptoo.Drag;


public class TestApp extends javax.swing.JPanel implements ActionListener {

    /**
     * create 변수
     */
	
	int w = 560;
	int h = 720;
	BufferedImage photoshop_Image;
	Stack<BufferedImage> imageRepo = new Stack<BufferedImage>();
    /**
     * create 변수
     */
	
	
    public TestApp() {
        initComponents();
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
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jButton1 = new javax.swing.JButton();

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
        kGradientPanel1.add(btn_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, -1, -1));

        kGradientPanel3.setkEndColor(new java.awt.Color(153, 153, 153));
        kGradientPanel3.setkStartColor(new java.awt.Color(153, 153, 153));
        kGradientPanel1.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 930, 710));

        kGradientPanel2.setkEndColor(new java.awt.Color(102, 102, 102));
        kGradientPanel2.setkStartColor(new java.awt.Color(51, 51, 51));
        kGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Load");
        jButton1.addActionListener(this);
        kGradientPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 30, 70, -1));

        kGradientPanel1.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 710));

        add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 710));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseClicked
        System.exit(0);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_exit;
    private javax.swing.JButton jButton1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    // End of variables declaration//GEN-END:variables
    
    public static void main(String args[]) {
    	JDialog dialog = new JDialog();
		dialog = new JDialog(null, "Students",
				ModalityType.APPLICATION_MODAL);
		dialog.setUndecorated(false);
		dialog.getContentPane().add(new TestApp());
		dialog.pack();
		dialog.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton myButton = (JButton)e.getSource();
		String temp = myButton.getText();
		if(temp.equals("Load")) {
			LoadImage();
		}
	}
	
	// 이미지 복사하기 메소드
	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	// 이미지 Load 메소드
	private void LoadImage() {
		JFileChooser chooser = new JFileChooser();
		String filePath;
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&GIF Images", "jpg", "gif", "jpeg");
		chooser.setFileFilter(filter);
		int ret = chooser.showOpenDialog(null);
		if(ret!= JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//파일 경로 알아내서
		filePath = chooser.getSelectedFile().getPath();
		try {
			Image resizeImage;															// 이미지 변경
			BufferedImage image;														// 저장된 이미지			
			File input = new File(filePath);											// 파일 이미지를 불러와서
			image = ImageIO.read(input);												// 버퍼 이미지에 저장하고
			// 이미지 resize 하기
			w = kGradientPanel3.getWidth();
			h = kGradientPanel3.getHeight();
			resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			photoshop_Image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);		// 새로운 이미지를 만들어서 rgb 형태로 만들고
			
			// photoshop_Image 에서는 그리기
			Graphics g = photoshop_Image.getGraphics();								
			g.drawImage(resizeImage, 0, 0, null);
			g.dispose();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Graphics2D draw = (Graphics2D)kGradientPanel3.getGraphics();					// 패널을 알아내서
		draw.drawImage(photoshop_Image, 0, 0, null);									// 패널에 그리기
		imageRepo.push(photoshop_Image); 												// 원본 이미지 저장
	}
}
