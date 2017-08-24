package alinhamento.semiglobal;


public class Main {

	public static void main(String[] args) {
		
		// exemplo:
		// os custos das operacoes, como parametro do construtor, na ordem: gap, match, mismatch
		AlgoritmoSemiGlobal ag = new AlgoritmoSemiGlobal(-2, +1, -1);
		
		// sequencias de caracteres
		char[] A = {'t','a','c','a','a','t'};
		char[] B = {'g','a','t','t','a','c','a'};
		
		ag.algoritmo(A, B);
		ag.imprimeMatriz();
	}

}
