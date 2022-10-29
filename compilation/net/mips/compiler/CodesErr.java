package net.mips.compiler;

public enum CodesErr {

	CAR_INC_ERR ("SYMBOLE INCONNU"),
	FIC_VID_ERR ("ERREUR D'OUVERTURE DE FICHIER");

	
	private String message;
	
	CodesErr(String message) {
		
		}
	 
	public String getmessage() 
	{
		return this.message;
	}
	
	public void setmessage(String msg) 
	{
		this.message = msg;
	}
	
}
