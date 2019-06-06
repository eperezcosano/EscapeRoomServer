package edu.upc.dsa.to.User;


import edu.upc.dsa.to.ObjTO;

import java.util.List;

public class UserInventary {

    private List<ObjTO> lista;

    public UserInventary(List<ObjTO> lista) {
        this.lista = lista;
    }

    public UserInventary() {
    }

    public List<ObjTO> getLista() {
        return lista;
    }

    public void setLista(List<ObjTO> lista) {
        this.lista = lista;
    }
}
