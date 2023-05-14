package Sistema;

import Artigos.*;
import Encomendas.*;
import Encomendas.TipoFormula;
import Exceptions.InputInvalidoException;
import Utilizadores.Comprador;
import Utilizadores.Utilizador;
import Utilizadores.Vendedor;


import java.io.IOException;
import java.util.*;

public class View {
    private Controller controller;

    public View() throws IOException, ClassNotFoundException {
        this.controller = new Controller();
    }

    public View(Controller controller){
        this.controller = controller;
    }

    public void run() throws InputInvalidoException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;


        while(!sair) {
            int opcaoMenuPrincipal = Menus.menuPrincipal();

            outer:
            switch (opcaoMenuPrincipal) {
                case 1 -> {
                    boolean menuCriacao = false;
                    while(!menuCriacao) {
                        int opcaoMenuCriacao = Menus.menuCriacao();
                        switch (opcaoMenuCriacao) {
                            case 1 -> criarComprador();
                            case 2 -> criarVendedor();
                            case 3 -> criarTransportadora();
                            case 4 -> {
                                menuCriacao = true;
                                break;
                            }
                            case 9 -> {
                                sair = true;
                                break outer;
                            }
                        }
                    }
                }
                case 2 -> {

                    boolean login = false;
                    while (!login) {
                        int opcaoMenuLogin = Menus.menuLogin();

                        loginLable:
                        switch (opcaoMenuLogin) {
                            case 1:
                                System.out.println("Login como comprador");
                                System.out.print("Introduza o email: ");
                                String email = scanner.nextLine();
                                System.out.print("Introduza a palavra-passe: ");
                                String password = scanner.nextLine();

                                int idComprador = this.controller.login(TipoConta.Comprador, email, password);
                                if (idComprador == -1){
                                    login = true;
                                    break;
                                }

                                if(this.controller.getDados().getFaturas().size() > 0){
                                    for(Map.Entry<Comprador, String> comp : this.controller.getDados().getFaturas().entrySet()){
                                        if(comp.getKey() == this.controller.getCompradorById(idComprador)){
                                            System.out.println(comp.getValue());
                                        }
                                    }
                                }
                                this.controller.getDados().getFaturas().clear();

                                boolean menuComprador = false;
                                while(!menuComprador){
                                    int opcaoMenuComprador = Menus.menuComprador();
                                    Comprador atual = controller.getCompradorById(idComprador);

                                    switch (opcaoMenuComprador){
                                        case 1 -> {
                                            alterarDadosComprador(atual);
                                        }
                                        case 2 -> {
                                            visualizacaoArtigosComprador(atual);
                                        }
                                        case 3 -> {
                                            System.out.println("Visualização das sua Encomendas");
                                            for(Encomenda enc : controller.getEncomendasDoComprador(atual)){
                                                System.out.println(enc);
                                            }
                                            System.out.print("Selecione o id duma das encomendas: ");
                                            int encomenda = scanner.nextInt();
                                            Encomenda enc = controller.getEncomendaById(encomenda);
                                            boolean menuEncomenda = false;

                                            while(!menuEncomenda) {
                                                int opcaoEncomenda = Menus.menuEncomenda();

                                                switch (opcaoEncomenda) {
                                                    case 1 -> {
                                                        this.controller.pagarEncomenda(enc);
                                                        //enc.pagarEncomenda();
                                                        //break;
                                                    }
                                                    case 2 -> {
                                                        //enc.devolucao();
                                                        this.controller.devolverEncomenda(enc);
                                                        //break;
                                                    }
                                                    case 3 -> {
                                                        System.out.println("Será devolvido o valor da encomenda: " + enc.getPrecoProdutos());
                                                        //break;
                                                    }
                                                    case 4 -> menuEncomenda = true;
                                                    case 9 -> {
                                                        sair = true;
                                                        break outer;
                                                    }
                                                }
                                            }
                                        }
                                        case 4 -> {
                                            break loginLable;
                                        }
                                        case 9 -> {
                                            sair = true;
                                            break outer;
                                        }
                                    }
                                }

                            case 2:
                                System.out.println("Login como vendedor");
                                System.out.print("Introduza o email: ");
                                String email1 = scanner.nextLine();
                                System.out.print("Introduza a palavra-passe: ");
                                String password1 = scanner.nextLine();

                                int idVendedor = this.controller.login(TipoConta.Vendedor, email1, password1);
                                if (idVendedor == -1){
                                    login = true;
                                    break;
                                }

                                boolean menuVendedor = false;
                                while(!menuVendedor){
                                    int opcaoMenuVendedor = Menus.menuVendedor();
                                    Vendedor atual = controller.getVendedorById(idVendedor);

                                    switch (opcaoMenuVendedor){
                                        case 1 -> {
                                            alterarDadosVendedor(atual);
                                        }
                                        case 2 -> {
                                            visualizacaoArtigosVendedor(atual);
                                        }
                                        case 3 -> {
                                            adicionarNovoArtigoVendaVendedor(atual);
                                        }
                                        case 4 -> {
                                            break loginLable;
                                        }
                                        case 9 -> {
                                            sair = true;
                                            break outer;
                                        }
                                    }
                                }

                            case 3:
                                System.out.println("Login como Transportadora");
                                System.out.print("Introduza o email: ");
                                String email2 = scanner.nextLine();
                                System.out.print("Introduza a palavra-passe: ");
                                String password2 = scanner.nextLine();

                                int idTransportadora = this.controller.login(TipoConta.Transportadora, email2, password2);
                                if (idTransportadora == -1) {
                                    login = true;
                                    break;
                                }

                                boolean menuTransportadora = false;
                                while(!menuTransportadora){
                                    int opcaoMenuTransportadora = Menus.menuTransportadora();
                                    Transportadora atual = this.controller.getTransportadoraById(idTransportadora);

                                    switch (opcaoMenuTransportadora){
                                        case 1 -> {
                                            alterarDadosTransportadora(atual);
                                        }
                                        case 2 -> {
                                            for(Artigos artigosAssociados : atual.getArtigosAssociados()){
                                                System.out.println(artigosAssociados);
                                            }
                                        }
                                        case 3 -> {
                                            if(atual.getTipoFormula() == TipoFormula.Default){
                                                atual.setTipoFormula(TipoFormula.Customized);
                                            }
                                            if(atual instanceof TransportadoraPremium){
                                                System.out.println("A sua mergem de lucro é esta: " + atual.getMargemLucro());
                                                System.out.println("Se pretende alterar a margem de lucro introduza agora o novo valor, caso contrário introduza o valor anterior: ");
                                                float novaMargem = scanner.nextFloat();
                                                System.out.println("A sua fórmula atual é esta: " + atual.getFormula());
                                                System.out.print("Introduza a nova fórmula (tem que ter as variaveis 'imposto', 'preco', 'margem' e 'impostoPremium'): ");
                                                String novaFormula = scanner.nextLine();

                                                if(novaMargem != atual.getMargemLucro() || !Objects.equals(novaFormula, atual.getFormula())){
                                                    this.controller.adicionaNovaFormula(atual, novaFormula, novaMargem);
                                                    //this.controller.atualizaFormula(atual, novaFormula, novaMargem);
                                                }
                                            }else{
                                                System.out.println("A sua mergem de lucro é esta: " + atual.getMargemLucro());
                                                System.out.println("Se pretende alterar a margem de lucro introduza agora o novo valor, caso contrário introduza o valor anterior: ");
                                                float novaMargem = scanner.nextFloat();
                                                System.out.println("A sua fórmula atual é esta: " + atual.getFormula());
                                                System.out.print("Introduza a nova fórmula (tem que ter as variaveis 'imposto', 'margem' e 'preco'): ");
                                                String novaFormula = scanner.nextLine();
                                                if(novaMargem != atual.getMargemLucro() || !Objects.equals(novaFormula, atual.getFormula())){
                                                    this.controller.adicionaNovaFormula(atual, novaFormula, novaMargem);
                                                    //this.controller.atualizaFormula(atual, novaFormula, novaMargem);
                                                }
                                            }
                                        }
                                        case 4 -> {
                                            break loginLable;
                                        }
                                        case 9 -> sair = true;
                                    }
                                }
                            case 4:
                                login = true;
                                break;
                            case 9:
                                sair = true;
                                break outer;
                        }
                    }
                }
                case 3 -> {
                    boolean menuVisualizacao = false;
                    while(!menuVisualizacao){
                        int opcaoMenuVisualizacao = Menus.menuVisualizacao();

                        switch (opcaoMenuVisualizacao){
                            case 1 -> {
                                for(Artigos arts : this.controller.getDados().getArtigos().values()){
                                    System.out.println(arts);
                                }
                            }
                            case 2 -> {
                                for(Utilizador vendedores : this.controller.getDados().getUtilizadores().values()){
                                    if(vendedores instanceof Vendedor){
                                        System.out.println(vendedores);
                                    }
                                }
                            }
                            case 3 -> {
                                for(Utilizador compradores : this.controller.getDados().getUtilizadores().values()){
                                    if(compradores instanceof Comprador){
                                        System.out.println(compradores);
                                    }
                                }
                            }
                            case 4 -> {
                                for(Transportadora transportadora : this.controller.getDados().getTransportadoras().values()){
                                    System.out.println(transportadora);
                                }
                            }
                            case 5 -> menuVisualizacao = true;
                            case 9 -> {
                                sair = true;
                                break outer;
                            }
                        }
                    }
                }
                case 4 ->{
                    boolean menuSaltosNoTempo = false;
                    while(!menuSaltosNoTempo){
                        int opcaoMenuTempo = Menus.menuSaltosTempo();

                        if (opcaoMenuTempo == -1) {
                            menuSaltosNoTempo = true;
                        } else {
                            this.controller.avancaTempo(opcaoMenuTempo);
                        }

                    }
                }
                case 5 -> {
                    boolean menuEstatistica = false;
                    while(!menuEstatistica){
                        int opcaoMenuEstatistica = Menus.menuEstatisticas();

                        switch (opcaoMenuEstatistica){
                            case 1 -> {
                                ArrayList<Vendedor> topVendedores = new ArrayList<>();
                                for(Utilizador u : this.controller.getDados().getUtilizadores().values()){
                                    if(u instanceof Vendedor){
                                        topVendedores.add((Vendedor) u);
                                    }
                                }
                                Collections.sort(topVendedores);
                                int lugar = 1;
                                for(Vendedor v : topVendedores){
                                    System.out.println(lugar + " --> " + v);
                                    lugar++;
                                }
                            }
                            case 2 -> {
                                ArrayList<TransportadoraPremium> topTransportadorasPremium = new ArrayList<>();
                                for(Transportadora t : this.controller.getDados().getTransportadoras().values()){
                                    if(t instanceof TransportadoraPremium){
                                        topTransportadorasPremium.add((TransportadoraPremium) t);
                                    }
                                }
                                Collections.sort(topTransportadorasPremium);
                                int lugar = 1;
                                for(TransportadoraPremium t : topTransportadorasPremium){
                                    System.out.println(lugar + " --> " + t);
                                    lugar++;
                                }
                            }
                            case 3 -> {
                                ArrayList<Transportadora> topTransportadoras = new ArrayList<>();
                                for(Transportadora t : this.controller.getDados().getTransportadoras().values()){
                                    if(!(t instanceof TransportadoraPremium)){
                                        topTransportadoras.add(t);
                                    }
                                }
                                Collections.sort(topTransportadoras);
                                int lugar = 1;
                                for(Transportadora t : topTransportadoras){
                                    System.out.println(lugar + " --> " + t);
                                    lugar++;
                                }
                            }
                            case 4 -> menuEstatistica = true;
                            case 9 -> {
                                sair = true;
                                break outer;
                            }
                        }
                    }
                }
                case 9 ->{
                    sair = true;
                    break outer;
                }
            }
        }
    }

    public void criarComprador() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o email: ");
        String email = scanner.nextLine();
        System.out.print("Introduza a sua palavra passe: ");
        String passWord = scanner.nextLine();
        System.out.print("Introduza o seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Introduza a sua morada: ");
        String morada = scanner.nextLine();
        System.out.print("Introduza o seu nif: ");
        int nif = scanner.nextInt();
        this.controller.adicionaComprador(email, nome, morada, nif, passWord);
    }

    public void criarVendedor() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o email: ");
        String email1 = scanner.nextLine();
        System.out.print("Introduza a sua palavra passe: ");
        String passWord1 = scanner.nextLine();
        System.out.print("Introduza o seu nome: ");
        String nome1 = scanner.nextLine();
        System.out.print("Introduza a sua morada: ");
        String morada1 = scanner.nextLine();
        System.out.print("Introduza o seu nif: ");
        int nif1 = scanner.nextInt();
        this.controller.adicionaVendedor(email1, nome1, morada1, nif1, passWord1);
    }

    public void criarTransportadora() throws IOException, InputInvalidoException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pretende criar uma Transportadora de que tipo?");
        System.out.println("1) Normal");
        System.out.println("2) Premium");
        System.out.print("Selecione uma destas: ");
        int opcaoTrans = scanner.nextInt();
        if (opcaoTrans != 1 && opcaoTrans != 2) throw new InputInvalidoException("Input Inválido");

        System.out.print("Introduza o email: ");
        String email2 = scanner.next();
        System.out.print("Introduza a sua palavra passe: ");
        String passWord2 = scanner.next();
        System.out.print("Introduza o  nome: ");
        String nome2 = scanner.next();
        System.out.print("Introduza o seu nif: ");
        int nif2 = scanner.nextInt();
        System.out.print("Introduza a sua margem de lucro(em flaot): ");
        float margem = scanner.nextFloat();

        if (opcaoTrans == 1) {
            System.out.println("A fórmula predifinida é: (preco*(margemLucro)*(1+imposto))");
            System.out.println("Pertende qual tipo de fórmula?");
            System.out.println("1) Fórmula Prédefinida");
            System.out.println("2) Fórmula Costumizada");
            System.out.print("Selecione uma destas: ");
            int opcaoFormula = scanner.nextInt();
            if (opcaoFormula != 1 && opcaoFormula != 2) throw new InputInvalidoException("Input Inválido");

            if (opcaoFormula == 2) {
                System.out.print("Introduza a sua formula (tem que ter as variaveis 'imposto', 'preco' e 'margem'): ");
                String formula = scanner.next();
                this.controller.adicionaTransportadora(email2, nome2, nif2, margem, TipoFormula.Customized, formula, passWord2);
            } else
                this.controller.adicionaTransportadora(email2, nome2, nif2, margem, TipoFormula.Default, "", passWord2);
        } else {
            System.out.println("A fórmula predifinida é: (preco*(margemLucro)*(1+imposto))+impostoPremium");
            System.out.println("Pertende qual tipo de fórmula?");
            System.out.println("1) Fórmula Prédefinida");
            System.out.println("2) Fórmula Costumizada");
            System.out.print("Selecione uma destas: ");
            int opcaoFormula1 = scanner.nextInt();
            if (opcaoFormula1 != 1 && opcaoFormula1 != 2) throw new InputInvalidoException("Input Inválido");

            if (opcaoFormula1 == 2) {
                System.out.print("Introduza a sua formula (tem que ter as variaveis 'imposto', 'preco', 'margem' e 'impostoPremium'): ");
                String formula1 = scanner.nextLine();
                this.controller.adicionaTransportadoraPremium(email2, nome2, nif2, margem, TipoFormula.Customized, formula1, formula1, passWord2);
            } else
                this.controller.adicionaTransportadoraPremium(email2, nome2, nif2, margem, TipoFormula.Default, "", "", passWord2);
        }
    }

    public void alterarDadosComprador(Comprador atual) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o novo email: ");
        String email3 = scanner.nextLine();
        System.out.print("Introduza o novo nome: ");
        String nome3 = scanner.nextLine();
        System.out.print("Introduza a nova morada: ");
        String morada3 = scanner.nextLine();
        System.out.print("Introduza o novo nif: ");
        int nif3 = scanner.nextInt();
        this.controller.atualizaComprador(atual,email3, nome3, morada3, nif3);
    }

    public void visualizacaoArtigosComprador(Comprador atual) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Visualização de Artigos");
        for(Artigos arts : controller.getDados().getArtigos().values()){
            System.out.println(arts);
        }
        while(true) {
            System.out.println("Indique o código de barras do artigo que prentende acicionar á sua encomenda(-1 para sair): ");
            int artigo = scanner.nextInt();
            if (artigo == -1) break;

            this.controller.adicionaArtigoAEncomenda(controller.getEncomendaAtiva(atual), controller.getArtigoByCode(String.valueOf(artigo)));
        }
    }

    public void alterarDadosVendedor(Vendedor atual) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o novo email: ");
        String email4 = scanner.nextLine();
        System.out.print("Introduza o novo nome: ");
        String nome4 = scanner.nextLine();
        System.out.print("Introduza a nova morada: ");
        String morada4 = scanner.nextLine();
        System.out.print("Introduza o novo nif: ");
        int nif4 = scanner.nextInt();
        this.controller.atualizaVendedor(atual, email4, nome4, morada4, nif4);
    }

    public void visualizacaoArtigosVendedor(Vendedor atual){
        System.out.println("Visualização de artigos do vendedor");
        for(Artigos art : atual.getEmVenda()){
            System.out.println(art);
        }
        System.out.print("Intoduza qualquer tecla para voltar ao menu anterior: ");
    }

    public void adicionarNovoArtigoVendaVendedor(Vendedor atual) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adicionar um novo artigo");
        System.out.print("Descrição do artigo: ");
        String descricao = scanner.nextLine();
        System.out.print("O seu artigo é usado? (0-não, 1-sim) : ");
        int usado = scanner.nextInt();
        System.out.print("Preço base do artigo: ");
        float preco = scanner.nextFloat();
        System.out.print("Preço do desconto: ");
        float desconto = scanner.nextFloat();
        System.out.print("Qual o estado de usado? (0-mau,1-medio,2-bom): ");
        int estado = scanner.nextInt();
        System.out.print("Quantos utilziadores teve este produto? (0 caso seja novo) :");
        int utilizadores = scanner.nextInt();
        System.out.print("Qual é o stock do produto : ");
        int stock = scanner.nextInt();

        System.out.print("Que tipo de artigo é (0-sapatilha, 1-t-shirt, 2-mala)? : ");
        int tipoDeArtigo = scanner.nextInt();

        if(tipoDeArtigo == 0){
            System.out.print("Indique o tamanho da sapatilha: ");
            int tamanho = scanner.nextInt();
            System.out.print("A sapatilha tem atilhos? (0-não, 1-sim): ");
            int atilhos = scanner.nextInt();
            System.out.print("Indique o ano de coleção desta sapatilha? : ");
            int anoSpatilha = scanner.nextInt();
            System.out.print("Indique a cor da sapatilha? : ");
            String cor = scanner.next();

            System.out.print("O artigo é conciderado Premium? (0-não, 1-sim): ");
            int premium = scanner.nextInt();
            if(premium == 1){
                for(Transportadora transportadora : this.controller.getDados().getTransportadoras().values()){
                    if(transportadora instanceof TransportadoraPremium){
                        System.out.println(transportadora);
                    }
                }
                System.out.print("Selecione o id duma destas transportadoras: ");
                int idTransportadoraPremium = scanner.nextInt();
                System.out.print("Indique o autor deste artigo premium: ");
                String autor = scanner.next();
                this.controller.adicionaNovoSapatilha(atual, usado, descricao, preco, desconto, estado, utilizadores, premium, idTransportadoraPremium, stock, autor, tamanho, atilhos, cor, anoSpatilha);
            }
            else{
                for(Transportadora transportadora : this.controller.getDados().getTransportadoras().values()){
                    if(!(transportadora instanceof TransportadoraPremium)){
                        System.out.println(transportadora);
                    }
                }
                System.out.print("Selecione o id duma destas transportadoras: ");
                int idTransportadora = scanner.nextInt();
                this.controller.adicionaNovoSapatilha(atual, usado, descricao, preco, desconto, estado, utilizadores, premium, idTransportadora, stock, "", tamanho, atilhos, cor, anoSpatilha);
            }

        } else if (tipoDeArtigo == 1) {
            for(Transportadora transportadora : this.controller.getDados().getTransportadoras().values()){
                if(!(transportadora instanceof TransportadoraPremium)){
                    System.out.println(transportadora);
                }
            }
            System.out.print("Selecione o id duma destas transportadoras: ");
            int idTransportadora = scanner.nextInt();

            System.out.print("Indique o tamanho da Tshirt (0-Small, 1-Medium, 2-Large, 3-Extra Large) : ");
            int tamanho = scanner.nextInt();
            System.out.print("Indique o padrão da Tshirt (0-Liso, 1-Riscas, 2-Palmeiras) : ");
            int padrao = scanner.nextInt();
            this.controller.adicionaNovoTshirt(atual, usado, descricao, preco, desconto, estado, utilizadores, idTransportadora, stock, tamanho, padrao);

        }else if (tipoDeArtigo == 2){
            System.out.print("Indique a dimensão da mala (0-Pequena, 1-Média, 2-Grande) : ");
            int dimensao = scanner.nextInt();
            System.out.print("Indique a textura da mala (0-Pele, 1-Tecido, 2-Sintético) : ");
            int textura = scanner.nextInt();
            System.out.print("Indique o ano de coleção desta mala : ");
            int anoMala = scanner.nextInt();

            System.out.print("O artigo é conciderado Premium? (0-não, 1-sim): ");
            int premium = scanner.nextInt();
            if(premium == 1){
                for(Transportadora transportadora : this.controller.getDados().getTransportadoras().values()){
                    if(transportadora instanceof TransportadoraPremium){
                        System.out.println(transportadora);
                    }
                }
                System.out.print("Selecione o id duma destas transportadoras: ");
                int idTransportadoraPremium = scanner.nextInt();
                System.out.print("Indique o autor deste artigo premium: ");
                String autor = scanner.nextLine();
                this.controller.adicionaNovoMala(atual, usado, descricao, preco, desconto, estado, utilizadores, premium, idTransportadoraPremium, stock, autor, dimensao, textura, anoMala);
            }
            else{
                for(Transportadora transportadora : this.controller.getDados().getTransportadoras().values()){
                    if(!(transportadora instanceof TransportadoraPremium)){
                        System.out.println(transportadora);
                    }
                }
                System.out.print("Selecione o id duma destas transportadoras: ");
                int idTransportadora = scanner.nextInt();
                this.controller.adicionaNovoMala(atual, usado, descricao, preco, desconto, estado, utilizadores, premium, idTransportadora, stock, "", dimensao, textura, anoMala);
            }
        }
    }

    public void alterarDadosTransportadora(Transportadora atual) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o novo email: ");
        String email5 = scanner.nextLine();
        System.out.print("Introduza o novo nome: ");
        String nome5 = scanner.nextLine();
        System.out.print("Introduza o novo nif: ");
        int nif5 = scanner.nextInt();
        System.out.print("Intoduza a nova margem de lucro: ");
        float margem2 = scanner.nextFloat();
        this.controller.atualizaTransportadora(atual, email5, nome5, nif5, margem2);
    }
}
