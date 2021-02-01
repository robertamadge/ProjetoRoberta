package br.com.util;

import org.primefaces.PrimeFaces;

public class JSFUtil {
    public static void atualizarComponente(String componente) {
        PrimeFaces.current().ajax().update(componente);
    }

    public static void abrirDialog(String dlg) {
        PrimeFaces.current().executeScript("PF('" + dlg + "').show();");
    }

    public static void fecharDialog(String dlg) {
        PrimeFaces.current().executeScript("PF('" + dlg + "').hide();");
    }
}
