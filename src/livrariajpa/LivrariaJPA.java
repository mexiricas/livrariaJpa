/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrariajpa;

import controle.EditoraCtrl;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;

/**
 *
 * @author Denis
 */
public class LivrariaJPA {

    /**
     * @param args the command line arguments
     */
    private static EditoraCtrl ls = new EditoraCtrl();
    private static Scanner leia = new Scanner(System.in);

    public static void main(String[] args) {

        // desabilita mensagens de LOG informativas
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        int opcao = 0;
        do {
            opcao = menu();
            switch (opcao) {

                case 1:
                    ls.listarEditoraHibernate();
                    break;
                case 2:
                    ls.inserir();
                    break;
                case 3:
                    ls.atualizar();
                    break;
                case 4:
                    ls.remova();
                    break;
                case 5:
                    ls.listarLivrosHibernate();
                    break;
                case 6:
                    ls.inserirLivro();
                    break;
                case 7:
                    ls.atualizarLivros();
                    break;
                case 8:
                    System.out.print("Eliminar");
                    break;
                case 9:
                    System.out.println("OBRIGADOO!!");
                    break;
                    
            }

        } while (opcao != 9);
    }

    private static int menu() {
        System.out.println();
        System.out.println("+============================+");
        System.out.println("|        O P Ç Õ E S         |");
        System.out.println("+============================+");
        System.out.println("+============menu============+");
        System.out.println("|  EDITORAS                  |");
        System.out.println("| 1. Listar todas            |");
        System.out.println("| 2. Cadastrar nova          |");
        System.out.println("| 3. Alterar dados           |");
        System.out.println("| 4. Eliminar                |");
        System.out.println("| Livros                     |");
        System.out.println("| 5. Listar todos            |");
        System.out.println("| 6. Cadastrar novo          |");
        System.out.println("| 7. Alterar dados           |");
        System.out.println("| 8. Eliminar                |");
        System.out.println("+============================+");
        System.out.println("| 9. Fim                     |");
        System.out.println("+============================+");

        int opcao;
        do {
            try {
                System.out.print("Digite a sua opção (1 a 9): ");
                opcao = leia.nextInt();
            } catch (InputMismatchException e) {
                opcao = 0;
                System.out.print("Digite a sua opção (1 a 9): ");

            }

        } while (opcao < 1 || opcao > 9);
        return opcao;
    }

}
