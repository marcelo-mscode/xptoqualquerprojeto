package br.com.testes.main;

public class ContaCaracteres {
	
	
	public String concatenaWrap(String texto){
		int c = texto.length();
		StringBuilder concat = new StringBuilder(texto);
		int num = 40;
		for (int i = 0; i < texto.length(); i++) {
			while (num <= c ) {
				concat.insert(texto.length() - c + num, "\\n");
				num = num + 50;
			}
		}
		String t2 = concat.toString();
		return t2;
	}
	
	
	public static void main(String[] args) {
		
		String texto = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		
		int c = texto.length();
		StringBuilder concat = new StringBuilder(texto);
		int num = 40;
		
		for (int i = 0; i < texto.length(); i++) {

			while (num <= c ) {
				concat.insert(texto.length() - c + num, "\\n");
				num = num + 50;
			}
		}
		
		
		
	//	String t2 = concaten  ;
		
	//	System.out.println(t2);
		
		
	}
	
	
	
	
}
