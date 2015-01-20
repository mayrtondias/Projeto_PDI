package Interface;


import java.awt.*;
import java.io.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

public class Estudos extends JFrame{
  private BufferedImage imagem;
  AreaImagem areaImagem;  

  public Estudos(){
    super("Estudos Java");
    
    Container c = getContentPane();
    c.setLayout(new BorderLayout());
 
    JButton btn = new JButton("Carregar Imagem");
    btn.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          JFileChooser fc = new JFileChooser();

          int res = fc.showOpenDialog(null);
          if(res == JFileChooser.APPROVE_OPTION){
     File arquivo = fc.getSelectedFile();  
          
            imagem = null;
          
            try{
              imagem = ImageIO.read(arquivo);
            }
            catch(IOException exc){
              JOptionPane.showMessageDialog(null, 
                "Erro ao carregar a imagem: " + 
                exc.getMessage());
            }

            if(imagem != null){
              areaImagem.imagem = imagem;
              areaImagem.repaint();  
            }
          }
        }
      }
    );

    c.add(btn, BorderLayout.SOUTH);
    
    // Cria a área de exibição da imagem
    areaImagem = new AreaImagem();
    c.add(areaImagem, BorderLayout.CENTER);    
 
    setSize(400, 300);
    setVisible(true);
  }
  
  public static void main(String args[]){
    Estudos app = new Estudos();
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}

// Sub-classe de JPanel para exibir a imagem
class AreaImagem extends JPanel{
  public BufferedImage imagem;

  public void paintComponent(Graphics g){ 
    super.paintComponent(g);
    
    // desenha a imagem no JPanel
    g.drawImage(imagem, 0, 0, this);
  } 
}