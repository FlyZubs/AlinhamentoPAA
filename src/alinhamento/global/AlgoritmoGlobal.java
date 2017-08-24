package alinhamento.global;

public class AlgoritmoGlobal {

	private int gap, match, mismatch;
	private int m, n;
	private int[][] matriz;

	public AlgoritmoGlobal(int gap, int match, int mismatch) {
		this.gap = gap;
		this.match = match;
		this.mismatch = mismatch;
	}

	public void algoritmo(char[] A, char[] B) {
		n = A.length;
		m = B.length;
		matriz = new int[n + 1][m + 1];
		matriz[0][0] = 0;
		int i, j, custoExtra;

		for (i = 1; i <= n; i++) {
			matriz[i][0] = matriz[i - 1][0] + gap;
		}
		for (j = 1; j <= m; j++) {
			matriz[0][j] = matriz[0][j - 1] + gap;
		}

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
	}

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
