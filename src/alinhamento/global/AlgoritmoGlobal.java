package alinhamento.global;

public class AlgoritmoGlobal {

	// declaracao das variaveis
	private int gap, match, mismatch; // custos
	private int m, n; // tamanho das sequencias
	private int[][] matriz; // matriz
	private int[] seq; // vetor que armazena o caminho do alinhamento otimo

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

	// metodo que pritna a matriz e o alinhamento das sequencias no console
	public void imprimeMatriz() {
		int i, j;
		for (i = 0; i <= n; i++) {
			for (j = 0; j <= m; j++) {
				if (matriz[i][j] >= 0) {
					System.out.print(" +" + matriz[i][j]);
				} else {
					System.out.print(" " + matriz[i][j]);
				}
			}
			System.out.println();
		}
	}

	// metodo que percorre a matriz da ultima posicao ate a primeira posicao atraves do alinhamento otimo
	public void otimo() {
		int i = n;
		int j = m;
		int c = 0;
		seq = new int[n + m + 2];
		while (i != 0 || j != 0) {
			if (i != 0 && j != 0) {
				if (matriz[i][j] == matriz[i - 1][j - 1] + match || matriz[i][j] == matriz[i - 1][j - 1] + mismatch) {
					seq[c] = 1;
					c++;
					i--;
					j--;
				} else {
					if (matriz[i][j] == matriz[i - 1][j] + gap) {
						seq[c] = 2;
						c++;
						i--;
					} else {
						seq[c] = 3;
						c++;
						j--;
					}
				}
			} else {
				if (i != 0) {
					seq[c] = 2;
					c++;
					i--;
				} else {
					seq[c] = 3;
					c++;
					j--;
				}
			}
		}
		
		int[] arm = new int[c];
		int k = c-1;
		for (i = 0; i < c; i++) {
			arm[k] = seq[i];
			k--;
		}
		seq = arm;
	}

	// metodo que imprime as sequencias alinhadas atraves do alinhamento otimo
	public void imprimeSequencias(char[] A, char[] B) {
		int i;
		int k = 0;
		
		System.out.println();
		for (i = 0; i < seq.length; i++) {
			if (seq[i] == 1) {
				System.out.print(A[k]);
				k++;
			} else {
				if (seq[i] == 2) {
					System.out.print(A[k]);
					k++;
				} else {
					System.out.print("-");
				}
			}
		}
		k = 0;
		System.out.println();
		for (i = 0; i < seq.length; i++) {
			if (seq[i] == 1) {
				System.out.print(B[k]);
				k++;
			} else {
				if (seq[i] == 2) {
					System.out.print("-");
				} else {
					System.out.print(B[k]);
					k++;
				}
			}
		}
	}

}
