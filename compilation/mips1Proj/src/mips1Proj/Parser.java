package mips1Proj;

import java.io.IOException;

public class Parser {
	private Scanner scanner;

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	public Parser(String nomFich) 
			throws IOException, ErreurCompilation {
			scanner=new Scanner(nomFich);
		}
	public void testAccept(Tokens t, CodesErr c) throws IOException, ErreurCompilation{
			 
	if (scanner.getSymbCour().getToken()==t)
		scanner.symbSuiv();
	else
		throw new ErreurSyntaxique(c);
}
}
