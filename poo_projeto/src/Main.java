import Artigos.*;
import Encomendas.*;
import Sistema.Dados;
import Sistema.Parser;
import Utilizadores.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Artigos> lista1 = new ArrayList<>();
        ArrayList<Artigos> lista2 = new ArrayList<>();

        Transportadora trans1 = new Transportadora("CTT", 999999999, "ctt@ctt.pt", lista1, TipoFormula.Default, "");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Deve introduzir a formula incluindo uma palavra 'preco' e outra 'imposto' (variaveis fixas)");
        //String line = scanner.nextLine();
        String line = "preco+(preco*imposto)+2";

        Transportadora trans2 = new Transportadora("FEDEX", 888888888, "fedex@fedex.com", lista1, TipoFormula.Customized, line);

        TransportadoraPremium transP1 = new TransportadoraPremium("CTT Premium", 777777777, "ctt@premium.ctt.pt", lista2, TipoFormula.Default, "", "");

        System.out.println("Deve introduzir a formula incluindo uma palavra 'preco', outra 'imposto' e outra 'impostoPremium' (variaveis fixas)");
        //String line2 = scanner.nextLine();
        String line2 = "preco+(preco*imposto)+impostoPremium";
        TransportadoraPremium transP2 = new TransportadoraPremium("FEDEX Premium", 666666666, "fedex@premium.fedex.com", lista2, TipoFormula.Customized, "", line2);

        Sapatilhas s1 = new Sapatilhas(true, "Sapatilha isada", 10.00f, 3.00f, Estado.Medio, 2, trans1, 45, true, "white", 2017);
        TShirst t1 = new TShirst(true, "Tshirt usada", 15.00f, 5.00f, Estado.Mau, 4, trans2, TamanhoTShirt.L, PadraoTShirt.Palmeiras);
        Malas m1 = new Malas(false, "Mala nova", 50.00f, 10.00f, Estado.Bom, 0, trans2, Dimenssao.Medio, TexturaMala.Pele, 2018);
        SapatilhasPremium sp1 = new SapatilhasPremium(false, "Yezzys", 200.00f, 30.00f, Estado.Bom, 0, transP1, 48, true, "Black", 2023, "YE");
        MalasPremium mp1 = new MalasPremium(false, "Mala luis Viton", 400.00f, 20.00f, Estado.Bom, 0, Dimenssao.Grande, TexturaMala.Pele , 2023, transP2, "Pierre");

        lista2.add(sp1);
        lista2.add(mp1);
        lista1.add(s1);
        lista1.add(t1);
        lista1.add(m1);

        ArrayList<Artigos> enc = new ArrayList<>();
        enc.add(sp1);
        enc.add(s1);
        enc.add(m1);

        Comprador comp1 = new Comprador();

        Encomenda e1 = new Encomenda(comp1, enc);
        System.out.println("----------ENCOMENDA ORIGINAL------------");
        System.out.println(e1);

        e1.adicionaArtigoAEncomenda(s1);
        System.out.println("----------ENCOMENDA ADICIONADA-----------");
        System.out.println(e1);
        e1.removeArtigoAEncomenda(s1);
        System.out.println("---------------ENCOMENDA RETIRADA------------");
        System.out.println(e1);

        System.out.println("-------------PAGAR ENCOMENDA-----------");
        //e1.setEstado(EstadoEncomenda.Expedida);
        e1.pagarEncomenda();
        System.out.println("Estado: " + e1.getEstado());
        //System.out.println("Comprador: " + e1.getComprador());

        e1.devolucao();

        HashMap<String,Transportadora> transportadoras = new HashMap<>();
        HashMap<String,Utilizador> utilizadores = new HashMap<>();
        HashMap<String, Artigos> artigos = new HashMap<>();
        HashMap<String, Encomenda> encomendas = new HashMap<>();
        int Tcounter=1;
        int TPcounter=1;
        int Ccounter=1;
        transportadoras.put("Tranportadora-"+Tcounter, trans1);
        Tcounter++;
        transportadoras.put("TransportadoraPremium-"+ TPcounter, transP1);
        TPcounter++;
        utilizadores.put("Comprador-"+Ccounter, comp1);
        Ccounter++;
        Comprador comp2 = new Comprador();
        utilizadores.put("Comprador-"+Ccounter, comp2);
        artigos.put("Sapatilha-1", s1);

        Dados d = new Dados(transportadoras, utilizadores, artigos, encomendas);
        Parser.escreveFicheiro("objetos.ser", d);

        Dados d2 = null;
        d2 = Parser.lerFicheiro("objetos.ser");

    }
}