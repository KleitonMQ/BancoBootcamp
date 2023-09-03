import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class ContaTerminal {
    static List<ContaCliente> contasAbertas = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        System.out.println("Bem vindo");

        Iniciar();

        System.out.println("Volte sempre.");
    }

    public static void Iniciar() {
        int opcao = 0;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Informe o número de acordo ao que deseja.");
            System.out.println("1 - Acessar conta");
            System.out.println("2 - Abrir Conta");
            System.out.println("3 - Sair");
            System.out.println("------------------------------------");

            while (true) {
                try {
                    opcao = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida. Por favor, seleciona uma das opções listadas.");
                    scanner.nextLine();
                }
            }
            switch (opcao) {
                case 1:
                    AcessarConta();
                    break;

                case 2:
                    AbrirConta();
                    break;
                case 3:

                    break;
                case 4:
                    System.out.println(contasAbertas.size());
                    break;
                default:
                    System.out.println("Opção inválida. Por favor selecione uma das opções listadas.");
                    break;
            }

        } while (opcao != 3);
    }

    public static void AcessarConta() {
        ContaCliente contaAtiva = null;
        Scanner scanner = new Scanner(System.in);
        int numeroConta;
        System.out.println("Informe o número de sua conta.");
        while (true) {
            try {
                numeroConta = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite apenas números inteiros.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        System.out.println("Informe a sua agência.");
        String agencia = scanner.nextLine();

        for (ContaCliente contaCliente : contasAbertas) {

            if (contaCliente.ConsultarNumeroConta() == numeroConta && contaCliente.ConsultarAgencia().equals(agencia)) {
                contaAtiva = contaCliente;
            }
        }
        if (contaAtiva != null) {
            int opcao;
            System.out.println("Seja bem vindo " + contaAtiva.ConsultarNome() + ".");
            do {
                System.out.println("Qual transação deseja realizar?");
                System.out.println("1 - Depositar");
                System.out.println("2 - Sacar");
                System.out.println("3 - Consultar Saldo");
                System.out.println("4 - Sair");
                System.out.println("------------------------------------");

                while (true) {
                    try {
                        opcao = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Opção inválida. Por favor, seleciona uma das opções listadas.");
                        System.out.println("Qual transação deseja realizar?");
                        System.out.println("1 - Depositar");
                        System.out.println("2 - Sacar");
                        System.out.println("3 - Consultar Saldo");
                        System.out.println("4 - Sair");
                        System.out.println("------------------------------------");
                        scanner.nextLine();
                    }
                }

                switch (opcao) {
                    case 1:
                        Depositar(contaAtiva);
                        break;

                    case 2:
                        Sacar(contaAtiva);
                        break;
                    case 3:
                        System.out.println("Seu saldo atual é de: " + contaAtiva.ConsultarSaldo());
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor selecione uma das opções listadas.");
                        break;
                }

            } while (opcao != 4);
        } else {
            System.out.println("Cliente não encontrado. Revise seus dados e tente novamente.");
        }
        Iniciar();
    }

    public static void AbrirConta() {
        ContaCliente conta = new ContaCliente(InserirNome(), SelecionarAgencia(), SaldoInicial());
        contasAbertas.add(conta);

        contasAbertas.get(contasAbertas.size() - 1).VisualizarInformacoes();

    }

    public static String InserirNome() {
        Scanner scanner = new Scanner(System.in);
        String nome = "";
        int opcao = 0;

        System.out.println("Muito bem, para inicar seu cadastro precisamos saber seu nome completo.");

        do {
            nome = scanner.nextLine();

            if (nome == "") {
                System.out.println("Por favor, digite um nome válido");
            } else {
                System.out.println("Seu nome: " + nome + ", está correto?");
                System.out.println("1 - Sim  | 2 - Não  |  3 - Cancelar");
                while (true) {
                    try {
                        opcao = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Opção inválida. Por favor, seleciona uma das opções listadas.");
                        System.out.println("1 - Sim  | 2 - Não  |  3 - Cancelar");
                        scanner.nextLine();
                    }
                }
                scanner.nextLine();
            }

            switch (opcao) {
                case 1:

                    return nome;

                case 2:
                    System.out.println("Digite seu nome completo.");
                    break;

                default:
                    break;
            }
        } while (opcao != 3);
        Iniciar();
        return null;
    }

    public static String SelecionarAgencia() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("Gostaria de abrir seu conta em qual das nossas agencias?");
        System.out.println("1 - SP | 2 - MG | 3 - BA | 4 - RJ | 5 - Cancelar");

        do {
            while (true) {
                try {
                    opcao = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida. Por favor, seleciona uma das opções listadas.");
                    scanner.nextLine();
                }
            }
            switch (opcao) {
                case 1:

                    return "SP";
                case 2:

                    return "MG";
                case 3:

                    return "BA";
                case 4:

                    return "RJ";
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    System.out.println("1 - SP | 2 - MG | 3 - BA | 4 - RJ");
                    break;
            }
        } while (opcao != 5);
        Iniciar();
        return null;
    }

    public static float SaldoInicial() {
        float deposito = -1;
        String entrada = "";
        Scanner scanner = new Scanner(System.in);
        do {

            System.out.println("Qual valor de depósito inicial?");

            entrada = scanner.nextLine();

            try {
                deposito = Float.parseFloat(entrada);
                return deposito;
            } catch (NumberFormatException e) {
                System.out.println("Valor no formato inválido, digite apenas números.");
            }
        } while (deposito < 0);
        return deposito;
    }

    public static void Depositar(ContaCliente cliente) {
        Scanner scanner = new Scanner(System.in);

        float deposito = -1;
        String entrada = "";
        do {

            System.out.println("Qual valor do depósito?");

            entrada = scanner.nextLine();

            try {
                deposito = Float.parseFloat(entrada);
                cliente.AdicionarSaldo(deposito);
            } catch (NumberFormatException e) {
                System.out.println("Valor no formato inválido, digite apenas números.");
            }
        } while (deposito < 0);
    }

    public static void Sacar(ContaCliente cliente) {
        Scanner scanner = new Scanner(System.in);

        float deposito = -1;
        String entrada = "";
        do {

            System.out.println("Qual valor de retirada?");

            entrada = scanner.nextLine();

            try {
                deposito = Float.parseFloat(entrada);
                cliente.DecrementarSaldo(deposito);
            } catch (NumberFormatException e) {
                System.out.println("Valor no formato inválido, digite apenas números.");
            }
        } while (deposito < 0);
    }
}
