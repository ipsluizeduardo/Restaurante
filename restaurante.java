package restaurante.restaurante;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Restaurante {

    public static void main(String[] args) throws IOException {
        boolean encerrar = false;
        boolean encComanda = false;
        InputStream is;
        OutputStream os;
        OutputStreamWriter osw;
        BufferedWriter bw;

        int opcao = 0;
        int item = 0;
        int count = 1;
        String[] cardapio = new String[20];
        while (count < 20) {
            cardapio[count] = "";
            count++;
        }
        count = 1;
        Scanner lerTec = new Scanner(System.in);
        String nome = "C:\\Restaurante\\cardapio.txt";
        String vendas = "C:\\Restaurante\\vendas.txt";
        String nomeCliente = "";
        int codigovenda = 0;
        double valorTotal = 0.00;
        while (!encerrar) {
            encComanda = false;
            
            System.out.println("Sistema de Vendas Restaurante Eduardo");
            System.out.println("Opções");
            System.out.println("(1) Iniciar Comanda");
            System.out.println("(0) Encerrar Sistema");
            System.out.println("Selecione uma opção: ");
            opcao = lerTec.nextInt();

            switch (opcao) {
                case 1:
                    while (!encComanda) {
                        System.out.println("Escolha um Item ");

                        try {
                            FileReader arq = new FileReader(nome);
                            BufferedReader LerArq = new BufferedReader(arq);

                            String linha = LerArq.readLine();
                            cardapio[count] = linha;
                            count++;
                            while (linha != null) {
                                System.out.printf("%s\n", linha);
                                linha = LerArq.readLine();
                                cardapio[count] = linha;
                                count++;
                            }
                            arq.close();
                        } catch (IOException e) {

                            System.err.printf("erro ao ler o arquivo%s.\n", e.getMessage());
                        }
                        System.out.println("0 | Encerrar Comanda");
                        System.out.println();
                        item = lerTec.nextInt();

                        if (item == 0) {
                            encComanda = true;
                            System.out.println("Codigo da venda:");
                            codigovenda = lerTec.nextInt();

                            System.out.println("Digite o nome do cliente:");
                            Scanner sc = new Scanner(System.in);
                            nomeCliente = sc.nextLine();
                            try {
                                os = new FileOutputStream(vendas, true);
                                osw = new OutputStreamWriter(os);
                                bw = new BufferedWriter(osw);
                                bw.newLine();
                                bw.write(codigovenda + " | " + nomeCliente + " | " + valorTotal);

                                bw.close();
                                os.close();
                                osw.close();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Restaurante.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            valorTotal = 0.00;    
                        } else {
                            count = 1;
                            while (count < 20) {
                                if (item == count) {
                                    System.out.println("Item Seleciondo: " + cardapio[count]);
                                    String[] param = cardapio[count].split("\\|");
                                    for (int x = 0; x < param.length; x++) {
                                            if(x == 2){
                                               valorTotal = valorTotal + Double.parseDouble(param[x]);
                                               System.out.println("Total: " + valorTotal);
                                            }
                                    }
                                    
                                    break;
                                }
                                count++;
                            }
                        }
                        count = 1;
                    }
                    break;
                default:
                    encerrar = true;
            }

        }

    }
}
