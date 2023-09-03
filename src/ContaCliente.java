import java.util.Random;

public class ContaCliente {
    public ContaCliente(String nome, String agencia, float saldo) {
        this.Nome = nome;
        this.Saldo = saldo;
        this.NumeroConta = GerarConta();
        this.Agencia = SelecionarAgencia(agencia);

    }

    private String Nome;
    private int NumeroConta;
    private String Agencia;
    private float Saldo;

    public void AdicionarSaldo(float valorDeposito) {
        this.Saldo += valorDeposito;
        System.out.println("Transação realizada com sucesso. Seu novo saldo é de " + this.Saldo +".");
    }

    public void DecrementarSaldo(float valorSaque){
        if (this.Saldo - valorSaque >= 0)
        {
            this.Saldo -= valorSaque;
            System.out.println("Transação realizada com sucesso. Você sacou "+ valorSaque + ". Seu novo saldo é de " + this.Saldo +".");
        } else{
            System.out.println("Saldo insuficiente");
        }
    }

    public String SelecionarAgencia(String estado) {
        switch (estado) {
            case "SP":
                return "067-8";

            case "RJ":
                return "153-9";

            case "BA":
                return "963-1";

            case "MG":
                return "654-3";

            default:
                return "147-5";
        }
    }

    public int GerarConta() {
        Random random = new Random();
        int valorgerado = random.nextInt(9000) + 1000;
        return valorgerado;
    }

    public void VisualizarInformacoes(){
        System.out.println("Olá "+ this.Nome +" , obrigado por criar uma conta em nosso banco, sua agência é "+ this.Agencia +", conta "+ this.NumeroConta +" e seu saldo "+ this.Saldo +" já está disponível para saque.");
    }

    public int ConsultarNumeroConta(){
        return this.NumeroConta;
    }

    public String ConsultarAgencia(){
        return this.Agencia;
    }

    public String ConsultarNome(){
        return this.Nome;
    }

    public float ConsultarSaldo(){
        return Saldo;
    }
}