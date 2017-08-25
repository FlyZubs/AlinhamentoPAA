package alinhamento.global;

public class Main {

	public static void main(String[] args) {
		
		// exemplo:
		// os custos das operacoes, como parametro do construtor, na ordem: gap, match, mismatch
		AlgoritmoGlobal ag = new AlgoritmoGlobal(-2, +1, -1);
		
		// sequencias de caracteres
		//char[] A = {'a','a','a','c'};
		//char[] B = {'a','g','c'};
		
		char[] A = {'a','t','t','a','t','c'};
		char[] B = {'a','g','g','g'};
		
		ag.algoritmo(A, B);
		ag.imprimeMatriz();
		ag.otimo();
		ag.imprimeSequencias(A, B);
	}

}
