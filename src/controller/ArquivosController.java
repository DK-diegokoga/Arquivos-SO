package controller;

//pacotes importados
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController{

	public ArquivosController() {
		super();

	}
	@Override
	public void readDir(String path) throws IOException {
		File dir = new File(path);
		if (dir.exists()&& dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				if(f.isFile()) {
					System.out.println("     \t" + f.getName());
				}else {
					System.out.println("<DIR>\t" + f.getName());
				}
			}
		}else {
			throw new IOException("Dirtório inválido");
		}
	}
	@Override
	public void createFile(String path, String name) throws IOException {
		File dir = new File(path);
		//File arq = new File("C:\\TEMP\\", "arquivo.txt");
//		File arq = new File(path, name + ".txt");//define um file com o caminho do diretório
		File arq = new File(path, name);
		if (dir.exists() && dir.isDirectory()){
			boolean existe = false;
			if (arq.exists()) {
				existe = true;//verifica se o arquivo existe
			}
			String conteudo = geraTxt();//gera um texto
			FileWriter fileWriter = new FileWriter(arq, existe);//abre o arquivo e define o tipo de operação de escrita 
			PrintWriter print = new PrintWriter(fileWriter);//
			print.write(conteudo);
			print.flush();//vai finalizar
			print.close();//para não deixar nada aberto
			fileWriter.close();//fecha o arquivo	
		}else {
			throw new IOException("Diretório Inválido");
		}
	}
	private String geraTxt() {
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		while(!linha.equalsIgnoreCase("fim")) {
			linha = JOptionPane.showInputDialog(null, "Digite uma frase ",
												"Entrada de texto",
												JOptionPane.INFORMATION_MESSAGE);
			if(!linha.equalsIgnoreCase("fim")) {
				buffer.append(linha + "\r\n");
			}
		}
		return buffer.toString();
	}
	@Override
	public void readFile(String path, String name) throws IOException {
		File arq = new File(path, name);
		if (arq.exists()&& arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);//abre o arquivo
			InputStreamReader leitor = new InputStreamReader(fluxo);//lê e converte o arquivo
			BufferedReader buffer = new BufferedReader(leitor);//guarda em um buffer
			String linha = buffer.readLine();
			while(linha!= null) {//procurando EOF final da linha 
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();//fecha o arquivo
		}else {
			throw new IOException("Arquivo Inválido");
		}
		
	}
	@Override
	public void openFile(String path, String name) throws IOException {
		File arq = new File (path, name);
		if (arq.exists()&& arq.isFile()) {
			Desktop desktop = Desktop.getDesktop();//faz abertura e a leitura de um arquivo lançando a partir da aplicação que esta associada  
			desktop.open(arq);
			
		}else {
			throw new IOException("Arquivo Inválido");
		}
		
	}
}