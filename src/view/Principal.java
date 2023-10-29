package view;

import controller.ModificacaoCadastroController;

public class Principal {

	public static void main(String[] args) {
		ModificacaoCadastroController m = new ModificacaoCadastroController();
		try {
			m.novoCadastro(41, 60, 8000);
			m.novoCadastro(31, 40, 5000);
			m.novoCadastro(21, 30, 3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
