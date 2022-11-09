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
		testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		testeAccept(Tokens.EG_TOKEN,CodesErr.EG_ERR);
		testeAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
		testeAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
		while(scanner.getSymbCour().getToken()==Tokens.ID_TOKEN) {
			scanner.symbSuiv();
			testeAccept(Tokens.EG_TOKEN,CodesErr.EG_ERR);
			testeAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
			testeAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
			
		}
		
		
		
	}
	public void VARS() throws Exception {
		testeAccept(Tokens.VAR_TOKEN,CodesErr.VARS_ERR);
		testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		while(scanner.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
			scanner.symbSuiv();
			testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
			}
		testeAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
			
	}
	
	public void INSTS() throws Exception {
		testeAccept(Tokens.BEGIN_TOKEN,CodesErr.BEGIN_ERR);
		INST();
		while(scanner.getSymbCour().getToken()==Tokens.PVIR_TOKEN) {
			scanner.symbSuiv();
			INST();
		}
		testeAccept(Tokens.END_TOKEN,CodesErr.END_ERR);
	}
	public void AFFEC() throws Exception {
		testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		testeAccept(Tokens.AFFEC_TOKEN,CodesErr.AFFEC_ERR);
		EXPR();
	}
	public void INST() throws Exception {
		switch(scanner.getSymbCour().getToken()) {
		case BEGIN_TOKEN:
		   INSTS();
			break;
		case ID_TOKEN:
			AFFEC();
			break;
		case IF_TOKEN:
			SI();
			break;
		case WHILE_TOKEN:
			TANQUE();
			break;
		case WRITE_TOKEN:
	        ECRIRE();
	        break;
		case READ_TOKEN:
			LIRE();
			break;
	default:
		throw new ErreurSyntaxique(CodesErr.INST_ERR);
			
		}
		
		
	}
   
    public void SI() throws Exception {
    	testeAccept(Tokens.IF_TOKEN, CodesErr.IF_ERR);
		cond();
		testeAccept(Tokens.THEN_TOKEN, CodesErr.THEN_ERR);
		INST();
	}
	
    public void TANQUE() throws Exception {
    	testeAccept(Tokens.WHILE_TOKEN, CodesErr.WHILE_ERR);
		cond();
		testeAccept(Tokens.DO_TOKEN, CodesErr.DO_ERR);
		INST();
	}
public void ECRIRE() throws Exception,ErreurCompilation{
	testeAccept(Tokens.WRITE_TOKEN, CodesErr.WRITE_ERR);
	testeAccept(Tokens.PARG_TOKEN, CodesErr.PARG_ERR);
	EXPR();
	while(scanner.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
		scanner.symbSuiv();
		EXPR();
	}
	testeAccept(Tokens.PARD_TOKEN, CodesErr.PARD_ERR);
}
public void LIRE() throws  ErreurCompilation, Exception {
	testeAccept(Tokens.READ_TOKEN, CodesErr.READ_ERR);
	testeAccept(Tokens.PARG_TOKEN, CodesErr.PARG_ERR);
	testeAccept(Tokens.ID_TOKEN, CodesErr.ID_ERR);
	while(scanner.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
		scanner.symbSuiv();
		testeAccept(Tokens.ID_TOKEN, CodesErr.ID_ERR);
	}
	testeAccept(Tokens.PARD_TOKEN, CodesErr.PARD_ERR);
}

public void cond() throws Exception {
	EXPR();
	switch(scanner.getSymbCour().getToken()) {
	case EG_TOKEN:
		scanner.symbSuiv();
		break;
	case DIFF_TOKEN:
		scanner.symbSuiv();
		break;
	case INF_TOKEN:
		scanner.symbSuiv();
		break;
	case SUP_TOKEN:
		scanner.symbSuiv();
		break;
	case INFEG_TOKEN:
		scanner.symbSuiv();
		break;
	case SUPEG_TOKEN:
		scanner.symbSuiv();
		break;
	default:
		throw new ErreurSyntaxique(CodesErr.RELOP_ERR);
	}
	EXPR();
}

public void EXPR() throws Exception,ErreurCompilation {
	term();
	while(scanner.getSymbCour().getToken()==Tokens.PLUS_TOKEN || scanner.getSymbCour().getToken()==Tokens.MOINS_TOKEN) {
		addop(); 
	     term();
}
}
public void term() throws Exception {
	fact();
	while(scanner.getSymbCour().getToken()==Tokens.MUL_TOKEN || scanner.getSymbCour().getToken()==Tokens.DIV_TOKEN) {
		scanner.symbSuiv();
		fact();
	}
}
public void fact() throws Exception {
	if(scanner.getSymbCour().getToken()==Tokens.ID_TOKEN) {
		testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
	}else if (scanner.getSymbCour().getToken()==Tokens.NUM_TOKEN) {
		testeAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
	}else if(scanner.getSymbCour().getToken()==Tokens.PARG_TOKEN) {
		testeAccept(Tokens.PARG_TOKEN,CodesErr.PARG_ERR);
       EXPR();
         testeAccept(Tokens.PARD_TOKEN,CodesErr.PARD_ERR);
}
}
public void addop() throws Exception {
	if(scanner.getSymbCour().getToken()==Tokens.PLUS_TOKEN) {
		testeAccept(Tokens.PLUS_TOKEN,CodesErr.PLUS_ERR);
	}else if (scanner.getSymbCour().getToken()==Tokens.MOINS_TOKEN) {
		testeAccept(Tokens.MOINS_TOKEN,CodesErr.MOINS_ERR);
	}
}
public static void main(String [] args) 
		 throws Exception ,ErreurCompilation{
	Parser parse=new Parser("testt.txt");
	parse.getScanner().initMotsCles();
	parse.getScanner().lireCar();
	parse.getScanner().symbSuiv();
	parse.PROGRAM();
	if (parse.getScanner().getSymbCour().getToken()==Tokens.EOF_TOKEN) 
		System.out.println("Analyse syntaxique reussie");
	else
		throw new ErreurSyntaxique(CodesErr.EOF_ERR);
}
}