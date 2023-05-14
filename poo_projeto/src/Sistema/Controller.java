package Sistema;

import Artigos.*;
import Encomendas.*;
import Utilizadores.*;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class Controller {
    private Dados dados;
    private LoginParser login;

    public Controller() throws IOException, ClassNotFoundException {
        if (Parser.lerFicheiro("objetos.ser") == null) {
            System.out.println("O ficheiro de objetos pode estar corrumpido ou não tem nada dentro (se a classe Dados for mudada, isto resulta no in.readObject() dar null)");
            this.dados = new Dados();
        }else this.dados = Parser.lerFicheiro("objetos.ser");
        this.login = new LoginParser();
    }

    public static void run() throws IOException, ClassNotFoundException {
        Dados dados = Parser.lerFicheiro("objetos.ser");
        LoginParser login = new LoginParser();
    }

    public Dados getDados() {
        return dados;
    }

    public void setDados(Dados dados) {
        this.dados = dados;
    }

    public LoginParser getLogin() {
        return login;
    }

    public void setLogin(LoginParser login) {
        this.login = login;
    }

    public void adicionaComprador(String email, String nome, String morada, int nif, String password) throws IOException {
        for(String emails : this.login.getContasCompradores().keySet()){
            if(emails.equals(email)){
                System.out.println("Já existe um comprador com esse email!");
                return;
            }
        }

        Comprador novoComprador = new Comprador(email, nome, morada, nif);
        this.dados.getUtilizadores().put("Comprador-"+novoComprador.getId(), novoComprador);
        this.login.adicionaConta(TipoConta.Comprador, novoComprador.getId(), email, password);
        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

    public void adicionaVendedor(String email, String nome, String morada, int nif, String password) throws IOException {
        for(String emails : this.login.getContasVendedores().keySet()){
            if(emails.equals(email)){
                System.out.println("Já existe um vendedor com esse email!");
                return;
            }
        }

        Vendedor novoVendedor = new Vendedor(email, nome, morada, nif);
        this.dados.getUtilizadores().put("Vendedor-"+novoVendedor.getId(), novoVendedor);
        this.login.adicionaConta(TipoConta.Vendedor, novoVendedor.getId(), email, password);
        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

    public void adicionaTransportadora(String email, String nome, int nif, float margem, TipoFormula tipo, String formula, String password) throws IOException {
        for(String emails : this.login.getContasTransportadoras().keySet()){
            if(emails.equals(email)){
                System.out.println("Já existe uma transportadora com esse email!");
                return;
            }
        }

        Transportadora novaTransportadora = new Transportadora(nome, nif, email, margem, tipo, formula);
        this.dados.getTransportadoras().put("Transportadora-"+novaTransportadora.getId(), novaTransportadora);
        this.login.adicionaConta(TipoConta.Transportadora, novaTransportadora.getId(), email, password);
        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

    public void adicionaTransportadoraPremium(String email, String nome, int nif, float margem, TipoFormula tipo, String formula, String formulaPremium, String password) throws IOException {
        for(String emails : this.login.getContasTransportadoras().keySet()){
            if(emails.equals(email)){
                System.out.println("Já existe uma transportadora premium com esse email!");
                return;
            }
        }

        TransportadoraPremium novaTransportadoraPremium = new TransportadoraPremium(nome, nif, email,margem, tipo, formula, formulaPremium);
        this.dados.getTransportadoras().put("Transportadora Premium-"+novaTransportadoraPremium.getId(), novaTransportadoraPremium);
        this.login.adicionaConta(TipoConta.Transportadora, novaTransportadoraPremium.getId(), email, password);
        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

    public void adicionaNovoSapatilha(Vendedor v,int usado, String descricao, float preco, float desconto, int estado, int numUtilizadores, int premium, int idTransportadora, int stock, String autor, int tamanho, int atilhos, String cor, int anoColecao) throws IOException {
        boolean usadoB;
        usadoB = usado == 1;

        Estado estadoS = null;
        if(estado == 0) estadoS = Estado.Mau;
        else if(estado == 1) estadoS = Estado.Medio;
        else if(estado == 2) estadoS = Estado.Bom;

        boolean atilhosB;
        atilhosB = atilhos != 0;

        if(premium == 1){
            SapatilhasPremium novaSapatilhaPremium = new SapatilhasPremium(usadoB, descricao, preco, desconto, estadoS, numUtilizadores, getTransportadoraPremiumById(idTransportadora), stock, tamanho, atilhosB, cor, anoColecao, autor);
            this.dados.getArtigos().put("SapatilhaPremium-"+novaSapatilhaPremium.getBarCode(), novaSapatilhaPremium);
            v.adicionaArtigoEmVenda(novaSapatilhaPremium);
            getTransportadoraPremiumById(idTransportadora).adicionaArtigoAssociado(novaSapatilhaPremium);
            Parser.escreveFicheiro("objetos.ser", this.dados);
        }else{
            Sapatilhas novaSapatilha = new Sapatilhas(usadoB, descricao, preco, desconto, estadoS, numUtilizadores, getTransportadoraById(idTransportadora), stock, tamanho, atilhosB, cor, anoColecao);
            this.dados.getArtigos().put("Sapatilha-"+novaSapatilha.getBarCode(), novaSapatilha);
            v.adicionaArtigoEmVenda(novaSapatilha);
            getTransportadoraById(idTransportadora).adicionaArtigoAssociado(novaSapatilha);
            Parser.escreveFicheiro("objetos.ser", this.dados);
        }
    }

    public void adicionaNovoTshirt(Vendedor v, int usado, String descricao, float preco, float desconto, int estado, int numUtilizadores, int idTransportadora, int stock, int tamanho, int padrao) throws IOException {
        boolean usadoB;
        usadoB = usado == 1;

        Estado estadoT = null;
        if(estado == 0) estadoT = Estado.Mau;
        else if(estado == 1) estadoT = Estado.Medio;
        else if(estado == 2) estadoT = Estado.Bom;

        TamanhoTShirt tamanhoT = null;
        if(tamanho == 0) tamanhoT = TamanhoTShirt.S;
        else if(tamanho == 1) tamanhoT = TamanhoTShirt.M;
        else if(tamanho == 2) tamanhoT = TamanhoTShirt.L;
        else if(tamanho == 3) tamanhoT = TamanhoTShirt.XL;

        PadraoTShirt padraoT = null;
        if(padrao == 0) padraoT = PadraoTShirt.Liso;
        else if(padrao == 1) padraoT = PadraoTShirt.Riscas;
        else if(padrao == 2) padraoT = PadraoTShirt.Palmeiras;

        TShirst novaTshirt = new TShirst(usadoB, descricao, preco, desconto, estadoT, numUtilizadores, getTransportadoraById(idTransportadora), stock, tamanhoT, padraoT);
        this.dados.getArtigos().put("Tshirt-"+novaTshirt.getBarCode(), novaTshirt);
        v.adicionaArtigoEmVenda(novaTshirt);
        getTransportadoraById(idTransportadora).adicionaArtigoAssociado(novaTshirt);
        Parser.escreveFicheiro("objetos.ser", this.dados);

    }

    public void adicionaNovoMala(Vendedor v, int usado, String descricao, float preco, float desconto, int estado, int numUtilizadores, int premium, int idTransportadora, int stock, String autor, int dimensao, int textura, int anoColecao) throws IOException {
        boolean usadoB;
        usadoB = usado == 1;

        Estado estadoS = null;
        if(estado == 0) estadoS = Estado.Mau;
        else if(estado == 1) estadoS = Estado.Medio;
        else if(estado == 2) estadoS = Estado.Bom;

        Dimenssao dimensaoM = null;
        if(dimensao == 0) dimensaoM = Dimenssao.Pequeno;
        else if(dimensao == 1) dimensaoM = Dimenssao.Medio;
        else if(dimensao == 2) dimensaoM = Dimenssao.Grande;

        TexturaMala texturaM = null;
        if(textura == 0) texturaM = TexturaMala.Pele;
        else if(textura == 1) texturaM = TexturaMala.Tecido;
        else if(textura == 2) texturaM = TexturaMala.Sintetico;

        if(premium == 1){
            MalasPremium novaMalaremium = new MalasPremium(usadoB, descricao, preco, desconto, estadoS, numUtilizadores, dimensaoM, texturaM, anoColecao, getTransportadoraPremiumById(idTransportadora), stock, autor);
            this.dados.getArtigos().put("MalaPremium-"+novaMalaremium.getBarCode(), novaMalaremium);
            v.adicionaArtigoEmVenda(novaMalaremium);
            getTransportadoraPremiumById(idTransportadora).adicionaArtigoAssociado(novaMalaremium);
            Parser.escreveFicheiro("objetos.ser", this.dados);
        }else{
            Malas novaMala = new Malas(usadoB, descricao, preco, desconto, estadoS, numUtilizadores, getTransportadoraById(idTransportadora), stock, dimensaoM, texturaM, anoColecao);
            this.dados.getArtigos().put("Mala"+novaMala.getBarCode(), novaMala);
            v.adicionaArtigoEmVenda(novaMala);
            getTransportadoraById(idTransportadora).adicionaArtigoAssociado(novaMala);
            Parser.escreveFicheiro("objetos.ser", this.dados);
        }
    }

    public int login(TipoConta tipo, String email, String password){ // Devolve false se id retornado for -1
        return this.login.validaLogin(tipo, email, password);
    }

    public Comprador getCompradorById(int id){
        for(Utilizador utilizador : dados.getUtilizadores().values()){
            if(utilizador instanceof Comprador){
                if(utilizador.getId() == id){
                    return (Comprador) utilizador;
                }
            }
        }
        return null;
    }

    public Vendedor getVendedorById(int id){
        for(Utilizador utilizador : dados.getUtilizadores().values()){
            if(utilizador instanceof Vendedor){
                if(utilizador.getId() == id){
                    return (Vendedor) utilizador;
                }
            }
        }
        return null;
    }

    public List<Encomenda> getEncomendasDoComprador(Comprador c){
        List<Encomenda> encomendas = new ArrayList<>();
        for(Encomenda enc : dados.getEncomendas().values()){
            if(enc.getComprador() == c){
                encomendas.add(enc);
            }
        }
        return encomendas;
    }

    public Artigos getArtigoByCode(String barcode){
        for(Artigos art : dados.getArtigos().values()){
            if(Objects.equals(art.getBarCode(), barcode)){
                return art;
            }
        }
        return null;
    }

    public void adicionaArtigoAEncomenda(Encomenda e, Artigos a) throws IOException {
        if(!e.getArtigos().contains(a)) a.setPrice(a.getPrice() - a.getDicountPrice());
        e.adicionaArtigoAEncomenda(a);
        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

    public Encomenda getEncomendaAtiva(Comprador c){
        if(dados.getEncomendas().size() == 0 ){
            ArrayList artigos = new ArrayList<Artigos>();
            Encomenda novaEncomenda = new Encomenda(c, artigos);
            this.dados.getEncomendas().put("Encomenda de"+c.getId(), novaEncomenda);
            return novaEncomenda;
        }else{
            for(Encomenda enc : dados.getEncomendas().values()){
                if(enc.getComprador() == c && enc.getEstado() == EstadoEncomenda.Pendente) return enc;
            }
            ArrayList artigos = new ArrayList<>();
            return new Encomenda(c, artigos);
        }
    }

    public void atualizaComprador(Comprador c, String email, String nome, String morada, int nif) throws IOException {
        this.login.alteraEmail(TipoConta.Comprador, c.getEmail(), email, c.getId());
        c.setEmail(email);
        c.setNome(nome);
        c.setMorada(morada);
        c.setNif(nif);
        Parser.escreveFicheiro("objetos.ser", this.dados);
        //this.dados.atualizarComprador(c, email, nome, morada, nif);
    }

    public void atualizaVendedor(Vendedor v, String email, String nome, String morada, int nif) throws IOException {
        this.login.alteraEmail(TipoConta.Vendedor, v.getEmail(), email, v.getId());
        v.setEmail(email);
        v.setNome(nome);
        v.setMorada(morada);
        v.setNif(nif);
        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

    public void atualizaTransportadora(Transportadora t, String email, String nome, int nif, float margem) throws IOException{
        this.login.alteraEmail(TipoConta.Transportadora, t.getEmail(), email, t.getId());
        t.setEmail(email);
        t.setNome(nome);
        t.setNif(nif);
        t.setMargemLucro(margem);
        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

    public void atualizaFormula(Transportadora t, String novaFormula) throws IOException {
        t.setFormula(novaFormula);
        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

    public void atualizaMargem(Transportadora t, float novaMargem) throws IOException {
        t.setMargemLucro(novaMargem);
        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

    public Encomenda getEncomendaById(int id){
        for(Encomenda enc : dados.getEncomendas().values()){
            if(enc.getId() == id){
                return enc;
            }
        }
        return null;
    }

    public Transportadora getTransportadoraById(int id){
        for(Transportadora transportadora : dados.getTransportadoras().values()){
            if(transportadora.getId() == id){
                return transportadora;
            }
        }
        return null;
    }

    public TransportadoraPremium getTransportadoraPremiumById(int id){
        for(Transportadora transportadora : dados.getTransportadoras().values()){
            if(transportadora instanceof TransportadoraPremium){
                TransportadoraPremium copia = (TransportadoraPremium) transportadora;
                if(copia.getId() == id){
                    return copia;
                }
            }
        }
        return null;
    }

    public void pagarEncomenda(Encomenda enc) throws IOException {
        enc.pagarEncomenda(this.dados.getDateTimeDoSistema());

        for(Artigos arts : enc.getArtigos()){
            for(Utilizador vendedor : this.dados.getUtilizadores().values()){
                if(vendedor instanceof Vendedor){
                    if(((Vendedor) vendedor).getEmVenda().contains(arts)){
                        ((Vendedor) vendedor).adicionaArtigoVendido(arts);
                        ((Vendedor) vendedor).setValorFaturado(((Vendedor) vendedor).getValorFaturado() + (arts.getPrice()-arts.getDicountPrice()));
                    }
                }
            }
            for(Transportadora trans : this.dados.getTransportadoras().values()){
                if(trans instanceof TransportadoraPremium) {
                    if (arts.getTransportadoraAssociada() == trans) {
                        trans.setValorFaturado(trans.getValorFaturado() + (((TransportadoraPremium) trans).calculaFormulaPremium(arts.getPrice()) - (arts.getPrice()-arts.getDicountPrice())));
                    }
                }else{
                    if(arts.getTransportadoraAssociada() == trans){
                        trans.setValorFaturado(trans.getValorFaturado() + (trans.calculaFormula(arts.getPrice()) - (arts.getPrice()-arts.getDicountPrice())));
                    }
                }
            }
            this.dados.getVendidos().add(arts);
        }
        this.dados.getEncomendasFinalizadas().add(enc);

        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

    public void devolverEncomenda(Encomenda enc) throws IOException {
        enc.devolucao(this.dados.getDateTimeDoSistema());

        for(Artigos arts : enc.getArtigos()){
            for(Utilizador vendedor : this.dados.getUtilizadores().values()){
                if(vendedor instanceof Vendedor){
                    if(((Vendedor) vendedor).getEmVenda().contains(arts)){
                        ((Vendedor) vendedor).removerArtigoVendido(arts);
                        ((Vendedor) vendedor).setValorFaturado(((Vendedor) vendedor).getValorFaturado() - (arts.getPrice()-arts.getDicountPrice()));
                    }
                }
            }
            this.dados.getVendidos().add(arts);
        }

        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

    public void adicionaNovaFormula(Transportadora t, String novaFormula, float novaMargem){
        this.dados.getNovasFormulas().put(t.getId(), novaFormula);
        this.dados.getNovasMargens().put(t.getId(), novaMargem);
    }

    public void avancaTempo(int dias) throws IOException {
        // DAR A TRANSPORTADORA DINHEIRO
        // ATUALIZAR OS PREÇOS DOS ARTIGOS (Sapatilhas.atualizaDesconto) NAO ESQUECER DE FAZER WRITE

        this.dados.avancaTempo(dias);

        for(Encomenda encs : this.dados.getEncomendasFinalizadas()){ // Expedir encomendas pagas
            encs.setEstado(EstadoEncomenda.Expedida);
            encs.setUltimaAlteracao(this.dados.getDateTimeDoSistema());
        }
        this.dados.getEncomendasFinalizadas().clear();
        for(Artigos arts : this.dados.getVendidos()){ // Diminuir ao stock
            arts.setStock(arts.getStock()-1);
        }
        this.dados.getVendidos().clear();

        for(Map.Entry<Integer, Float> entrada : this.dados.getNovasMargens().entrySet()){ // Atualizar margens
            int id = entrada.getKey();
            float margem = entrada.getValue();
            atualizaMargem(getTransportadoraById(id), margem);
        }
        this.dados.getNovasMargens().clear();
        for(Map.Entry<Integer, String> entrada : this.dados.getNovasFormulas().entrySet()){ // Atualizar formulas
            int id = entrada.getKey();
            String formula = entrada.getValue();
            atualizaFormula(getTransportadoraById(id), formula);
        }
        this.dados.getNovasFormulas().clear();

        int anoAtual = this.dados.getDateTimeDoSistema().getYear();
        for(Artigos arts : this.dados.getArtigos().values()){ // Atualizar preços de descontos
            if(arts instanceof SapatilhasPremium){
                if(((SapatilhasPremium) arts).getCollectionYear() > anoAtual) ((SapatilhasPremium) arts).atualizarPrecoSapatilhasPremium(anoAtual);
            } else if (arts instanceof Sapatilhas) {
                if(((Sapatilhas) arts).getCollectionYear() > anoAtual) ((Sapatilhas) arts).atualizaPrecoDesconto(anoAtual);
            } else if (arts instanceof MalasPremium) {
                if(((MalasPremium) arts).getCollectionYear() > anoAtual) ((MalasPremium) arts).atualizaPrecoMalasPremium(anoAtual);
            } else if (arts instanceof Malas) {
                if(((Malas) arts).getCollectionYear() > anoAtual) ((Malas) arts).atualizaPrecoDesconto(anoAtual);
            }
        }
        // USAR AQUI ULTIMAALTERACAO DA ENCOMENDA PARA VER QUANDO PASSA A ENREGUE

        for(Encomenda encs : this.dados.getEncomendas().values()){ // Entregar a Encomenda
            Duration duration = Duration.between(encs.getUltimaAlteracao(), this.dados.getDateTimeDoSistema());
            long horasPassadas = duration.toHours();
            if(encs.getEstado() == EstadoEncomenda.Expedida && horasPassadas > 24){
                encs.setEstado(EstadoEncomenda.Entregue);
                encs.setDataEntrega(this.dados.getDateTimeDoSistema());
                String fatura = "Foi entregue a encomenda: " + encs.getId() + " ; " + "Artigos: " + encs.getArtigos() + " ; " + "Preço dos produtos: " + encs.getPrecoProdutos() + " ; " + "Preço final: " + encs.getCustosExpedicao();
                this.dados.getFaturas().put(encs.getComprador(), fatura);
            }
        }

        Parser.escreveFicheiro("objetos.ser", this.dados);
    }

}
