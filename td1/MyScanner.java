import java.io.*;

/**
 * Classe de remplacement pour Scanner
 * - est plus rapide
 * - permet de soumettre un programme `a un juge qui ne connait que JDK1.1
 * 
 */
class Locale {
    final static int US=0;
}

class MyScanner {
    // le BufferedInputStream a l'avantage par rapport au InputStream
    // de faire moins d'appels systeme lors de la lecture
    private BufferedInputStream in;

    // c contient le prochain caractere que read() va rendre, ou -1 si EOF
    int     c;

    boolean enDebutDeLigne;

    public MyScanner(InputStream stream) {
	in = new BufferedInputStream(stream);
	try {
	    enDebutDeLigne = true;
	    c  = (char)in.read();
	} catch (IOException e) {
	    c  = -1;
	}
    }

    public boolean hasNext() {
	if (!enDebutDeLigne) 
	    throw new Error("hasNext ne fonctionne seulement "+
			    "apres un appel a nextLine");
	return c != -1;
    }

    public String next() {
	StringBuffer sb = new StringBuffer();
	enDebutDeLigne = false;
	try {
	    // ignorer les blancs en debut
	    while (c <= ' ') {
		c = in.read();
	    } 
	    // consommer tout ce qui n'est pas blanc
	    while (c > ' ') {
		sb.append((char)c);
		c = in.read();
	    }
	} catch (IOException e) {
	    c = -1;
	    return "";
	}
	return sb.toString();
    }

    public String nextLine() {
	StringBuffer sb = new StringBuffer();
	enDebutDeLigne = true;
	try {
	    // consommer toute la ligne
	    while (c != '\n') {
		sb.append((char)c);
		c = in.read();
	    }
	    // consommer aussi le retour chariot
	    c = in.read();
	} catch (IOException e) {
	    c = -1;
	    return "";
	}
	return sb.toString();	
    }

    public int nextInt() {
	String s = next();
	try {
	    return Integer.parseInt(s);
	} catch (NumberFormatException e) {
	    return 0; //throw new Error("Malformed number " + s);
	}
    }
    
    public double nextDouble() {
	return new Double(next());
    }

    public void useLocale(int l) {} // on peut ignorer cet appel
}
