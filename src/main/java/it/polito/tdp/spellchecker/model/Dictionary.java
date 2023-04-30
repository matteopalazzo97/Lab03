package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	public enum Languages{
		Italian,
		English
	}
	
	private List <String> dizionario;
	private Languages language;
	
	public Dictionary() {
		//costruttore vuoto per poterlo creare quando serve dall'interfaccia grafica
	}
	
	public void loadDictionary (Languages language) throws IOException, IllegalArgumentException {
		//da qua
		if(language == null) {
			throw new IllegalArgumentException("Lingua selezionata non valida.");
		}
		/*
		 * Allora porcoddio: questa merda scatenava 984654436 errori del porco di dio e in più è una cosa che non si
		 * verifica usando il programma normalmente quindi porcoddio non voglio saperne un cazzo
		 * 
		 * Il problema era dovuto all'equals tra i language che sono strani visto che sono fatti con enum
		 * che non so bene come funziona con il tipo di dato, nel senso che non so se Languages è una classe o cosa
		 * 
		if(language != null && this.language.equals(language)) {
			System.out.println("Dizionario già letto.");
			return;
		}
		*/
		
		//a qua
		//sono controllini del cazzo, a me interessa usare il pattern MVC
		
		List <String> nuovoDizionario = new LinkedList <String>();
		
		try {
			
			FileReader fr = new FileReader("src/main/resources/" + language.toString() + ".txt");
			BufferedReader br = new BufferedReader(fr);
			
			String parola;
			
			while((parola = br.readLine()) != null) {
				nuovoDizionario.add(parola);
			}
			
			br.close();
			
			this.dizionario = nuovoDizionario;
			
			System.out.println("Ho letto il dizionario. Contiene " + this.dizionario.size() + " parole.");
			/*
			 * SONO LE 2:14 E SONO RIUSCITO A LEGGERE IL DIZIONARIO: SEMBRA UN PICCOLO PASSO MA 
			 * SOLO CON QUESTI POSSO FARE UN LUNGO PERCORSO VERSO IL PASSAGGIO DELL'ESAME
			 * DOMANI SI CONTINUA. FORZA PORCODDIOOOOOO!!!!
			 */
			return;
			
		} catch (FileNotFoundException e) {
			System.out.println("Errore lettura file.");
		}
		
	}

	public List<RichWord> controllo(List<String> listaTestoInput) {
		
		/*
		 * questo stupido di metodo deve trasformare la lista di stringhe che riceve in una lista di richword
		 * e settare false e true opportunamente
		 */
		
		List<RichWord> risultato = new LinkedList<RichWord>();
		
		for(String s: listaTestoInput) {
			
			RichWord r = new RichWord(s);
			
			if(!dizionario.contains(s.toLowerCase())) {
				r.setCorretta(false);
			} else {
				r.setCorretta(true);
			}
			
			risultato.add(r);
		}
		
		return risultato;
	}

}
