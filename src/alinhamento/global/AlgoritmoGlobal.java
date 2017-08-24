package alinhamento.global;

public class AlgoritmoGlobal {
	
	// declaracao das variaveis
	private int gap, match, mismatch; // custos
	private int m, n; // tamanho das sequencias
	private int[][] matriz; // matriz
	
	// construtor que recebe os custos das operacoes
	public AlgoritmoGlobal(int gap, int match, int mismatch) {
		this.gap = gap;
		this.match = match;
		this.mismatch = mismatch;
	}
	
	// algoritmo de alinhamento global
	public void algoritmo(char[] A, char[] B) {
		n = A.length;
		m = B.length;
		matriz = new int[n + 1][m + 1];
		matriz[0][0] = 0;
		int i, j, custoExtra;
		
		// inicializacao da matriz com a primeira linha e coluna recebendo o gap
		for (i = 1; i <= n; i++) {
			matriz[i][0] = matriz[i - 1][0] + gap;
		}
		for (j = 1; j <= m; j++) {
			matriz[0][j] = matriz[0][j - 1] + gap;
		}
		
		// construcao do restante da matriz
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= m; j++) {
				// comparacao das sequencias para saber se a operacao eh match ou mismatch
				if (A[i - 1] == B[j - 1]) {
					custoExtra = match;
				} else {
					custoExtra = mismatch;
				}
				matriz[i][j] = Math.max(matriz[i - 1][j - 1] + custoExtra,
						Math.max(matriz[i - 1][j] + gap, matriz[i][j - 1] + gap));
			}
		}
	}
	
	// metodo que pritna a matriz no console
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
	}

}
