package alinhamento.semiglobal;

public class AlgoritmoSemiGlobal {
	
	// declaracao das variaveis
	private int gap, match, mismatch; // custos
	private int m, n; // tamanho das sequencias
	private int[][] matriz; // matriz
	private int maximo; // pontuacao maxima de alinhamento otimo

	// construtor que recebe os custos das operacoes
	public AlgoritmoSemiGlobal(int gap, int match, int mismatch) {
		this.gap = gap;
		this.match = match;
		this.mismatch = mismatch;
	}

	// algoritmo de alinhamento semi global
	public void algoritmo(char[] A, char[] B) {
		n = A.length;
		m = B.length;
		matriz = new int[n + 1][m + 1];
		int i, j, custoExtra;

		// inicializacao da matriz com a primeira linha e coluna recebendo 0
		for (i = 0; i <= n; i++) {
			matriz[i][0] = 0;
		}
		for (j = 1; j <= m; j++) {
			matriz[0][j] = 0;
		}

		// construcao do restante da matriz
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= m; j++) {
				if (A[i - 1] == B[j - 1]) {
					custoExtra = match;
				} else {
					custoExtra = mismatch;
				}
				matriz[i][j] = Math.max(matriz[i - 1][j - 1] + custoExtra,
						Math.max(matriz[i - 1][j] + gap, matriz[i][j - 1] + gap));
			}
		}
		
		// busca pela pontuacao maxima de alinhamento otimo na ultima linha e coluna
		maximo = Integer.MIN_VALUE;
		
		for (i = 0; i <= n; i++) {
			if (matriz[i][m] > maximo) {
				maximo = matriz[i][m];
			}
		}
		for (j = 0; j <= m-1; j++) {
			if (matriz[n][j] > maximo) {
				maximo = matriz[n][j];
			}
		}
	}

	// metodo que printa a matriz e a pontuacao maxima de alinhamento otimo no console
	public void imprimeMatriz() {
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (matriz[i][j] >= 0) {
					System.out.print(" +" + matriz[i][j]);
				} else {
					System.out.print(" " + matriz[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println("\n" + "ALINHAMENTO OTIMO = " + maximo);
	}

}
