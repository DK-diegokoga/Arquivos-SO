package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {
		
		IArquivosController arqCont = new ArquivosController();
		
//		String dirWin = "C:\\Windows";
		String path = "C:\\SemanadeEstudos\\Teste";//chumbar o caminho que precisa fazer ate a pasta
//		String name = "teste.csv";//salva no excel
		String name = "teste.txt";//salva no txt
		
		try {
//			arqCont.readDir(dirWin);
			arqCont.createFile(path, name);//Insere dados no txt ou excel se for usar o teste.csv
			arqCont.readFile(path,name);//lê e apresenta os dados do arquivo no console
//			arqCont.openFile(path, name);//abre a aplicação com os dados 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}