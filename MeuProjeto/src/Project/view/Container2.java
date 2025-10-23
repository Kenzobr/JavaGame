package Project.view;
import javax.swing.JFrame;
import Project.service.Fase;

public class Container2 extends JFrame {
	public Container2(String nomeJogador) {
		// Cria um novo objeto Fase com o nome do jogador
		add(new Fase(nomeJogador));
		System.out.println("Inciando");
		setTitle("Kenzo Games");
		setSize(1024, 728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centraliza a janela
		this.setResizable(true);
		setVisible(true);
	}

}