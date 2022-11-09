package net.mips.compiler;

import java.io.IOException;

public class Parser {
	Scanner scanner;

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public Parser(String filename) throws IOException, ErreurCompilation {
		scanner = new Scanner(filename);
		
	}
	
	public void testeAccept(Tokens t,CodesErr c) throws Exception {
		if(scanner.getSymbCour().getToken()==t) {
			scanner.symbSuiv();
		}
		else {
			throw new ErreurLexicale(c);
		}
			
			
	}
	
	public void PROGRAM() throws Exception {
		testeAccept(Tokens.PROGRAM_TOKEN,CodesErr.PROGRAM_ERR);
		testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		testeAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
		BLOCK();
		testeAccept(Tokens.PNT_TOKEN,CodesErr.PNT_ERR);
	}
	public void BLOCK() throws Exception {
		CONSTS();
		VARS();
		INSTS();
		
	}
	public void CONSTS() throws Exception,ErreurLexicale {
		
		testeAccept(Tokens.CONST_TOKEN,CodesErr.CONSTS_ERR);
	}
	public void VARS() throws Exception {
		testeAccept(Tokens.VAR_TOKEN,CodesErr.VARS_ERR);
	}
	public void INSTS() throws Exception {
		testeAccept(Tokens.INST_TOKEN,CodesErr.INST_ERR);
	}
	public void INST() throws Exception {
		
	}
    public void affec() throws Exception {
		
	}
    public void si() throws Exception {
		
	}
	
    public void tantque() throws Exception {
		
	}
public void ecrire() throws Exception {
		
	}
public void lire() throws Exception {
	
}
public void cond() throws Exception {
	
}
public void expr() throws Exception {
	
}
public void term() throws Exception {
	
}
public void fact() throws Exception {
	
}
}
