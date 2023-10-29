package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;

import model.Cliente;
import model.Lista;

public class ModificacaoCadastroController {

	public ModificacaoCadastroController() {
		super();
	}
	
	private void novoArquivo(Lista<Cliente> l, String nomeArquivo) throws Exception {
		File dir = new File("C:\\TEMP");
		File arq = new File(dir, nomeArquivo);
		if (dir.exists() && dir.isDirectory()) {
			boolean arquivoExiste = false;
			if (arq.exists()) {
				arquivoExiste = true;
			}
			
			int tamanho = l.size();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < tamanho; i++) {
				Cliente c = l.get(i);
				String cliente = c.CPF+";"+c.nome+";"+c.idade+";"+c.credito+"\r\n";
				buffer.append(cliente);
			}
			
			FileWriter fw = new FileWriter(arq, arquivoExiste);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(buffer.toString());
			pw.flush();
			pw.close();
			fw.close();
		}
		else {
			throw new IOException("Diretório Inválido");
		}
	}
	
	public void novoCadastro(int idadeMin, int idadeMax, double limiteCredito) throws Exception {
		Lista<Cliente> l = new Lista<>();
		File arq = new File("C:\\TEMP", "cadastro.csv");
		if (arq.exists()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while (linha!=null) {
				linha = linha.replace("\"", "");
				String [] split = linha.split(";");
				if (split.length>1) {
					NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
					Number number = format.parse(split[3]);
					double credito = number.doubleValue();
					Cliente c = new Cliente(split[0], split[1], Integer.parseInt(split[2]), credito);
					if (c.idade >= idadeMin && c.idade <= idadeMax && c.credito>limiteCredito) {
						l.addLast(c);
					}
				}
				linha = buffer.readLine();
			}
			
			String nomeArquivo = "Idade "+idadeMin+" a " +idadeMax+" limite "+limiteCredito+".csv";
			novoArquivo(l, nomeArquivo);
			
			buffer.close();
			leitor.close();
			fluxo.close();
		}
	}
}


