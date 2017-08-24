package alinhamento.semiglobal;


public class Main {

	public static void main(String[] args) {
		AlgoritmoSemiGlobal ag = new AlgoritmoSemiGlobal(-2, +1, -1);
		
		char[] A = {'a','t','c'};
		char[] B = {'t','t'};
		
		ag.algoritmo(A, B);
		ag.imprimeMatriz();
	}

}
