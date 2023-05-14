package Artigos;

import Encomendas.Dimenssao;
import Encomendas.TipoFormula;
import Encomendas.Transportadora;
import Encomendas.TransportadoraPremium;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MalasPremiumTest {

    @Test
    void testAtualizaPrecoMalasPremium() {
        ArrayList<Artigos> lista2 = new ArrayList<>();
        TransportadoraPremium transP1 = new TransportadoraPremium("CTT Premium", 777777777, "ctt@premium.ctt.pt", lista2, 1.02f, TipoFormula.Default, "", "");
        MalasPremium mp1 = new MalasPremium(false, "Mala luis Viton", 400.00f, 20.00f, Estado.Bom, 0, Dimenssao.Grande, TexturaMala.Pele , 2020, transP1, 2, "Pierre");
        mp1.atualizaPrecoMalasPremium(2023);
        assertEquals(640.00f, mp1.getPrice(), "O novo valor da mala está errado! Era esperado 640.00f mas recebi: " + mp1.getPrice());
    }
}