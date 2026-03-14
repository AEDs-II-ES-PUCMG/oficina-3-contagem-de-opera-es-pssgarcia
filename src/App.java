import java.util.Random;

/** 
 * MIT License
 *
 * Copyright(c) 2024-255 João Caram <caram@pucminas.br>
 *                       Eveline Alonso Veloso
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {
    static final int[] tamanhosTesteGrande =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] tamanhosTestePequeno = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random(42);
    static long operacoes;
    static double nanoToMilli = 1.0/1_000_000;

    /**
     * Código de teste 1. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa....
     */
    static int codigo1(int[] vetor) {
        int resposta = 0;
        for (int i = 0; i < vetor.length; i += 2) {
            operacoes++;
            resposta += vetor[i]%2; // Operação relevante: Soma do resultado do módulo por 2 dos elementos de índice par do vetor
        }
        return resposta;
    }

    /**
     * Código de teste 2. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa....
     */
    static int codigo2(int[] vetor) {
        int contador = 0;
        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                operacoes++;
                contador++; // Operação relevante: Incremento do contador
            }

        }
        return contador;
    }

    /**
     * Código de teste 3. Este método...
     * @param vetor Vetor com dados para teste.
     */
    static void codigo3(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[j] < vetor[menor])
                    operacoes++;
                    menor = j;
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp; 
        }
        // Operação relevante: Comparações
    }

    /**
     * Código de teste 4 (recursivo). Este método...
     * @param n Ponto inicial do algoritmo
     * @return Um inteiro que significa...
     */
    static int codigo4(int n) {
        operacoes++;
        if (n <= 2)
            return 1;
        else
            return codigo4(n - 1) + codigo4(n - 2);
        // Operação relevante: Calcular o fibonnaci recursivamente
    }

    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho){
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;
        
    }
    public static void main(String[] args) {
        for (int i = 0; i < tamanhosTesteGrande.length; i++) {
            int[] vetor = gerarVetor(tamanhosTesteGrande[i]);
            operacoes = 0;
            long inicio = System.nanoTime();
            int resposta = codigo1(vetor);
            long fim = System.nanoTime();
            System.out.printf("Teste 1 - Tamanho: %d, Resposta: %d, Operações: %d, Tempo: %.2f ms\n", vetor.length, resposta, operacoes, (fim - inicio)*nanoToMilli);

            operacoes = 0;
            inicio = System.nanoTime();
            int resposta2 = codigo2(vetor);
            long fim2 = System.nanoTime();
            System.out.printf("Teste 2 - Tamanho: %d, Resposta: %d, Operações: %d, Tempo: %.2f ms\n", vetor.length, resposta2, operacoes, (fim2 - inicio)*nanoToMilli);
        }

        for (int i = 0; i < tamanhosTesteMedio.length; i++) {
            int[] vetor = gerarVetor(tamanhosTesteMedio[i]);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo3(vetor);
            long fim3 = System.nanoTime();
            System.out.printf("Teste 3 - Tamanho: %d, Operações: %d, Tempo: %.2f ms\n", vetor.length, operacoes, (fim3 - inicio)*nanoToMilli);
        }

        for (int i = 0; i < tamanhosTestePequeno.length; i++) {
            int n = tamanhosTestePequeno[i];
            operacoes = 0;
            long inicio = System.nanoTime();
            int resposta4 = codigo4(n);
            long fim4 = System.nanoTime();
            System.out.printf("Teste 4 - n: %d, Resposta: %d, Operações: %d, Tempo: %.2f ms\n", n, resposta4, operacoes, (fim4 - inicio)*nanoToMilli);
        }
    }
}
