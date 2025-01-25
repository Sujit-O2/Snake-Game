import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
      int BWid=600;
      int BHei=600;
      JFrame ss=new JFrame("Snake Game");
      ss.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ss.setSize(BWid,BHei);
      ss.setLocationRelativeTo(null);
      ss.setVisible(true);
      ss.setResizable(false);
      SnakeGame sg=new SnakeGame(BWid,BHei);
     
      ss.add(sg);
      ss.pack();
      sg.requestFocus();
    }
}
