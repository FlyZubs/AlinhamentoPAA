package alinhamento.semiglobal;

public class AlgoritmoSemiGlobal {

	// declaracao das variaveis
	private int gap, match, mismatch; // custos
	private int m, n; // tamanho das sequencias
	private int[][] matriz; // matriz
	private int maximo; // pontuacao maxima de alinhamento otimo
	private int[] seq; // vetor que armazena o caminho do alinhamento otimo

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
		for (j = 0; j <= m - 1; j++) {
			if (matriz[n][j] > maximo) {
				maximo = matriz[n][j];
			}
		}
	}

	// metodo que printa a matriz e a pontuacao maxima de alinhamento otimo no
	// console
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

	// metodo que percorre a matriz da ultima posicao ate a primeira posicao atraves
	// do alinhamento otimo
	public void otimo() {
		int i = n;
		int j = m;
		int c = 0;
		seq = new int[n + m + 2];
		while (matriz[i][j] != maximo) {
			if (i == 0) {
				i = n;
				c = 0;
				break;
			} else {
				seq[c] = 2;
				c++;
				i--;
			}
		}
		if (matriz[i][j] != maximo) {
			while (matriz[i][j] != maximo) {
				if (j == 0) {
					j = m;
					c = 0;
					break;
				} else {
					seq[c] = 3;
					c++;
					j--;
				}
			}
		}
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
		int k = c - 1;
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
