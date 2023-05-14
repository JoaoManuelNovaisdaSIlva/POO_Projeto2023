package Artigos;

import Encomendas.Dimenssao;
import Encomendas.TipoFormula;
import Encomendas.TransportadoraPremium;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SapatilhasPremiumTest {

    @Test
    void atualizarPrecoSapatilhasPremium() {
        ArrayList<Artigos> lista2 = new ArrayList<>();
        TransportadoraPremium transP1 = new TransportadoraPremium("CTT Premium", 777777777, "ctt@premium.ctt.pt", lista2, 1.02f, TipoFormula.Default, "", "");
        SapatilhasPremium sp1 = new SapatilhasPremium(false, "Yezzys", 200.00f, 30.00f, Estado.Bom, 0, transP1, 2, 48, true, "Black", 2023, "YE");
        sp1.atualizarPrecoSapatilhasPremium(2025);
        assertEquals(240.00f, sp1.getPrice(), "O novo valor da sapatilha está errado! Era esperado 240.00f mas recebi: " + sp1.getPrice());
    }
}