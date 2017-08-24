package alinhamento.global;

public class Main {

	public static void main(String[] args) {
		
		// exemplo:
		// os custos das operacoes, como parametro do construtor, na ordem: gap, match, mismatch
		AlgoritmoGlobal ag = new AlgoritmoGlobal(-2, +1, -1);
		
		// sequencias de caracteres
		char[] A = {'a','t','c'};
		char[] B = {'t','t'};
		
		ag.algoritmo(A, B);
		ag.imprimeMatriz();
	}

}
