import Artigos.*;

import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //Artigos a1 = new Artigos()
        ArrayList<Sapatilhas> lista = new ArrayList<>();
        for(int i=0; i<100; i++){
            Sapatilhas s2 = new Sapatilhas(false, "Sapatilha nova", 20.00f, 5.00f, 3, 0, i, true, "Black", 1800+i);
            for(Sapatilhas s : lista){
                if(s2.getBarCode() == s.getBarCode()){
                    System.out.println("BAR CODES IGUAIS!!");
                    return;
                }
            }
            lista.add(s2);
        }
        //System.out.println(s1);
        System.out.println(lista);

        Sapatilhas s1 = new Sapatilhas(false, "Sapatilha nova", 20.00f, 5.00f, 3, 0, 45, true, "Black", 2022);
        Sapatilhas s3 = new Sapatilhas(false, "Sapatilha nova", 20.00f, 5.00f, 3, 0, 45, true, "Black", 2022);
        System.out.println(s1.equals(s3));
        Sapatilhas s4 = s1.clone();
        //s4.setPrice(0.00f); Apenas para verificar se o super.equals() funciona na definição da equals(Object o)
        System.out.println(s1.equals(s4));
        Sapatilhas s5 = new Sapatilhas(true, "Sapatilha para desconto", 30.00f, 4.99f, 2, 1, 40, true, "Yellow", 2021);
        System.out.println(s5);
        s5.atualizaPrecoDesconto(s5, 2024);
        System.out.println("NOVO PREÇO DO DESCONTO: " + s5); // ALGUNS BUGS AQUI NO ATUALIZA DESCONTO

        TShirst t1 = new TShirst(true, "Tshirt fraca velha", 5.30f, 1.99f, 1, 3, "L", "liso");
        System.out.println(t1);
        TShirst t2 = new TShirst(t1);
        System.out.println(t2);

        Malas m1 = new Malas(true, "Mala alterna", 39.99f, 9.99f, 2, 1, 2, "pele", 2020);
        System.out.println(m1);
        Malas m2 = m1.clone();
        System.out.println(m1.equals(m2));

        SapatilhasPremium sp1 = new SapatilhasPremium(false, "Yezzies do kanye crazy", 200.00f, 0.00f, 3, 0, 50, false, "White", 2023, "Kanye Krazy");
        System.out.println(sp1);
        sp1.atualizarPrecoSapatilhasPremium(sp1, 2024);
        System.out.println(sp1);
    }
}