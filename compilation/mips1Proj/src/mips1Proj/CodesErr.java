package mips1Proj;

public enum CodesErr {
	CAR_INC_ERR("Symbole inconnu"),
	FIC_VID_ERR("Erreur d'ouverture de fichier"),
	PROGRAM_ERR("Mot clé program attendu"),
	ID_ERR("Identificateur attendu"),
	PVIR_ERR("Symbole ; attendu");
 String message;
	CodesErr(String msg) {
		this.message=msg;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
