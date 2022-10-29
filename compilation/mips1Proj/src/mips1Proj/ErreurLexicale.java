package mips1Proj;

public class ErreurLexicale extends ErreurCompilation {
	public ErreurLexicale(CodesErr code) {
		super(code.getMessage());
	}
}
